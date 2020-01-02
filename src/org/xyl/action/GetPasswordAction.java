package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.User;
import org.xyl.iservice.IUserService;
import org.xyl.util.MD5Util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("getPasswordAction")
@Scope("prototype")
public class GetPasswordAction extends ActionSupport{

	@Resource private IUserService userService;
	private User user;
	
	public void validateGetPassword(){
		
		System.out.println("�����֤-----------------");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		
		if("first".equals(action)){
			String userName=request.getParameter("userName");
			if(userName==null||userName.trim().length()==0){
				this.addFieldError("userNameError","�û�����ʽ����");
			}else{
				user=userService.loadByUserName(userName);
				if(user==null){
					this.addFieldError("userNameError", "�����ڸ��û�");
				}
			}
			ActionContext.getContext().put("urlAction","password/firstStep.jsp");
		}else if("second".equals(action)){
			String answer=request.getParameter("answer");
			String userId=request.getParameter("userId");
			
			if(userId!=null&&userId.trim().length()>0){
				user=userService.load(Long.parseLong(userId));
			}
			
			if(answer==null||answer.trim().length()==0){
				this.addFieldError("answerError","����ĸ�ʽ����");
			}else{
				if(!user.getAnswer().equals(answer)){
					this.addFieldError("answerError","����Ĵ𰸴���");
				}
			}
			
			ActionContext.getContext().put("urlAction","password/secondStep.jsp");
		}
		
	}
	
	public String getPassword(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		
		if("first".equals(action)){
			String userName=request.getParameter("userName");
			user=userService.loadByUserName(userName);
			ActionContext.getContext().put("urlAction","password/secondStep.jsp");
			System.out.println("��һ���ɹ�--------");
		}else if("second".equals(action)){
			String userId=request.getParameter("userId");
			user=userService.load(Long.parseLong(userId));
			ActionContext.getContext().put("urlAction","password/threeStep.jsp");
		}
		
		//ActionContext.getContext().put("user",user);
		return Action.SUCCESS;
	}
	
	
	public void validateChangePassword(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String password=request.getParameter("password");
		String confirmPwd=request.getParameter("confirmPwd");
		if(password!=null&&!(password.trim().length()>0&&password.trim().length()<=20)){
			this.addFieldError("passwordError", "�����ʽ�����ұ�����1~20֮�䣡");
		}else if(confirmPwd!=null&&!(confirmPwd.trim().length()>0&&confirmPwd.trim().length()<=20)){
			this.addFieldError("passwordError", "ȷ�������ʽ�����ұ�����1~20֮�䣡");
		}else if(!password.equals(confirmPwd)){
			this.addFieldError("passwordError", "�����������벻һ�£�");
		}
		System.out.println("enter password validate-----------------------");
		ActionContext.getContext().put("urlAction","password/threeStep.jsp");

	}
	public String changePassword(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String password=request.getParameter("password");
		String userId=request.getParameter("user.userId");
		user=userService.load(Long.parseLong(userId));
		System.out.println(userId+"------------");
		user.setPassword(MD5Util.getEncryptedPwd(password));
		userService.updateUser(user);
		System.out.println("�ɹ��޸�����----------------");
		ActionContext.getContext().put("urlAction","password/success.jsp");

		return Action.SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

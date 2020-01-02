package org.xyl.action;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Admin;
import org.xyl.bean.User;
import org.xyl.iservice.IAdminService;
import org.xyl.iservice.IUserService;
import org.xyl.util.ActionUtil;
import org.xyl.util.MD5Util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport{
	
	@Resource private IAdminService adminService;
	@Resource private IUserService userService;
	
	private Admin admin;
	private User user;
		
	private String userName;
	private String password;
	private String verifycode;
	
	
	public void validateLoginUser(){
		
		//HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println(userName);
		User user=userService.loadByUserName(userName);
		System.out.println(user+"==");
		if(user==null){ //throw new MessageException("�û������ڣ�");
			this.addFieldError("loginError","�û������ڣ�");
			System.out.println("-----------");
		}else if(!MD5Util.validPassword(password, user.getPassword())/*user.getPassword().equals(MD5Util.getEncryptedPwd(password))*/){ 
			//throw new MessageException("�û����벻��ȷ��");
			this.addFieldError("loginError","�û����벻��ȷ��");
		}else if(!user.isUserEnable()){
			//throw new MessageException("���û��Ѿ���ͣ�ã��������Ա��ϵ");
			this.addFieldError("loginError","�˺����������...");
		}
		//System.out.println(MD5Util.getEncryptedPwd(password)+"----");
		//System.out.println(MD5Util.validPassword(password, user.getPassword()));
		//System.out.println(MD5Util.getEncryptedPwd(password).equals(user.getPassword()));
		System.out.println(userName+"--"+password);
		ActionContext.getContext().put("urlAction", "page/front/left.jsp");
		//ActionContext.getContext().put("urlAction", "loginError.html");
	}
	
	public String loginUser(){
		user=userService.loadByUserName(userName);
		//User user=userService.login(userName, password);
		ActionContext.getContext().getSession().put("loginUser",user);
		System.out.println(userName+"-------"+password);
		
		ActionContext.getContext().put("urlAction","page/front/left.jsp");
		return Action.SUCCESS;
	}
	
	public void validateLoginAdmin(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String code=(String)session.getAttribute("rand");
		System.out.println("�����֤��--"+verifycode+"===�ṩ����֤��--"+code);
		
		Admin admin=adminService.loadByUserName(userName);
		if(admin==null){
			this.addFieldError("loginError","�û������ڣ�");
		}else if(!MD5Util.validPassword(password, admin.getPassword())/*admin.getPassword().equals(MD5Util.getEncryptedPwd(password))*/){ 
			this.addFieldError("loginError","�û����벻��ȷ��");
		}else{
			if(verifycode==null||verifycode.equals("")||verifycode.trim().length()==0){
				this.addFieldError("loginError","��֤�벻��Ϊ��!");
			}else if(!verifycode.equalsIgnoreCase(code)){
				this.addFieldError("loginError","��֤���������!");
			}
		}
		
		ActionContext.getContext().put("urlAction", "page/login.jsp");
	}
	
	//����Ա��¼
	public String loginAdmin(){
		
		System.out.println(userName+"----------------"+adminService);
		
		admin=adminService.loadByUserName(userName);
		ActionContext.getContext().getSession().put("loginAdmin",admin);
		
		System.out.println(admin.getType()+"----------");
		
		if(admin.getType().equals("super")){
			ActionContext.getContext().put("urlAction","page/super/main.jsp");
		}else if(admin.getType().equals("general")){
			ActionContext.getContext().put("urlAction","page/general/main.jsp");
		}else if(admin.getType().equals("vip")){
			ActionContext.getContext().put("urlAction","page/vip/main.jsp");
		}else{
			ActionContext.getContext().put("urlAction","page/front/main.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	public String loginOutUser() {
		ActionContext.getContext().getSession().remove("loginUser");
		ActionContext.getContext().put("urlAction", "page/front/main.jsp");
		return ActionUtil.REDIRECT;
	}
	
	public String loginOutAdmin(){
		ActionContext.getContext().getSession().remove("loginAdmin");
		ActionContext.getContext().put("urlAction", "page/login.jsp");
		return ActionUtil.REDIRECT;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	
	
}

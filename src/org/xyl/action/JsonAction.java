package org.xyl.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.xyl.bean.User;
import org.xyl.bo.TranObj;
import org.xyl.iservice.IUserService;
import org.xyl.util.ActionUtil;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Scope("prototype")
@Controller("jsonAction")
public class JsonAction {

	@Resource private IUserService userService;

	public void checkUserOrEmail() throws IOException{
		
		TranObj to = new TranObj();
	
		HttpServletRequest request=ServletActionContext.getRequest();
		String userName=request.getParameter("userName");
		String email=request.getParameter("email");
		
		System.out.println("========"+email+"=="+userName);
		User user=null;
		if(userName!=null){
			try{
				if(userName!=null)userName=new String(userName.getBytes("ISO-8859-1"));
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			System.out.println("========"+email+"=="+userName);
			user=userService.loadByUserName(userName);
		}
		if(email!=null)user=userService.loadByEmail(email);
		
		if(user==null){
			to.setResult(0);
			to.setFailure();
			to.setMsg("不存在该用户");
		}else{
			to.setResult(1);
			to.setSuccess();
			to.setMsg("存在该用户");
		}
		
		ActionUtil.sendJson(to, ServletActionContext.getResponse());
	}
	
}

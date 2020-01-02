package org.xyl.interceptor;


import org.xyl.bean.Admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PermissionInterceptor extends MethodFilterInterceptor {

	protected String doIntercept(ActionInvocation action) throws Exception{
		
		System.out.println("À¹½ØÆ÷Ö´ÐÐ-------------------------------");
		
		Admin admin=(Admin)ActionContext.getContext().getSession().get("loginAdmin");
		System.out.println(admin+"--------------------");
		if(admin!=null) return action.invoke();
		
		return "adminLogin";
	}

}


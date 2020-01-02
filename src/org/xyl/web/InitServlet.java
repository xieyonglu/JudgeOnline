package org.xyl.web;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.xyl.util.AuthUtil;

@SuppressWarnings("serial")
public class InitServlet extends HttpServlet{

	
	public void init() throws ServletException{
		Map<Integer,List<String>> auths=AuthUtil.initAuth();
		System.out.println("*************************系统初始化完毕*************************");
		System.out.println(auths);
		//将对象存储到servletContext(application)
		this.getServletContext().setAttribute("auths",auths);
		super.init();
		
	}
	
}

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
		System.out.println("*************************ϵͳ��ʼ�����*************************");
		System.out.println(auths);
		//������洢��servletContext(application)
		this.getServletContext().setAttribute("auths",auths);
		super.init();
		
	}
	
}

package org.xyl.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xyl.bean.Admin;
import org.xyl.bean.User;
import org.xyl.util.AuthUtil;

public class AuthFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//��������ҳ��ʱ��ͨ��servletContext��ȡȨ����Ϣ��������Ӧ���ж�
		//1����ȡҪ���ʵ�·��
		HttpServletRequest hRequest=(HttpServletRequest)request;
		HttpServletResponse hResponse=(HttpServletResponse)response;
		String path=hRequest.getRequestURI();
		String rPath=path.substring(hRequest.getContextPath().length());
		String sessionId=request.getParameter("jsessionId");
		User loginUser=(User)hRequest.getSession().getAttribute("loginUser");
		Admin loginAdmin=(Admin)hRequest.getSession().getAttribute("loginAdmin");
		
		/*
		 * ר�������ж�uploadify��flash���ϴ������
		 */
		if(loginUser==null&&sessionId!=null&&!"".equals(sessionId.trim())){
			loginUser=(User)MsgSessionContext.getSession(sessionId).getAttribute("loginUser");
		}
		int roleId=0;
		
		//if(loginUser!=null){
		//	roleId=loginUser.getType();
		//}
		if(rPath.endsWith("/page/user/main.jsp")&&loginUser==null||
		   rPath.endsWith("/page/super/main.jsp")&&loginAdmin==null||
		   rPath.endsWith("/page/general/main.jsp")&&loginAdmin==null||
		   rPath.endsWith("/page/vip/main.jsp")&&loginAdmin==null){
			request.getRequestDispatcher("/inc/errorPage.jsp").forward(request, response);
		}
		
		boolean canAccess=AuthUtil.checkAuth(roleId, (Map<Integer,List<String>>) hRequest.getSession().getServletContext().getAttribute("auths"), rPath);
		
		/*if(!canAccess){
			hResponse.sendRedirect("user_authError.action");
			return;
		}*/
		chain.doFilter(request, hResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}















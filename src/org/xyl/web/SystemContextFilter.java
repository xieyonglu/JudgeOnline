package org.xyl.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.xyl.util.SystemContext;

public class SystemContextFilter implements Filter{

	private int pageIndex=0;
	private int pageSize=0;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub	
		try{
			int pageOffset=0;
			try{
				pageOffset=Integer.parseInt(request.getParameter("pager.offset"));
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			
			String sort=request.getParameter("sort");
			String order=request.getParameter("order");
			SystemContext.setPageSize(pageSize);
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setOrder(order);
			SystemContext.setSort(sort);
			SystemContext.setRealPath(((HttpServletRequest)request).getSession().getServletContext().getRealPath(""));
		
		}finally{
			SystemContext.removePageOffset();
			SystemContext.removePathSize();
			SystemContext.removeOrder();
			SystemContext.removeSort();
		}
	
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		if(config.getInitParameter("pageSize")!=null){
			pageSize=Integer.parseInt(config.getInitParameter("pageSize"));
		}else{
			pageSize=15;
		}
		if(config.getInitParameter("pageIndex")!=null){
			pageIndex=Integer.parseInt(config.getInitParameter("pageIndex"));
		}else{
			pageIndex=1;
		}
	}

	
}

package org.xyl.web;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.xyl.util.SystemContext;


public class CleanListener implements ServletContextListener{

	private Timer timer;
	private Timer indexTimer;
	private WebApplicationContext wac=null;
	private String realPath=null;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("---------------------��������Ѿ��ر�---------------------");
	}
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		wac=WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		System.out.println("-----------��������������Ѿ��������Ѿ���ȡ��"+wac+"��------------");
		realPath=sce.getServletContext().getRealPath("");
		timer=new Timer();
		timer.scheduleAtFixedRate(new ClearDataTask(),50000,300000);
		indexTimer=new Timer();
		indexTimer.scheduleAtFixedRate(new IndexCommit(),600000,600000);
	
	}
	
	private class ClearDataTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			SystemContext.setRealPath(realPath);
			System.out.println("����������"+new Date());
			
			//IAttachmentService attachmentService = (IAttachmentService)wac.getBean("attachmentService");
			//attachmentService.updateClearAttach();
		}
		
	}
	
	
	private class IndexCommit extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			SystemContext.setRealPath(realPath);
			System.out.println("�����������ύ"+new Date());
			
			//IIndexService indexService = (IIndexService)wac.getBean("indexService");
			//indexService.updateCommitIndex();
		}
		
	}
	
	
	
	
}






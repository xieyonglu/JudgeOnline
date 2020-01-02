package org.xyl.web;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.xyl.bean.Match;
import org.xyl.iservice.IMatchService;
import org.xyl.util.SystemContext;


public class MatchListener implements ServletContextListener{

	private Timer timer;
	private WebApplicationContext wac=null;
	//private String realPath=null;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("---------------------比赛程序已经关闭---------------------");
	}
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		wac=WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		System.out.println("-----------比赛的启动程序已经开启（已经获取了"+wac+"）------------");
		//realPath=sce.getServletContext().getRealPath("");
		timer=new Timer();
		//timer.scheduleAtFixedRate(new ClearDataTask(),50000,300000);
		timer.scheduleAtFixedRate(new ClearDataTask(),50000,50000);
	}
	
	private class ClearDataTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//SystemContext.setRealPath(realPath);
			System.out.println("进行了清理"+new Date());
			IMatchService matchService = (IMatchService)wac.getBean("matchService");
			List<Match> list=matchService.listAllMatch();
			
			for(int i=0;i<list.size();i++){
				Match match=list.get(i);
				System.out.println(match.getState()+"===");
				
				//把比赛设置为开始还未
				if(match.getState()!=0&&
						(match.getStartDate().getTime()>(new Date()).getTime())){
					match.setState(0);
					matchService.updateMatch(match);
				}
				
				//把比赛设置为已经开始
				if(match.getState()!=1&&
						(match.getStartDate().getTime()<=(new Date()).getTime())&&
						(match.getEndDate().getTime()>=(new Date()).getTime())){
					match.setState(1);
					matchService.updateMatch(match);
				}
				
				//把比赛设置为已经结束
				if(match.getState()!=2&&
						(match.getEndDate().getTime()<(new Date()).getTime())){
					match.setState(2);
					matchService.updateMatch(match);
				}
			}
			//IAttachmentService attachmentService = (IAttachmentService)wac.getBean("attachmentService");
			//attachmentService.updateClearAttach();
		}
		
	}
	
	
	
}






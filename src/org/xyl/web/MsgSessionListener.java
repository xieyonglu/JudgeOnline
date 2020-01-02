package org.xyl.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MsgSessionListener implements HttpSessionListener{


	public void sessionCreated(HttpSessionEvent hse) {
		// TODO Auto-generated method stub
		MsgSessionContext.addSession(hse.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		// TODO Auto-generated method stub
		MsgSessionContext.removeSession(hse.getSession().getId());
	}
	
}

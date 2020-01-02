package org.xyl.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class MsgSessionContext {

	private final static Map<String,HttpSession> sessions=new HashMap<String,HttpSession>();
	
	
	public static synchronized void addSession(HttpSession session){
		if(session!=null){
			sessions.put(session.getId(),session);
			System.out.println("ÃÌº”¡Àsession:"+session.getId());
		}
	}
	
	public static synchronized void removeSession(String sessionId){
		if(sessionId!=null){
			sessions.remove(sessionId);
			System.out.println("“∆≥˝¡Àsession:"+sessionId);
		}
	}
	
	public static HttpSession getSession(String sessionId){
		return sessions.get(sessionId);
	}
	
	
}

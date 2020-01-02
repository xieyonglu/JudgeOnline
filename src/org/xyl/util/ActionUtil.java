package org.xyl.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ActionUtil {

	public final static String MSG_LIST="messge_list";
	public final static String REDIRECT="redirect";
	
	public static void sendJson(Object obj,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		String json=JsonJacksonUtil.getInstance().objToJson(obj);
		response.getWriter().write(json);
	}
	
}

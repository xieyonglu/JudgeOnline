package org.xyl.test;

import org.xyl.bean.User;
import org.xyl.util.JsonJacksonUtil;

public class JsonJacksonTest {

	public static void main(String[] args){
		JsonJacksonUtil jjUtil=JsonJacksonUtil.getInstance();
		User user=new User("userName_1","password_1","question_1","answer_1","email_1");
		System.out.println(jjUtil.objToJson(user));
		
		
		String str="{'userId':null,'userName':'userName_1','password':'password_1','email':'email_1','question':'question_1','answer':'answer_1','picture':null,'createDate':null,'userEnable':false,'commentEnable':false,'passCount':0,'submitCount':0,'trueProblemCount':0,'falseProblemCount':0,'otherInfo':null,'comment':[],'codes':[],'submits':[]}";
	
		System.out.println(jjUtil.jsonToObject(str, User.class));
	}
}

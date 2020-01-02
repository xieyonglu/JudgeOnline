package org.xyl.util;

import java.util.Arrays;

public class EmailUtil{
	
	public static void main(String[] args){
		String email="yongluxie@ebaotech.com";
		System.out.println("����У�飺"+EmailUtil.checkEmail(email));
	}

	public static boolean checkEmail(String email){
		String[] arrayStr=new String[]{"ac","com","net","org","edu","gov","mil","ac\\.cn","com\\.cn","edu\\.cn","net\\.cn","org\\.cn"};
		String temp=join(arrayStr,"|");
		//ͨ���ַ�������ʽ����������ʽ
		String regex="[0-9a-zA-Z](\\w|-)*@\\w+\\.("+temp+")";
		
		return email.matches(regex);
		
	}
	
	public static String join(String[] arrayStr,String separator){
		String temp="";
		for(int i=0;i<arrayStr.length;i++){
			if(i!=(arrayStr.length-1)){
				temp=temp+arrayStr[i]+separator;
			}else{
				temp=temp+arrayStr[i];
			}
			
		}
		System.out.println(temp);
		return temp;
	}
	
}

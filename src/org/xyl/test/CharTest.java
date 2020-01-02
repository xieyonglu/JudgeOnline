package org.xyl.test;

import java.io.File;
import java.net.InetAddress;

import org.xyl.util.IPTimeStamp;
import org.xyl.util.PropertiesUtil;

public class CharTest {

	public static void main1(String[] args) {
		char a='A';
		for(int i=0;i<3;i++){
			System.out.println((char)('A'+i));
		}
	}
	
	public static void main2(String[] args) throws Exception{
		IPTimeStamp st=new IPTimeStamp();
		String str="192.68.1.1";
		String s[]=str.split("\\.");
		String xyl="";
		for(int i=0;i<s.length;i++){
			xyl=xyl+CharTest.addZero(s[i], 3);
		}
		//19206800100120121106100559875557
		System.out.println(xyl+st.getIPTimeRand());
		
	}

	public static  String addZero(String str,int len){
		StringBuffer s=new StringBuffer();
		s.append(str);
		while(s.length()<len){
			s.insert(0,"0");
		}
		return s.toString();
	}
	
	public static void main(String[] args){
		String filePath=PropertiesUtil.getPropertiesValue("filePath");
		File file=new File(filePath);
		System.out.println(file.exists()+"--------");
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	
	
}
















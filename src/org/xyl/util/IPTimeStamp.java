package org.xyl.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IPTimeStamp {

	private SimpleDateFormat sdf=null;
	private String ip=null;
	
	public IPTimeStamp(){
		
	}
	
	public IPTimeStamp(String ip){
		this.ip=ip;
	}
	
	public String getIPTimeRand(){
		StringBuffer buf=new StringBuffer();
		if(this.ip!=null){
			String s[]=null;
			if(this.ip.contains(".")){
				s=this.ip.split("\\.");
				for(int i=0;i<s.length;i++){
					buf.append(this.addZero(s[i],3));
				}
			}else if(this.ip.contains(":")){
				s=this.ip.split("\\:");
				for(int i=0;i<s.length;i++){
					buf.append(this.addZero(s[i],4));
				}
			}
		}
		buf.append(this.getTimeStamp());
		Random r=new Random();
		for(int i=0;i<3;i++){
			buf.append(r.nextInt(10));
		}
		return buf.toString();
	}
	
	private String addZero(String str,int len){
		StringBuffer s=new StringBuffer();
		s.append(str);
		while(s.length()<len){
			s.insert(0,"0");
		}
		return s.toString();
	}
	
	public String getDate(){
		this.sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return this.sdf.format(new Date());
	}
	
	public String getTimeStamp(){
		this.sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return this.sdf.format(new Date());
	}
	
	public String getIPTimeStamp(String ipaddress){
		String s[]=null;
		
		if(ipaddress.contains(".")){
			s=ipaddress.split("\\.");
		}else if(ipaddress.contains(":")){
			s=ipaddress.split("\\:");
		}
		
		String ip="";
		
		if(ipaddress.contains(".")){
			for(int i=0;i<s.length;i++){
				ip=ip+this.addZero(s[i], 3);
			}
		}else if(ipaddress.contains(":")){
			for(int i=0;i<s.length;i++){
				ip=ip+this.addZero(s[i], 4);
			}
		}
		
		return ip+getIPTimeRand();
	}
	
	
	public static void main(String[] args){
		IPTimeStamp ip=new IPTimeStamp();
		System.out.println(ip.getIPTimeStamp("0:0:0:0:0:0:0:1"));
		System.out.println(ip.getIPTimeStamp("12.12.3.33"));
	}
}





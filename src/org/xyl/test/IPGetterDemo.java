package org.xyl.test;

import java.net.InetAddress;




public class IPGetterDemo {
	//the entrance of  application
	public static void main(String[] args){
		
		System.out.println("Local Host Ip is "+getLocalHostIP());
		String localHostName=getLocalHostName();
		System.out.println("the local Host name is "+localHostName);
		
		String[] localIPs=getAllHostIPs(localHostName);
		if(null!=localIPs){
			for(int i=0;i<localIPs.length;i++){
				System.out.println("One Of Local IPs  "+localIPs[i]);
			}
		}
	}
	
	/*
	 * get the local host ip
	 */
	public static String getLocalHostIP(){
		try{
			InetAddress addr=InetAddress.getLocalHost();
			return addr.getHostAddress();
		}catch(Exception e){
			return "";
		}
	}
	
	/*
	 * get the local host name
	 */
	public static String getLocalHostName(){
		try{
			InetAddress addr=InetAddress.getLocalHost();
			return addr.getHostName();
		}catch(Exception e){
			return "";
		}
	}
	
	/*
	 * get all host ips by the given hostName
	 */
	public static String[] getAllHostIPs(String hostName){
		String[] ips=null;
		try{
			InetAddress[] addrs=InetAddress.getAllByName(hostName);
			if(null!=addrs){
				ips=new String[addrs.length];
				for(int i=0;i<addrs.length;i++){
					ips[i]=addrs[i].getHostAddress();
				}
			}
		}catch(Exception e){
			ips=null;
		}
		return ips;
	}
	
	
}



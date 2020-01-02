package org.xyl.util;

public class IPAddressUtil{
	
	public static boolean checkIPScole(String from,String to,String ip){
		if(from.contains(".")){
			if(ip.contains(":")){
				return false;
			}
			return IPAddressUtil.checkIPV4Scole(from,to,ip);
		}else if(from.contains(":")){
			if(ip.contains(".")){
				return false;
			}
			return IPAddressUtil.checkIPV6Scole(from,to,ip);
		}
		return false;
	}
	
	public static boolean checkIPV4Scole(String from,String to,String ip){
		boolean aflag=false,bflag=false;
		
		String[] from_str=from.split("\\.");
		String[] to_str=to.split("\\.");
		String[] ip_str=ip.split("\\.");
		
		if(Integer.parseInt(ip_str[0])>Integer.parseInt(from_str[0])&&
				Integer.parseInt(ip_str[0])<Integer.parseInt(to_str[0])){
			return true;
		}
		if(Integer.parseInt(ip_str[0])==Integer.parseInt(from_str[0])){
			for(int i=1;i<from_str.length;i++){
				if(Integer.parseInt(ip_str[i])<Integer.parseInt(from_str[i])){
					return false;
				}
			}
			aflag=true;
		}
		if(Integer.parseInt(ip_str[0])==Integer.parseInt(to_str[0])){
			for(int i=1;i<to_str.length;i++){
				if(Integer.parseInt(ip_str[i])>Integer.parseInt(to_str[i])){
					return false;
				}
			}
			bflag=true;
		}
		
		if(aflag&&bflag)return true;
		
		return false;
	}
	
	public static boolean checkIPV6Scole(String from,String to,String ip){
		boolean aflag=false,bflag=false;
		
		String[] from_str=IPAddressUtil.tranStrArray(from.split("\\:"));
		String[] to_str=IPAddressUtil.tranStrArray(to.split("\\:"));
		String[] ip_str=IPAddressUtil.tranStrArray(ip.split("\\:"));
		
		if(Double.parseDouble(ip_str[0])>Double.parseDouble(from_str[0])&&
				Double.parseDouble(ip_str[0])<Double.parseDouble(to_str[0])){
			return true;
		}
		
		if(Double.parseDouble(ip_str[0])==Double.parseDouble(from_str[0])){
			for(int i=1;i<from_str.length;i++){
				if(Double.parseDouble(ip_str[i])<Double.parseDouble(from_str[i])){
					return false;
				}
			}
			aflag=true;
		}
		
		if(Double.parseDouble(ip_str[0])==Double.parseDouble(to_str[0])){
			for(int i=1;i<to_str.length;i++){
				if(Double.parseDouble(ip_str[i])>Double.parseDouble(to_str[i])){
					return false;
				}
			}
			bflag=true;
		}
		
		if(aflag&&bflag)return true;
		
		return false;
	}
	
	public static String[] tranStrArray(String[] str){
		for(int i=0;i<str.length;i++){
			str[i]=IPAddressUtil.tran16To10(str[i])+"";
		}
		return str;
	}

	public static double tran16To10(String str){
		double sum=0;
		for(int i=0;i<str.length();i++){
			switch(str.charAt(i)){
			case '0' :sum=sum+0*Math.pow(16,(str.length()-i-1));break;
			case '1' :sum=sum+1*Math.pow(16,(str.length()-i-1));break;
			case '2' :sum=sum+2*Math.pow(16,(str.length()-i-1));break;
			case '3' :sum=sum+3*Math.pow(16,(str.length()-i-1));break;
			case '4' :sum=sum+4*Math.pow(16,(str.length()-i-1));break;
			case '5' :sum=sum+5*Math.pow(16,(str.length()-i-1));break;
			case '6' :sum=sum+6*Math.pow(16,(str.length()-i-1));break;
			case '7' :sum=sum+7*Math.pow(16,(str.length()-i-1));break;
			case '8' :sum=sum+8*Math.pow(16,(str.length()-i-1));break;
			case '9' :sum=sum+9*Math.pow(16,(str.length()-i-1));break;
			case 'A' :case 'a':sum=sum+10*Math.pow(16,(str.length()-i-1));break;
			case 'B' :case 'b':sum=sum+11*Math.pow(16,(str.length()-i-1));break;
			case 'C' :case 'c':sum=sum+12*Math.pow(16,(str.length()-i-1));break;
			case 'D' :case 'd':sum=sum+13*Math.pow(16,(str.length()-i-1));break;
			case 'E' :case 'e':sum=sum+14*Math.pow(16,(str.length()-i-1));break;
			case 'F' :case 'f':sum=sum+15*Math.pow(16,(str.length()-i-1));break;
			default:break;
			}
		}
		
		//System.out.println("sum=="+sum);
		return sum;
	}
	
	//检查ipAddress是否是IPV4
	public static boolean checkIPV4(String ipAddress){
		if(ipAddress==null||ipAddress.trim().length()==0)return false;
		
		if(ipAddress.length()>3)return false;
		if(!ipAddress.matches("\\d+"))return false;
		if(Integer.parseInt(ipAddress)>=0&&
				Integer.parseInt(ipAddress)<=255){
			return true;
		}else{
			return false;
		}
	}
	
	//检查ipAddress是否是IPV6
	public static boolean checkIPV6(String ipAddress){
		
		if(ipAddress==null||ipAddress.trim().length()==0)return false;
		
		if(ipAddress.length()>4)return false;
		
		for(int i=0;i<ipAddress.length();i++){
			char c=ipAddress.charAt(i);
			if(!(c=='a'||c=='A'||c=='b'||c=='B'||c=='c'||c=='C'||c=='D'||c=='D'||c=='e'||c=='E'||
			   c=='f'||c=='F'||c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||
			   c=='8'||c=='9')){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		String from="23.23.234.234";
		String to="23.23.255.243";
		System.out.println(IPAddressUtil.compareIpAddress(from, to));
	}
	
	//比较两个IP的大小
	public static boolean compareIpAddress(String from,String to){
		if(from.contains(".")){
			return IPAddressUtil.compareIpAddressV4(from,to);
		}else if(from.contains(":")){
			return IPAddressUtil.compareIpAddressV6(from,to);
		}
		return false;
	}
	
	
	public static boolean compareIpAddressV4(String from,String to){
		
		String[] from_str=from.split("\\.");
		String[] to_str=to.split("\\.");
		
		for(int i=0;i<from_str.length;i++){
			if(Integer.parseInt(to_str[i])>Integer.parseInt(from_str[i])){
				return true;
			}
			if(Integer.parseInt(to_str[i])<Integer.parseInt(from_str[i])){
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean compareIpAddressV6(String from,String to){
		
		String[] from_str=IPAddressUtil.tranStrArray(from.split("\\:"));
		String[] to_str=IPAddressUtil.tranStrArray(to.split("\\:"));
		for(int i=0;i<from_str.length;i++){
			if(Double.parseDouble(to_str[i])>Double.parseDouble(from_str[i])){
				return true;
			}
			if(Double.parseDouble(to_str[i])<Double.parseDouble(from_str[i])){
				return false;
			}
		}
		
		return true;
	}
	
	
	
	
}










package org.xyl.test;

public class IPUtilTest {

	public static void main(String[] args){
		String from="fe80:0:0:0:8816:c6dc:322f:1";
		
		String ip="fe80:0:0:0:8816:c6dc:322f:ffff";
		
		String to="fe80:0:0:0:8816:c6dc:322f:ffff";
		/*String from="122.123.124.122";
		
		String ip="122.123.124.126";
		
		String to="122.123.124.127";*/
		
		
		
		System.out.println(IPUtilTest.checkIPScole(from, to, ip));
	}
	
	public static boolean checkIPScole(String from,String to,String ip){
		if(from.contains(".")){
			return IPUtilTest.checkIPV4Scole(from,to,ip);
		}else if(from.contains(":")){
			return IPUtilTest.checkIPV6Scole(from,to,ip);
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
		
		String[] from_str=IPUtilTest.tranStrArray(from.split("\\:"));
		String[] to_str=IPUtilTest.tranStrArray(to.split("\\:"));
		String[] ip_str=IPUtilTest.tranStrArray(ip.split("\\:"));
		
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
	
	
	public static void main1(String[] args){
		IPUtilTest.tran16To10("0001");
	}
	
	public static String[] tranStrArray(String[] str){
		for(int i=0;i<str.length;i++){
			str[i]=IPUtilTest.tran16To10(str[i])+"";
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
}









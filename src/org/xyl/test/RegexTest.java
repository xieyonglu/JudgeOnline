package org.xyl.test;

public class RegexTest {

	public static void main(String[] args){
		String str="123";
		if(str.matches("\\d+")){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
	
	
}

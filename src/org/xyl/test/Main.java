package org.xyl.test;


import java.util.Scanner;

public class Main{
	
	public static void main5(String[] args){
		Scanner in=new Scanner(System.in);
		int n,sum=0;
		int[] cmt=new int[50];
		n=in.nextInt();
		for(int i=0;i<n;i++){
			sum=0;
			for(int j=0;j<5;j++){
				sum=sum+in.nextInt();
			}
			cmt[i]=sum;
		}
		for(int i=0;i<n;i++){
			System.out.println(cmt[i]);
		}
	}
	
	public static void main4(String[] args){
		Scanner in=new Scanner(System.in);
		int sum=0,n=in.nextInt();
		for(int i=1;i<=n;i++){
			sum+=i;
		}
		System.out.println(sum);
	}
	
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int a=in.nextInt();
		int b=in.nextInt();
        String str="";
        for(int i=0;i<10000;i++){
        	str=str+i;
        }
		System.out.println(a+b);
	}
	
	public static void main3(String[] args){
		
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		for(int i=0;i<n;i++){
			for(int j=0;j<n-i;j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public static void main1(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		for(int i=1;i<=n;i++){
			for(int j=1;j<=i;j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	
	
	public static void main2(String[] args){
		Scanner in=new Scanner(System.in);
		String str1=in.nextLine();
		String str2=in.nextLine();
		
		int[] array=getStringIndex(str1,str2);
		
		if(array!=null){
			for(int i=0;i<array.length;i++){
				if(i!=array.length-1)
					System.out.print(array[i]+" ");
				else
					System.out.println(array[i]);
			}
		}
		
	}
	
	public static int[] getStringIndex(String str1,String str2){
		int array[]=new int[str1.length()];
		int i=0,j=0,cmt=0,temp;
		for(i=0;i<str1.length();i++){
			temp=i;
			for(j=0;j<str2.length()&&i<str1.length();j++){
				//System.out.println(str1.charAt(i)+"---"+str2.charAt(j));
				if(str1.charAt(i)==str2.charAt(j)){
					i++;
					//j++;
				}else{
					break;
				}
			}
			if(j==str2.length()){
				array[cmt]=temp;
				cmt++;
			}
			i=temp;
		}
		
		
		//////////////////////////////////
		int a[]=new int[cmt];
		for(int k=0;k<cmt;k++){
			a[k]=array[k];
		}
		return a;
	}
	
}



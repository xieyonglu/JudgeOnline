package org.xyl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DateUtil {

	public DateUtil(){}
	
	
	//该日期加1，得到下一个日期
	public static Date addCount(Date date){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String str=sdf.format(date);

		int year=Integer.parseInt(str.substring(0, 4));
		int month=Integer.parseInt(str.substring(5, 7));
		int day=Integer.parseInt(str.substring(8, 10));
		
		
		switch(month){
		case 1:case 3:case 5:case 7:case 8:case 10:case 12:
			if(day<31){
				return (new GregorianCalendar(year,month-1,day+1)).getTime();
			}else{
				if(month<12){
					return (new GregorianCalendar(year,month,1)).getTime();
				}else{
					return (new GregorianCalendar(year+1,0,1)).getTime();
				}
			}
		case 4:case 6:case 9:case 11:
			if(day<30){
				return (new GregorianCalendar(year,month-1,day+1)).getTime();
			}
			else{
				return (new GregorianCalendar(year,month,1)).getTime();
			}
		case 2:
			if(year%400==0||(year%4==0&&year%100!=0)){//是闰年
				if(day<29) {
					return (new GregorianCalendar(year,month-1,day+1)).getTime();
				}
				else {
					return (new GregorianCalendar(year,month,1)).getTime();
				}
			}else{
				if(day<28){
					return (new GregorianCalendar(year,month-1,day+1)).getTime();
				}else {
					return (new GregorianCalendar(year,month,1)).getTime();
				}
				
			}
		}
		return date;
	}
	
	//该日期加1，得到下一个日期(月)
	public static Date addMonthCount(Date date){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String str=sdf.format(date);

		int year=Integer.parseInt(str.substring(0, 4));
		int month=Integer.parseInt(str.substring(5, 7));
		
		if(month<12){
			month++;
		}else{
			year++;
			month=1;
		}
		try {
			return sdf.parse(year+"-"+month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//给定两个日期，求这些日期的所有list集合(日)
	public static List<String> getBetweenDay(Date start,Date end){
		
		List<String> list=new ArrayList<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date temp=start;
		
		while(!temp.equals(end)){
			list.add(sdf.format(temp));
			temp=addCount(temp);
		}
		list.add(sdf.format(end));
		
		return list;
	}
	
	
	
	//给定两个日期，求这些日期的所有list集合（月）
	public static List<String> getBetweenMonth(Date start,Date end){
		List<String> list=new ArrayList<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date startDate=null,endDate=null;
		
		
		try{
			startDate=sdf.parse(sdf.format(start));
			endDate=sdf.parse(sdf.format(end));
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		while(!startDate.equals(endDate)){
			list.add(sdf.format(startDate));
			startDate=addMonthCount(startDate);
		}
		list.add(sdf.format(end));
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		return list;
	}
	
	//给定两个日期，求这些日期的所有list集合（年）
	public static List<String> getBetweenYear(Date start,Date end){
		List<String> list=new ArrayList<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		int startYear=Integer.parseInt(sdf.format(start));
		int endYear=Integer.parseInt(sdf.format(end));
		
		while(startYear!=endYear){
			list.add(startYear+"");
			startYear++;
		}
		list.add(endYear+"");
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		return list;
	}
	
	public static void main2(String[] args) throws Exception{
		//GregorianCalendar start=new GregorianCalendar(1999,12,12);
		//GregorianCalendar end=new GregorianCalendar(1999,12,12);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		
		getBetweenMonth(sdf.parse("1999-12-12"),sdf.parse("2012-12-12"));
	}
	
	//比较两个日期的大小
	public static int compare(String start,String end){
		int aYear=Integer.parseInt(start.substring(0, 4));
		int aMonth=Integer.parseInt(start.substring(5, 7));
		int aDay=Integer.parseInt(start.substring(8, 10));
		
		int bYear=Integer.parseInt(end.substring(0, 4));
		int bMonth=Integer.parseInt(end.substring(5, 7));
		int bDay=Integer.parseInt(end.substring(8, 10));
		
		
		if(aYear>bYear){
			return 1;
		}else if(aYear<bYear){
			return -1;
		}else{
			if(aMonth>bMonth){
				return 1;
			}else if(aMonth<bMonth){
				return -1;
			}else{
				if(aDay>bDay){
					return 1;
				}else if(aDay<bDay){
					return -1;
				}else{
					return 0;
				}
			}
		}
	}
	
	//得到某一个字符串在list集合中的索引
	public static int getIndex(List<String> list,String str){
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(str)){
				return i;
			}
		}
		return -1;
	}
	
	//得到最近一周的日期
	public static String getNearWeek(String str,int len){	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		Date date=null;
		try {
			date = formatter.parse(str);//取时间
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(date);
	    calendar.add(calendar.DATE,-len);//把日期往后增加一天.整数往后推,负数往前移动
	    date=calendar.getTime(); //这个时间就是日期往后推一天的结果        
	    
	    String dateString = formatter.format(date);
		return dateString;
	}
	
	
	//得到最近一月的日期
	public static String getNearMonth(String str){
		int y=Integer.parseInt(str.substring(0,4));
		int m=Integer.parseInt(str.substring(5,7));
		int d=Integer.parseInt(str.substring(8,10));	
		if(m==01){
			m=12;
			y--;
		}else{
			m--;
		}
		d=1;	
		String s=y+"-"+addZero(m+"",2)+"-"+addZero(d+"",2);
		return s;
	}
	
	//补0操作
	public static String addZero(String str,int len){
		StringBuffer s = new StringBuffer() ;
		s.append(str) ;
		while(s.length() < len){
			s.insert(0,"0") ;
		}
		return s.toString() ;
	}
	
	public static void main(String[] args){
		Calendar ca=new GregorianCalendar();
		int year=ca.get(Calendar.YEAR);//得到当前的年
		int month=ca.get(Calendar.MONTH)+1;//得到当前的月
		int day=ca.get(Calendar.DAY_OF_MONTH);//得到当前的日
		int week=ca.get(Calendar.DAY_OF_WEEK);//得到当前的星期
		
		
		String startTime=year+"-"+addZero(month+"",2)+"-"+addZero(day+"",2);
		String endTime=getNearWeek(startTime,week+7);
		
		System.out.println(startTime+"--"+endTime);
		
		//Calendar ca=new GregorianCalendar();
		//int year=ca.get(Calendar.YEAR);//得到当前的年
		//int month=ca.get(Calendar.MONTH)+1;//得到当前的月
		//int day=ca.get(Calendar.DAY_OF_MONTH);//得到当前的日
		
		
		startTime=year+"-"+addZero(month+"",2)+"-"+addZero(day+"",2);
		endTime=getNearMonth(startTime);
		
		System.out.println(startTime+"--"+endTime);
	}
	
}






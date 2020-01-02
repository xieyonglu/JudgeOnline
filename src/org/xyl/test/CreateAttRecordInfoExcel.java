package org.xyl.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.xyl.bean.User;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CreateAttRecordInfoExcel{
	
	
	private static String filePath="c:\\";
	private static String[] columnName={"学号","姓名","性别","11","22"};
	
	public static void main(String[] args){
		try{
			createExcel();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void createExcel() throws Exception{
		
		File outFile = new File(filePath+"123.xls");
		WritableWorkbook workbook = Workbook.createWorkbook(outFile);
		WritableSheet sheet = workbook.createSheet("学生考勤信息", 0);
		
		List<User> all=new ArrayList<User>();
		all.add(new User("1","1","1","1","1"));
		all.add(new User("2","1","1","1","1"));
		all.add(new User("3","1","1","1","1"));
		
		Label lab = null;
		for (int x = 0; x < columnName.length; x++) {
			lab = new Label(x,0,columnName[x]);
			sheet.addCell(lab) ;
		}
		
		for (int y = 0; y < all.size(); y++) {
			User user=all.get(y);
			lab = new Label(0,y+1,user.getUserName());				sheet.addCell(lab) ;
			lab = new Label(1,y+1,user.getPassword());				sheet.addCell(lab) ;
			lab = new Label(2,y+1,user.getQuestion());				sheet.addCell(lab) ;
			lab = new Label(3,y+1,user.getAnswer());				sheet.addCell(lab) ;
			lab = new Label(4,y+1,user.getEmail());					sheet.addCell(lab) ;
			lab = new Label(5,y+1,user.getEmail());					sheet.addCell(lab) ;
		}
		workbook.write() ;
		workbook.close() ;
	}

	
}




package org.xyl.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.xyl.bean.Form;
import org.xyl.bean.Match;
import org.xyl.bean.User;
import org.xyl.iservice.IFormService;
import org.xyl.iservice.IMatchService;
import org.xyl.iservice.IUserService;
import org.xyl.util.ExcelWorkSheet;
import org.xyl.util.MD5Util;
import org.xyl.util.PropertiesUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("handleExcelAction")
@Scope("prototype")
public class HandleExcelAction extends ActionSupport {
	
	@Resource IUserService userService;
	@Resource IFormService formService;
	@Resource IMatchService matchService;
	
	private File excelFile; // 上传的文件
	private String excelFileFileName; // 保存原始文件名
	private ExcelWorkSheet<User> excelWorkSheet; // 将Excel文件解析完毕后信息存放到这个对象中
	
	
	public HSSFWorkbook createWorkBook(InputStream is) throws IOException {
		if (excelFileFileName.toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(is);
		}
		//if (excelFileFileName.toLowerCase().endsWith("xlsx")) {
		//	return new XSSFWorkbook(is);
		//}
		return null;
	}

	public void validateImportExcel(){
		System.out.println("验证------"+excelFileFileName);
		if (!excelFileFileName.toLowerCase().endsWith("xls")) {
			this.addFieldError("importError", "文件格式错误！");
		}
		ActionContext.getContext().put("urlAction", "user/import_excel.jsp");
	}
	
	public String importExcel() throws Exception {
		HSSFWorkbook book = createWorkBook(new FileInputStream(excelFile));
		// book.getNumberOfSheets(); 判断Excel文件有多少个sheet
		HSSFSheet sheet = book.getSheetAt(0);
		
		System.out.println(sheet.getLastRowNum());
		
		for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
			HSSFRow row = sheet.getRow(j);	
			HSSFCell cell=row.getCell((short) 0);//学号
			String userName=handlerString(cell.toString());	
			User user=new User(userName,MD5Util.getEncryptedPwd("1"),"1","1","1");//用户名-真实姓名-密码-问题-答案-邮箱		
			userService.addUser(user);
		}
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//导出报表
	public String exportFormExcel(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String matchId=request.getParameter("matchId");
		Match match=matchService.load(Long.parseLong(matchId));
		String hql="from Form as obj where obj.match.matchId='"+matchId+"' order by obj.problemCount desc,obj.totalTime asc";
		List<Form> list=formService.listAllFormByHql(hql);
		Form form=null;
		
		//导出的文件路径
		String exportPath=PropertiesUtil.getPropertiesValue("exportPath");
		String fileName=PropertiesUtil.getPropertiesValue("fileName");
		
		File outFile = new File(exportPath+fileName+".xls");
		WritableWorkbook workbook;
		try{
			workbook = Workbook.createWorkbook(outFile);
			WritableSheet sheet = workbook.createSheet(match.getMatchName()+"ACM报表信息", 0);
			
			String[] columnName={"名次","用户名","试题数","总用时(m)"};
			
			Label lab = null;
			for (int x=0;x<columnName.length;x++) {
				lab = new Label(x,0,columnName[x]);
				sheet.addCell(lab) ;
			}
			for(int x=columnName.length;x<match.getProblemCount()+columnName.length;x++){
				lab = new Label(x,0,(char)('A'+x-columnName.length)+"");
				sheet.addCell(lab);
			}
			
			for(int y=0;y<list.size();y++){
				form=list.get(y);
				lab = new Label(0,y+1,y+1+"");
				sheet.addCell(lab);
				lab = new Label(1,y+1,form.getUserName());
				sheet.addCell(lab);
				lab = new Label(2,y+1,form.getProblemCount()+"");
				sheet.addCell(lab);
				lab = new Label(3,y+1,form.getTotalTime()+"");
				sheet.addCell(lab);
				for(int z=0;z<match.getProblemCount();z++){
					String str=this.getProblem(form, z+1);
					lab = new Label(z+4,y+1,this.writeForm(str));
					sheet.addCell(lab) ;
				}
			}
			
			workbook.write() ;
			workbook.close() ;
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(RowsExceededException e){
			e.printStackTrace();
		}catch(WriteException e){
			e.printStackTrace();
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	private String writeForm(String str){
		
		Integer minuteNum=Integer.parseInt(PropertiesUtil.getPropertiesValue("minuteNum"));
		if(str==null){
			return "(0=0+0*"+minuteNum+")";
		}
		String[] s=str.split("\\|");
		
		
		int sum=0;
		if(s[1]==null){
			s[0]="";
			s[1]="";
			sum=0;
		}else if(!s[0].equals("0")){
			sum=Integer.parseInt(s[0])+Integer.parseInt(s[1])*minuteNum;
		}
		return sum+"="+s[0]+"+"+s[1]+"*20";
	}
	
	private String getProblem(Form form,int index){
		switch(index){
		case 1:
			return form.getProblemA();
		case 2:
			return form.getProblemB();
		case 3:
			return form.getProblemC();
		case 4:
			return form.getProblemD();
		case 5:
			return form.getProblemE();
		case 6:
			return form.getProblemF();
		case 7:
			return form.getProblemG();
		case 8:
			return form.getProblemH();
		case 9:
			return form.getProblemI();
		case 10:
			return form.getProblemG();
		case 11:
			return form.getProblemK();
		case 12:
			return form.getProblemL();
		case 13:
			return form.getProblemM();
		case 14:
			return form.getProblemN();
		case 15:
			return form.getProblemO();
		default:
			return null;
		}
	}
	
	private String handlerString(String str){
		if(str.indexOf(".")!=-1){
			return str.substring(0,str.indexOf("."));
		}else{
			return str;
		}
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileFileName() {
		return excelFileFileName;
	}

	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}

	public ExcelWorkSheet<User> getExcelWorkSheet() {
		return excelWorkSheet;
	}

	public void setExcelWorkSheet(ExcelWorkSheet<User> excelWorkSheet) {
		this.excelWorkSheet = excelWorkSheet;
	}
	
	

}

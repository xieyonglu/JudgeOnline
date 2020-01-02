package org.xyl.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bo.DataBase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("dbBackUpResertAction")
@Scope("prototype")
public class DbBackUpRevertAction extends ActionSupport {

	public final String BACKUP_COMMAND = "mysqldump";
	public final String REVERT_COMMAND = "mysql";
	public final String CREATE_COMMAND = "mysqladmin";
	
	private DataBase database=new DataBase();
	private File dataFile;//要导入的数据库文件
	private String dataFileFileName;//要导入的数据库文件名

	public String backup() {
		boolean isSuccess = true;
		HttpServletRequest request=ServletActionContext.getRequest();
		String path=request.getParameter("path");
		String filepath=path+File.separator+database.getFile();
		
		try {
			Runtime rt = Runtime.getRuntime();
			String backupStr = this.getBackupStr();
			
			System.out.println("cmd /c "+backupStr+filepath);
			Process process = rt.exec("cmd /c "+backupStr+filepath);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		
		if(isSuccess){
			ActionContext.getContext().put("urlAction", "inc/success.jsp");
		}else{
			ActionContext.getContext().put("urlAction", "inc/failure.jsp");
		}
		return Action.SUCCESS;
	}

	private String getBackupStr() {
		// mysqldump judgeonline -uroot -pmysqladmin --default-character-set=gb2312 --result-file=
		
		String backupStr = BACKUP_COMMAND + " "+database.getDatabaseName()+" -u" + database.getUserName() + " -p"
			+ database.getPassword() + " --default-character-set=gb2312 --result-file=";		
		return backupStr;
	}
	
	public void validateRevert(){
		
		System.out.println("验证------"+dataFile.getPath()+"---"+dataFileFileName+"----"+dataFile.getAbsolutePath());
		HttpServletRequest request=ServletActionContext.getRequest();
		String file=request.getParameter("dataFile");
		if (!dataFileFileName.toLowerCase().endsWith("sql")) {
			this.addFieldError("importError", "文件格式错误！");
		}
		ActionContext.getContext().put("urlAction", "database/import_db.jsp");
	}

	public String revert() {
		boolean isSuccess = true;
		try{
			String revertStr=this.getRevertStr();
			String createStr=this.getCreateStr();
			System.out.println(createStr);
			System.out.println("cmd /c "+revertStr+" < "+dataFile.getPath());
				
			Runtime.getRuntime().exec(createStr);
			Runtime.getRuntime().exec("cmd /c "+revertStr+" < "+dataFile.getPath());
				
		}catch(Exception e){
			e.printStackTrace();
			isSuccess=false;
		}
		
		if(isSuccess){
			ActionContext.getContext().put("urlAction", "inc/success.jsp");
		}else{
			ActionContext.getContext().put("urlAction", "inc/failure.jsp");
		}
		return Action.SUCCESS;
	}

	private String getCreateStr(){
		String createStr=CREATE_COMMAND+" -u"+database.getUserName()+" -p"+database.getPassword()+" create "+database.getDatabaseName();
		return createStr;
	}
	
	private String getRevertStr() {
		String backupStr = REVERT_COMMAND + " -u" + database.getUserName() + " -p"
			+ database.getPassword() + " " + database.getDatabaseName();
		System.out.println(backupStr);
		return backupStr;
	}

	public File getDataFile() {
		return dataFile;
	}

	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}

	public String getDataFileFileName() {
		return dataFileFileName;
	}

	public void setDataFileFileName(String dataFileFileName) {
		this.dataFileFileName = dataFileFileName;
	}
	
	
}

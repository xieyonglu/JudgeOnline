package org.xyl.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

class DB{
	
	private String userName="root";
	private String password="mysqladmin";
	private String host="localhost";
	private String encoding="utf-8";
	private String name="judgeonline";
	
	public DB(){}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

public class DBBackUpRevertTest {

	public final String BACKUP_COMMAND = "mysqldump";
	public final String ENCODING = "utf8";
	
	private DB db=new DB();
	
	@Test
	public void test(){
		//backup("D:\\mysql.sql");
		revert("D:\\mysql.sql");
	}

	public boolean backup(String file) {
		boolean isSuccess = true;
		try {
			Runtime rt = Runtime.getRuntime();
			String backupStr = this.getBackupStr();
			// logger.infoT(backupStr);
			Process process = rt.exec("cmd /c"+backupStr+file);

			/*BufferedReader br = new BufferedReader(new InputStreamReader(
					process.getInputStream(), ENCODING));

			String inStr = "";
			StringBuffer sb = new StringBuffer("");
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr).append("");
			}
			String outStr = sb.toString();

			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(file), ENCODING);
			writer.write(outStr);
			writer.flush();

			br.close();
			writer.close();*/
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	private String getBackupStr() {
		/*String backupStr = BACKUP_COMMAND + " -u" + db.getUserName() + " -p"
				+ db.getPassword() + " -h" + db.getHost() + " --set-charset="
				+ db.getEncoding() + " " + db.getName();*/
		// mysqldump judgeonline -uroot -pmysqladmin --default-character-set=gb2312 --result-file=
		String backupStr = BACKUP_COMMAND + " "+db.getName()+" -u" + db.getUserName() + " -p"
		+ db.getPassword() + " --default-character-set=gb2312 --result-file=";
		//String cmd ="mysqldump -h localhost -uroot -p1234 test blog_user > e:/mysql.sql"; //一定要加 -h localhost(或是服务器IP地址)   
		
		System.out.println(backupStr);
		return backupStr;
	}

	// MYSQL还原命令行：
	// SQL代码
	// mysql -hhostname -uusername -ppassword databasename < ‘backupfile’

	public final String REVERT_COMMAND = "mysql";

	public boolean revert(String file) {
		try {
			Runtime rt = Runtime.getRuntime();
			String revertStr = this.getRevertStr();
			
			System.out.println("mysqladmin -u root -pmysqladmin create judgeonline");
			System.out.println("cmd /c "+revertStr+" < "+file);
			
			//Process process = Runtime.getRuntime().exec(revertStr+" < "+file);
			Runtime.getRuntime().exec("mysqladmin -u root -pmysqladmin create judgeonline");
			Runtime.getRuntime().exec("cmd /c "+revertStr+" < "+"D:\\mysql.sql");
			/*String inStr;
			StringBuffer sb = new StringBuffer("");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), ENCODING));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr).append("");
			}
			String outStr = sb.toString();

			OutputStreamWriter writer = new OutputStreamWriter(process
					.getOutputStream(), ENCODING);
			writer.write(outStr);
			writer.flush();
			br.close();
			writer.close();*/
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private String getRevertStr() {
		/*String backupStr = REVERT_COMMAND + " -u" + db.getUserName() + " -p"
				+ db.getPassword() + " -h" + db.getHost() + " " + db.getName();
		*/
		String backupStr = REVERT_COMMAND + " -u" + db.getUserName() + " -p"
		+ db.getPassword() + " " + db.getName();
		System.out.println(backupStr);
		return backupStr;
	}

}

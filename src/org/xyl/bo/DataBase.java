package org.xyl.bo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.xyl.util.PropertiesUtil;

public class DataBase {

	private String userName;
	private String password;
	private String host;
	private String encoding;
	private String databaseName;
	private String file;
	
	public DataBase(){
		Properties prop = new Properties();
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			prop.load(in);
			userName = prop.getProperty("username");
			password = prop.getProperty("password");
			databaseName = prop.getProperty("databaseName");
			encoding = prop.getProperty("encoding");
			host="localhost";
			file=prop.getProperty("file");
			
			System.out.println(userName+"---"+password+"---"+databaseName+"---"+encoding+"--"+file);
			in.close();
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

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

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
}

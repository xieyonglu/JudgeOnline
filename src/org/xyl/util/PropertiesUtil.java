package org.xyl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil{
	
	/*private InputStream input=null;
	
	public PropertiesUtil(){}
	
	public PropertiesUtil(String fileName){
		input = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
	}
	
	public String getValue(String key){
		Properties prop = new Properties();
		try {
			prop.load(input);
			String value = prop.getProperty(key);
			in.close();
			return value;
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}*/
	
	public static String getPropertiesValue(String key) {
		Properties prop = new Properties();
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("session.properties");
		try {
			prop.load(in);
			String value = prop.getProperty(key);
			in.close();
			return value;
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

}

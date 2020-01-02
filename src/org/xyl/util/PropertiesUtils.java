package org.xyl.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {

	private static Properties jdbcProperties;
	private static Properties daoProperties;
	private static Map<String,Properties> maps=new HashMap<String, Properties>();
	
	
	public static Properties createProperties(String name){
		if(maps.get(name)!=null){
			return maps.get(name);
		}else{
			Properties properties=new Properties();
			try{
				properties.load(Properties.class.getClassLoader().
						getResourceAsStream("org/xyl/util/"+name+".properties"));
				
			}catch(IOException e){
				return null;
			}
			maps.put(name,properties);
			return properties;
		}
	}
	
	public static Properties createJDBCProperties(){
		if(jdbcProperties==null){
			jdbcProperties=new Properties();
			try{
				jdbcProperties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("org/xyl/util/jdbc.properties"));
			}catch(IOException e){
				e.printStackTrace();
			}
			return jdbcProperties;
		}else{
			return jdbcProperties;
		}
	}
	
	public static Properties createDaoProperties(){
		
		if(daoProperties==null){
			daoProperties=new Properties();
			try{
				daoProperties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("org/xyl/util/dao.properties"));
			}catch(IOException e){
				e.printStackTrace();
			}
			return daoProperties;
		}else{
			return daoProperties;
		}
	}
	
	
}






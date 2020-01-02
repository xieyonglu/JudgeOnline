package org.xyl.util;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.JsonParseException;

public class JsonJacksonUtil {

	private final static JsonJacksonUtil jjUtil=new JsonJacksonUtil();
	private final static JsonFactory jFactory=new JsonFactory();
	
	public static JsonJacksonUtil getInstance(){
		return jjUtil;
	}
	
	/**
	 * 把Object对象转换为Json对象
	 * @param obj
	 * @return
	 */
	public String objToJson(Object obj){
		//通过JsonFactory创建JsonGenerator，要传入相应的输出流
		StringWriter writer=new StringWriter();
		JsonGenerator jg=null;
		try{
			jg=jFactory.createJsonGenerator(writer);
			//创建对象mapper
			ObjectMapper mapper=new ObjectMapper();
			mapper.writeValue(jg,obj);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(jg!=null)jg.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return writer.getBuffer().toString();
	}
	
	/**
	 * 把Json对象装换为Object对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public Object jsonToObject(String json,Class clazz){
		ObjectMapper mapper=new ObjectMapper();
		Object obj=null;
		try{
			obj=mapper.readValue(json,clazz);
		}catch(JsonParseException e){
			e.printStackTrace();
		}catch(JsonMappingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return obj;
	}
	
	
	
}






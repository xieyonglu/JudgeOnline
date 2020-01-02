package org.xyl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class AuthUtil {

	public static boolean checkAuth(int roleId,Map<Integer,List<String>> auths,String path){
		List<String> reses=auths.get(roleId);
		Pattern pattern=null;
		for(String resReg:reses){
			resReg=resReg.replace("*",".*");
			resReg=resReg.trim();
			pattern=Pattern.compile(resReg);
			if(pattern.matcher(path).matches())
				return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer,List<String>> initAuth(){
		SAXReader reader=new SAXReader();
		Map<Integer,List<String>> auths=new HashMap<Integer,List<String>>();
		try{
			
			Document doc=reader.read(AuthUtil.class.getClassLoader().getResourceAsStream("auth.xml"));
			Element ele=doc.getRootElement();
			List<Element> eles=ele.selectNodes("role");
			List<String> reses=null;
			for(Element e:eles){
				reses=new ArrayList<String>();
				int roleId=Integer.parseInt(e.attributeValue("id"));
				auths.put(roleId,reses);
				addToRes(reses,e);
			}
		}catch(DocumentException e){
			e.printStackTrace();
		}finally{
			reader.resetHandlers();
			reader=null;
		}
		return auths;
	}
	
	
	public static void addToRes(List<String> reses,Element e){
		Element includeEle=(Element)e.selectSingleNode("include");
		if(includeEle!=null){
			Element includeResEle=(Element)e.selectSingleNode("/auto/role[name='"+includeEle.getTextTrim()+"']");
			addToRes(reses,includeResEle);
		}
		Element rele=(Element)e.selectSingleNode("resource");
		String resStr=rele.getTextTrim();
		String[] resStrs=resStr.split(",");
		reses.addAll(Arrays.asList(resStrs));
	}
	
}






















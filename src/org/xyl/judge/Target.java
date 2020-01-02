package org.xyl.judge;

import java.util.Vector;

import org.xyl.bean.TestData;

public class Target {
	
	public String targetId;
	public String fileName;
	public String filePath;
	public String fileExtension;
	public String objExtension;
	public String output;
	public String input;
	public long limitTime;
	public long limitMemory;
	public String code;//我添加
	public String flag;//我添加
	public Vector<TestData> testData;
	
	public Target(){
		//System.out.println("====target");
		fileName="";
		filePath="";
		fileExtension="";
		objExtension="";
		output="";
		input="";
		limitTime=10;
		limitMemory=10;
		targetId="";
		code="";
		flag="";
		testData=new Vector<TestData>();
	}
	
	public Target(String filePath,String fileName,String fileExtension,String objExtension,String input,String output){
		this.filePath=filePath;
		this.fileExtension=fileExtension;
		this.fileName=fileName;
		this.objExtension=objExtension;
		this.output=output;
		this.input=input;
		limitTime=10;
		limitMemory=10;
	}
	
	public void setTargetId(String targetId){
		this.targetId=targetId;
	}
	
	public void setLimit(long limitTime,long limitMemory){
		this.limitTime=limitTime;
		this.limitMemory=limitMemory;
	}

}



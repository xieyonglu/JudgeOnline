package org.xyl.judge;

import java.io.File;

import org.xyl.bean.TestData;
import org.xyl.action.SubmitAction;


public class Manager implements IManager {
	
	public Target target;
	public OutResult result;
	private Compiler compiler;
	private Executor executor;
	private Runtime runtime;
	private Controller controler;
	
	public Manager(Target target){
		
		this.target=target;
		runtime= Runtime.getRuntime();
		result=new OutResult();
		controler=new Controller(runtime,target);
		
		
		//根据目标文件的后缀名，初始化compiler和executor
		if(target.fileExtension.endsWith("cpp")){
			compiler=new Compiler("g++",target,runtime);
			executor=new Executor("","\""+target.filePath+"\"",target,runtime);			
		}else if(target.fileExtension.endsWith("c")){
			compiler=new Compiler("gcc",target,runtime);
			executor=new Executor("","\""+target.filePath+"\"",target,runtime);			
		}else if(target.fileExtension.endsWith("java")){
			compiler=new Compiler("Javac",target,runtime);
			executor=new Executor("Java","-cp .;\""+target.filePath+";\"",target,runtime);			
		}else{
			compiler=new Compiler("Javac",target,runtime);
			executor=new Executor("Java","-cp .;\""+target.filePath+";\"",target,runtime);
		}
		
		System.out.println("初始化-----"+result.getLanguage());
		
	}
	
	public void control(){
		OutResult or=new OutResult();
		
		if(target==null){
			System.out.println("no target file!");
			return;
		}
		
		//System.out.println(result.getState()+"---state---");//未编译
		
		if(!"rightAnswer".equals(result.getState())){
			result=compiler.compile();
		}
		
		System.out.println("----结果显示:"+result.getState()+"--"+result.getLanguage());//编译成功或失败，只有编译成功后才可以运行目标文件
		
		
		if("compileSuccess".equals(result.getState()) ||"rightAnswer".equals(result.getState())){//为多次评判结果服务
			System.out.println("begine control"+target.input+"---"+target.output);
			
			
			System.out.println("线程死活--"+controler.isAlive());
			//boolean flag=true;
			
			for(int i=0;i<target.testData.size();i++){
				
				TestData td=target.testData.get(i);
				target.input=td.getInput();
				target.output=td.getOutput();
				
				result=executor.execute(controler);
				System.out.println("每次运行后的结果:"+result);
				
				//根据每一次的结果设置，总结果的memory,time和state
				if(or.getMemory()<result.getMemory())
					or.setMemory(result.getMemory());
				if(or.getTime()<result.getTime())
					or.setTime(result.getTime());
				
				or.targetId=result.targetId;
				or.setState(result.getState());
				or.setLanguage(result.getLanguage());
				
				if(!result.getState().equals("rightAnswer")){
					or.setMemory(-1);
					or.setTime(-1);
					break;
				}
				
			}
			System.out.println("线程死活--"+controler.isAlive());
		}else{
			
			//编译错误
			System.out.println("结果为====:"+result.getState());
			or.targetId=result.targetId;
			or.setState(result.getState());
			or.setLanguage(result.getLanguage());
			or.setTime(-1);
			or.setMemory(-1);
			
			if(target.fileExtension.endsWith("cpp")){
				or.setLanguage("c++");	
			}else if(target.fileExtension.endsWith("c")){
				or.setLanguage("c");			
			}else if(target.fileExtension.endsWith("java")){
				or.setLanguage("java");	
			}
			
			//SubmitAction.saveOutResult(or,target.code);
		}
		
		//SubmitAction submitAction=new SubmitAction();
		SubmitAction.saveOutResult(or,target.code,target.flag);
		System.out.println("全部结束之后---------------");
		
		
		//创建用户文件目录，每一个用户拥有自己的文件目录
		File dir=new File(target.filePath);
		if(dir.exists()){
			deleteFile(dir);
		}
		System.out.println(dir.exists()+"----77777----"+target.filePath);
		
	}
	
	//删除file下所有的文件，包括file
	private void deleteFile(File file){
		if(file.isDirectory()){
			for(File f:file.listFiles()){
				System.out.println(f.delete());
			}
		}
		file.delete();
	}
	
	public void setCompilerName(String name){
		compiler.name=name;
	}
	
}








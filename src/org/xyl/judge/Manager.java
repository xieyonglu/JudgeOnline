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
		
		
		//����Ŀ���ļ��ĺ�׺������ʼ��compiler��executor
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
		
		System.out.println("��ʼ��-----"+result.getLanguage());
		
	}
	
	public void control(){
		OutResult or=new OutResult();
		
		if(target==null){
			System.out.println("no target file!");
			return;
		}
		
		//System.out.println(result.getState()+"---state---");//δ����
		
		if(!"rightAnswer".equals(result.getState())){
			result=compiler.compile();
		}
		
		System.out.println("----�����ʾ:"+result.getState()+"--"+result.getLanguage());//����ɹ���ʧ�ܣ�ֻ�б���ɹ���ſ�������Ŀ���ļ�
		
		
		if("compileSuccess".equals(result.getState()) ||"rightAnswer".equals(result.getState())){//Ϊ������н������
			System.out.println("begine control"+target.input+"---"+target.output);
			
			
			System.out.println("�߳�����--"+controler.isAlive());
			//boolean flag=true;
			
			for(int i=0;i<target.testData.size();i++){
				
				TestData td=target.testData.get(i);
				target.input=td.getInput();
				target.output=td.getOutput();
				
				result=executor.execute(controler);
				System.out.println("ÿ�����к�Ľ��:"+result);
				
				//����ÿһ�εĽ�����ã��ܽ����memory,time��state
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
			System.out.println("�߳�����--"+controler.isAlive());
		}else{
			
			//�������
			System.out.println("���Ϊ====:"+result.getState());
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
		System.out.println("ȫ������֮��---------------");
		
		
		//�����û��ļ�Ŀ¼��ÿһ���û�ӵ���Լ����ļ�Ŀ¼
		File dir=new File(target.filePath);
		if(dir.exists()){
			deleteFile(dir);
		}
		System.out.println(dir.exists()+"----77777----"+target.filePath);
		
	}
	
	//ɾ��file�����е��ļ�������file
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








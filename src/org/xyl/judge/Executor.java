package org.xyl.judge;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Executor {
	
	public String name;
	public String args;
	private Target target;
	private OutResult result;
	private Process process ;
	//private ProcessBuilder process;
	private Runtime rt;
 
	public Executor(String executorName, String args, Target target,Runtime rt) {
		this.name = executorName;
		this.args = args;
		this.target = target;
		result = new OutResult();
		result.setState("compileSuccess");
		result.targetId=target.targetId;
		this.rt=rt;
		process=null;
	}
	
	public Process getProcess(){
		if(process==null){
			System.out.println("process is null.");
		}
		System.out.println("get process");
		return process;
	}

	public OutResult execute(Controller controller) {
			
		try { 
			System.out.println("--begine excute--");
			
			//�����ļ��ĺ�׺����ִ����Ӧ��Ŀ���ļ�
			if(target.fileExtension.endsWith("cpp")){
				String str=args.substring(1,args.length()-1);
				process = rt.exec(name+" "+str+"\\"+target.fileName+".exe");//----����C++
				result.setLanguage("c++");
			}else if(target.fileExtension.endsWith("c")){
				String str=args.substring(1,args.length()-1);
				process = rt.exec(name+" "+str+"\\"+target.fileName+".exe");//----����C
				result.setLanguage("c");
			}else{
				process = rt.exec(name+" "+args+" "+target.fileName);//----����Java
				result.setLanguage("java");
			}
			
			
			controller.setProcess(process);
			controller.setOutResult(result);
			
			System.out.println(controller.isAlive()+"-"+controller.isDaemon()+"-"+controller.isInterrupted());
			if(!controller.isAlive())
				controller.start();
						
			redirectInputDataToProcess();
			redirectExecuteInfoToResult();
			
			if(!controller.isAlive())
				controller.interrupt();
			
			if(!isErro()){
				result.setState("runSuccess");
				if(checkAnswerFromTarget()){
					result.setState("rightAnswer");
					System.out.println("memory="+result.getMemory()+" time="+result.getTime()+" sate="+result.getState());
				}else{
					result.setState("wrongAnswer");
				}
			}else{
				//result.setState("limitOut");
			}
			System.out.println("--end excute--");
		} catch (IOException e) {
			// e.printStackTrace();
			result.setState("runError");
			e.printStackTrace();
			System.out.println("����" + e.getMessage());
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("����" + ex.getMessage());
		}
		System.out.println(" result="+result.getState());
		
		return result;
	}
	
	private void redirectInputDataToProcess() {
		// TODO Auto-generated method stub
		
		System.out.println("����----"+target.input);
		
		if(!"".equals(target.input)){
			OutputStream osStdOut=process.getOutputStream();
			try {
				osStdOut.write(target.input.getBytes());
				osStdOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private boolean checkAnswerFromTarget(){
		
		System.out.println("���м��1��"+target.output+"====="+result.outExecute);
		
		if(target.output.equals(result.outExecute)){
			System.out.println("equal");
			return true;
		}
		return false;
	}
	
	private boolean isErro() {
		String resultState=result.getState();
		if(("timeOut".equals(resultState)&&!"memoryOut".equals(resultState)) ){
			return true;
		}
		return false;
	}


	private void redirectExecuteInfoToResult(){
		InputStream isStdErro = process.getErrorStream();
		InputStream isStdIn = process.getInputStream();
		byte b[] = new byte[1024];
		
		try{
			int readBytes=isStdErro.read(b);
			if(readBytes<0)
				readBytes=0;
			String s=new String(b,0,readBytes);
			result.erroExecute=s;
			isStdErro.close();
			readBytes=isStdIn.read(b);
			
			System.out.println(readBytes+"============");
			
			
			readBytes-=2;  //������0d 0a
			if(readBytes<0)
				readBytes=0;
			String s1=new String(b,0,readBytes);
			result.outExecute=s1;
			
			isStdIn.close();	
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}

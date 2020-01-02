package org.xyl.judge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class JugeSystem extends Thread {
	
	private Map<String, Engine> engines;
	private Queue<Target> targets;
	private Queue<OutResult> results;
	private Queue<String> codes;
	private boolean isStart;
	private Engine engine;

	public JugeSystem(String engineType) {
		isStart = false;
		engines = new HashMap<String, Engine>();
		targets = new LinkedList<Target>();
		results = new LinkedList<OutResult>();
		codes=new LinkedList<String>();
		engines.put(engineType, EngineFactory.createEngine(engineType));
	}

	private void juge() {
		if (!targets.isEmpty()) {
			
			String engineName="";
			Target t;
			String code;
			
			synchronized(targets){
				t=(Target)targets.poll();
				code=codes.peek();
			}
			
			if(".java".equals(t.fileExtension)) {
				engineName = "Java";
			}else if(".c".equals(t.fileExtension)) {
				engineName = "C";
			}else if(".cpp".equals(t.fileExtension)) {
				engineName = "C++";
			}
			
			newInputFile(t,code);
			engine=engines.get(engineName);
						
			if(engine!=null){
				
				engine.target(t);
				engine.run();
				
				results.add(engine.getManager().result);
			}else{
				
				//�����������ύʱ�����׷���������󣿣����д����
				System.out.println("engineName--->>"+engineName);
				System.out.println("JugeSystem����------Cound not create engine.");
			}
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		if (isStart != true) {
			isStart = true;
		}
		if (isStart) {
			System.out.println("juge.......");
			int targetNumber = targets.size();
			
			try{
				while(true){
					if(targets.size()>0) juge();
					sleep(4000-targetNumber);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}

		}

	}
	
	private void newInputFile(Target target,String code){
		
		//�����û��ļ�Ŀ¼��ÿһ���û�ӵ���Լ����ļ�Ŀ¼
		File dir=new File(target.filePath);
		if(!dir.exists()){
			dir.mkdir();
		}
		System.out.println(dir.exists()+"--�����ļ���-"+target.filePath);
		
		
		File inputFile=new File(target.filePath+target.fileName+target.fileExtension);
		System.out.println(target.filePath+"--"+target.fileName+"--"+target.fileExtension);
		
		try{
			OutputStream os = new FileOutputStream(inputFile);
			os.write(code.getBytes());
			os.close();
		}catch(FileNotFoundException e){
			System.out.println("����"+e.getMessage());
		}catch(IOException e) {
			System.out.println("����"+e.getMessage());
		}
		
	}
	
	public void addEngine(String engineType) {
		engines.put(engineType, EngineFactory.createEngine(engineType));
	}

	public void delEngine(String engineType) {
		engines.remove(engineType);
	}

	public void clearAllEngine() {
		engines.clear();
	}

	public void addTarget(Target target) {
		targets.add(target);
	}

	public void delTarget(Target target) {
		targets.remove(target);
	}

	public void clearAllTarget() {
		targets.clear();
	}

	public OutResult getResult() {
		return results.poll();		//�Ƴ���ͷ
	}
	
	public String getCode() {
		return codes.poll();		//�Ƴ���ͷ
	}
	
	public void addCode(String code){
		codes.add(code);
	}
	
	public OutResult getHeadResult(){
		return results.peek();
	}
	
	public void delResult(OutResult result) {
		results.remove(result);
	}

	public void clearAllResult() {
		results.clear();
	}

	public int getEngineNumber() {
		return engines.size();
	}

	public int getTargetNumber() {
		return targets.size();
	}

	public int getResutlNumber() {
		return results.size();
	}
	
	
}




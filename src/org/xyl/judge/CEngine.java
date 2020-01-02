package org.xyl.judge;

public class CEngine extends Engine {
	
	private Manager cManager;
	
	public CEngine(){
	}
	
	public void run() {
		if(cManager==null){
			System.out.println("û��Ŀ���ļ���");
			return;
		}
		cManager.control();
	}
	
	public Manager getManager() {
		if(cManager==null){
			System.out.println("û��Ŀ���ļ���");
			return null;
		}
		return cManager;
	}
	
	public void target(Target target) {
		if(target==null){
			System.out.println("û��Ŀ���ļ�!");
			return;
		}
		cManager=new Manager(target);
	}
	
}

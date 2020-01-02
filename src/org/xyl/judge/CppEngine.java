package org.xyl.judge;

public class CppEngine extends Engine {


	private Manager cppManager;
	
	public CppEngine(){
	}
	
	public void run() {
		if(cppManager==null){
			System.out.println("û��Ŀ���ļ���");
			return;
		}
		cppManager.control();
	}
	
	public Manager getManager() {
		if(cppManager==null){
			System.out.println("û��Ŀ���ļ���");
			return null;
		}
		return cppManager;
	}
	
	public void target(Target target) {
		if(target==null){
			System.out.println("û��Ŀ���ļ�!");
			return;
		}
		cppManager=new Manager(target);
	}
	
}

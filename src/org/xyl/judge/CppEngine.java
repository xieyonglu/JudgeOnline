package org.xyl.judge;

public class CppEngine extends Engine {


	private Manager cppManager;
	
	public CppEngine(){
	}
	
	public void run() {
		if(cppManager==null){
			System.out.println("没有目标文件！");
			return;
		}
		cppManager.control();
	}
	
	public Manager getManager() {
		if(cppManager==null){
			System.out.println("没有目标文件！");
			return null;
		}
		return cppManager;
	}
	
	public void target(Target target) {
		if(target==null){
			System.out.println("没有目标文件!");
			return;
		}
		cppManager=new Manager(target);
	}
	
}

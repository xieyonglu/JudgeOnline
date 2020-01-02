package org.xyl.judge;

public class CEngine extends Engine {
	
	private Manager cManager;
	
	public CEngine(){
	}
	
	public void run() {
		if(cManager==null){
			System.out.println("没有目标文件！");
			return;
		}
		cManager.control();
	}
	
	public Manager getManager() {
		if(cManager==null){
			System.out.println("没有目标文件！");
			return null;
		}
		return cManager;
	}
	
	public void target(Target target) {
		if(target==null){
			System.out.println("没有目标文件!");
			return;
		}
		cManager=new Manager(target);
	}
	
}

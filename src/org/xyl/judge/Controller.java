package org.xyl.judge;

import java.util.Date;

public class Controller extends Thread {
	
	private long limitTime;
	private long limitMemory;
	private Runtime rt;
	private OutResult result;
	private Process process;

	public Controller(Runtime rt, Target t) {
		this.limitTime = t.limitTime;
		this.rt = rt;
		this.limitMemory = t.limitMemory;
		process = null;
	}

	public void run() {
		
		System.out.println("测试time与memory---"+this.limitTime+"=="+this.limitMemory);
		
		long oldTime=new Date().getTime();
		long oldMemory=rt.freeMemory();
		limitTime=limitTime+oldTime;
		limitMemory=oldMemory-limitMemory;
		long nowTime;
		
		try {
			while (true) {
				
				nowTime=new Date().getTime();
				System.out.println("nowTime=" + nowTime+" limitTime="+limitTime);
				System.out.println("freeMemory=" + rt.freeMemory()+" limitMemrory="+limitMemory);
				System.out.println((nowTime-limitTime)+"==="+(rt.freeMemory()-limitMemory));
				
				if(nowTime>limitTime||rt.freeMemory()<limitMemory){
					if(process==null){
						System.out.println("process is null");
						return;
					}else{
						process.destroy();
						process=null;
						if(nowTime>(limitTime)){
							result.setState("timeOut");
						}else{
							result.setState("memoryOut");
						}
						//result.setTime(-1);
						//result.setMemory(-1);
						System.out.println("limitOut");
						return;
					}
				}else{
					System.out.println("check time and memory.");
				}
				result.setTime(nowTime-oldTime);
				//result.setMemory(rt.totalMemory()-rt.freeMemory());
				result.setMemory(oldMemory-rt.freeMemory());
				sleep(1000);
			}

		}catch(IllegalThreadStateException e){
			e.printStackTrace();
			System.out.println("进程尚未退出！");
			return;

		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("程序" + ex.getMessage());
			return;
		}

	}
	
	public void setOutResult(OutResult result) {
		this.result = result;
	}

	public String getResultState() {
		return result.getState();
	}

	public void setTarget(Target t) {
		this.limitTime = t.limitTime;
		this.limitMemory = t.limitMemory;
	}

	public void setProcess(Process p) {
		process = p;
	}

}




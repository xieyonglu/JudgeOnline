package org.xyl.judge;

public class OutResult {
	
	public String targetId;
	public String outCompile;
	public String outExecute;
	public String erroCompile;
	public String erroExecute;
	public String language;
	
	private long memory;
	private long time;
	private String state;
	
	public OutResult(){
		state="Œ¥±‡“Î";
		memory=0;
		time=0;
		outCompile=null;
		outExecute=null;
		erroCompile=null;
		erroExecute=null;
	}
	
	public String toString(){
		return "targetId:"+targetId+"\toutCompile:"+outCompile+"\toutExecute:"+outExecute+"\terroCompile:"+erroCompile+"\terroExecute:"+erroExecute+"\tmomory:"+memory+"\ttime:"+time+"\tstate:"+state;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public long getMemory() {
		return memory;
	}
	
	public void setMemory(long memory) {
		this.memory = memory;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
}

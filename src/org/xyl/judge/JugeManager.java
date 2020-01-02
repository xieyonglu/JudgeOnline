package org.xyl.judge;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.iservice.ITestDataService;
import org.xyl.judge.JugeSystem;
import org.xyl.judge.Target;
import org.xyl.util.PropertiesUtil;
import org.xyl.bean.Problem;
import org.xyl.bean.TestData;
import org.xyl.bean.User;

@Controller("jugeManager")
@Scope("prototype")
public class JugeManager{
	
	@Resource ITestDataService testDataService;
	
	private JugeSystem system = null; 
	private HttpServletRequest request;
	
	public JugeManager(){}

	public void setJugeSystem(JugeSystem system) {
		this.system = system;
	}

	public void addJuge(String engineName,Problem problem, User user, String code,String flag,String fileName) {
		
		try {
	
			String filePath=PropertiesUtil.getPropertiesValue("filePath");
			String problemId=problem.getProblemId()+"";
			String userId=user.getUserId()+"";
			
			File file=new File(filePath);//加入路径文件不存在，建立一个路径文件
			if(!file.exists()){
				file.mkdirs();
			}
			
			Target target=new Target();
			target.fileName="Main";//文件名即为用户ID_试题ID
			target.targetId=problemId+"_"+userId;
			target.filePath=filePath+fileName+"\\";//每一个用户在自己的路径下运行程序
			target.code=code;
			target.flag=flag;
			
			//根据engineName初始化target
			if(engineName.equals("java")){
				target.fileExtension = ".java";
				target.objExtension = ".class";
			}else if(engineName.equals("c")){
				target.fileExtension = ".c";
				target.objExtension = ".exe";
			}else if(engineName.equals("cpp")){
				target.fileExtension = ".cpp";
				target.objExtension = ".exe";
			}
			
			//Set<TestData> ts=problem.getTestDatas();
			List<TestData> ts=testDataService.findByProblemId(problem.getProblemId());
			Vector<TestData> tests=new Vector<TestData>();
			Iterator<TestData> iter=ts.iterator();
			while(iter.hasNext()){
				tests.add(iter.next());
			}
			System.out.println("待测试的结果共有个数："+tests.size());
			
			target.testData=tests;
			
			//得到该试题的时间限制和内存限制
			String limit[]={problem.getTimeLimit(),problem.getMemoryLimit()};
			if(limit!=null){
				target.setLimit(Long.valueOf(limit[0]),Long.valueOf(limit[1])); //时间和内存限定
				System.out.println(limit[0]+" "+limit[1]);
			}else{
				target.setLimit(6000, 10000);	//默认时间和内存限定 
			}
			
			system.addTarget(target);
			system.addCode(code);
			
			System.out.println("结果处理==》》"+system.getResutlNumber());
			if (system.getResutlNumber() >= 1) {
				setJugeResult();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " add JugeManager");
		}
	}

	//处理提交以后的结果----查看提交以后的结果
	public void setJugeResult() {
		System.out.println("结果提交到数据库");
		try {
			OutResult result = system.getResult();
			System.out.println("结果的ID--->>>"+result.targetId);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " 提交到数据库");
		}
	}

	
}

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
			
			File file=new File(filePath);//����·���ļ������ڣ�����һ��·���ļ�
			if(!file.exists()){
				file.mkdirs();
			}
			
			Target target=new Target();
			target.fileName="Main";//�ļ�����Ϊ�û�ID_����ID
			target.targetId=problemId+"_"+userId;
			target.filePath=filePath+fileName+"\\";//ÿһ���û����Լ���·�������г���
			target.code=code;
			target.flag=flag;
			
			//����engineName��ʼ��target
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
			System.out.println("�����ԵĽ�����и�����"+tests.size());
			
			target.testData=tests;
			
			//�õ��������ʱ�����ƺ��ڴ�����
			String limit[]={problem.getTimeLimit(),problem.getMemoryLimit()};
			if(limit!=null){
				target.setLimit(Long.valueOf(limit[0]),Long.valueOf(limit[1])); //ʱ����ڴ��޶�
				System.out.println(limit[0]+" "+limit[1]);
			}else{
				target.setLimit(6000, 10000);	//Ĭ��ʱ����ڴ��޶� 
			}
			
			system.addTarget(target);
			system.addCode(code);
			
			System.out.println("�������==����"+system.getResutlNumber());
			if (system.getResutlNumber() >= 1) {
				setJugeResult();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " add JugeManager");
		}
	}

	//�����ύ�Ժ�Ľ��----�鿴�ύ�Ժ�Ľ��
	public void setJugeResult() {
		System.out.println("����ύ�����ݿ�");
		try {
			OutResult result = system.getResult();
			System.out.println("�����ID--->>>"+result.targetId);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " �ύ�����ݿ�");
		}
	}

	
}

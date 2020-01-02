package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.xyl.bean.Problem;
import org.xyl.bean.TestData;
import org.xyl.iservice.IProblemService;
import org.xyl.iservice.ITestDataService;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("testDataAction")
@Scope("prototype")
public class TestDataAction extends ActionSupport{

	@Resource private ITestDataService testDataService;
	@Resource private IProblemService problemService;
	private TestData testData;
	private Problem problem;
	
	private PageBean<TestData> pageBean;
	private String condition1;
	private String condition2;
	

	//添加测试用例校验
	public void validateInsertTestData(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		problem=problemService.load(problemId);
		
		String input=testData.getInput();
		String output=testData.getOutput();
		if(input==null||!(input.trim().length()>0&&input.trim().length()<=500)){
			this.addFieldError("testData.input","测试输入格式不正确，必须为1~500之间！");
		}
		if(output==null||!(output.trim().length()>0&&output.trim().length()<=500)){
			this.addFieldError("testData.output","测试输出格式不正确，必须为1~500之间！");
		}
		
		ActionContext.getContext().put("urlAction", "testData/insert_testData.jsp");
	}
	
	//添加测试用例
	public String insertTestData(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		problem=problemService.load(problemId);
		
		testData.setProblem(problem);
		
		testDataService.addTestData(testData);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	public String deleteTestData(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long testDataId=Long.parseLong(request.getParameter("testDataId"));
		testDataService.deleteTestDataById(testDataId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateTestData(){
		String input=testData.getInput();
		String output=testData.getOutput();
		if(input==null||!(input.trim().length()>0&&input.trim().length()<=500)){
			this.addFieldError("testData.input","测试输入格式不正确，必须为1~500之间！");
		}
		if(output==null||!(output.trim().length()>0&&output.trim().length()<=500)){
			this.addFieldError("testData.output","测试输出格式不正确，必须为1~500之间！");
		}
		
		ActionContext.getContext().put("urlAction", "testData/update_testData.jsp");
	}
	
	//修改管理员信息
	public String updateTestData(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		problem=problemService.load(problemId);
		
		testData.setProblem(problem);
		
		testDataService.updateTestData(testData);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个测试用例
	public String showTestData(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long testDataId=Long.parseLong(request.getParameter("testDataId"));
		String action=request.getParameter("action");
		testData=testDataService.load(testDataId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","testData/update_testData.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","testData/show_testData.jsp");
		}
		
		return Action.SUCCESS;
	}

	//分页显示用户信息
	public String pageTestData(){
		
		System.out.println("===================pageTestData------------------");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		problem=problemService.load(problemId);
		
		try{
			if(condition1!=null)condition1=new String(condition1.getBytes("ISO-8859-1"));
			if(condition2!=null)condition2=new String(condition2.getBytes("ISO-8859-1"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		System.out.println(pageIndex+"---------"+pageSize);
		
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		System.out.println(condition1+"----"+condition2+"---"+pageIndex+"---"+pageSize);
		String hql="from TestData as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		hql+=" and obj.problem.problemId='"+problemId+"' order by obj.createDate desc";
		System.out.println("hql===="+hql);
		
		//int offset,length;
		//offset=pageBean.getCurrentPage();
		//length=pageBean.getPageSize();
		pageBean=testDataService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<TestData> list=pageBean.getList();
		//pageBean.init();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		ActionContext.getContext().put("urlAction","testData/list_testData.jsp");
		return Action.SUCCESS;
	}
	
	//批量删除
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			testDataService.deleteTestDataById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	

	public PageBean<TestData> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<TestData> pageBean) {
		this.pageBean = pageBean;
	}

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}
	
	public TestData getTestData() {
		return testData;
	}

	public void setTestData(TestData testData) {
		this.testData = testData;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	
}

package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Code;
import org.xyl.bean.Match;
import org.xyl.bean.Problem;
import org.xyl.bean.User;
import org.xyl.iservice.ICodeService;
import org.xyl.iservice.IMatchService;
import org.xyl.iservice.IProblemService;
import org.xyl.iservice.IUserService;
import org.xyl.util.Escape;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("codeAction")
@Scope("prototype")
public class CodeAction extends ActionSupport{

	@Resource private ICodeService codeService;
	@Resource private IProblemService problemService;
	@Resource private IMatchService matchService;
	@Resource private IUserService userService;
	
	private Code code;
	private User user;
	private Problem problem;
	private Match match;
	
	private PageBean<Code> pageBean;
	private String condition1;
	private String condition2;
	
	private String conditionA;
	private String conditionB;
	private String conditionC;
	private String conditionD;
	
	
	public void validateInsertCode(){
		
		//ActionContext.getContext().put("urlAction", "manager/insertAdmin.jsp");
	}
	
	//插入一条管理员信息
	public String insertCode(){
		codeService.addCode(code);
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//删除指定Id的管理员
	public String deleteCode(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long codeId=Long.parseLong(request.getParameter("codeId"));
		codeService.deleteCodeById(codeId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateCode(){
		//ActionContext.getContext().put("urlAction", "admin/updateAdmin.jsp");
	}
	
	//修改管理员信息
	public String updateCode(){
		codeService.updateCode(code);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个用户
	public String showCode(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long codeId=Long.parseLong(request.getParameter("codeId"));
		
		String action=request.getParameter("action");
		code=codeService.load(codeId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","code/update_code.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","code/show_code.jsp");
		}
		
		return Action.SUCCESS;
	}

	//分页显示用户运行
	public String pageCode(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		String type=request.getParameter("type");
		String userId=request.getParameter("userId");
		String problemId=request.getParameter("problemId");
		String matchId=request.getParameter("matchId");
		
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
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
		String hql="from Code as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" as u where u."+condition1+" like '%"+condition2+"%'";
			}
		}
		
		//根据problemId，查找某一道试题的所有运行结果
		if(problemId!=null&&problemId.trim().length()>0){
			hql=hql+" and obj.problem.problemId='"+Long.parseLong(problemId)+"'";
			problem=problemService.load(Long.parseLong(problemId));
		}
		
		//根据problemId，查找某一道试题的所有运行结果
		if(matchId!=null&&matchId.trim().length()>0){
			hql=hql+" and obj.match.matchId='"+Long.parseLong(matchId)+"'";
			match=matchService.load(Long.parseLong(matchId));
		}else{
			hql=hql+" and obj.match.matchId is null";
		}
		
		//根据userId，查找某一道试题的所有运行结果
		if(userId!=null&&userId.trim().length()>0){
			hql=hql+" and obj.user.userId='"+Long.parseLong(userId)+"'";
			user=userService.load(Long.parseLong(userId));
		}
		
		if("sort".equals(type)){
			hql=hql+" and obj.state='rightAnswer' order by createDate asc,memory asc,codeLength asc";
		}else{
			hql=hql+" order by obj.createDate desc";
		}
		
		System.out.println("hql===="+hql);
		
		pageBean=codeService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Code> list=pageBean.getList();

		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		
		if("all".equals(action)){
			
			if(problemId!=null&&problemId.trim().length()>0){//这一道题的所有运行结果
				if("sort".equals(type)){//这一道题的排行
					ActionContext.getContext().put("urlAction","code/list_code_all_problem_sort.jsp");
				}else{
					ActionContext.getContext().put("urlAction","code/list_code_all_problem.jsp");
				}
				
			}else{
				ActionContext.getContext().put("urlAction","code/list_code_all.jsp");
			}
				
		}else if("admin".equals(action)){
			
			if(problemId!=null&&problemId.trim().length()>0){
				ActionContext.getContext().put("urlAction","code/list_code_admin_problem.jsp");
			}else if(matchId!=null&&matchId.trim().length()>0){
				ActionContext.getContext().put("urlAction","code/list_code_admin_match.jsp");
			}else{
				ActionContext.getContext().put("urlAction","code/list_code_admin.jsp");
			}
			
		}else if("user".equals(action)){
			
			if(problemId!=null&&problemId.trim().length()>0){
				ActionContext.getContext().put("urlAction","code/list_code_user_problem.jsp");
			}else if(matchId!=null&&matchId.trim().length()>0){
				ActionContext.getContext().put("urlAction","code/list_code_user_match.jsp");
			}else{
				ActionContext.getContext().put("urlAction","code/list_code_user.jsp");
			}
			
		}
		
		return Action.SUCCESS;
	}
	
	//分页显示带查询的用户运行
	public String pageFindCode(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		String type=request.getParameter("type");
		String userId=request.getParameter("userId");
		String problemId=request.getParameter("problemId");
		String matchId=request.getParameter("matchId");
		
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		
		System.out.println(pageIndex+"----------"+pageSize);
		
		try{
			if(condition1!=null)condition1=new String(condition1.getBytes("ISO-8859-1"));
			if(condition2!=null)condition2=new String(condition2.getBytes("ISO-8859-1"));
			
			if(conditionA!=null)conditionA=new String(conditionA.getBytes("ISO-8859-1"));
			if(conditionB!=null)conditionB=new String(conditionB.getBytes("ISO-8859-1"));
			if(conditionC!=null)conditionC=new String(conditionC.getBytes("ISO-8859-1"));
			if(conditionD!=null)conditionD=new String(conditionD.getBytes("ISO-8859-1"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
				
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		String hql="from Code as obj where 1=1";
		
		if(conditionA!=null&&conditionA.trim().length()>0){
			hql+=" and obj.problem.title like'%"+conditionA+"%'";
		}
		if(conditionB!=null&&conditionB.trim().length()>0){
			hql+=" and obj.user.userName like'%"+conditionB+"%'";
		}
		if(conditionC!=null&&conditionC.trim().length()>0&&!"all".equals(conditionC)){
			if("cpp".equals(conditionC))conditionC="c++";
			hql+=" and obj.language='"+conditionC+"'";
		}
		if(conditionD!=null&&conditionD.trim().length()>0&&!"all".equals(conditionD)){
			hql+=" and obj.state='"+conditionD+"'";
		}
		System.out.println(conditionA+"=="+conditionB+"=="+conditionC+"=="+conditionD+"==");
		
		//根据problemId，查找某一道试题的所有运行结果
		if(problemId!=null&&problemId.trim().length()>0){
			hql=hql+" and obj.problem.problemId='"+Long.parseLong(problemId)+"'";
			problem=problemService.load(Long.parseLong(problemId));
		}
		
		//根据problemId，查找某一道试题的所有运行结果
		if(matchId!=null&&matchId.trim().length()>0){
			hql=hql+" and obj.match.matchId='"+Long.parseLong(matchId)+"'";
			match=matchService.load(Long.parseLong(matchId));
		}else{
			hql=hql+" and obj.match.matchId is null";
		}
		
		//根据userId，查找某一道试题的所有运行结果
		if(userId!=null&&userId.trim().length()>0){
			hql=hql+" and obj.user.userId='"+Long.parseLong(userId)+"'";
			user=userService.load(Long.parseLong(userId));
		}
		
		if("sort".equals(type)){
			hql=hql+" and obj.state='rightAnswer' order by runtime asc,memory asc,codeLength asc,createDate desc";
		}else{
			hql=hql+" order by obj.createDate desc";
		}
		
		System.out.println("hql===="+hql);
		
		pageBean=codeService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Code> list=pageBean.getList();

		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		
		if("all".equals(action)){
			
			if(problemId!=null&&problemId.trim().length()>0){//这一道题的所有运行结果
				if(matchId!=null&&matchId.trim().length()>0){
					if("sort".equals(type)){//这一道题的排行
						
						System.out.println("sort----------------------");
						ActionContext.getContext().put("urlAction","code/list_code_all_problem_match_sort.jsp");
					}else{
						
						System.out.println("match---problem----------------------");
						ActionContext.getContext().put("urlAction","code/list_code_all_problem_match.jsp");
					}
				}else{
					if("sort".equals(type)){//这一道题的排行
						
						System.out.println("sort----------------------");
						ActionContext.getContext().put("urlAction","code/list_code_all_problem_sort.jsp");
					}else{
					
						ActionContext.getContext().put("urlAction","code/list_code_all_problem.jsp");
					}
				}
			}else if(matchId!=null&&matchId.trim().length()>0){//这一场比赛的所有运行结果
				if("sort".equals(type)){//这一道题的排行
					ActionContext.getContext().put("urlAction","code/list_code_all_match_sort.jsp");
				}else{
					ActionContext.getContext().put("urlAction","code/list_code_all_match.jsp");
				}
			}else{
				ActionContext.getContext().put("urlAction","code/list_code_all.jsp");
			}
				
		}else if("admin".equals(action)){
			
			if(problemId!=null&&problemId.trim().length()>0){
				
				if(matchId!=null&&matchId.trim().length()>0){
					ActionContext.getContext().put("urlAction","code/list_code_admin_problem_match.jsp");
				}else{
					ActionContext.getContext().put("urlAction","code/list_code_admin_problem.jsp");
				}
				
			}else if(matchId!=null&&matchId.trim().length()>0){
				ActionContext.getContext().put("urlAction","code/list_code_admin_match.jsp");
			}else{
				ActionContext.getContext().put("urlAction","code/list_code_admin.jsp");
			}
			
		}else if("user".equals(action)){
			
			if(problemId!=null&&problemId.trim().length()>0){
				ActionContext.getContext().put("urlAction","code/list_code_user_problem.jsp");
			}else if(matchId!=null&&matchId.trim().length()>0){
				ActionContext.getContext().put("urlAction","code/list_code_user_match.jsp");
			}else{
				ActionContext.getContext().put("urlAction","code/list_code_user.jsp");
			}
			
		}
		
		
		System.out.println(ActionContext.getContext().get("urlAction")+"-------------------");
		return Action.SUCCESS;
	}
	
	//批量删除
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			codeService.deleteCodeById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	

	public PageBean<Code> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Code> pageBean) {
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

	public String getConditionA() {
		return conditionA;
	}

	public void setConditionA(String conditionA) {
		this.conditionA = conditionA;
	}

	public String getConditionB() {
		return conditionB;
	}

	public void setConditionB(String conditionB) {
		this.conditionB = conditionB;
	}

	public String getConditionC() {
		return conditionC;
	}

	public void setConditionC(String conditionC) {
		this.conditionC = conditionC;
	}

	public String getConditionD() {
		return conditionD;
	}

	public void setConditionD(String conditionD) {
		this.conditionD = conditionD;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}

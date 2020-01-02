package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Admin;
import org.xyl.bean.Match;
import org.xyl.bean.Problem;
import org.xyl.bean.Type;
import org.xyl.iservice.IAdminService;
import org.xyl.iservice.IMatchService;
import org.xyl.iservice.IProblemService;
import org.xyl.iservice.ITypeService;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("problemAction")
@Scope("prototype")
public class ProblemAction extends ActionSupport{

	@Resource private IProblemService problemService;
	@Resource private ITypeService typeService;
	@Resource private IAdminService adminService;
	@Resource private IMatchService matchService;
	
	private Problem problem;
	private Type type;
	private Admin admin;
	private Match match;
	
	private PageBean<Problem> pageBean;
	private String condition1;
	private String condition2;
	
//////////////////////////////////////
	private String typeName;
	public String getTypeName() {return typeName;}
	public void setTypeName(String typeName) {this.typeName = typeName;}
//////////////////////////////////////
	
	
	//��������ǰУ��
	public void validateInsertProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String hardFactor=problem.getHardFactor()+"";
		//String typeName=request.getParameter("typeName");
		
		System.out.println(problem.getTitle()+"-------");
		Problem pro=problemService.loadByTitle(problem.getTitle());
		
		if(problem.getTitle()!=null&&!(problem.getTitle().trim().length()>0&&problem.getTitle().trim().length()<=50)){
			this.addFieldError("problem.title", "��������ʽ�����ұ�����1~50֮�䣡");
		}
		if(problem.getSource()!=null&&!(problem.getSource().trim().length()>0&&problem.getSource().trim().length()<=20)){
			this.addFieldError("problem.source", "������Դ��ʽ�����ұ�����1~20֮�䣡");
		}
		String timeLimit=problem.getTimeLimit();
		
		if(timeLimit!=null&&(!(timeLimit.trim().length()>0&&timeLimit.trim().length()<=20)||!timeLimit.matches("\\d+"))){
			this.addFieldError("problem.timeLimit", "����ʱ�����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}
		String memoryLimit=problem.getMemoryLimit();
		if(memoryLimit!=null&&(!(memoryLimit.trim().length()>0&&memoryLimit.trim().length()<=20)||!memoryLimit.matches("\\d+"))){
			this.addFieldError("problem.memoryLimit", "�����ڴ����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}
		
		if(pro!=null){
			this.addFieldError("problem.title", "�Ѿ����ڸñ��⣡");
		}
		
		if("null".equals(hardFactor)||hardFactor==null||hardFactor.trim().length()==0){
			this.addFieldError("problem.hardFactor", "��ѡ��һ�������Ѷȣ�");
		}
		
		if("--��ѡ��--".equals(typeName)||typeName==null||typeName.trim().length()==0){
			this.addFieldError("problem.typeName", "��ѡ��һ�����");
		}
		
		System.out.println(hardFactor+"---"+typeName);
		
		ActionContext.getContext().put("urlAction", "problem/insert_problem.jsp");
	}
	
	//����һ��������Ϣ
	public String insertProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		Long typeId=Long.parseLong(request.getParameter("typeName"));
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		
		type=typeService.load(typeId);
		//admin=adminService.load(adminId);
		admin=(Admin)ActionContext.getContext().getSession().get("loginAdmin");
		
		System.out.println(type+"---"+typeId+"---"+admin);
		
		type.setProblemCount(type.getProblemCount()+1);
		problem.setTypeName(type.getTypeName());
		problem.setAuthor(admin.getUserName());
		problem.setType(type);
		problem.setAdmin(admin);
		problem.setMatch(null);//�Ǳ������⣬��Ϊnull
	
		
		typeService.updateType(type);
		problemService.addProblem(problem);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//��������ǰУ��
	public void validateInsertMatchProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long matchId=Long.parseLong(request.getParameter("match.matchId"));
		
		String hardFactor=problem.getHardFactor()+"";
		String typeName=request.getParameter("typeName");
		Integer sequence=problem.getSequence();
		
		//match=matchService.load(matchId);
		//ActionContext.getContext().put("match",match);
		
		
		if(problem.getTitle()!=null&&!(problem.getTitle().trim().length()>0&&problem.getTitle().trim().length()<=50)){
			this.addFieldError("problem.title", "��������ʽ�����ұ�����1~50֮�䣡");
		}
		if(problem.getSource()!=null&&!(problem.getSource().trim().length()>0&&problem.getSource().trim().length()<=20)){
			this.addFieldError("problem.source", "������Դ��ʽ�����ұ�����1~20֮�䣡");
		}
		String timeLimit=problem.getTimeLimit();
		
		if(timeLimit!=null&&(!(timeLimit.trim().length()>0&&timeLimit.trim().length()<=20)||!timeLimit.matches("\\d+"))){
			this.addFieldError("problem.timeLimit", "����ʱ�����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}
		String memoryLimit=problem.getMemoryLimit();
		if(memoryLimit!=null&&(!(memoryLimit.trim().length()>0&&memoryLimit.trim().length()<=20)||!memoryLimit.matches("\\d+"))){
			this.addFieldError("problem.memoryLimit", "�����ڴ����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}
		
		
		System.out.println(problem.getTitle()+"-------");
		Problem pro=problemService.loadByTitleAndMatch(problem.getTitle(), matchId);
		if(pro!=null){
			this.addFieldError("problem.title", "�������Ѿ����ڸñ��⣡");
		}
		
		if(sequence==0){
			this.addFieldError("problem.sequence", "��ѡ��������ţ�");
		}else{
			Problem p=problemService.loadBySequenceAndMatch(sequence, matchId);
			if(p!=null){
				this.addFieldError("problem.sequence", "�����������Ѿ����ڸ���ţ�");
			}
		}
		
		if("null".equals(hardFactor)||hardFactor==null||hardFactor.trim().length()==0){
			this.addFieldError("problem.hardFactor", "��ѡ��һ�������Ѷȣ�");
		}
		
		if("--��ѡ��--".equals(typeName)||typeName==null||typeName.trim().length()==0){
			this.addFieldError("problem.typeName", "��ѡ��һ�����");
		}
		
		System.out.println(hardFactor+"---"+typeName);
		
		ActionContext.getContext().put("urlAction", "problem/insert_problem_match.jsp");
	}
	
	//����һ��������Ϣ
	public String insertMatchProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long typeId=Long.parseLong(request.getParameter("typeName"));
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		Long matchId=Long.parseLong(request.getParameter("match.matchId"));
		
		type=typeService.load(typeId);
		admin=(Admin)ActionContext.getContext().getSession().get("loginAdmin");
		
		System.out.println(admin+"====================");
		
		match=matchService.load(matchId);
		match.setProblemCount(match.getProblemCount()+1);
		
		System.out.println(type+"---"+typeId+"---"+admin);
		
		//type.setProblemCount(type.getProblemCount()+1);
		//problem.setType(type);
		//problem.setAdmin(admin);
		System.out.println(match.getMatchId()+"==================***");
		problem.setTypeName(type.getTypeName());
		problem.setAuthor(admin.getUserName());
		problem.setMatch(match);
	
		
		matchService.updateMatch(match);
		problemService.addProblem(problem);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//ɾ��ָ��Id������
	public String deleteProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		
		problem=problemService.load(problemId);
		type=typeService.load(problem.getType().getTypeId());
		
		type.setProblemCount(type.getProblemCount()-1);
		
		typeService.updateType(type);
		problemService.deleteProblemById(problemId);
		
		System.out.println("ɾ������ɹ�������");
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//ɾ��ָ��Id�ı�������
	public String deleteMatchProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		Long matchId=Long.parseLong(request.getParameter("matchId"));
		
		match=matchService.load(matchId);
		match.setProblemCount(match.getProblemCount()-1);	
		
		problemService.deleteProblemById(problemId);
		matchService.updateMatch(match);
		
		System.out.println("ɾ������ɹ�������");
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//����ĳһ������
	public String findProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		problem=problemService.load(problemId);
		
		System.out.println("==="+action+"---"+problemId);
		
		if("show".equals(action)){
			ActionContext.getContext().put("urlAction", "problem/show_problem.jsp");
		}else if("update".equals(action)){
			ActionContext.getContext().put("urlAction", "problem/update_problem.jsp");
		}else if("testData".equals(action)){
			ActionContext.getContext().put("urlAction", "testData/insert_testData.jsp");
		}else if("all".equals(action)){
			ActionContext.getContext().put("urlAction", "problem/show_problem_all.jsp");
		}else if("match".equals(action)){
			type=typeService.loadByTypeName(problem.getTypeName());
			ActionContext.getContext().put("urlAction", "problem/update_problem_match.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	//����ĳһ�α�����ĳһ������
	public String findMatchProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		Long problemId=Long.parseLong(request.getParameter("problemId"));
		Long matchId=Long.parseLong(request.getParameter("matchId"));
		
		problem=problemService.load(problemId);
		match=matchService.load(matchId);
		
		System.out.println("==="+action+"---"+problemId);
		
		if("all".equals(action)){
			ActionContext.getContext().put("urlAction", "problem/show_problem_match_all.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	public void validateUpdateProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		//Long typeId=Long.parseLong(request.getParameter("typeId"));
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		//admin=adminService.load(adminId);
		//type=typeService.load(typeId);
		//problem.setAdmin(admin);
		//problem.setType(type);
		
		
		String hardFactor=problem.getHardFactor()+"";
		String typeName=request.getParameter("typeName");
		
		if(problem.getTitle()!=null&&!(problem.getTitle().trim().length()>0&&problem.getTitle().trim().length()<=50)){
			this.addFieldError("problem.title", "��������ʽ�����ұ�����1~50֮�䣡");
		}
		if(problem.getSource()!=null&&!(problem.getSource().trim().length()>0&&problem.getSource().trim().length()<=20)){
			this.addFieldError("problem.source", "������Դ��ʽ�����ұ�����1~20֮�䣡");
		}
		
		String timeLimit=problem.getTimeLimit();
		if(timeLimit!=null&&(!(timeLimit.trim().length()>0&&timeLimit.trim().length()<=20)||!timeLimit.matches("\\d+"))){
			this.addFieldError("problem.timeLimit", "����ʱ�����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}
		String memoryLimit=problem.getMemoryLimit();
		if(memoryLimit!=null&&(!(memoryLimit.trim().length()>0&&memoryLimit.trim().length()<=20)||!memoryLimit.matches("\\d+"))){
			this.addFieldError("problem.memoryLimit", "�����ڴ����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}	
		
		System.out.println(problem.getTitle()+"-------");
		Problem pro=problemService.loadByTitle(problem.getTitle());
		if(pro!=null&&pro.getProblemId()!=problem.getProblemId()){
			this.addFieldError("problem.title", "�Ѿ����ڸñ��⣡");
		}
		
		if("null".equals(hardFactor)||hardFactor==null||hardFactor.trim().length()==0){
			this.addFieldError("problem.hardFactor", "��ѡ��һ�������Ѷȣ�");
		}
		
		if("--��ѡ��--".equals(typeName)||typeName==null||typeName.trim().length()==0){
			this.addFieldError("problem.typeName", "��ѡ��һ�����");
		}
		
		System.out.println(hardFactor+"---"+typeName);
		ActionContext.getContext().put("urlAction", "problem/update_problem.jsp");
	}
	
	//�޸�ĳһ��������Ϣ
	public String updateProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long newTypeId=Long.parseLong(request.getParameter("typeName"));
		//Long oldTypeId=Long.parseLong(request.getParameter("typeId"));
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		
		Type oldType=problemService.load(problem.getProblemId()).getType();//typeService.load(oldTypeId);
		Type newType=typeService.load(newTypeId);
		admin=problemService.load(problem.getProblemId()).getAdmin();
		
		System.out.println(oldType.getTypeName()+"*****"+newType.getTypeName());
		
		//problem.setCreateDate(new Date());
		problem.setTypeName(newType.getTypeName());
		problem.setAuthor(admin.getUserName());
		problem.setType(newType);
		problem.setAdmin(admin);
		
		//oldType.getProblems().remove(problem);
		//newType.getProblems().add(problem);
		if(oldType.getTypeId()!=newType.getTypeId()){
			oldType.setProblemCount(oldType.getProblemCount()-1);
			newType.setProblemCount(newType.getProblemCount()+1);
			typeService.updateType(oldType);
			typeService.updateType(newType);
		}
		
		
		problemService.updateProblem(problem);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	public void validateUpdateMatchProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//Long matchId=Long.parseLong(request.getParameter("matchId"));
		
		//Long typeId=Long.parseLong(request.getParameter("typeName"));
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		
		//type=typeService.load(typeId);
		//admin=adminService.load(adminId);
		//match=matchService.load(matchId);
		//problem.setMatch(match);
		match=problemService.load(problem.getProblemId()).getMatch();
		
		String hardFactor=problem.getHardFactor()+"";
		String typeName=request.getParameter("typeName");
		Integer sequence=problem.getSequence();
		
		System.out.println(problem.getTitle()+"-------");
		
		if(problem.getTitle()!=null&&!(problem.getTitle().trim().length()>0&&problem.getTitle().trim().length()<=50)){
			this.addFieldError("problem.title", "��������ʽ�����ұ�����1~50֮�䣡");
		}else{
			Problem pro=problemService.loadByTitleAndMatch(problem.getTitle(), match.getMatchId());		
			//System.out.println(pro.getProblemId()+"-----------------"+problem.getProblemId());
			if(pro!=null&&pro.getProblemId()!=problem.getProblemId()){
				this.addFieldError("problem.title", "�������Ѿ����ڸñ��⣡");
			}
		}
		if(problem.getSource()!=null&&!(problem.getSource().trim().length()>0&&problem.getSource().trim().length()<=20)){
			this.addFieldError("problem.source", "������Դ��ʽ�����ұ�����1~20֮�䣡");
		}
		String timeLimit=problem.getTimeLimit();
		if(timeLimit!=null&&(!(timeLimit.trim().length()>0&&timeLimit.trim().length()<=20)||!timeLimit.matches("\\d+"))){
			this.addFieldError("problem.timeLimit", "����ʱ�����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}
		String memoryLimit=problem.getMemoryLimit();
		if(memoryLimit!=null&&(!(memoryLimit.trim().length()>0&&memoryLimit.trim().length()<=20)||!memoryLimit.matches("\\d+"))){
			this.addFieldError("problem.memoryLimit", "�����ڴ����Ƹ�ʽ���󣬱���Ϊ����������1~20֮�䣡");
		}
		
		
		if(sequence==0){
			this.addFieldError("problem.sequence", "��ѡ��������ţ�");
		}else{
			Problem p=problemService.loadBySequenceAndMatch(sequence, match.getMatchId());
			//System.out.println(p.getProblemId()+"-----------------"+problem.getProblemId());
			if(p!=null&&p.getProblemId()!=problem.getProblemId()){
				this.addFieldError("problem.sequence", "�����������Ѿ����ڸ���ţ�");
			}
		}
		
		if("null".equals(hardFactor)||hardFactor==null||hardFactor.trim().length()==0){
			this.addFieldError("problem.hardFactor", "��ѡ��һ�������Ѷȣ�");
		}
		
		if("--��ѡ��--".equals(typeName)||typeName==null||typeName.trim().length()==0){
			this.addFieldError("problem.typeName", "��ѡ��һ�����");
		}
		
		System.out.println(hardFactor+"---"+typeName);
		
		ActionContext.getContext().put("urlAction", "problem/update_problem_match.jsp");

	}
	
	//�޸�ĳһ��������Ϣ
	public String updateMatchProblem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long typeId=Long.parseLong(request.getParameter("typeName"));
		
		type=typeService.load(typeId);
		match=problemService.load(problem.getProblemId()).getMatch();
		//match.setProblemCount(match.getProblemCount()+1);
		
		System.out.println(match.getMatchId()+"==================***");
		problem.setMatch(match);
		//problem.setCreateDate(new Date());
		problem.setTypeName(type.getTypeName());
		
		matchService.updateMatch(match);
		problemService.updateProblem(problem);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}

	//��ҳ��ʾ����������Ϣ
	public String pageProblem(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		String race=request.getParameter("matchId");
		String typeId=request.getParameter("typeId");
		String adminId=request.getParameter("adminId");
		
		
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		
		
		try{
			if(condition1!=null)condition1=new String(condition1.getBytes("ISO-8859-1"));
			if(condition2!=null)condition2=new String(condition2.getBytes("ISO-8859-1"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		String hql="from Problem as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--��ѡ��--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		
		//����race�Ƿ���ֵ������ĳһ�α�������������
		if(race!=null&&race.trim().length()>0){
			hql+=" and obj.match.matchId='"+Long.parseLong(race)+"'";
			match=matchService.load(Long.parseLong(race));
		}else{
			hql+=" and obj.match.matchId is null";
		}
		
		//����type�Ƿ���ֵ������ĳһ�����͵���������
		if(typeId!=null&&typeId.trim().length()>0){
			hql+=" and obj.type.typeId='"+Long.parseLong(typeId)+"'";
			type=typeService.load(Long.parseLong(typeId));
		}
		
		//����adminId�Ƿ���ֵ������ĳһ����Ա����������
		if(adminId!=null&&adminId.trim().length()>0){
			hql+=" and obj.admin.adminId='"+Long.parseLong(adminId)+"'";
			admin=adminService.load(Long.parseLong(adminId));
		}
		
		hql=hql+" order by obj.createDate desc";
		
		
		System.out.println("hql===="+hql);
		
		pageBean=problemService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Problem> list=pageBean.getList();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		
		if("all".equals(action)){
			if(typeId!=null&&typeId.trim().length()>0){
				ActionContext.getContext().put("urlAction","problem/list_problem_type_all.jsp");
			}else{
				ActionContext.getContext().put("urlAction","problem/list_problem_all.jsp");
			}
			
		}else if("admin".equals(action)){
			if(typeId!=null&&typeId.trim().length()>0){
				ActionContext.getContext().put("urlAction","problem/list_problem_type_admin.jsp");
			}else if(adminId!=null&&adminId.trim().length()>0){
				System.out.println("�ҵ���������----------");
				ActionContext.getContext().put("urlAction","problem/list_problem_admin.jsp");
			}else{
				ActionContext.getContext().put("urlAction","problem/list_problem.jsp");
			}
			
		}else if("match".equals(action)){
			//match=matchService.load(Long.parseLong(race));
			System.out.println("�����������----------------------");
			ActionContext.getContext().put("urlAction","problem/list_problem_match.jsp");
		}else if("run".equals(action)){//�������й���
			ActionContext.getContext().put("urlAction","problem/list_problem_run.jsp");
		}else{
			ActionContext.getContext().put("urlAction","problem/list_problem.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	public void validatePageMatchProblem(){
		System.out.println("����״̬��֤----------");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String matchId=request.getParameter("matchId");
		match=matchService.load(Long.parseLong(matchId));
		
		System.out.println("---"+match.getState());
		
		if("all".equals(action)&&match.getState()==0){
			this.addFieldError("matchError","������δ��ʼ");
		}
		
		if(match.getState()==1){
			//this.addFieldError("matchError","����������");
		}
		
		if(match.getState()==2){
			//this.addFieldError("matchError","��������");
		}
		
		ActionContext.getContext().put("urlAction","matchError.html");
	}
	
	//��ҳ��ʾĳһ����������������Ϣ
	public String pageMatchProblem(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		String matchId=request.getParameter("matchId");
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		
		try{
			if(condition1!=null)condition1=new String(condition1.getBytes("ISO-8859-1"));
			if(condition2!=null)condition2=new String(condition2.getBytes("ISO-8859-1"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		String hql="from Problem as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--��ѡ��--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		
		if(matchId!=null&&matchId.trim().length()>0){
			hql+=" and obj.match.matchId='"+Long.parseLong(matchId)+"'";
			match=matchService.load(Long.parseLong(matchId));
		}
		
		hql=hql+" order by obj.sequence asc";
		
		System.out.println(matchId+"hql===="+hql);
		
		pageBean=problemService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Problem> list=pageBean.getList();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		
		if("run".equals(action)){
			ActionContext.getContext().put("urlAction","problem/list_problem_match_run.jsp");
		}else if("all".equals(action)){
			ActionContext.getContext().put("urlAction","problem/list_problem_match_all.jsp");
		}else{
			ActionContext.getContext().put("urlAction","problem/list_problem_match_all.jsp");
		}
		
		System.out.println(ActionContext.getContext().get("urlAction")+"---------------"+action);
		
		return Action.SUCCESS;
	}
	
	//����ɾ��
	public String batchDeleteMatch(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String matchId=request.getParameter("matchId");
		String str=request.getParameter("str");
		String[] s=str.split("-");
		
		for(int i=0;i<s.length;i++){
			problem=problemService.load(Long.parseLong(s[i]));
			//type=typeService.load(problem.getType().getTypeId());
			//type.setProblemCount(type.getProblemCount()-1);			
			//typeService.updateType(type);		
			problemService.deleteProblemById(Long.parseLong(s[i]));
		}
		
		match=matchService.load(Long.parseLong(matchId));
		match.setProblemCount(match.getProblemCount()-s.length);
		matchService.updateMatch(match);
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//����ɾ��
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			problem=problemService.load(Long.parseLong(s[i]));
			type=typeService.load(problem.getType().getTypeId());
			type.setProblemCount(type.getProblemCount()-1);			
			typeService.updateType(type);		
			problemService.deleteProblemById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	

	public PageBean<Problem> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Problem> pageBean) {
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

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
}

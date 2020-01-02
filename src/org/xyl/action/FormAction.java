package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Form;
import org.xyl.bean.Match;
import org.xyl.iservice.IFormService;
import org.xyl.iservice.IMatchService;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("formAction")
@Scope("prototype")
public class FormAction extends ActionSupport{

	@Resource private IFormService formService;
	@Resource private IMatchService matchService;
	
	private Form form;
	private Match match;
	
	private PageBean<Form> pageBean;
	private String condition1;
	private String condition2;
	
	public void validateInsertForm(){
		ActionContext.getContext().put("urlAction", "admin/insertAdmin.jsp");
	}
	
	//插入一条管理员信息
	public String insertForm(){
		formService.addForm(form);
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//删除指定Id的管理员
	public String deleteForm(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long formId=Long.parseLong(request.getParameter("formId"));
		formService.deleteFormById(formId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateForm(){
		
		ActionContext.getContext().put("urlAction", "admin/updateAdmin.jsp");
	}
	//修改管理员信息
	public String updateForm(){
		formService.updateForm(form);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个用户
	public String showAdmin(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		Long formId=Long.parseLong(request.getParameter("formId"));
		String action=request.getParameter("action");
		form=formService.load(formId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","admin/updateAdmin.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","admin/showAdmin.jsp");
		}
		
		return Action.SUCCESS;
	}

	//分页显示用户报表
	public String pageForm(){
				
		HttpServletRequest request=ServletActionContext.getRequest();
		String matchId=request.getParameter("matchId");
		match=matchService.load(Long.parseLong(matchId));
		
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
		
		String hql="from Form as obj where 1=1";
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		hql=hql+" and obj.match.matchId='"+matchId+"' order by obj.problemCount desc,obj.totalTime asc";
		System.out.println("hql===="+hql);
		
		pageBean=formService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Form> list=pageBean.getList();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		ActionContext.getContext().put("urlAction","form/list_form.jsp");
		ActionContext.getContext().put("count",match.getProblemCount());
		return Action.SUCCESS;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public PageBean<Form> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Form> pageBean) {
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

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	
}




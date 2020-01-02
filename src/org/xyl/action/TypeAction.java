package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Type;
import org.xyl.iservice.ITypeService;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("typeAction")
@Scope("prototype")
public class TypeAction extends ActionSupport{

	private ITypeService typeService;
	private Type type;
	private String typeName;
	
	private List<Type> typeList=new ArrayList<Type>();
	
	private PageBean<Type> pageBean;
	private String condition1;
	private String condition2;

	
	
	@Resource
	public void setTypeService(ITypeService typeService){
		this.typeService = typeService;
	}

	//插入试题类型校验
	public void validateInsertType(){
		System.out.println(type.getTypeName()+"---"+type.getProblemCount());
		if(type.getTypeName()==null||!(type.getTypeName().trim().length()>0&&type.getTypeName().length()<=20)){
			this.addFieldError("typeError","类型格式错误，必须在1~20之间！");
		}
		Type t=typeService.loadByTypeName(type.getTypeName());
		if(t!=null){
			this.addFieldError("typeError","该类型已经存在！");
		}
		ActionContext.getContext().put("urlAction", "type/insert_type.jsp");
	}
	
	//插入一条试题类型信息
	public String insertType(){
		typeService.addType(type);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//删除指定Id的类型
	public String deleteType(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long typeId=Long.parseLong(request.getParameter("typeId"));
		typeService.deleteTypeById(typeId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateType(){
		System.out.println(type.getTypeName()+"---"+type.getProblemCount());
		Type t=typeService.loadByTypeName(type.getTypeName());
		if(t!=null&&t.getTypeId()!=type.getTypeId()){
			this.addFieldError("typeError","该类型已经存在！");
		}
		ActionContext.getContext().put("urlAction", "type/update_Type.jsp");
	}
	//修改管理员信息
	public String updateType(){
		typeService.updateType(type);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个类型
	public String showType(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long typeId=Long.parseLong(request.getParameter("typeId"));
		String action=request.getParameter("action");
		type=typeService.load(typeId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","type/update_type.jsp");
		}else if(type.equals("show")){
			ActionContext.getContext().put("urlAction","type/show_type.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	//查找所有的类型
	public String findAllType(){
		typeList=typeService.listAllType();
		System.out.println("总的类型个数："+typeList.size());
		ActionContext.getContext().put("typeList",typeList);
		ActionContext.getContext().put("urlAction","type/show_type.jsp");
		return Action.SUCCESS;
	}

	//分页显示试题类型信息
	public String pageType(){
		
		System.out.println("===================pageType------------------");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		
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
		String hql="from Type";
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" as u where u."+condition1+" like '%"+condition2+"%'";
			}
		}
		System.out.println("hql===="+hql);
		
		pageBean=typeService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Type> list=pageBean.getList();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		if("admin".equals(action)){
			ActionContext.getContext().put("urlAction","type/list_type.jsp");
		}else if("all".equals(action)){
			ActionContext.getContext().put("urlAction","type/list_type_all.jsp");
		}else{
			ActionContext.getContext().put("urlAction","type/list_type_all.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	//批量删除
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			typeService.deleteTypeById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	

	public PageBean<Type> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Type> pageBean) {
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
	
	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
}

package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Admin;
import org.xyl.bean.User;
import org.xyl.iservice.IAdminService;
import org.xyl.iservice.IUserService;
import org.xyl.util.MD5Util;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport{

	@Resource private IAdminService adminService;
	@Resource private IUserService userService;
	private Admin admin;
	private PageBean<Admin> pageBean;
	private String condition1;
	private String condition2;
	
	private String confirmPwd;
	
	public void validateInsertAdmin(){
		
		String userName=admin.getUserName();
		String password=admin.getPassword();
		String email=admin.getEmail();
		String address=admin.getAddress();
		String telephone=admin.getTelephone();
		String trueName=admin.getTrueName();
		if(userName!=null&&!(userName.trim().length()>0&&userName.trim().length()<=20)){
			this.addFieldError("admin.userName", "用户名格式错误，必须在1~20之间！");
		}
		if(password!=null&&!(password.trim().length()>0&&password.trim().length()<=20)){
			this.addFieldError("admin.password", "密码格式错误，必须在1~20之间！");
		}
		if(confirmPwd!=null&&!(confirmPwd.trim().length()>0&&confirmPwd.trim().length()<=20)){
			this.addFieldError("confirmPwd", "确认密码格式错误，必须在1~20之间！");
		}
		if(email!=null&&!(email.trim().length()>=0&&email.trim().length()<=20)){
			this.addFieldError("admin.email", "邮箱格式错误，必须在0~20之间！");
		}
		if(address!=null&&!(address.trim().length()>=0&&address.trim().length()<=20)){
			this.addFieldError("admin.address", "地址格式错误，必须在0~20之间！");
		}
		if(telephone!=null&&!(telephone.trim().length()>=0&&telephone.trim().length()<=20)){
			this.addFieldError("admin.telephone", "电话格式错误，必须在0~20之间！");
		}
		if(trueName!=null&&!(trueName.trim().length()>=0&&trueName.trim().length()<=20)){
			this.addFieldError("admin.trueName", "真实姓名格式错误，必须在0~20之间！");
		}
		
		
		//是否包含此用户验证
		if(admin.getUserName()!=null&&admin.getUserName().trim().length()>0){
			User u=userService.loadByUserName(admin.getUserName());
			Admin a=adminService.loadByUserName(admin.getUserName());
			if(u!=null||a!=null){
				this.addFieldError("admin.userName", "已经存在该用户");
			}
		}
		
		String type=admin.getType();
		
		if(type.equals("select")){
			this.addFieldError("user.type", "请选择一种权限");
		}	
		
		System.out.println(confirmPwd+":"+(confirmPwd==null)+":"+confirmPwd.equals("")+":"+(confirmPwd.trim().length()==0));
		if(!admin.getPassword().equals(confirmPwd)){
			this.addFieldError("confirmPwd", "密码两次输入不一致！");
		}
		
		ActionContext.getContext().put("urlAction", "admin/insertAdmin.jsp");
	}
	
	//插入一条管理员信息
	public String insertAdmin(){
		admin.setPassword(MD5Util.getEncryptedPwd(admin.getPassword()));
		adminService.addAdmin(admin);
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//删除指定Id的管理员
	public String deleteAdmin(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long adminId=Long.parseLong(request.getParameter("adminId"));
		adminService.deleteAdminById(adminId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateAdmin(){
		String userName=admin.getUserName();
		String password=admin.getPassword();
		String email=admin.getEmail();
		String address=admin.getAddress();
		String telephone=admin.getTelephone();
		String trueName=admin.getTrueName();
		if(userName!=null&&!(userName.trim().length()>0&&userName.trim().length()<=20)){
			this.addFieldError("admin.userName", "用户名格式错误，必须在1~20之间！");
		}
		if(password!=null&&!(password.trim().length()>0&&password.trim().length()<=20)){
			this.addFieldError("admin.password", "密码格式错误，必须在1~20之间！");
		}
		if(confirmPwd!=null&&!(confirmPwd.trim().length()>0&&confirmPwd.trim().length()<=20)){
			this.addFieldError("confirmPwd", "确认密码格式错误，必须在1~20之间！");
		}
		if(email!=null&&!(email.trim().length()>=0&&email.trim().length()<=20)){
			this.addFieldError("admin.email", "邮箱格式错误，必须在0~20之间！");
		}
		if(address!=null&&!(address.trim().length()>=0&&address.trim().length()<=20)){
			this.addFieldError("admin.address", "地址格式错误，必须在0~20之间！");
		}
		if(telephone!=null&&!(telephone.trim().length()>=0&&telephone.trim().length()<=20)){
			this.addFieldError("admin.telephone", "电话格式错误，必须在0~20之间！");
		}
		if(trueName!=null&&!(trueName.trim().length()>=0&&trueName.trim().length()<=20)){
			this.addFieldError("admin.trueName", "真实姓名格式错误，必须在0~20之间！");
		}
		
		
		System.out.println(admin+"----------");
		
		String type=admin.getType();
		
		if(type.equals("select")){
			this.addFieldError("user.type", "请选择一种权限");
		}	
		
		System.out.println(confirmPwd+":"+(confirmPwd==null)+":"+confirmPwd.equals("")+":"+(confirmPwd.trim().length()==0));
		if(!admin.getPassword().equals(confirmPwd)){
			this.addFieldError("confirmPwd", "密码两次输入不一致！");
		}
		
		ActionContext.getContext().put("urlAction", "admin/updateAdmin.jsp");
	}
	//修改管理员信息
	public String updateAdmin(){
		admin.setPassword(MD5Util.getEncryptedPwd(admin.getPassword()));
		adminService.updateAdmin(admin);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个用户
	public String showAdmin(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		Long adminId=Long.parseLong(request.getParameter("adminId"));
		String action=request.getParameter("action");
		admin=adminService.load(adminId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","admin/updateAdmin.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","admin/showAdmin.jsp");
		}
		
		System.out.println(action+"----"+admin);
		
		return Action.SUCCESS;
	}

	//分页显示用户信息
	public String pageAdmin(){
		
		System.out.println("===================pageAdmin------------------");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
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
		String hql="from Admin";
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" as u where u."+condition1+" like '%"+condition2+"%'";
			}
		}
		System.out.println("hql===="+hql);
		
		//int offset,length;
		//offset=pageBean.getCurrentPage();
		//length=pageBean.getPageSize();
		pageBean=adminService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Admin> list=pageBean.getList();
		//pageBean.init();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		ActionContext.getContext().put("urlAction","admin/listAdmin.jsp");
		return Action.SUCCESS;
	}
	
	//批量删除
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			adminService.deleteAdminById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	

	public PageBean<Admin> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Admin> pageBean) {
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	
	
}

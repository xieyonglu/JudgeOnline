package org.xyl.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Admin;
import org.xyl.bean.OtherInfo;
import org.xyl.bean.User;
import org.xyl.iservice.IAdminService;
import org.xyl.iservice.IUserService;
import org.xyl.service.AdminService;
import org.xyl.util.EmailUtil;
import org.xyl.util.MD5Util;
import org.xyl.util.PageBean;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

	
	@Resource private IUserService userService;
	@Resource private IAdminService adminService;
	private User user;
	private Admin admin;
	
	private PageBean<User> pageBean;
	private String condition1;
	private String condition2;
	private String confirmPwd;
	
	private File myPicture;
	private String myPictureContentType;
	private String myPictureFileName;
	
	
	public User getModel() {
		// TODO Auto-generated method stub
		if(user==null) user=new User();
		return user;
	}
	
	public void validateInsertUser(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String userName=user.getUserName();
		String email=user.getEmail();
		String password=user.getPassword();
		String answer=user.getAnswer();
		String confirmPwd=request.getParameter("confirmPwd");
		String passQuestion=request.getParameter("passQuestion");
		String verCode=request.getParameter("verCode");
		
		HttpSession session=request.getSession();
		String code=(String)session.getAttribute("rand");
		
		//System.out.println("�����֤��--"+verCode+"===�ṩ����֤��--"+code);
		//System.out.println(confirmPwd+"---"+passQuestion);
		//System.out.println(user);
		if(userName!=null&&!(userName.trim().length()>0&&userName.trim().length()<=20)){
			this.addFieldError("user.userName", "�û�����ʽ�����ұ�����1~20֮�䣡");
		}else{
			//�Ƿ�������û���֤
			if(user.getUserName()!=null&&user.getUserName().trim().length()>0){
				User u=userService.loadByUserName(user.getUserName());
				Admin a=adminService.loadByUserName(user.getUserName());
				if(u!=null||a!=null){
					this.addFieldError("user.userName", "�Ѿ����ڸ��û�");
				}
			}
		}
		if(password!=null&&!(password.trim().length()>0&&password.trim().length()<=20)){
			this.addFieldError("user.password", "�����ʽ�����ұ�����1~20֮�䣡");
		}
		if(confirmPwd!=null&&!(confirmPwd.trim().length()>0&&confirmPwd.trim().length()<=20)){
			this.addFieldError("confirmPwd", "ȷ�������ʽ���󣬱�����1~20֮�䣡");
		}
		if(answer!=null&&!(answer.trim().length()>0&&answer.trim().length()<=20)){
			this.addFieldError("user.answer", "�𰸸�ʽ�����ұ�����1~20֮�䣡");
		}
		if(email!=null&&!(email.trim().length()>0&&email.trim().length()<=50)){
			this.addFieldError("user.email", "�����ʽ�����ұ�����1~50֮�䣡");
		}else if(!EmailUtil.checkEmail(email)){
			this.addFieldError("user.email", "�����ʽ�����ұ�����1~50֮�䣡");
		}else{
			if(userService.loadByEmail(email)!=null){
				this.addFieldError("user.email", "�������Ѿ����ڣ�");
			}
		}
		
		if(passQuestion.equals("��ѡ��һ������")){
			this.addFieldError("questionError", "��ѡ��һ������");
		}else if(passQuestion.equals("�Զ�������")){
			int length=user.getQuestion().trim().length();
			if(!(length>=2&&length<=20)){
				this.addFieldError("questionError","�Զ������������2~20֮�䣡");
			}
		}else{
			user.setQuestion(passQuestion);
		}
		
		if(!user.getPassword().equals(confirmPwd)){
			this.addFieldError("confirmPwd", "�����������벻һ�£�");
		}
		
		if(verCode==null||verCode.equals("")||verCode.trim().length()==0){
			this.addFieldError("verCodeError","��֤�벻��Ϊ��!");
		}else if(!code.equalsIgnoreCase(verCode)){
			this.addFieldError("verCodeError","��֤���������!");
		}
		
		ActionContext.getContext().put("urlAction", "user/register.jsp");
		
	}
	
	//�ɹ�ע��һ���û�
	public String insertUser(){
		user.setPassword(MD5Util.getEncryptedPwd(user.getPassword()));
		userService.addUser(user);
		ActionContext.getContext().put("urlAction", "user/addSelfInfo.jsp");
		return Action.SUCCESS;
	}
	
	public void validateUpdateUser(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		//String userName=user.getUserName();
		String email=user.getEmail();
		String password=user.getPassword();
		String answer=user.getAnswer();
		String question=user.getQuestion();
		String confirmPwd=request.getParameter("confirmPwd");
		String passQuestion=request.getParameter("passQuestion");
		
		//String verCode=request.getParameter("verCode");
		
		System.out.println(passQuestion+"===========");
		
		
		
		//HttpSession session=request.getSession();
		//String code=(String)session.getAttribute("rand");
		
		//System.out.println("�����֤��--"+verCode+"===�ṩ����֤��--"+code);
		//System.out.println(confirmPwd+"---"+passQuestion);
		//System.out.println(user);
		/*if(userName!=null&&!(userName.trim().length()>0&&userName.trim().length()<=20)){
			this.addFieldError("user.userName", "�û�����ʽ�����ұ�����1~20֮�䣡");
		}else{
			//�Ƿ�������û���֤
			if(user.getUserName()!=null&&user.getUserName().trim().length()>0){
				User u=userService.loadByUserName(user.getUserName());
				Admin a=adminService.loadByUserName(user.getUserName());
				if(u!=null||a!=null){
					this.addFieldError("user.userName", "�Ѿ����ڸ��û�");
				}
			}
		}*/
		if(password!=null&&!(password.trim().length()>0&&password.trim().length()<=20)){
			this.addFieldError("user.password", "�����ʽ�����ұ�����1~20֮�䣡");
		}
		if(confirmPwd!=null&&!(confirmPwd.trim().length()>0&&confirmPwd.trim().length()<=20)){
			this.addFieldError("confirmPwd", "ȷ�������ʽ���󣬱�����1~20֮�䣡");
		}
		if(answer!=null&&!(answer.trim().length()>0&&answer.trim().length()<=20)){
			this.addFieldError("user.answer", "�𰸸�ʽ�����ұ�����1~20֮�䣡");
		}
		if(email!=null&&!(email.trim().length()>0&&email.trim().length()<=50)){
			this.addFieldError("user.email", "�����ʽ�����ұ�����1~50֮�䣡");
		}else if(!EmailUtil.checkEmail(email)){
			this.addFieldError("user.email", "�����ʽ�����ұ�����1~50֮�䣡");
		}else{
			User u=userService.loadByEmail(email);
			if(u!=null&&u.getUserId()!=user.getUserId()){
				this.addFieldError("user.email", "�������Ѿ����ڣ�");
			}
		}
		
		if(passQuestion.equals("��ѡ��һ������")){
			this.addFieldError("questionError", "��ѡ��һ������");
		}else if(passQuestion.equals("�Զ�������")){
			int length=user.getQuestion().trim().length();
			if(!(length>=2&&length<=20)){
				this.addFieldError("questionError","�Զ������������2~20֮�䣡");
			}
		}
		
		if(!user.getPassword().equals(confirmPwd)){
			this.addFieldError("confirmPwd", "�����������벻һ�£�");
		}
		
		/*if(verCode==null||verCode.equals("")||verCode.trim().length()==0){
			this.addFieldError("verCodeError","��֤�벻��Ϊ��!");
		}else if(!code.equalsIgnoreCase(verCode)){
			this.addFieldError("verCodeError","��֤���������!");
		}*/
		user=userService.load(user.getUserId());
		user.setEmail(email);
		user.setPassword(password);
		user.setAnswer(answer);
		if(passQuestion.equals("�Զ�������")){
			user.setQuestion(question);
		}else{
			user.setQuestion(passQuestion);
		}
		
		ActionContext.getContext().put("urlAction", "user/updateBasicInfo.jsp");
		
	}
	
	//�޸��û�������Ϣ
	public String updateUser(){
		user.setPassword(MD5Util.getEncryptedPwd(user.getPassword()));
		userService.updateUser(user);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//ɾ��ָ��Id���û�
	public String deleteUser(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long userId=Long.parseLong(request.getParameter("userId"));
		userService.deleteUserById(userId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	//�����û��˺��Ƿ����
	public String userEnableSet(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String type=request.getParameter("type");
		String userId=request.getParameter("userId");
		User user=userService.load(Long.parseLong(userId));
		if(type.equals("unEnable")){
			user.setUserEnable(false);
		}else if(type.equals("enable")){
			user.setUserEnable(true);
		}
		userService.updateUser(user);
		System.out.println(type+"-----------"+userId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//�����û������Ƿ����
	public String commentEnableSet(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String type=request.getParameter("type");
		String userId=request.getParameter("userId");
		User user=userService.load(Long.parseLong(userId));
		if(type.equals("unEnable")){
			user.setCommentEnable(false);
		}else if(type.equals("enable")){
			user.setCommentEnable(true);
		}
		userService.updateUser(user);
		System.out.println(type+"-----------"+userId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//�������е�ע���û�
	public String findAllUser(){
		List<User> list=userService.listAllUser();
		ActionContext.getContext().put("list",list);
		ActionContext.getContext().put("urlAction","manager/superAdmin/tab/tab.jsp");
		
		System.out.println("----"+list.size());
		
		return Action.SUCCESS;
	}
	
	//���ҵ����û���Ϣ
	public String showUser(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long userId=Long.parseLong(request.getParameter("userId"));
		String action=request.getParameter("action");
		user=userService.load(userId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","user/updateUserInfo.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","user/showUserInfo.jsp");
		}else if(action.equals("picture")){
			ActionContext.getContext().put("urlAction","user/myPicture.jsp");
		}else if(action.equals("basic")){
			ActionContext.getContext().put("urlAction","user/updateBasicInfo.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	//��ҳ��ʾ�û���Ϣ
	public String pageUser(){
		
		System.out.println("===================pageUser------------------");
		
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
		String hql="from User as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--��ѡ��--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		hql=hql+" order by createDate desc";
		System.out.println("hql===="+hql);
		
		//int offset,length;
		//offset=pageBean.getCurrentPage();
		//length=pageBean.getPageSize();
		pageBean=userService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<User> list=pageBean.getList();
		ActionContext.getContext().put("list",list);
		ActionContext.getContext().put("urlAction","user/listUser.jsp");
		
		System.out.println(list.size()+"--------");
		return Action.SUCCESS;
	}
	
	public String list(){
		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ActionContext.getContext().put("pages",userService.find(user.getUserName()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	public void validateAdminLimit(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String limit=request.getParameter("limit");
		if("--select--".equals(limit)){
			this.addFieldError("limitError", "��ѡ��Ȩ��");
		}
		
		
		System.out.println("------yangzheng---------"+limit);
		//String userId=request.getParameter("userId");
		//User user=userService.load(Long.parseLong(userId));
		//Admin a=adminService.loadByUserName(user.getUserName());
		//if(a!=null){
		//	this.addFieldError("limitError", "�Ѿ����ڸ��û���");
		//}
		
		ActionContext.getContext().put("urlAction", "inc/failure.jsp");
	}
	
	public String adminLimit(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String limit=request.getParameter("limit");
		String userId=request.getParameter("userId");
		User user=userService.load(Long.parseLong(userId));
		OtherInfo otherInfo=user.getOtherInfo();
		
		Admin admin=new Admin();
		admin.setUserName(user.getUserName());
		admin.setPassword(user.getPassword());
		admin.setTrueName(otherInfo.getTrueName());
		admin.setSex(otherInfo.getSex());
		admin.setEmail(user.getEmail());
		admin.setTelephone(otherInfo.getTelephone());
		admin.setBirthday(otherInfo.getBirthday());
		admin.setAddress(otherInfo.getProvince()+otherInfo.getCity()+otherInfo.getArea());
		admin.setType(limit);
		admin.setCreateDate(new Date());
		
		adminService.addAdmin(admin);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	public void validateUpdateMyPicture(){
		System.out.println("�ϴ�ͼƬ��֤----------------------");
		//this.addFieldError("pictureError", "ͼƬ������Ҫ��");
		ActionContext.getContext().put("urlAction", "user/myPicture.jsp");
	}
	
	//�޸ĸ���ͷ��
	public String updateMyPicture(){
		String realPath=ServletActionContext.getServletContext().getRealPath("/upload/user");
		HttpServletRequest request=ServletActionContext.getRequest();
		String userId=request.getParameter("userId");
		User user=userService.load(Long.parseLong(userId));
		
		File file=new File(realPath);
		if(!file.exists()){
			file.mkdir();
		}
		
		try{
			if(myPicture!=null){
				int position=myPictureFileName.lastIndexOf(".");
				String picName=user.getUserName()+myPictureFileName.substring(position);
				FileUtils.copyFile(myPicture,new File(file,picName));
				
				user.setPicture(picName);
				userService.updateUser(user);
				
				System.out.println(picName+"------------");
			}
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//����ɾ��
	public String batchDeal(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String flag=request.getParameter("flag");
		String str=request.getParameter("str");
		
		
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			user=userService.load(Long.parseLong(s[i]));
			
			if("delete".equals(flag)){
				System.out.println("========="+s[i]);
				
				userService.deleteUserById(Long.parseLong(s[i]));
			}else{ 
				
				if("userEnable".equals(flag)){
					user.setUserEnable(true);
				}else if("userUnable".equals(flag)){
					user.setUserEnable(false);
				}else if("commentEnable".equals(flag)){
					user.setCommentEnable(true);
				}else if("commentUnable".equals(flag)){
					user.setCommentEnable(false);
				}
				
				userService.updateUser(user);
			}
			
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public PageBean<User> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<User> pageBean) {
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

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public File getMyPicture() {
		return myPicture;
	}

	public void setMyPicture(File myPicture) {
		this.myPicture = myPicture;
	}

	public String getMyPictureContentType() {
		return myPictureContentType;
	}

	public void setMyPictureContentType(String myPictureContentType) {
		this.myPictureContentType = myPictureContentType;
	}

	public String getMyPictureFileName() {
		return myPictureFileName;
	}

	public void setMyPictureFileName(String myPictureFileName) {
		this.myPictureFileName = myPictureFileName;
	}

}

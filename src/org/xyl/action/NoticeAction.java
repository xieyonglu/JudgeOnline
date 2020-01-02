package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Admin;
import org.xyl.bean.Notice;
import org.xyl.iservice.IAdminService;
import org.xyl.iservice.INoticeService;
import org.xyl.util.Escape;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("noticeAction")
@Scope("prototype")
public class NoticeAction extends ActionSupport{

	@Resource private INoticeService noticeService;
	@Resource private IAdminService adminService;
	
	private Notice notice;
	private Admin admin;
	private Integer count;
	private List<Notice> noticeList=new ArrayList<Notice>();
	
	private PageBean<Notice> pageBean;
	private String condition1;
	private String condition2;

	public void validateInsertNotice(){
		String title=notice.getTitle();
		String content=notice.getContent();
		
		if(title==null||!(title.trim().length()>0&&title.trim().length()<=100)){
			this.addFieldError("notice.title","公告标题不正确，必须为1~100位！");
		}
		if(content.trim().length()==0){
			this.addFieldError("notice.content","公告内容不正确，至少为1位！");
		}
		
		ActionContext.getContext().put("urlAction", "notice/insert_notice.jsp");
	}
	
	//插入一条管理员信息
	public String insertNotice(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		admin=(Admin)ActionContext.getContext().getSession().get("loginAdmin");
		//admin=adminService.load(adminId);
		notice.setAdmin(admin);
		notice.setAuthor(admin.getUserName());
		
		noticeService.addNotice(notice);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//删除指定Id的管理员
	public String deleteNotice(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long noticeId=Long.parseLong(request.getParameter("noticeId"));
		noticeService.deleteNoticeById(noticeId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateNotice(){
		String title=notice.getTitle();
		String content=notice.getContent();
		
		if(title==null||!(title.trim().length()>0&&title.trim().length()<=100)){
			this.addFieldError("notice.title","公告标题不正确，必须为1~100位！");
		}
		if(content.trim().length()==0){
			this.addFieldError("notice.content","公告内容不正确，至少为1位！");
		}
		ActionContext.getContext().put("urlAction", "notice/update_notice.jsp");
	}
	//修改管理员信息
	public String updateNotice(){
		//HttpServletRequest request=ServletActionContext.getRequest();
		//not-null property references a null 
		//or transient value: org.xyl.bean.Notice.admin; 
		//nested exception is org.hibernate.PropertyValueException: 
		//not-null property references a null or transient value: 
		//org.xyl.bean.Notice.admin

		//Notice no=noticeService.load(notice.getNoticeId());
		admin=noticeService.load(notice.getNoticeId()).getAdmin();
		notice.setAdmin(admin);
		notice.setAuthor(admin.getUserName());
		//System.out.println(adminId+"--------"+notice.getNoticeId());
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		
		
		noticeService.updateNotice(notice);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个用户
	public String showNotice(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long noticeId=Long.parseLong(request.getParameter("noticeId"));
		String action=request.getParameter("action");
		notice=noticeService.load(noticeId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","notice/update_notice.jsp");
		}else{
			notice.setBrowser(notice.getBrowser()+1);
			noticeService.updateNotice(notice);
			
			if(action.equals("show")){
				ActionContext.getContext().put("urlAction","notice/show_notice.jsp");
			}else if(action.equals("all")){
				ActionContext.getContext().put("urlAction","notice/show_notice_all.jsp");
			}else{
				ActionContext.getContext().put("urlAction","notice/show_notice_all.jsp");
			}
		}
		
		return Action.SUCCESS;
	}
	
	//查找前cmt个notice
	public String listCmtNotice(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer count=(Integer)request.getAttribute("count");
		String hql="from Notice as obj order by obj.createDate desc";
		
		System.out.println(noticeService+"------------"+count);
		System.out.println(noticeService.pageByCondition(hql, 0, count)+"------------");
		
		noticeList=noticeService.pageByCondition(hql, 0, count).getList();
		
		System.out.println("total notice size====="+noticeList.size());
		ActionContext.getContext().put("noticeList",noticeList);
		return Action.SUCCESS;
	}

	//分页显示用户信息
	public String pageNotice(){
		
		System.out.println("===================pageAdmin------------------");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String adminId=request.getParameter("adminId");
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
		String hql="from Notice as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		if(adminId!=null&&adminId.trim().length()>0){
			hql=hql+" and obj.admin.adminId='"+adminId+"'";
			admin=adminService.load(Long.parseLong(adminId));
		}
		hql=hql+" order by obj.createDate desc";
		System.out.println("hql===="+hql);
		
		//int offset,length;
		//offset=pageBean.getCurrentPage();
		//length=pageBean.getPageSize();
		pageBean=noticeService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Notice> list=pageBean.getList();
		//pageBean.init();
		
		System.out.println(list.size()+"------------------");
		
		ActionContext.getContext().put("list",list);
		
		if(adminId!=null&&adminId.trim().length()>0){
			ActionContext.getContext().put("urlAction","notice/list_notice_my.jsp");
		}else if("all".equals(action)){
			ActionContext.getContext().put("urlAction","notice/list_notice_all.jsp");
		}else{
			ActionContext.getContext().put("urlAction","notice/list_notice.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	//自动搜索，匹配字符串
	public String auto() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String word = request.getParameter("word");
		//String str=URLDecoder.decode(word,"utf-8");
		word=Escape.unescape(word);

		List<String> qlist=new ArrayList<String>();
		
		List<Notice> list=noticeService.listAllNoticeByHql("from Notice as obj where obj.title like '"+word+"%'");
		int length=list.size()>10?10:list.size();
		for(int i=0;i<length;i++){
			qlist.add(list.get(i).getTitle());
		}
		System.out.println(list.size()+"============-=-=");
			
		request.setAttribute("words", qlist);
		request.setAttribute("word", word);
		
		System.out.println(qlist.size()+"=======-=-=-=");
		ActionContext.getContext().put("urlAction","wordxml.jsp");
		return Action.SUCCESS;

	}
	
	//批量删除
	public String batchDelete(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			noticeService.deleteNoticeById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	

	public PageBean<Notice> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Notice> pageBean) {
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

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}
	
	
	
}

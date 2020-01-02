package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.Admin;
import org.xyl.bean.Match;
import org.xyl.iservice.IAdminService;
import org.xyl.iservice.ICodeService;
import org.xyl.iservice.IMatchService;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("matchAction")
@Scope("prototype")
public class MatchAction extends ActionSupport{

	@Resource private IAdminService adminService;
	@Resource private IMatchService matchService;
	@Resource private ICodeService codeService;
	
	private Admin admin;
	private Match match;
	
	private PageBean<Match> pageBean;
	private String condition1;
	private String condition2;

	
	public void validateInsertMatch(){
		
		String regex="\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";//������֤
		HttpServletRequest request=ServletActionContext.getRequest();
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		
		String matchName=match.getMatchName();
		String address=match.getAddress();
		
		if(matchName!=null&&!(matchName.trim().length()>0&&matchName.trim().length()<=20)){
			this.addFieldError("match.matchName", "�������Ƹ�ʽ�����ұ�����1~20֮�䣡");
		}
		if(address!=null&&!(address.trim().length()>0&&address.trim().length()<=20)){
			this.addFieldError("match.address", "������ַ��ʽ�����ұ�����1~20֮�䣡");
		}

		boolean flagStart=false,flagEnd=false;
		if(startDate!=null&&startDate.trim().length()==0){
			this.addFieldError("match.startDate", "������ʼʱ�䲻��Ϊ�գ�");
		}else{
			if(!startDate.matches(regex)){
				this.addFieldError("match.startDate", "������ʼʱ����󣬱�����yyyy-MM-dd HH:mm:ss��ʽ��");
			}else{
				flagStart=true;
			}
		}
		if(endDate!=null&&endDate.trim().length()==0){
			this.addFieldError("match.endDate", "��������ʱ�䲻��Ϊ�գ�");
		}else{
			if(!endDate.matches(regex)){
				this.addFieldError("match.endDate", "��������ʱ����󣬱�����yyyy-MM-dd HH:mm:ss��ʽ��");
			}else{
				flagEnd=true;
			}
		}
		if(flagStart&&flagEnd){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date start=sdf.parse(startDate);
				Date end=sdf.parse(endDate);
				
				if(!start.equals(end)){
					if(!start.before(end)){
						this.addFieldError("match.startDate", "��ʼʱ��Ӧ�����ڵ��ڽ���ʱ�䣡");
					}
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Match m=matchService.loadByMatchName(matchName);
		if(m!=null){
			this.addFieldError("match.matchName", "�Ѿ����ڸñ�����Ϣ�ˣ�");
		}
		ActionContext.getContext().put("urlAction", "match/insert_match.jsp");
	}
	
	//����һ������Ա��Ϣ
	public String insertMatch(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HttpServletRequest request=ServletActionContext.getRequest();
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");

		try{
			match.setStartDate(sdf.parse(startDate));
			match.setEndDate(sdf.parse(endDate));
		}catch(ParseException e){
			e.printStackTrace();
		}
			
		admin=(Admin)ActionContext.getContext().getSession().get("loginAdmin");
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		//admin=adminService.load(adminId);
		
		match.setAuthor(admin.getUserName());
		match.setAdmin(admin);
		match.setProblemCount(0);
		match.setPersonCount(0);
		matchService.addMatch(match);
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//ɾ��ָ��Id�Ĺ���Ա
	public String deleteMatch(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long matchId=Long.parseLong(request.getParameter("matchId"));
		matchService.deleteMatchById(matchId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateMatch(){
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String regex="\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";//������֤
		HttpServletRequest request=ServletActionContext.getRequest();
		//String adminId=request.getParameter("adminId");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		
		//���У�鲻�ɹ�����admin.match��ֵ����ȥ
		//if(adminId!=null&&adminId.trim().length()>0){
		//	admin=adminService.load(Long.parseLong(adminId));
		//	match.setAdmin(admin);
		//}
		
		
		System.out.println("�޸���֤----------------");
		String matchName=match.getMatchName();
		String address=match.getAddress();
		
		if(matchName!=null&&!(matchName.trim().length()>0&&matchName.trim().length()<=20)){
			this.addFieldError("match.matchName", "�������Ƹ�ʽ�����ұ�����1~20֮�䣡");
		}
		if(address!=null&&!(address.trim().length()>0&&address.trim().length()<=20)){
			this.addFieldError("match.address", "������ַ��ʽ�����ұ�����1~20֮�䣡");
		}
		
		boolean flagStart=false,flagEnd=false;
		if(startDate!=null&&startDate.trim().length()==0){
			this.addFieldError("match.startDate", "������ʼʱ�䲻��Ϊ�գ�");
		}else{
			if(!startDate.matches(regex)){
				this.addFieldError("match.startDate", "������ʼʱ����󣬱�����yyyy-MM-dd HH:mm:ss��ʽ��");
			}else{
				flagStart=true;
			}
		}
		if(endDate!=null&&endDate.trim().length()==0){
			this.addFieldError("match.endDate", "��������ʱ�䲻��Ϊ�գ�");
		}else{
			if(!endDate.matches(regex)){
				this.addFieldError("match.endDate", "��������ʱ����󣬱�����yyyy-MM-dd HH:mm:ss��ʽ��");
			}else{
				flagEnd=true;
			}
		}
		if(flagStart&&flagEnd){
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date start=sdf.parse(startDate);
				Date end=sdf.parse(endDate);
				if(!start.equals(end)){
					if(!start.before(end)){
						this.addFieldError("match.startDate", "��ʼʱ��Ӧ�����ڵ��ڽ���ʱ�䣡");
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Match m=matchService.loadByMatchName(matchName);
		if(m!=null&&m.getMatchId()!=match.getMatchId()){
			this.addFieldError("match.matchName", "�Ѿ����ڸñ�����Ϣ�ˣ�");
		}
		ActionContext.getContext().put("urlAction", "match/update_match.jsp");
	}
	
	//�޸ı�����Ϣ
	public String updateMatch(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HttpServletRequest request=ServletActionContext.getRequest();
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");

		try{
			match.setStartDate(sdf.parse(startDate));
			match.setEndDate(sdf.parse(endDate));
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		//Long adminId=Long.parseLong(request.getParameter("adminId"));
		//admin=adminService.load(adminId);
		admin=matchService.load(match.getMatchId()).getAdmin();
		
		match.setAuthor(admin.getUserName());
		match.setAdmin(admin);
		//match.setCreateDate(new Date());
		
		matchService.updateMatch(match);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//��ʾĳ������
	public String showMatch(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		Long matchId=Long.parseLong(request.getParameter("matchId"));
		String action=request.getParameter("action");
		match=matchService.load(matchId);
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction","match/update_match.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","match/show_match.jsp");
		}else if(action.equals("problem")){
			ActionContext.getContext().put("urlAction","problem/insert_problem_match.jsp");
		}else if(action.equals("form")){
			ActionContext.getContext().put("urlAction","form/reportSearch.jsp");
		}/*else if(action.equals("ipaddress")){
			ActionContext.getContext().put("urlAction","ip/insert_ip.jsp");
		}*/else if(action.equals("ipaddressv4")){
			ActionContext.getContext().put("urlAction","ip/insert_ipv4.jsp");
		}else if(action.equals("ipaddressv6")){
			ActionContext.getContext().put("urlAction","ip/insert_ipv6.jsp");
		}
		
		return Action.SUCCESS;
	}
	
	//���ñ���״̬
	public String setMatchState(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		Long matchId=Long.parseLong(request.getParameter("matchId"));
		Integer state=Integer.parseInt(request.getParameter("state"));
		
		match=matchService.load(matchId);
		match.setState(state);
		matchService.updateMatch(match);
		
		
		System.out.println("---action---"+action);
		
		if("match".equals(action)){
			ActionContext.getContext().put("urlAction","inc/timeout.jsp");
		}else{
			ActionContext.getContext().put("urlAction","inc/success.jsp");
		}
		
		return Action.SUCCESS;
	}

	//��ҳ��ʾ������Ϣ
	public String pageMatch(){
				
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		String state=request.getParameter("state");
		String adminId=request.getParameter("adminId");
		
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
		String hql="from Match as obj where 1=1 ";
		
		if(condition1!=null&&condition1.trim().length()>0&&!"--��ѡ��--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		
		//���ݱ�����״̬���������е���֮��صı���
		if(state!=null&&state.trim().length()>0){
			hql=hql+" and obj.state='"+state+"'";
		}
		
		//���ݹ���Ա��ID���������е���֮����Ա��صı���
		if(adminId!=null&&adminId.trim().length()>0){
			hql=hql+" and obj.admin.adminId='"+adminId+"'";
			admin=adminService.load(Long.parseLong(adminId));
		}
		
		hql=hql+" order by obj.createDate desc";
		
		System.out.println("hql===="+hql);
		
		pageBean=matchService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<Match> list=pageBean.getList();
		
		System.out.println(list.size()+"------------------"+action);
		
		ActionContext.getContext().put("list",list);
		
		if("admin".equals(action)){
			if(state!=null&&state.trim().length()>0){
				ActionContext.getContext().put("urlAction","match/list_match.jsp");
			}else if(adminId!=null&&adminId.trim().length()>0){
				ActionContext.getContext().put("urlAction","match/list_match_admin.jsp");
			}else{
				ActionContext.getContext().put("urlAction","match/list_match.jsp");
			}
			
		}else if("all".equals(action)){
			if(state!=null&&state.trim().length()>0){
				ActionContext.getContext().put("urlAction","match/list_match_now.jsp");
			}else{
				ActionContext.getContext().put("urlAction","match/list_match_all.jsp");
			}
			
		}else if("result".equals(action)){
			ActionContext.getContext().put("urlAction","match/list_match_result_admin.jsp");
		}else if("user".equals(action)){
			List<Match> listMatch=new ArrayList<Match>();
			String userId=request.getParameter("userId");
			if(userId!=null&&userId.trim().length()>0){
				for(int i=0;i<list.size();i++){
					Match m=list.get(i);
					if(codeService.listAllCodeByMatchUser(m.getMatchId(), Long.parseLong(userId)).size()>0){
						listMatch.add(m);
					}
				}
			}
			ActionContext.getContext().put("list",listMatch);
			ActionContext.getContext().put("urlAction","match/list_match_user.jsp");
		}else{
			ActionContext.getContext().put("urlAction","match/list_match_all.jsp");
		}
		return Action.SUCCESS;
	}
	
	//����ɾ��
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			matchService.deleteMatchById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
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

	public PageBean<Match> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Match> pageBean) {
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
	
}

package org.xyl.action;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.User;
import org.xyl.iservice.ICodeService;
import org.xyl.iservice.IUserService;

import org.xyl.util.DateUtil;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("sortAction")
@Scope("prototype")
public class SortAction extends ActionSupport{

	
	@Resource private IUserService userService;
	@Resource private ICodeService codeService;
	
	private User user;
	
	private String start;
	private String end;
	
	private PageBean<User> pageBean;
	private String condition1;
	private String condition2;
	
	
	//分页显示排序信息
	public String pageSort(){
		
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
		
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		String hql="from User as obj where 1=1 ";
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		hql=hql+" order by obj.trueProblemCount desc,obj.passCount*1.0/obj.submitCount desc";
		System.out.println("hql===="+hql);
		
		pageBean=userService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<User> list=pageBean.getList();
		ActionContext.getContext().put("list",list);
		System.out.println(list.size()+"--------");
		
		
		if("all".equals(action)){
			ActionContext.getContext().put("urlAction","sort/sort_all.jsp");
		}else if("user".equals(action)){
			
		}else if("admin".equals(action)){
			
		}
		
		return Action.SUCCESS;
	}
	
	//查询时间验证
	public void validateSortByTime() throws Exception{
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HttpServletRequest request=ServletActionContext.getRequest();
		start=request.getParameter("start");
		end=request.getParameter("end");
		
		System.out.println(start+"------"+end);
		
		Date startDate=codeService.findMaxMinDate("min");
		Date endDate=codeService.findMaxMinDate("max");
		
		System.out.println(startDate+"-----"+endDate);
		
		String regex="\\d{4}-\\d{2}-\\d{2}";//日期验证
		int a=1,b=1;
		if(start==null||start.trim().length()==0){
			if(startDate!=null&&startDate.toString().trim().length()!=0){
				start=sdf.format(startDate);
			}else{
				this.addFieldError("startError", "数据库中无数据！");
				a=0;
			}
		}else{
			start=start.substring(0,10);
			if(!start.matches(regex)){
				this.addFieldError("startError", "开始日期格式不对，应为yyyy-MM-dd！");
				a=0;
			}else{
				start=start+" 00:00:00";
			}
		}
		
		if(end==null||end.trim().length()==0){
			if(endDate!=null&&endDate.toString().trim().length()!=0){
				end=sdf.format(endDate);
			}else{
				this.addFieldError("endError", "数据库中无数据！");
				b=0;
			}
		}else{
			end=end.substring(0,10);
			if(!end.matches(regex)){
				this.addFieldError("endError", "结束日期格式不对，应为yyyy-MM-dd！");
				b=0;
			}else{
				end=end+" 23:59:59";
			}
		}
		
		
		System.out.println(start+"-****-"+end);
		
		if(a!=0&&b!=0){
			if(DateUtil.compare(start, end)>0){
				this.addFieldError("orderError", "开始日期应该小于等于结束日期！");
			}
		}
		
		ActionContext.getContext().put("urlAction","sort/sort_admin_time.jsp");
		
	}
	
	//按时间排序
	public String sortByTime() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		
		System.out.println("开始时间:"+start);
		System.out.println("结束时间:"+end);
		
		//yyyy-mm-dd hh:mm:ss
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		List<User> userList=userService.sortUserByTime(sdf.parse(start), sdf.parse(end));
		
		System.out.println(action+"共得到结果："+userList.size()+"--"+userService);
		User userTemp=new User();
		User u=null;
		
		for(int i=0;i<userList.size();i++){
			userTemp=userList.get(i);
			u=userService.load(userTemp.getUserId());	
			userTemp.setUserName(u.getUserName());
		}
		
		request.setAttribute("userList",userList);
		
		//System.out.println("排序的代码数:"+userList);
		//for(int i=Integer.parseInt(pageIndex);i<Integer.parseInt(pageIndex)+Integer.parseInt(pageIndex);i++){
		//	pageBean.getList().add(userList.get(i));
		//}
		this.initPageBean(userList);/////////////////////////////////////////
		
		
		if("admin".equals(action)){
			ActionContext.getContext().put("urlAction","sort/sort_admin_time.jsp");
		}else if("user".equals(action)){
			ActionContext.getContext().put("urlAction","sort/sort_all.jsp");
		}else if("all".equals(action)){
			ActionContext.getContext().put("urlAction","sort/sort_all_time.jsp");
			//ActionContext.getContext().put("urlAction","sort/sort_all_total.jsp");
		}else if("week".equals(action)){
			ActionContext.getContext().put("urlAction","sort/sort_all_week.jsp");
			//ActionContext.getContext().put("urlAction","sort/sort_all_total.jsp");
		}else if("month".equals(action)){
			ActionContext.getContext().put("urlAction","sort/sort_all_month.jsp");
			//ActionContext.getContext().put("urlAction","sort/sort_all_total.jsp");
		}else{
			ActionContext.getContext().put("urlAction","sort/sort_all_time.jsp");
		}
		
		System.out.println("---urlAction----"+ActionContext.getContext().get("urlAction"));
		
		return Action.SUCCESS;
	}
	
	//最近一周的排名
	public String sortByWeek() throws Exception{
		Calendar ca=new GregorianCalendar();
		int year=ca.get(Calendar.YEAR);//得到当前的年
		int month=ca.get(Calendar.MONTH)+1;//得到当前的月
		int day=ca.get(Calendar.DAY_OF_MONTH);//得到当前的日
		int week=ca.get(Calendar.DAY_OF_WEEK);//得到当前的星期
		
		
		String startTime=year+"-"+DateUtil.addZero(month+"",2)+"-"+DateUtil.addZero(day+"",2);
		String endTime=DateUtil.getNearWeek(startTime,week+7);
		
		System.out.println(startTime+"--"+endTime);
		start=endTime+" 00:00:00";
		end=startTime+" 23:59:59";
		
		System.out.println(start+"--===--"+end);
		
		return this.sortByTime();
	}
	
	//最近一周的排名
	public String sortByMonth() throws Exception{
		Calendar ca=new GregorianCalendar();
		int year=ca.get(Calendar.YEAR);//得到当前的年
		int month=ca.get(Calendar.MONTH)+1;//得到当前的月
		int day=ca.get(Calendar.DAY_OF_MONTH);//得到当前的日
		//int week=ca.get(Calendar.DAY_OF_WEEK);//得到当前的星期
		
		
		String startTime=year+"-"+DateUtil.addZero(month+"",2)+"-"+DateUtil.addZero(day+"",2);
		String endTime=DateUtil.getNearMonth(startTime);
		
		System.out.println(startTime+"--"+endTime);
		//start=endTime;
		//end=startTime;
		start=endTime+" 00:00:00";
		end=startTime+" 23:59:59";
		
		return this.sortByTime();
	}
	
	private void initPageBean(List<User> userList){
		HttpServletRequest request=ServletActionContext.getRequest();
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		int allRow=userList.size();
		int totalPage=PageBean.countTotalPage(Integer.parseInt(pageSize), allRow);
		
		//int length=Integer.parseInt(pageSize);
		
		int currentPage = PageBean.countCurrentPage(Integer.parseInt(pageIndex),totalPage);
		int offset = PageBean.countOffset(Integer.parseInt(pageSize), currentPage);
		int length=(offset+Integer.parseInt(pageSize))>allRow?allRow:(offset+Integer.parseInt(pageSize));
		
		System.out.println(currentPage+"----"+offset+"----"+length+"--"+pageIndex+"---"+pageSize);	
		//List<User> list = userDao.pageByHql(hql, offset, length);
		List<User> list=new ArrayList<User>();
		//pageBean.getList()=new ArrayList<User>();
		if(offset>=0){
			for(int i=offset;i<length;i++){
				list.add(userList.get(i));
			}
		}
		System.out.println("--------"+list.size());
		ActionContext.getContext().put("list",list);
		pageBean = new PageBean<User>();
		pageBean.setPageSize(Integer.parseInt(pageSize));
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	

}

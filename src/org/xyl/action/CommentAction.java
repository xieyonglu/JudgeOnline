package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.xyl.bean.Comment;
import org.xyl.bean.Match;
import org.xyl.bean.Problem;
import org.xyl.bean.User;

import org.xyl.iservice.ICommentService;
import org.xyl.iservice.IMatchService;
import org.xyl.iservice.IProblemService;
import org.xyl.iservice.IUserService;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends ActionSupport implements ModelDriven<Comment>{

	@Resource private ICommentService commentService;
	@Resource private IProblemService problemService;
	@Resource private IMatchService matchService;
	@Resource private IUserService userService;
	
	private List<Comment> list=new ArrayList<Comment>();
	private Comment comment;
	private Problem problem;
	private Match match;
	private User user;
	
	private PageBean<Comment> pageBean;
	private PageBean<Problem> pageBeanProblem;
	private PageBean<Match> pageBeanMatch;
	private String condition1;
	private String condition2;
	

	public Comment getModel() {
		// TODO Auto-generated method stub
		if(comment==null) comment=new Comment();
		return comment;
	}
	
	//插入问题评论验证
	public void validateInsertComment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String userId=request.getParameter("userId");
		String content=comment.getContent();
		
		if(!(userId!=null&&userId.trim().length()>0)){
			this.addFieldError("commentError", "请先登录，然后评论！");
		}else{
			User user=userService.load(Long.parseLong(userId));
			if(!user.isCommentEnable()){	
				this.addFieldError("commentError","你的评论已禁用，速与管理员联系！");
			}else if(!(content.length()>=1&&content.length()<=500)){
				this.addFieldError("commentError", "评论必须在1~500之间！");
			}
		}	
		
		ActionContext.getContext().put("urlAction", "inc/comment_error.jsp");
	}
	
	
	
	//插入问题评论
	public String insertComment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String userId=request.getParameter("userId");
		String problemId=request.getParameter("problemId");
		String matchId=request.getParameter("matchId");
		
		System.out.println(problemId+"------"+matchId);
		
		user=userService.load(Long.parseLong(userId));
		if(problemId!=null&&problemId.trim().length()>0){
			problem=problemService.load(Long.parseLong(problemId));
			comment.setProblem(problem);
			
			String hql="from Comment obj where obj.problem.problemId='"+problemId+"'";
			List<Comment> list=commentService.loadByHql(hql);
			comment.setFloor(list.size()+1);
		}
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
			comment.setMatch(match);
			
			String hql="from Comment obj where obj.match.matchId='"+matchId+"'";
			List<Comment> list=commentService.loadByHql(hql);
			comment.setFloor(list.size()+1);
		}
		
		comment.setUser(user);
		commentService.addComment(comment);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//插入回复验证
	public void validateInsertCommentReply(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String userId=request.getParameter("userId");
		String content=comment.getContent();
		
		if(!(userId!=null&&userId.trim().length()>0)){
			this.addFieldError("commentError", "请先登录，然后评论！");
		}else{
			User user=userService.load(Long.parseLong(userId));
			if(!user.isCommentEnable()){	
				this.addFieldError("commentError","你的评论已禁用，速与管理员联系！");
			}
		}
		
		if(!(content.length()>=1&&content.length()<=500)){
			this.addFieldError("commentError", "回复必须在1~500之间！");
		}
		/*User user=(User)request.getSession().getAttribute("loginUser");
		if(!user.isCommentEnable()){
			this.addFieldError("commentError","你的评论已禁用，速与管理员联系！");
		}*/
		ActionContext.getContext().put("urlAction", "inc/comment_error.jsp");
	}
	
	//插入评论回复
	public String insertCommentReply(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String userId=request.getParameter("userId");
		String commentId=request.getParameter("commentId");
		String problemId=request.getParameter("problemId");
		String matchId=request.getParameter("matchId");
		
		user=userService.load(Long.parseLong(userId));
		Comment com=commentService.load(Long.parseLong(commentId));
		com.setReplyCount(com.getReplyCount()+1);//回复数加1
		
		comment.setUser(user);
		
		if(problemId!=null&&problemId.trim().length()>0){
			problem=problemService.load(Long.parseLong(problemId));
			comment.setProblem(problem);
			
			String hql="from Comment obj where obj.problem.problemId='"+problemId+"'";
			List<Comment> list=commentService.loadByHql(hql);
			comment.setFloor(list.size()+1);
		}
			
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
			comment.setMatch(match);
			
			String hql="from Comment obj where obj.match.matchId='"+matchId+"'";
			List<Comment> list=commentService.loadByHql(hql);
			comment.setFloor(list.size()+1);
		}
		
		commentService.addComment(comment);
		commentService.updateComment(com);
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	//删除评论
	public String deleteComment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String commentId=request.getParameter("commentId");
		commentService.deleteCommentById(Long.parseLong(commentId));
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个评论
	public String showComment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String commentId=request.getParameter("commentId");
		String action=request.getParameter("action");
		
		comment=commentService.load(Long.parseLong(commentId));
		
		if(action.equals("update")){
			ActionContext.getContext().put("urlAction", "comment/update_comment.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction", "comment/show_comment.jsp");
		}else if(action.equals("matchUpdate")){
			ActionContext.getContext().put("urlAction", "comment/update_match_comment.jsp");
		}
		return Action.SUCCESS;
	}
	
	public void validateUpdateComment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String content=comment.getContent();
		if(!(content.length()>=1&&content.length()<=500)){
			this.addFieldError("commentError", "回复必须在1~500之间！");
		}
		User user=(User)request.getSession().getAttribute("loginUser");
		if(!user.isCommentEnable()){
			this.addFieldError("commentError","你的评论已禁用，速与管理员联系！");
		}
	}
	
	public String updateComment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String userId=request.getParameter("userId");
		String problemId=request.getParameter("problemId");
		String matchId=request.getParameter("matchId");
		
		user=userService.load(Long.parseLong(userId));
		if(problemId!=null&&problemId.trim().length()>0){
			problem=problemService.load(Long.parseLong(problemId));
			comment.setProblem(problem);
		}
			
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
			comment.setMatch(match);
		}
		
		comment.setUser(user);
		//comment.setCreateDate(new Date());
		commentService.updateComment(comment);
				
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//找出某一个问题或比赛的所有评论
	public String findComment(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String userId=request.getParameter("userId");
		String problemId=request.getParameter("problemId");
		String matchId=request.getParameter("matchId");
		
		String hql="from Comment as obj where 1=1 ";
		if(userId!=null&&userId.trim().length()>0){
			user=userService.load(Long.parseLong(userId));
			ActionContext.getContext().put("user", user);/////////???????????????????????????
			hql=hql+" and obj.user.userId='"+userId+"'";
		
		}
		if(problemId!=null&&problemId.trim().length()>0){
			problem=problemService.load(Long.parseLong(problemId));
			ActionContext.getContext().put("problem", problem);/////////???????????????????????????
			hql=hql+" and obj.problem.problemId='"+problemId+"'";
		}
			
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
			ActionContext.getContext().put("match", match);/////////???????????????????????????
			hql=hql+" and obj.match.matchId='"+matchId+"'";
		}
		hql=hql+" order by obj.createDate desc";
		
		//list=commentService.loadByHql(hql);
		
		
		
		//ActionContext.getContext().put("list", list);
		
		//*****************************//
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		System.out.println(hql+"------------------------");
		pageBean=commentService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		list=pageBean.getList();
		System.out.println(problemId+"----本题的总共评论数:----"+list.size());
		ActionContext.getContext().put("list",list);
		//*****************************//
		
		if("admin".equals(action)){
			if(problemId!=null&&problemId.trim().length()>0){
				ActionContext.getContext().put("urlAction", "comment/show_comment_admin.jsp");
			}else if(matchId!=null&&matchId.trim().length()>0){
				ActionContext.getContext().put("urlAction", "comment/show_comment_match_admin.jsp");
			}
		}else if("user".equals(action)){
			if(problemId!=null&&problemId.trim().length()>0){
				ActionContext.getContext().put("urlAction", "comment/show_comment_user.jsp");
			}		
			if(matchId!=null&&matchId.trim().length()>0){
				ActionContext.getContext().put("urlAction", "comment/show_comment_match_user.jsp");
			}
		}else{
			if(problemId!=null&&problemId.trim().length()>0){
				ActionContext.getContext().put("urlAction", "comment/show_comment_all.jsp");
			}else if(matchId!=null&&matchId.trim().length()>0){
				ActionContext.getContext().put("urlAction", "comment/show_comment_match_all.jsp");
			}
		}
		
		return Action.SUCCESS;
	}
	
	//分页所有的与评论有关的比赛或试题
	public String pageComment(){
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		String action=request.getParameter("action");
		String type=request.getParameter("type");
		
		String userId=request.getParameter("userId");
		
		
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
		String hql="";
		if("match".equals(type)){
			hql="from Match as obj where obj.matchId in(select distinct c.match.matchId from Comment as c where 1=1";
		}else{
			hql="from Problem as obj where obj.problemId in(select distinct c.problem.problemId from Comment as c where 1=1";
		}
		if(userId!=null&&userId.trim().length()>0){
			hql+=" and c.user.userId='"+userId+"'";
		}
		hql+=")";
		if(condition1!=null&&condition1.trim().length()>0&&!condition1.equals("--请选择--")){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		System.out.println(pageIndex+"--------"+pageSize);
		System.out.println("hql===="+hql);
		
		if("match".equals(type)){
			pageBeanMatch=matchService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
			List<Match> list=pageBeanMatch.getList();
			ActionContext.getContext().put("list",list);
		}else{
			pageBeanProblem=problemService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
			List<Problem> list=pageBeanProblem.getList();
			ActionContext.getContext().put("list",list);
		}
		
		
		System.out.println("list.size()-----"+list.size());
		if("user".equals(action)){
			if("match".equals(type)){
				ActionContext.getContext().put("urlAction", "comment/list_comment_match_user.jsp");
			}else{
				ActionContext.getContext().put("urlAction", "comment/list_comment_user.jsp");
			}
			
		}else if("admin".equals(action)){
			if("match".equals(type)){
				ActionContext.getContext().put("urlAction", "comment/list_comment_match_admin.jsp");
			}else{
				ActionContext.getContext().put("urlAction", "comment/list_comment_admin.jsp");
			}
		}else if("all".equals(action)){
			if("match".equals(type)){
				ActionContext.getContext().put("urlAction", "comment/list_comment_match_all.jsp");
			}else{
				ActionContext.getContext().put("urlAction", "comment/list_comment_all.jsp");
			}
			
		}else{
			if("match".equals(type)){
				ActionContext.getContext().put("urlAction", "comment/list_comment_match_user.jsp");
			}else{
				ActionContext.getContext().put("urlAction", "comment/list_comment_user.jsp");
			}
		}
		
		return Action.SUCCESS;
	}
	
	//批量删除
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			commentService.deleteCommentById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}

	public List<Comment> getList() {
		return list;
	}

	public void setList(List<Comment> list) {
		this.list = list;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

	public PageBean<Comment> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Comment> pageBean) {
		this.pageBean = pageBean;
	}

	public PageBean<Problem> getPageBeanProblem() {
		return pageBeanProblem;
	}

	public void setPageBeanProblem(PageBean<Problem> pageBeanProblem) {
		this.pageBeanProblem = pageBeanProblem;
	}

	public PageBean<Match> getPageBeanMatch() {
		return pageBeanMatch;
	}

	public void setPageBeanMatch(PageBean<Match> pageBeanMatch) {
		this.pageBeanMatch = pageBeanMatch;
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



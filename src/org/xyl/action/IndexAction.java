package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.iservice.IIndexService;
import org.xyl.index.IndexUtil;
import org.xyl.util.Escape;
import org.xyl.util.PageBean;
import org.xyl.bean.Notice;
import org.xyl.bo.Index;
import org.xyl.bo.IndexField;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private IIndexService indexService;
	private String condition1;
	private String condition2;
	private PageBean<Index> pageBean=new PageBean<Index>();
	
	@Resource
	public void setIndexService(IIndexService indexService) {
		this.indexService = indexService;
	}

	public PageBean<Index> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Index> pageBean) {
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

	public String addIndex(){
		//Notice notice=new Notice(10L,"test--->我们伟大的北京","tikeeee","test-->我们伟大的北京...",9,new Date());
		//IndexField field = IndexUtil.msg2IndexField(notice);
		//indexService.addIndex(field,false);
		return SUCCESS;
	}
	
	public String updateIndex(){
		//Notice notice=new Notice(10L,"testUpdate--->北京","tikeeee","test-->我们伟大的北京...",9,new Date());
		//IndexField field = IndexUtil.msg2IndexField(notice);
		//indexService.updateIndex(field);
		return Action.SUCCESS;
	}
	
	//删除某一个索引
	public String deleteIndex(){
		String noticeId=request.getParameter("noticeId");
		indexService.deleteIndex(noticeId);
		return Action.SUCCESS;
	}
	
	//清空所有的索引
	public String deleteAllIndex(){
		indexService.deleteAllIndex();
		return Action.SUCCESS;
	}
	
	public String commitIndex() {
		indexService.commitIndex();
		return Action.SUCCESS;
	}
	
	public String reConstructor() {
		indexService.reConstructorIndex();
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	public String updateSet() {
		indexService.updateSetIndex();
		return Action.SUCCESS;	
	}
	
	public String search(){
		
		try{
			condition2=new String(condition2.getBytes("ISO-8859-1"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		System.out.println(condition2+"----------");
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		System.out.println(pageIndex+"---------"+pageSize);
		
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		pageBean=indexService.findByIndex(condition2,Integer.parseInt(pageIndex),Integer.parseInt(pageSize));
		ActionContext.getContext().put("PageBean", pageBean);
		ActionContext.getContext().put("urlAction", "notice/list_notice_all.jsp");
		
		if(pageBean!=null&&pageBean.getList()!=null)
			System.out.println(pageBean.getList().size()+"--------");
		ActionContext.getContext().put("list",pageBean.getList());
		return Action.SUCCESS;
	}

	//@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
}




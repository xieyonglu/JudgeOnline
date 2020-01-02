package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Notice;
import org.xyl.util.PageBean;

public interface INoticeService {

	public void addNotice(Notice notice);
	
	public void updateNotice(Notice notice);
	
	public void deleteNoticeById(Long noticeId);
	
	public Notice load(Long noticeId);
	
	public List<Notice> listAllNoticeByHql(String hql);
	
	public PageBean<Notice> find(String name);
	
	public PageBean<Notice> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
}

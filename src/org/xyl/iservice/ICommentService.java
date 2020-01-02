package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Comment;
import org.xyl.util.PageBean;

public interface ICommentService {

	public void addComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public void deleteCommentById(Long commentId);
	
	public Comment load(Long commentId);
	
	public List<Comment> listAllComment();
	
	public PageBean<Comment> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
	public List<Comment> loadByHql(String hql);

}

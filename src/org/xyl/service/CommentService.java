package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Comment;
import org.xyl.idao.ICommentDao;
import org.xyl.iservice.ICommentService;
import org.xyl.util.PageBean;

@Service("commentService")
public class CommentService implements ICommentService{

	private ICommentDao commentDao;
	
	
	public ICommentDao getCommentDao() {
		return commentDao;
	}

	@Resource
	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		comment.setCreateDate(new Date());
		//comment.setFloor(0);
		comment.setReplyCount(0);
		commentDao.add(comment);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=commentDao.batchDelete(str);
		System.out.println("flag-------"+flag);
		return flag;
	}

	public void deleteCommentById(Long commentId) {
		// TODO Auto-generated method stub
		commentDao.delete(commentId);
	}

	public List<Comment> listAllComment() {
		// TODO Auto-generated method stub
		return commentDao.list("from Comment");
	}

	public Comment load(Long commentId) {
		// TODO Auto-generated method stub
		return commentDao.load(commentId);
	}

	public PageBean<Comment> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = commentDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<Comment> list = commentDao.pageByHql(hql, offset, length);
		PageBean<Comment> pageBean = new PageBean<Comment>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateComment(Comment comment) {
		// TODO Auto-generated method stub
		//comment.setCreateDate(new Date());
		commentDao.update(comment);
	}

	public List<Comment> loadByHql(String hql) {
		// TODO Auto-generated method stub
		return commentDao.list(hql);
	}
	

}

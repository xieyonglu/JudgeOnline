package org.xyl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Submit;
import org.xyl.idao.ISubmitDao;
import org.xyl.iservice.ISubmitService;
import org.xyl.util.PageBean;

@Service("submitService")
public class SubmitService implements ISubmitService{

	private ISubmitDao submitDao;
	
	@Resource
	public void setSubmitDao(ISubmitDao submitDao) {
		this.submitDao = submitDao;
	}

	public void addSubmit(Submit submit) {
		// TODO Auto-generated method stub
		submitDao.add(submit);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=submitDao.batchDelete(str);
		return flag;
	}

	public void deleteSubmitById(Long submitId) {
		// TODO Auto-generated method stub
		submitDao.delete(submitId);
	}

	public List<Submit> listAllSubmitByHql(String hql) {
		// TODO Auto-generated method stub
		return submitDao.list(hql);
	}

	public Submit load(Long submitId) {
		// TODO Auto-generated method stub
		return submitDao.load(submitId);
	}

	public PageBean<Submit> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = submitDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<Submit> list = submitDao.pageByHql(hql, offset, length);
		PageBean<Submit> pageBean = new PageBean<Submit>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateSubmit(Submit submit) {
		// TODO Auto-generated method stub
		submitDao.update(submit);
	}

	//@Override
	public Submit findByUserProblem(Long userId, Long problemId) {
		// TODO Auto-generated method stub
		String hql="from Submit as obj where obj.user.userId='"+userId+"' and obj.problem.problemId='"+problemId+"'";
		return submitDao.loadByHql(hql);
	}

	public Long submitCount(Long problemId, int state) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Submit as obj where obj.problem.problemId='"+problemId+"'";
		if(state==0){
			hql=hql+" and obj.passCount=0";
		}else{
			hql=hql+" and obj.passCount!=0";
		}
		return (Long) submitDao.loadObjectByHql(hql);
	}

}

package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Submit;
import org.xyl.util.PageBean;

public interface ISubmitService {

	public void addSubmit(Submit submit);
	
	public void updateSubmit(Submit submit);
	
	public void deleteSubmitById(Long submitId);
	
	public Submit load(Long submitId);
	
	public Submit findByUserProblem(Long userId,Long problemId);
	
	public List<Submit> listAllSubmitByHql(String hql);
	
	public PageBean<Submit> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
	public Long submitCount(Long problemId,int state);
	
}

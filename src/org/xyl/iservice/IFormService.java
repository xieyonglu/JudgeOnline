package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Form;
import org.xyl.util.PageBean;

public interface IFormService {

	public void addForm(Form form);
	
	public void updateForm(Form form);
	
	public void deleteFormById(Long formId);
	
	public Form load(Long formId);
	
	public Form loadByUserId(Long userId);
	
	public Form loadByUserAndMatch(Long userId,Long matchId);
	
	public List<Form> loadByMatchId(Long matchId);
	
	public List<Form> listAllFormByHql(String hql);
	
	public PageBean<Form> pageByCondition(String hql,int pageNumber,int pageSize);

	public boolean batchDelete(String[] str);
	
}

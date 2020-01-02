package org.xyl.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.xyl.bean.Form;
import org.xyl.idao.IFormDao;
import org.xyl.iservice.IFormService;
import org.xyl.util.PageBean;

@Service("formService")
public class FormService implements IFormService{

	private IFormDao formDao;
	
	@Resource
	public void setFormDao(IFormDao formDao) {
		this.formDao = formDao;
	}

	public void addForm(Form form) {
		// TODO Auto-generated method stub
		formDao.add(form);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=formDao.batchDelete(str);
		return flag;
	}

	public void deleteFormById(Long formId) {
		// TODO Auto-generated method stub
		formDao.delete(formId);
	}

	public PageBean<Form> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return formDao.find("from Form");
		}else{
			return formDao.find("from Form where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<Form> listAllFormByHql(String hql) {
		// TODO Auto-generated method stub
		return formDao.list(hql);
	}

	public Form load(Long formId) {
		// TODO Auto-generated method stub
		return formDao.load(formId);
	}

	public PageBean<Form> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = formDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<Form> list = formDao.pageByHql(hql, offset, length);
		PageBean<Form> pageBean = new PageBean<Form>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateForm(Form form) {
		// TODO Auto-generated method stub
		formDao.update(form);
	}

	//@Override
	public Form loadByUserId(Long userId) {
		// TODO Auto-generated method stub
		String hql="from Form as obj where obj.userId='"+userId+"'";
		return formDao.loadByHql(hql);
	}
	
	//@Override
	public List<Form> loadByMatchId(Long matchId){
		// TODO Auto-generated method stub
		String hql="from Form as obj where obj.match.matchId='"+matchId+"'";
		return formDao.list(hql);
	}
	
	public Form loadByUserAndMatch(Long userId, Long matchId) {
		// TODO Auto-generated method stub
		String hql="from Form as obj where obj.userId='"+userId+"' and obj.match.matchId='"+matchId+"'";
		return formDao.loadByHql(hql);
	}

	
}

package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Type;
import org.xyl.idao.ITypeDao;
import org.xyl.iservice.ITypeService;
import org.xyl.util.PageBean;

@Service("typeService")
public class TypeService implements ITypeService{

	private ITypeDao typeDao;
	
	@Resource
	public void setTypeDao(ITypeDao typeDao) {
		this.typeDao = typeDao;
	}
	
	public void addType(Type type) {
		// TODO Auto-generated method stub
		type.setCreateDate(new Date());
		type.setProblemCount(0L);
		typeDao.add(type);
	}
	

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=typeDao.batchDelete(str);
		return flag;
	}

	public void deleteTypeById(Long typeId) {
		// TODO Auto-generated method stub
		typeDao.delete(typeId);
	}
	
	public Type loadByTypeName(String typeName) {
		// TODO Auto-generated method stub
		return typeDao.loadByHql("from Type where typeName=?",typeName);
	}


	public List<Type> listAllType(){
		// TODO Auto-generated method stub
		return typeDao.list("from Type");
	}

	public Type load(Long typeId) {
		// TODO Auto-generated method stub
		return typeDao.load(typeId);
	}
	

	public PageBean<Type> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = typeDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<Type> list = typeDao.pageByHql(hql, offset, length);
		PageBean<Type> pageBean = new PageBean<Type>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateType(Type type) {
		// TODO Auto-generated method stub
		//type.setCreateDate(new Date());
		typeDao.update(type);
	}

	public PageBean<Type> find(String typeName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Type;
import org.xyl.util.PageBean;

public interface ITypeService {

	public void addType(Type type);
	
	public void updateType(Type type);
	
	public void deleteTypeById(Long typeId);
	
	public Type load(Long typeId);
	
	public Type loadByTypeName(String typeName);
	
	public List<Type> listAllType();
	
	public PageBean<Type> find(String typeName);
	
	public PageBean<Type> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
}

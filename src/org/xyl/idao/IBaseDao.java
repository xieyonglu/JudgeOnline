package org.xyl.idao;

import java.util.List;
import java.util.Map;

import org.xyl.util.PageBean;

public interface IBaseDao<T> {

	public T add(T t);
	public void update(T t);
	public void delete(T t);
	public void delete(long id);
	
	public List<T> list(String hql,Object[] args);
	public List<T> list(String hql);
	public List<T> list(String hql,Object arg);
	
	public PageBean<T> find(String hql,Object[] args);
	public PageBean<T> find(String hql,Object arg);
	public PageBean<T> find(String hql);
	
	public T loadByHql(String hql,Object[] args);
	public T loadByHql(String hql,Object arg);
	public T loadByHql(String hql);
	public T load(long id);
	
	public Object loadObjectByHql(String hql,Object[] args);
	public Object loadObjectByHql(String hql,Object arg);
	public Object loadObjectByHql(String hql);
	
	//------------------------------------------------------
	public List<T> pageByHql(final String hql,int offset,int length);
	
	public boolean batchDelete(String[] str);

	public int getAllRowCount(String hql);
	
	public <T> List<T> findAllBySql(String sql,Class<T> cls,String c,final Map<String, Object> params);
	
}




package org.xyl.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import org.xyl.idao.IBaseDao;
import org.xyl.util.PageBean;
import org.xyl.util.SystemContext;

@Repository("baseDao")
public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> clz;
	
	@SuppressWarnings({"unchecked"})
	private Class<T> getClz(){
		if(clz==null)
			clz=(Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			return clz;
	}
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 添加对象
	 */
	public T add(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
		return t;
	}

	/**
	 * 更新对象
	 */
	public void update(T t) {
		// TODO Auto-generated method stub		
		//this.getHibernateTemplate().update(t);
		//this.getHibernateTemplate().flush();
		this.getHibernateTemplate().merge(t);
	}
	
	/**
	 * 删除对象
	 */
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * 根据ID删除对象
	 */
	public void delete(long id) {
		// TODO Auto-generated method stub
		this.delete(load(id));
	}

	public PageBean<T> find(String hql, Object[] args) {
		// TODO Auto-generated method stub
		PageBean<T> pages=new PageBean<T>();
		String sort=SystemContext.getSort();
		String order=SystemContext.getOrder();
		int pageSize=SystemContext.getPageSize();
		int pageOffset=SystemContext.getPageOffset();
		String countHql=getCountHql(hql);
		if(sort!=null&&!"".equals(sort.trim())){
			hql+=" order by "+sort;
			if(order!=null&&!"".equals(order.trim())){
				hql+=" "+order;
			}else{
				hql+=" asc";
			}
		}
		Query query=setQuery(hql,args);
		Query countQuery=setQuery(countHql,args);
		List<T> list=query.setFirstResult(pageOffset).setMaxResults(pageSize).list();
		
		pages.setList(list);
		//pages.setOffset(pageOffset);
		pages.setPageSize(pageSize);
		Long totalRecord=(Long)countQuery.uniqueResult();
		pages.setTotalPage(totalRecord.intValue());
		return pages;
	}

	public PageBean<T> find(String hql, Object arg) {
		// TODO Auto-generated method stub
		return find(hql,new Object[]{arg});
	}

	public PageBean<T> find(String hql) {
		// TODO Auto-generated method stub
		return find(hql,null);
	}

	public List<T> list(String hql, Object[] args) {
		// TODO Auto-generated method stub
		String order=SystemContext.getOrder();
		String sort=SystemContext.getSort();
		if(sort!=null&&!"".equals(sort.trim())){
			hql=hql+" order by "+sort;
			if(order!=null&&"".equals(order.trim())){
				hql=hql+" "+order;
			}else{
				hql=hql+" asc";
			}
		}
		
		
		System.out.println(hql+"----------");
		
		
		Query query=this.getSession().createQuery(hql);
		if(args!=null){
			int index=0;
			for(Object arg:args){
				query.setParameter(index++,arg);
			}
		}
		return query.list();
	}

	public List<T> list(String hql) {
		// TODO Auto-generated method stub
		return list(hql,null);
	}

	public List<T> list(String hql, Object arg) {
		// TODO Auto-generated method stub
		return list(hql,new Object[]{arg});
	}

	public T load(long id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(getClz(), id);
	}

	public T loadByHql(String hql, Object[] args) {
		// TODO Auto-generated method stub
		T t=null;
		Query q=setQuery(hql,args);
		t=(T)q.uniqueResult();
		return t;
	}

	public T loadByHql(String hql, Object arg) {
		// TODO Auto-generated method stub
		return loadByHql(hql,new Object[]{arg});
	}

	public T loadByHql(String hql) {
		// TODO Auto-generated method stub
		return loadByHql(hql,null);
	}

	public Object loadObjectByHql(String hql, Object[] args) {
		// TODO Auto-generated method stub
		Object t=null;
		Query query=setQuery(hql,args);
		t=(Object)query.uniqueResult();
		return t;
	}

	public Object loadObjectByHql(String hql, Object arg) {
		// TODO Auto-generated method stub
		return loadObjectByHql(hql,new Object[]{arg});
	}

	public Object loadObjectByHql(String hql) {
		// TODO Auto-generated method stub
		return loadObjectByHql(hql,null);
	}
	
	public List<T> pageByHql(final String hql,int offset,int length) {
		List<T> list = null;
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		list = query.list();
		return list;
	}

	public int getAllRowCount(String hql) {
		return this.getSession().createQuery(hql).list().size();
	}
	
	/**
	 * 根据hql执行更新操作
	 * @param hql
	 * @param args
	 */
	protected void updateByHql(String hql,Object[] args){
		Query query=setQuery(hql,args);
		query.executeUpdate();
	}
	
	protected void updateByHql(String hql,Object arg){
		updateByHql(hql,new Object[]{arg});
	}
	
	protected void updateByHql(String hql){
		updateByHql(hql,null);
	}
	
	private Query setQuery(String hql,Object[] args){
		
		System.out.println(hql+"========");
		
		Query query=this.getSession().createQuery(hql);
		if(args!=null){
			int index=0;
			for(Object arg:args){
				query.setParameter(index++,arg);
			}
		}
		return query;
	}
	
	private String getCountHql(String hql){
		String str=hql.substring(hql.indexOf("from "));
		str="select count(*) "+str;
		str=str.replaceAll("fetch","");
		return str;
	}
	
	//批量删除
	public boolean batchDelete(String[] str){
		boolean flag=false;
		for(int i=0;i<str.length;i++){
			this.delete(Integer.parseInt(str[i]));
			flag=true;
		}
		return flag;
	}
	
	//---------------------------
	public <T> List<T> findAllBySql(String sql,Class<T> cls,String c,final Map<String, Object> params){
		SQLQuery query=this.getSession().createSQLQuery(sql);
		
		System.out.println("sql=="+sql);
		System.out.println("================"+params.size());
		for(Map.Entry<String, Object> e:params.entrySet()){
			
			System.out.println(e.getKey()+"---"+(Date)e.getValue());
			query.setTimestamp(e.getKey(), (Date) e.getValue());
		}
		
		System.out.println("================"+params.size());
		query.addEntity(c,cls);
		
		return query.list();
	}
	

}

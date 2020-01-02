package org.xyl.iservice;

import org.xyl.util.PageBean;

import org.xyl.bo.Index;
import org.xyl.bo.IndexField;

public interface IIndexService {
	
	public void addIndex(IndexField fields,boolean inDatabase);
	
	public void deleteIndex(String id);
	
	//清空所有的索引
	public void deleteAllIndex();
	
	public void updateIndex(IndexField fields);
	
	//设置索引，将数据库中没有添加为索引的对象完全添加
	public void updateSetIndex();
	
	//提交索引,仅仅是从内存中提交
	public void commitIndex();
	
	//重构索引
	public void reConstructorIndex();
	
	public PageBean<Index> findByIndex(String condition,int pageNumber,int pageSize);
	
}




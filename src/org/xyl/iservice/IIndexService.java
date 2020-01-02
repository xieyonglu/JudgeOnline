package org.xyl.iservice;

import org.xyl.util.PageBean;

import org.xyl.bo.Index;
import org.xyl.bo.IndexField;

public interface IIndexService {
	
	public void addIndex(IndexField fields,boolean inDatabase);
	
	public void deleteIndex(String id);
	
	//������е�����
	public void deleteAllIndex();
	
	public void updateIndex(IndexField fields);
	
	//���������������ݿ���û�����Ϊ�����Ķ�����ȫ���
	public void updateSetIndex();
	
	//�ύ����,�����Ǵ��ڴ����ύ
	public void commitIndex();
	
	//�ع�����
	public void reConstructorIndex();
	
	public PageBean<Index> findByIndex(String condition,int pageNumber,int pageSize);
	
}




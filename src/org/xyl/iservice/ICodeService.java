package org.xyl.iservice;

import java.util.Date;
import java.util.List;

import org.xyl.bean.Code;
import org.xyl.util.PageBean;

public interface ICodeService {

	public void addCode(Code code);
	
	public void updateCode(Code code);
	
	public void deleteCodeById(Long CodeId);
	
	public Code load(Long codeId);
	
	public List<Code> listAllCode();
	
	//�õ����к�
	public List<Code> listAllCodeByMatch(Long matchId);
	
	public List<Code> listAllCodeByMatchUser(Long matchId,Long userId);
	
	public PageBean<Code> find(String name);
	
	public PageBean<Code> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
	//�ҳ��ύ�Ĵ���������Сʱ��
	public Date findMaxMinDate(String flag);
	
}

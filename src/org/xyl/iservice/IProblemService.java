package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Problem;
import org.xyl.util.PageBean;

public interface IProblemService {

	public void addProblem(Problem problem);
	
	public void updateProblem(Problem problem);
	
	public void deleteProblemById(Long problemId);
	
	public Problem load(Long problemId);
	
	public Problem loadByTitle(String title);
	
	//ָ������������
	public Problem loadByTitleAndMatch(String title,Long matchId);
	
	//�ƶ���������ŵ�����
	public Problem loadBySequenceAndMatch(Integer sequence,Long matchId);
	
	public List<Problem> listAllProblem();
	
	//����ָ����������������
	public List<Problem> listMatchProblem(Long matchId);
	
	public PageBean<Problem> find(String name);
	
	public PageBean<Problem> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
}

package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.TestData;
import org.xyl.util.PageBean;

public interface ITestDataService {

	public void addTestData(TestData testData);
	
	public void updateTestData(TestData testData);
	
	public void deleteTestDataById(Long testDataId);
	
	public TestData load(Long testDataId);
	
	public List<TestData> findByProblemId(Long problemId);
	
	public PageBean<TestData> find(String name);
	
	public PageBean<TestData> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
}

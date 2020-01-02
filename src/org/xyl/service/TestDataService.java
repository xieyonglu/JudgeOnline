package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.TestData;
import org.xyl.idao.ITestDataDao;
import org.xyl.iservice.ITestDataService;
import org.xyl.util.PageBean;

@Service("testDataService")
public class TestDataService implements ITestDataService{

	private ITestDataDao testDataDao;
	
	@Resource
	public void setTestDataDao(ITestDataDao testDataDao) {
		this.testDataDao = testDataDao;
	}

	public void addTestData(TestData testData) {
		// TODO Auto-generated method stub
		testData.setCreateDate(new Date());
		testDataDao.add(testData);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=testDataDao.batchDelete(str);
		return flag;
	}

	public void deleteTestDataById(Long testDataId) {
		// TODO Auto-generated method stub
		testDataDao.delete(testDataId);
	}

	public PageBean<TestData> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return testDataDao.find("from TestData");
		}else{
			return testDataDao.find("from TestData where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<TestData> findByProblemId(Long problemId) {
		// TODO Auto-generated method stub
		return testDataDao.list("from TestData as obj where obj.problem.problemId='"+problemId+"'");
	}

	public TestData load(Long testDataId) {
		// TODO Auto-generated method stub
		return testDataDao.load(testDataId);
	}

	public PageBean<TestData> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = testDataDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<TestData> list = testDataDao.pageByHql(hql, offset, length);
		PageBean<TestData> pageBean = new PageBean<TestData>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateTestData(TestData testData) {
		// TODO Auto-generated method stub
		//testData.setCreateDate(new Date());
		testDataDao.update(testData);
	}

}

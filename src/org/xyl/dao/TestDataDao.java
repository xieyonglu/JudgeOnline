package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.TestData;
import org.xyl.idao.ITestDataDao;

@Repository("testDataDao")
public class TestDataDao extends BaseDao<TestData> implements ITestDataDao{

}

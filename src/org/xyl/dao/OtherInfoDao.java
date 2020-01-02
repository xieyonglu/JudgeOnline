package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.OtherInfo;
import org.xyl.idao.IOtherInfoDao;

@Repository("otherInfoDao")
public class OtherInfoDao extends BaseDao<OtherInfo> implements IOtherInfoDao{

}

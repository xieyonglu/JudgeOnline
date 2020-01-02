package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Submit;
import org.xyl.idao.ISubmitDao;

@Repository("submitDao")
public class SubmitDao extends BaseDao<Submit> implements ISubmitDao{

}

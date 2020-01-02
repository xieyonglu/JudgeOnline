package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Problem;
import org.xyl.idao.IProblemDao;

@Repository("problemDao")
public class ProblemDao extends BaseDao<Problem> implements IProblemDao{

}

package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Match;
import org.xyl.idao.IMatchDao;

@Repository("matchDao")
public class MatchDao extends BaseDao<Match> implements IMatchDao{

}

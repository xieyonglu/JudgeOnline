package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Code;
import org.xyl.idao.ICodeDao;

@Repository("codeDao")
public class CodeDao extends BaseDao<Code> implements ICodeDao{

}

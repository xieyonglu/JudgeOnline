package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Type;
import org.xyl.idao.ITypeDao;

@Repository("typeDao")
public class TypeDao extends BaseDao<Type> implements ITypeDao{

}

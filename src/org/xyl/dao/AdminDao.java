package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Admin;
import org.xyl.idao.IAdminDao;

@Repository("adminDao")
public class AdminDao extends BaseDao<Admin> implements IAdminDao{

}

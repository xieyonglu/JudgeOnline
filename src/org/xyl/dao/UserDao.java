package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.User;
import org.xyl.idao.IUserDao;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao{

}

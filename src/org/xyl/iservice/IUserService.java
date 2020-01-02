package org.xyl.iservice;

import java.util.Date;
import java.util.List;

import org.xyl.bean.User;
import org.xyl.util.PageBean;

public interface IUserService {

	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUserById(Long userId);
	
	public User load(Long userId);
	
	public List<User> listAllUser();
	
	public User login(String userName,String password);
	
	public PageBean<User> find(String userName);
	
	public PageBean<User> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public User loadByUserName(String userName);
	
	public User loadByEmail(String email);
	
	public boolean checkUser(String userName);
	
	public boolean batchDelete(String[] str);
	
	public List<User> sortUserByTime(Date startDate,Date endDate);
	
	//某一道题，做对做错的有哪些人
	public List<User> listSubmitUser(Long problemId,int state);

}

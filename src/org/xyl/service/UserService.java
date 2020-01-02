package org.xyl.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.xyl.bean.OtherInfo;
import org.xyl.bean.User;
import org.xyl.idao.IUserDao;
import org.xyl.iservice.IUserService;
import org.xyl.util.MessageException;
import org.xyl.util.PageBean;


@Service("userService")
public class UserService implements IUserService{

	private IUserDao userDao;
	
	@Resource
	public void setUserDao(IUserDao userDao){
		this.userDao=userDao;
	}
	
	
	public void addUser(User user) {
		// TODO Auto-generated method stub
		User u=loadByUserName(user.getUserName());
		if(u!=null) throw new MessageException("要添加的用户已经存在，不能添加");
		
		user.setPicture("default.jpg");//首先设置一个默认图片
		user.setCreateDate(new Date());
		user.setUserEnable(true);
		user.setCommentEnable(true);
		OtherInfo otherInfo=new OtherInfo();
		otherInfo.setUser(user);
		
		user.setOtherInfo(otherInfo);//级联插入otherInfo表
		
		userDao.add(user);
	}

	public boolean checkUser(String userName) {
		// TODO Auto-generated method stub
		long count=(Long)userDao.loadObjectByHql("select count(*) from User where userName=?",userName);
		return count>0?true:false;
	}

	public void deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		userDao.delete(userId);
	}

	public PageBean<User> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return userDao.find("from User");
		}else{
			return userDao.find("from User where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return userDao.list("from User");
	}

	public User load(Long userId) {
		// TODO Auto-generated method stub
		return userDao.load(userId);
	}

	public User loadByUserName(String userName){
		// TODO Auto-generated method stub
		return userDao.loadByHql("from User where userName=?",userName);
	}
	
	public User loadByEmail(String email){
		// TODO Auto-generated method stub
		return userDao.loadByHql("from User where email=?",email);
	}

	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		User user=loadByUserName(userName);
		if(user==null) throw new MessageException("用户不存在！");
		if(!user.getPassword().equals(password)) 
			throw new MessageException("用户密码不正确！");
		if(!user.isUserEnable())
			throw new MessageException("该用户已经被停用，请与管理员联系");
		
		return user;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	public PageBean<User> pageByCondition(String hql, int pageNumber,int pageSize) {
		//String hql = "from Student";
		int allRow = userDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<User> list = userDao.pageByHql(hql, offset, length);
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}
	
	//批量删除
	public boolean batchDelete(String[] str){
		boolean flag=userDao.batchDelete(str);
		System.out.println("flag-------"+flag);
		return flag;
	}

	public List<User> sortUserByTime(Date startDate,Date endDate){
		// TODO Auto-generated method stub
		
		String sql="select aa as {u.userId},bb as {u.trueProblemCount},cc as {u.passCount},dd as {u.submitCount},dd-cc as {u.falseProblemCount},'userName' as {u.userName},'password' as {u.password},'email' as {u.email},'2000-10-10' as {u.createDate},'0' as {u.question},'0' as {u.answer},'0' as {u.picture},'0' as {u.userEnable},'0' as {u.commentEnable} ,'1' as {u.otherInfo} from("+
					"select A.userId as aa,A.cmt as bb,B.cmt as cc,C.cmt as dd from"+ 
					"(select a.userId,count(*) cmt from (select distinct userId,problemId from t_code where state='rightAnswer'  and createDate between :startDate and :endDate and matchId is null) a group by a.userId) A,"+
					"(select userId,count(*) cmt from t_code where state='rightAnswer' and createDate between :startDate and :endDate and matchId is null group by userId) B,"+
					"(select userId,count(*) cmt from t_code where createDate between :startDate and :endDate and matchId is null group by userId) C "+
					"where A.userId=B.userId and A.userId=C.userId order by A.cmt desc,B.cmt/C.cmt desc"+
					") u";

		System.out.println(startDate+"---userService---"+endDate);
			
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startDate",startDate);
		map.put("endDate",endDate);
			
		return this.userDao.findAllBySql(sql, User.class, "u", map);
	}


	public List<User> listSubmitUser(Long problemId, int state) {
		// TODO Auto-generated method stub
		String hql="select obj.user.userId from Submit as obj where obj.problem.problemId='"+problemId+"'";
		if(state==0){
			hql=hql+" and obj.passCount=0";
		}else{
			hql=hql+" and obj.passCount!=0";
		}
		hql="from User as u where u.userId in("+hql+")";
		return this.userDao.list(hql);
	}
	
	
}









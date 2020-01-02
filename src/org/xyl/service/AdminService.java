package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Admin;
import org.xyl.idao.IAdminDao;
import org.xyl.iservice.IAdminService;
import org.xyl.util.MessageException;
import org.xyl.util.PageBean;

@Service("adminService")
public class AdminService implements IAdminService{

	private IAdminDao adminDao;
	
	
	public IAdminDao getAdminDao() {
		return adminDao;
	}

	@Resource
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Admin a=loadByUserName(admin.getUserName());
		if(a!=null) throw new MessageException("要添加的用户已经存在，不能添加");
		admin.setCreateDate(new Date());
		adminDao.add(admin);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=adminDao.batchDelete(str);
		System.out.println("flag-------"+flag);
		return flag;
	}

	public boolean checkAdmin(String userName) {
		// TODO Auto-generated method stub
		long count=(Long)adminDao.loadObjectByHql("select count(*) from Admin where userName=?",userName);
		return count>0?true:false;
	}

	public void deleteAdminById(Long adminId) {
		// TODO Auto-generated method stub
		adminDao.delete(adminId);
	}

	public PageBean<Admin> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return adminDao.find("from Admin");
		}else{
			return adminDao.find("from Admin where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<Admin> listAllAdmin() {
		// TODO Auto-generated method stub
		return adminDao.list("from Admin");
	}

	public Admin load(Long adminId) {
		// TODO Auto-generated method stub
		return adminDao.load(adminId);
	}

	public Admin loadByUserName(String userName) {
		// TODO Auto-generated method stub
		return adminDao.loadByHql("from Admin where userName=?",userName);
	}

	public Admin login(String userName, String password) {
		// TODO Auto-generated method stub
		Admin admin=loadByUserName(userName);
		if(admin==null) throw new MessageException("用户不存在！");
		if(!admin.getPassword().equals(password)) 
			throw new MessageException("用户密码不正确！");
		return admin;
	}

	public PageBean<Admin> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow=adminDao.getAllRowCount(hql);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		
		int length=pageSize;
		int currentPage=PageBean.countCurrentPage(pageNumber,totalPage);
		int offset=PageBean.countOffset(pageSize, currentPage);
		
		List<Admin> list=adminDao.pageByHql(hql, offset, length);
		PageBean<Admin> pageBean=new PageBean<Admin>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		//admin.setCreateDate(new Date());
		adminDao.update(admin);
	}

}

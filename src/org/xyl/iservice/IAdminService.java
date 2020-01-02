package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Admin;
import org.xyl.util.PageBean;

public interface IAdminService {

	public void addAdmin(Admin admin);
	
	public void updateAdmin(Admin admin);
	
	public void deleteAdminById(Long adminId);
	
	public Admin load(Long adminId);
	
	public List<Admin> listAllAdmin();
	
	public Admin login(String userName,String password);
	
	public PageBean<Admin> find(String name);
	
	public PageBean<Admin> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public Admin loadByUserName(String userName);
	
	public boolean checkAdmin(String userName);
	
	public boolean batchDelete(String[] str);
	
}

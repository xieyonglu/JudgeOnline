package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.IpAddress;
import org.xyl.idao.IIpAddressDao;
import org.xyl.iservice.IIpAddressService;
import org.xyl.util.PageBean;

@Service("ipAddressService")
public class IpAddressService implements IIpAddressService{

	private IIpAddressDao ipAddressDao;
	
	
	public IIpAddressDao getIpAddressDao() {
		return ipAddressDao;
	}

	@Resource
	public void setIpAddressDao(IIpAddressDao ipAddressDao) {
		this.ipAddressDao = ipAddressDao;
	}

	public void addIpAddress(IpAddress ipAddress) {
		// TODO Auto-generated method stub
		ipAddress.setCreateDate(new Date());
		ipAddressDao.add(ipAddress);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=ipAddressDao.batchDelete(str);
		System.out.println("flag-------"+flag);
		return flag;
	}

	public void deleteIpAddressById(Long ipAddressId) {
		// TODO Auto-generated method stub
		ipAddressDao.delete(ipAddressId);
	}

	public List<IpAddress> findIpByFromTo(String from,String to){
		// TODO Auto-generated method stub
		String hql="from IpAddress as obj where obj.from='"+from+"' and obj.to='"+to+"'";
		return ipAddressDao.list(hql);
	}
	
	public List<IpAddress> findIpByFromToMatch(String from,String to,Long matchId){
		// TODO Auto-generated method stub
		String hql="from IpAddress as obj where obj.from='"+from+"' and obj.to='"+to+"' and obj.match.matchId='"+matchId+"'";
		return ipAddressDao.list(hql);
	}

	public List<IpAddress> listAllIpAddress() {
		// TODO Auto-generated method stub
		return ipAddressDao.list("from IpAddress");
	}
	
	public List<IpAddress> listAllIpAddressByMatchId(Long matchId){
		// TODO Auto-generated method stub
		return ipAddressDao.list("from IpAddress as obj where obj.match.matchId='"+matchId+"'");
	}
	

	public IpAddress load(Long ipAddressId) {
		// TODO Auto-generated method stub
		return ipAddressDao.load(ipAddressId);
	}

	public PageBean<IpAddress> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow=ipAddressDao.getAllRowCount(hql);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		
		int length=pageSize;
		int currentPage=PageBean.countCurrentPage(pageNumber,totalPage);
		int offset=PageBean.countOffset(pageSize, currentPage);
		
		List<IpAddress> list=ipAddressDao.pageByHql(hql, offset, length);
		PageBean<IpAddress> pageBean=new PageBean<IpAddress>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateIpAddress(IpAddress ipAddress) {
		// TODO Auto-generated method stub
		ipAddress.setCreateDate(new Date());
		ipAddressDao.update(ipAddress);
	}

}

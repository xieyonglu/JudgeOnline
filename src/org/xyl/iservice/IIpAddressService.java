package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.IpAddress;
import org.xyl.util.PageBean;

public interface IIpAddressService {

	public void addIpAddress(IpAddress ipAddress);
	
	public void updateIpAddress(IpAddress ipAddress);
	
	public void deleteIpAddressById(Long ipAddressId);
	
	public IpAddress load(Long ipAddressId);
	
	public List<IpAddress> findIpByFromTo(String from,String to);
	
	public List<IpAddress> findIpByFromToMatch(String from,String to,Long matchId);
	
	public List<IpAddress> listAllIpAddress();
	
	public List<IpAddress> listAllIpAddressByMatchId(Long matchId);
	
	public PageBean<IpAddress> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public boolean batchDelete(String[] str);
	
}

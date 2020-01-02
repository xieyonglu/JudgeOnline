package org.xyl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.OtherInfo;
import org.xyl.idao.IOtherInfoDao;
import org.xyl.iservice.IOtherInfoService;


@Service("otherInfoService")
public class OtherInfoService implements IOtherInfoService{

	private IOtherInfoDao otherInfoDao;
	
	
	public IOtherInfoDao getOtherInfoDao() {
		return otherInfoDao;
	}
	
	@Resource
	public void setOtherInfoDao(IOtherInfoDao otherInfoDao) {
		this.otherInfoDao = otherInfoDao;
	}

	public void add(OtherInfo otherInfo) {
		// TODO Auto-generated method stub
		otherInfoDao.add(otherInfo);
	}

	public void update(OtherInfo otherInfo) {
		// TODO Auto-generated method stub
		otherInfoDao.update(otherInfo);
	}

}

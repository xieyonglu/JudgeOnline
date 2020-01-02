package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.IpAddress;
import org.xyl.idao.IIpAddressDao;

@Repository("ipAddressDao")
public class IpAddressDao extends BaseDao<IpAddress> implements IIpAddressDao{

}

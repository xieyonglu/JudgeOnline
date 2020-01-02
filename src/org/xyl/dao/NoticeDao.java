package org.xyl.dao;

import org.springframework.stereotype.Repository;
import org.xyl.bean.Notice;
import org.xyl.idao.INoticeDao;

@Repository("noticeDao")
public class NoticeDao extends BaseDao<Notice> implements INoticeDao{

}

package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Notice;
import org.xyl.idao.INoticeDao;
import org.xyl.iservice.INoticeService;
import org.xyl.util.PageBean;

@Service("noticeService")
public class NoticeService implements INoticeService{

	private INoticeDao noticeDao;
	
	@Resource
	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		notice.setCreateDate(new Date());
		notice.setBrowser(0);
		noticeDao.add(notice);
	}

	

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=noticeDao.batchDelete(str);
		return flag;
	}
	

	public void deleteNoticeById(Long noticeId) {
		// TODO Auto-generated method stub
		noticeDao.delete(noticeId);
	}

	public PageBean<Notice> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return noticeDao.find("from Notice");
		}else{
			return noticeDao.find("from Notice where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<Notice> listAllNoticeByHql(String hql) {
		// TODO Auto-generated method stub
		return noticeDao.list(hql);
	}

	public Notice load(Long noticeId) {
		// TODO Auto-generated method stub
		return noticeDao.load(noticeId);
	}
	

	public PageBean<Notice> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = noticeDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<Notice> list = noticeDao.pageByHql(hql, offset, length);
		PageBean<Notice> pageBean = new PageBean<Notice>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		//notice.setCreateDate(new Date());
		noticeDao.update(notice);
	}


}

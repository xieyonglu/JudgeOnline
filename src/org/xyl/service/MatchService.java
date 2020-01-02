package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Match;
import org.xyl.idao.IMatchDao;
import org.xyl.iservice.IMatchService;
import org.xyl.util.PageBean;

@Service("matchService")
public class MatchService implements IMatchService{

	private IMatchDao matchDao;

	@Resource
	public void setMatchDao(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}

	public void addMatch(Match match) {
		// TODO Auto-generated method stub
		match.setCreateDate(new Date());
		matchDao.add(match);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=matchDao.batchDelete(str);
		return flag;
	}
	

	public void deleteMatchById(Long matchId) {
		// TODO Auto-generated method stub
		matchDao.delete(matchId);
	}

	public PageBean<Match> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return matchDao.find("from Match");
		}else{
			return matchDao.find("from Match where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<Match> listAllMatch() {
		// TODO Auto-generated method stub
		return matchDao.list("from Match");
	}

	public Match load(Long matchId) {
		// TODO Auto-generated method stub
		return matchDao.load(matchId);
	}

	public Match loadByMatchName(String matchName) {
		// TODO Auto-generated method stub
		return matchDao.loadByHql("from Match where matchName=?",matchName);
	}
	

	public PageBean<Match> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = matchDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<Match> list = matchDao.pageByHql(hql, offset, length);
		PageBean<Match> pageBean = new PageBean<Match>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateMatch(Match match) {
		// TODO Auto-generated method stub
		//match.setCreateDate(new Date());
		matchDao.update(match);
	}

}

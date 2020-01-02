package org.xyl.iservice;

import java.util.List;

import org.xyl.bean.Match;
import org.xyl.util.PageBean;

public interface IMatchService {

	public void addMatch(Match match);
	
	public void updateMatch(Match match);
	
	public void deleteMatchById(Long adminId);
	
	public Match load(Long matchId);
	
	public List<Match> listAllMatch();
	
	public PageBean<Match> find(String matchName);
	
	public PageBean<Match> pageByCondition(String hql,int pageNumber,int pageSize);
	
	public Match loadByMatchName(String matchName);
	
	public boolean batchDelete(String[] str);
	
}

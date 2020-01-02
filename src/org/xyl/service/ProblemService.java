package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Problem;
import org.xyl.idao.IProblemDao;
import org.xyl.iservice.IProblemService;
import org.xyl.util.PageBean;

@Service("problemService")
public class ProblemService implements IProblemService{

	private IProblemDao problemDao;
	
	
	@Resource
	public void setProblemDao(IProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	public void addProblem(Problem problem) {
		// TODO Auto-generated method stub
		problem.setCreateDate(new Date());
		problem.setPassCount(0L);
		problem.setSubmitCount(0L);
		problemDao.add(problem);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=problemDao.batchDelete(str);
		return flag;
	}

	
	public void deleteProblemById(Long problemId) {
		// TODO Auto-generated method stub
		problemDao.delete(problemId);
	}

	public PageBean<Problem> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return problemDao.find("from Problem");
		}else{
			return problemDao.find("from Problem where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<Problem> listAllProblem() {
		// TODO Auto-generated method stub
		return problemDao.list("from Problem");
	}

	public Problem load(Long problemId) {
		// TODO Auto-generated method stub
		return problemDao.load(problemId);
	}
	

	public PageBean<Problem> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow = problemDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		
		int length = pageSize;
		int currentPage = PageBean.countCurrentPage(pageNumber,totalPage);
		int offset = PageBean.countOffset(pageSize, currentPage);
		
		List<Problem> list = problemDao.pageByHql(hql, offset, length);
		PageBean<Problem> pageBean = new PageBean<Problem>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateProblem(Problem problem) {
		// TODO Auto-generated method stub
		//problem.setCreateDate(new Date());
		problemDao.update(problem);
	}
	
	public Problem loadByTitle(String title) {
		// TODO Auto-generated method stub
		return problemDao.loadByHql("from Problem where title=?",title);
	}

	
	public Problem loadByTitleAndMatch(String title, Long matchId) {
		// TODO Auto-generated method stub
		String hql="from Problem as obj where obj.title='"+title+"' and obj.match.matchId=?";
		return problemDao.loadByHql(hql,matchId);
	}
	
	public Problem loadBySequenceAndMatch(Integer sequence, Long matchId) {
		// TODO Auto-generated method stub
		String hql="from Problem as obj where obj.sequence='"+sequence+"' and obj.match.matchId=?";
		return problemDao.loadByHql(hql,matchId);
	}

	public List<Problem> listMatchProblem(Long matchId) {
		// TODO Auto-generated method stub
		String hql="from Problem as obj where obj.match.matchId='"+matchId+"'";
		return problemDao.list(hql);
	}

}



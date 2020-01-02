package org.xyl.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xyl.bean.Code;
import org.xyl.idao.ICodeDao;
import org.xyl.iservice.ICodeService;
import org.xyl.util.PageBean;

@Service("codeService")
public class CodeService implements ICodeService{

	private ICodeDao codeDao;

	@Resource
	public void setCodeDao(ICodeDao codeDao) {
		this.codeDao = codeDao;
	}

	public void addCode(Code code) {
		// TODO Auto-generated method stub
		code.setCreateDate(new Date());
		codeDao.add(code);
	}

	public boolean batchDelete(String[] str) {
		// TODO Auto-generated method stub
		boolean flag=codeDao.batchDelete(str);
		return flag;
	}

	public void deleteCodeById(Long codeId) {
		// TODO Auto-generated method stub
		codeDao.delete(codeId);
	}

	public PageBean<Code> find(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name.trim())){
			return codeDao.find("from Code");
		}else{
			return codeDao.find("from Code where nickname like ? or userName like ?",
					new Object[]{"%"+name+"%","%"+name+"%"});
			
		}
	}

	public List<Code> listAllCode() {
		// TODO Auto-generated method stub
		return codeDao.list("from Code");
	}
	
	public List<Code> listAllCodeByMatch(Long matchId){
		// TODO Auto-generated method stub
		String hql="from Code as obj where 1=1 ";
		if(matchId==null){
			hql=hql+" and obj.match.matchId is null";
		}else{
			hql=hql+" and obj.match.matchId ='"+matchId+"'";
		}
		return codeDao.list(hql);
	}
	
	public List<Code> listAllCodeByMatchUser(Long matchId,Long userId){
		// TODO Auto-generated method stub
		String hql="from Code as obj where obj.match.matchId='"+matchId+"' and obj.user.userId='"+userId+"'";
		return codeDao.list(hql);
	}

	public Code load(Long codeId) {
		// TODO Auto-generated method stub
		return codeDao.load(codeId);
	}


	public PageBean<Code> pageByCondition(String hql, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		int allRow=codeDao.getAllRowCount(hql);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		
		int length=pageSize;
		int currentPage=PageBean.countCurrentPage(pageNumber,totalPage);
		int offset=PageBean.countOffset(pageSize, currentPage);
		
		List<Code> list=codeDao.pageByHql(hql, offset, length);
		PageBean<Code> pageBean=new PageBean<Code>();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		
		return pageBean;
	}

	public void updateCode(Code code) {
		// TODO Auto-generated method stub
		code.setCreateDate(new Date());
		codeDao.update(code);
	}
	
	public Date findMaxMinDate(String flag){
		// TODO Auto-generated method stub
		String hql="";
		if("max".equals(flag)){
			hql="select max(createDate) from Code as c where c.state='rightAnswer'";	
		}else{
			hql="select min(createDate) from Code as c where c.state='rightAnswer'";
		}
		Date date=(Date)codeDao.loadObjectByHql(hql);
		return date;
	}

}

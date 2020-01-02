package org.xyl.util;

import java.util.List;

public class PageBean<E> {

	private List<E> list;
	
	private int allRow;
	private int totalPage;
	private int currentPage;
	private int pageSize;
	
	private boolean isFirstPage;
	private boolean isLastPage;
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isFirstPage() {
		return currentPage == 1;
	}

	public boolean isLastPage() {
		return currentPage == totalPage;
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1;
	}

	public boolean isHasNextPage() {
		return currentPage != totalPage;
	}
	
	//以下是三个静态方法
	
	public static int countTotalPage(int pageSize,int allRow){
		int totalPage=allRow%pageSize==0?allRow/pageSize:allRow/pageSize+1;
		return totalPage;
	}
	
	public static int countOffset(int pageSize,int currentPage){
		int offset=pageSize*(currentPage-1);
		return offset;
	}
	
	public static int countCurrentPage(int page,int totalPage){
		int curPage=(page<=0?1:page);
		curPage=(curPage>=totalPage)?totalPage:curPage;
		return curPage;
	}

	
	
}

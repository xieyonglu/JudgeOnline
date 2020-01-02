package org.xyl.bo;

import java.util.Date;

/**
 * Ë÷Òý¶ÔÏó
 * @author Administrator
 *
 */
public class Index {
	
	private Long noticeId;
	private String title;
	private String author;
	private String content;
	private int browser;
	private Date createDate;
	
	public Index(){}
	
	public Long getNoticeId() {
		return noticeId;
	}
	
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getBrowser() {
		return browser;
	}
	
	public void setBrowser(int browser) {
		this.browser = browser;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}



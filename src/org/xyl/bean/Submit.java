package org.xyl.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_submit")
public class Submit {

	private Long submitId;
	private Long passCount;
	private Long submitCount;
	private Integer state;//ÕýÈ·×´Ì¬
	
	private Date firstTrueDate;
	private Date lastSubmitDate;
	
	private User user;
	private Problem problem;
	private Match match;
	
	@Id @GeneratedValue
	public Long getSubmitId() {
		return submitId;
	}
	
	public void setSubmitId(Long submitId) {
		this.submitId = submitId;
	}
	
	@Column(name="passCount",nullable=false)
	public Long getPassCount() {
		return passCount;
	}
	
	public void setPassCount(Long passCount) {
		this.passCount = passCount;
	}
	
	@Column(name="submitCount",nullable=false)
	public Long getSubmitCount() {
		return submitCount;
	}
	
	public void setSubmitCount(Long submitCount) {
		this.submitCount = submitCount;
	}
	
	@Column(name="state",nullable=false)
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name="firstTrueDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFirstTrueDate() {
		return firstTrueDate;
	}
	
	public void setFirstTrueDate(Date firstTrueDate) {
		this.firstTrueDate = firstTrueDate;
	}
	
	@Column(name="lastSubmitDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastSubmitDate() {
		return lastSubmitDate;
	}

	public void setLastSubmitDate(Date lastSubmitDate) {
		this.lastSubmitDate = lastSubmitDate;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="userId",nullable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false/*,fetch=FetchType.EAGER*/)
	@JoinColumn(name="problemId",nullable=false)
	public Problem getProblem() {
		return problem;
	}
	
	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true/*,fetch=FetchType.EAGER*/)
	@JoinColumn(name="matchId",nullable=true)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	
	
}




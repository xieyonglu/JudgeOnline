package org.xyl.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_match")
public class Match {

	private Long matchId;
	private String matchName;
	private String author;
	private Integer state;
	private String address;
	private String content;
	private Integer problemCount;
	private Integer personCount;
	
	private Date startDate;
	private Date endDate;
	private Date createDate;
	
	private Admin admin;
	
	private Set<Form> forms=new HashSet<Form>();
	private Set<Problem> problems=new HashSet<Problem>();
	private Set<Code> codes=new HashSet<Code>();
	private Set<Comment> comments=new HashSet<Comment>();
	private Set<Submit> submits=new HashSet<Submit>();
	private Set<IpAddress> ipAddresses=new HashSet<IpAddress>();
	
	
	@Id @GeneratedValue
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	
	@Column(name="matchName",nullable=false,length=20)
	public String getMatchName(){
		return matchName;
	}

	public void setMatchName(String matchName){
		this.matchName = matchName;
	}

	@Column(name="author",nullable=false,length=20)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name="state",nullable=false,length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name="address",nullable=false,length=20)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="problemCount",nullable=false)
	public Integer getProblemCount() {
		return problemCount;
	}

	public void setProblemCount(Integer problemCount) {
		this.problemCount = problemCount;
	}
	
	@Column(name="personCount",nullable=false)
	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	@Column(nullable=false,name="startDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(nullable=false,name="endDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(nullable=false,name="createDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="adminId")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public Set<Form> getForms() {
		return forms;
	}

	public void setForms(Set<Form> forms) {
		this.forms = forms;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public Set<Problem> getProblems() {
		return problems;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public void setProblems(Set<Problem> problems){
		this.problems = problems;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public Set<Code> getCodes() {
		return codes;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public Set<Submit> getSubmits() {
		return submits;
	}

	public void setSubmits(Set<Submit> submits) {
		this.submits = submits;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="match",fetch=FetchType.LAZY)
	public Set<IpAddress> getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(Set<IpAddress> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}
	
	
}





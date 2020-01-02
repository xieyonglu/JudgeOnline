package org.xyl.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_code")
public class Code{
	
	private Long codeId;
	private String codeText;
	private String state;
	private String language;
	private String memory;
	private String runtime;
	private Integer codeLength;
	private Date createDate;
	private String ipaddress;
	private String runNumber;//‘À––∫≈
	
	private Problem problem;
	private User user;
	private Match match;
	
	public Code(){}
	
	public Code(String codeText,String state,String language,String memory,String runtime,Integer codeLength,Date createDate,String ipaddress){
		this.codeText=codeText;
		this.state=state;
		this.language=language;
		this.memory=memory;
		this.runtime=runtime;
		this.codeLength=codeLength;
		this.createDate=createDate;
		this.ipaddress=ipaddress;
	}

	@Id @GeneratedValue
	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	@Lob
	public String getCodeText() {
		return codeText;
	}

	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}

	@Column(name="state",nullable=false,length=20)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="language",nullable=false,length=20)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name="memory",nullable=false,length=20)
	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	@Column(name="runtime",nullable=false,length=20)
	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	@Column(name="codeLength",nullable=false,length=20)
	public Integer getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(Integer codeLength) {
		this.codeLength = codeLength;
	}
	
	@Column(name="runNumber",nullable=false,length=20)
	public String getRunNumber() {
		return runNumber;
	}

	public void setRunNumber(String runNumber) {
		this.runNumber = runNumber;
	}

	@Column(nullable=false,name="createDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="ipaddress",nullable=true,length=30)
	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="problemId")
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)
	@JoinColumn(name="matchId",nullable=true)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	
}

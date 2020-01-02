package org.xyl.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_form")
public class Form {

	private Long formId;
	private Long userId;
	private String userName;
	private Integer problemCount;
	private Long totalTime;
	
	//目前最大是十五道试题--应该够用
	private String problemA;
	private String problemB;
	private String problemC;
	private String problemD;
	private String problemE;
	
	private String problemF;
	private String problemG;
	private String problemH;
	private String problemI;
	private String problemJ;
	
	private String problemK;
	private String problemL;
	private String problemM;
	private String problemN;
	private String problemO;
	
	private Match match;
	
	public Form(){}
	
	@Id @GeneratedValue
	public Long getFormId() {
		return formId;
	}
	
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	
	@Column(name="userId",nullable=false)
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="userName",nullable=false,length=16)
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="problemCount",nullable=false)
	public Integer getProblemCount() {
		return problemCount;
	}
	
	public void setProblemCount(Integer problemCount) {
		this.problemCount = problemCount;
	}
	
	@Column(name="totalTime",nullable=false)
	public Long getTotalTime() {
		return totalTime;
	}
	
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
	
	@Column(name="problemA",nullable=true,length=20)
	public String getProblemA() {
		return problemA;
	}
	
	public void setProblemA(String problemA) {
		this.problemA = problemA;
	}
	
	@Column(name="problemB",nullable=true,length=20)
	public String getProblemB() {
		return problemB;
	}
	
	public void setProblemB(String problemB) {
		this.problemB = problemB;
	}
	
	@Column(name="problemC",nullable=true,length=20)
	public String getProblemC() {
		return problemC;
	}
	
	public void setProblemC(String problemC) {
		this.problemC = problemC;
	}
	
	@Column(name="problemD",nullable=true,length=20)
	public String getProblemD() {
		return problemD;
	}
	
	public void setProblemD(String problemD) {
		this.problemD = problemD;
	}
	
	@Column(name="problemE",nullable=true,length=20)
	public String getProblemE() {
		return problemE;
	}
	
	public void setProblemE(String problemE) {
		this.problemE = problemE;
	}
	
	@Column(name="problemF",nullable=true,length=20)
	public String getProblemF() {
		return problemF;
	}
	
	public void setProblemF(String problemF) {
		this.problemF = problemF;
	}
	
	@Column(name="problemG",nullable=true,length=20)
	public String getProblemG() {
		return problemG;
	}
	
	public void setProblemG(String problemG) {
		this.problemG = problemG;
	}
	
	@Column(name="problemH",nullable=true,length=20)
	public String getProblemH() {
		return problemH;
	}
	
	public void setProblemH(String problemH) {
		this.problemH = problemH;
	}
	
	@Column(name="problemI",nullable=true,length=20)
	public String getProblemI() {
		return problemI;
	}
	
	public void setProblemI(String problemI) {
		this.problemI = problemI;
	}
	
	@Column(name="problemJ",nullable=true,length=20)
	public String getProblemJ() {
		return problemJ;
	}
	
	public void setProblemJ(String problemJ) {
		this.problemJ = problemJ;
	}
	
	@Column(name="problemK",nullable=true,length=20)
	public String getProblemK() {
		return problemK;
	}
	
	public void setProblemK(String problemK) {
		this.problemK = problemK;
	}
	
	@Column(name="problemL",nullable=true,length=20)
	public String getProblemL() {
		return problemL;
	}
	
	public void setProblemL(String problemL) {
		this.problemL = problemL;
	}
	
	@Column(name="problemM",nullable=true,length=20)
	public String getProblemM() {
		return problemM;
	}
	
	public void setProblemM(String problemM) {
		this.problemM = problemM;
	}
	
	@Column(name="problemN",nullable=true,length=20)
	public String getProblemN() {
		return problemN;
	}
	
	public void setProblemN(String problemN) {
		this.problemN = problemN;
	}
	
	@Column(name="problemO",nullable=true,length=20)
	public String getProblemO() {
		return problemO;
	}
	
	public void setProblemO(String problemO) {
		this.problemO = problemO;
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






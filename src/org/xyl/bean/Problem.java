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
@Table(name="t_problem")
public class Problem{

	private Long problemId;
	
	private String title;
	private String description;
	private String input;//输入
	private String output;//输出
	private String caseInput;//样例输入
	private String caseOutput;//样例输出
	private String source;//来源
	
	private Integer hardFactor;//难度系数
	private String timeLimit;
	private String memoryLimit;
	
	private Date createDate;
	private String author;
	private String typeName;
	
	private Long submitCount;
	private Long passCount;
	private Integer sequence;//如果是试题，则表示题的顺序
	
	private Type type;
	private Admin admin;
	private Match match;
	
	private Set<Code> codes=new HashSet<Code>();
	private Set<Comment> comments=new HashSet<Comment>();
	private Set<TestData> testDatas=new HashSet<TestData>();
	private Set<Submit> submits=new HashSet<Submit>(); 
	
	public Problem(){}


	@Id @GeneratedValue
	public Long getProblemId() {
		return problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	@Column(name="title",nullable=false,length=50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="input",nullable=false,length=500)
	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}

	@Column(name="output",nullable=false,length=500)
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Column(name="caseInput",nullable=false,length=500)
	public String getCaseInput() {
		return caseInput;
	}

	public void setCaseInput(String caseInput) {
		this.caseInput = caseInput;
	}

	@Column(name="caseOutput",nullable=false,length=500)
	public String getCaseOutput() {
		return caseOutput;
	}

	public void setCaseOutput(String caseOutput) {
		this.caseOutput = caseOutput;
	}

	@Column(name="source",nullable=false,length=20)
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

	@Column(name="hardFactor",nullable=false)
	public Integer getHardFactor() {
		return hardFactor;
	}


	public void setHardFactor(Integer hardFactor) {
		this.hardFactor = hardFactor;
	}

	@Column(name="timeLimit",nullable=false,length=20)
	public String getTimeLimit() {
		return timeLimit;
	}
	
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name="memoryLimit",nullable=false,length=20)
	public String getMemoryLimit() {
		return memoryLimit;
	}
	
	public void setMemoryLimit(String memoryLimit) {
		this.memoryLimit = memoryLimit;
	}

	@Column(nullable=false,name="createDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(nullable=false,name="author",length=20)
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(nullable=false,name="typeName",length=20)
	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	@Column(name="submitCount",nullable=false)
	public Long getSubmitCount() {
		return submitCount;
	}
	
	public void setSubmitCount(Long submitCount) {
		this.submitCount = submitCount;
	}

	@Column(name="passCount",nullable=false)
	public Long getPassCount() {
		return passCount;
	}

	public void setPassCount(Long passCount) {
		this.passCount = passCount;
	}
	
	@Column(name="sequence")
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)
	@JoinColumn(name="typeId",nullable=true)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)
	@JoinColumn(name="adminId",nullable=true)
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin){
		this.admin = admin;
	}
	
	//可以取空值
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)
	@JoinColumn(name="matchId",nullable=true)
	public Match getMatch(){
		return match;
	}

	public void setMatch(Match match){
		this.match = match;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="problem",fetch=FetchType.LAZY)
	public Set<Code> getCodes() {
		return codes;
	}

	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="problem",fetch=FetchType.LAZY)
	public Set<Comment> getComments() {
		return comments;
	}
	
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="problem",fetch=FetchType.LAZY)
	public Set<TestData> getTestDatas() {
		return testDatas;
	}
	
	public void setTestDatas(Set<TestData> testDatas){
		this.testDatas = testDatas;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="problem",fetch=FetchType.LAZY)
	public Set<Submit> getSubmits() {
		return submits;
	}
	
	public void setSubmits(Set<Submit> submits) {
		this.submits = submits;
	}
	
	
}

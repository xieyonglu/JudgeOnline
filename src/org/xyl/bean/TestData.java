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
@Table(name="t_testData")
public class TestData{
	
	private Long testDataId;
	private String input;
	private String output;
	private Date createDate;
	
	private Problem problem;

	public TestData() {}

	public TestData(long testDataId,String input, String output) {
		this.testDataId = testDataId;
		this.input = input;
		this.output = output;
	}

	@Id @GeneratedValue
	public Long getTestDataId() {
		return testDataId;
	}

	public void setTestDataId(Long testDataId) {
		this.testDataId = testDataId;
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

	@Column(nullable=false,name="createDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="problemId")
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	

}

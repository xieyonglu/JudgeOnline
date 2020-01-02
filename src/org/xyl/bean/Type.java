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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_type")
public class Type{
	
	private Long typeId;
	private String typeName;
	private Long problemCount;
	private Date createDate;
	
	private Set<Problem> problems=new HashSet<Problem>();
	
	public Type(){}
	
	public Type(String typeName,long problemCount,Date createDate){
		this.typeName=typeName;
		this.problemCount=problemCount;
		this.createDate=createDate;
	}

	@Id @GeneratedValue
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(length=20,nullable=false,name="typeName")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(nullable=false,name="problemCount")
	public Long getProblemCount() {
		return problemCount;
	}

	public void setProblemCount(Long problemCount) {
		this.problemCount = problemCount;
	}

	@Column(nullable=false,name="createDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="type",fetch=FetchType.LAZY)
	public Set<Problem> getProblems() {
		return problems;
	}

	public void setProblems(Set<Problem> problems) {
		this.problems = problems;
	}
	
	
}




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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_user")
public class User {

	private Long userId;
	
	private String userName;
	private String password;
	private String email;
	private String question;
	private String answer;
	private String picture;
	
	private Date createDate;//���û��Ĵ���ʱ��
	private boolean userEnable;//���û��Ƿ����
	private boolean commentEnable;//���û��������Ƿ����
	
	private int passCount;//ͨ���ܴ���
	private int submitCount;//�ύ�ܴ���
	private int trueProblemCount;//����ȷ��������
	private int falseProblemCount;//�������������
	
	private OtherInfo otherInfo;
	
	private Set<Comment> comment=new HashSet<Comment>();
	private Set<Code> codes=new HashSet<Code>();
	private Set<Submit> submits=new HashSet<Submit>();
	
	public User(){}
	
	public User(String userName,String password,String question,String answer,String email){
		this.userName=userName;
		this.password=password;
		this.question=question;
		this.answer=answer;
		this.email=email;
	}
	
	public User(String userName,String password,String email,String question,String answer,boolean userEnable,boolean commentEnable){
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.question=question;
		this.answer=answer;
		this.userEnable=userEnable;
		this.commentEnable=commentEnable;
	}
	
	@Override
	public String toString(){
		return "�û���:"+userName+"\t���룺"+password+"\t���䣺"+email+"\t���⣺"+question+"\t��:"+answer+"\n";
	}
	
	
	/**
	 * ��ȡ����
	 * @return ����
	 */
	@Id @GeneratedValue
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * ��������
	 * @param userId ���������
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(length=16,nullable=false,name="userName")
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(length=60,nullable=false,name="password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length=50,nullable=false,name="email")
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length=30,nullable=false,name="question")
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Column(length=30,nullable=false,name="answer")
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Column(length=20,nullable=false,name="picture")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(nullable=false,name="createDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(nullable=false,name="userEnable")
	public boolean isUserEnable() {
		return userEnable;
	}
	
	public void setUserEnable(boolean userEnable) {
		this.userEnable = userEnable;
	}
	
	@Column(nullable=false,name="commentEnable")
	public boolean isCommentEnable() {
		return commentEnable;
	}
	
	public void setCommentEnable(boolean commentEnable) {
		this.commentEnable = commentEnable;
	}
	
	@Column(nullable=false,name="passCount")
	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	@Column(nullable=false,name="submitCount")
	public int getSubmitCount() {
		return submitCount;
	}

	public void setSubmitCount(int submitCount) {
		this.submitCount = submitCount;
	}

	@Column(nullable=false,name="trueProblemCount")
	public int getTrueProblemCount() {
		return trueProblemCount;
	}

	public void setTrueProblemCount(int trueProblemCount) {
		this.trueProblemCount = trueProblemCount;
	}

	@Column(nullable=false,name="falseProblemCount")
	public int getFalseProblemCount() {
		return falseProblemCount;
	}

	public void setFalseProblemCount(int falseProblemCount) {
		this.falseProblemCount = falseProblemCount;
	}

	@OneToOne(optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name="otherInfoId")
	public OtherInfo getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(OtherInfo otherInfo) {
		this.otherInfo = otherInfo;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
			,mappedBy="user",fetch=FetchType.LAZY)
	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="user",fetch=FetchType.LAZY)
	public Set<Code> getCodes() {
		return codes;
	}

	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="user",fetch=FetchType.LAZY)
	public Set<Submit> getSubmits() {
		return submits;
	}

	public void setSubmits(Set<Submit> submits) {
		this.submits = submits;
	}
	
}

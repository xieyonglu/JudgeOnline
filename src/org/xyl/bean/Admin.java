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
@Table(name="t_admin")
public class Admin {

	private Long adminId;
	private String userName;
	private String password;
	private String trueName;
	private String sex;
	private String email;
	private String telephone;
	private Date birthday;
	private String address;
	private String type;
	private Date createDate;
	
	private Set<Problem> problems=new HashSet<Problem>();
	private Set<Notice> notices=new HashSet<Notice>();
	private Set<Match> matchs=new HashSet<Match>();
	
	public Admin(){}
	
	public Admin(Long adminId,String userName,String password,String trueName,String sex,String email,
			String telephone,Date birthday,String address){
		this.adminId=adminId;
		this.userName=userName;
		this.password=password;
		this.trueName=trueName;
		this.sex=sex;
		this.email=email;
		this.telephone=telephone;
		this.birthday=birthday;
		this.address=address;
	}
	
	@Override
	public String toString(){
		return "ID:"+adminId+"\t用户名:"+userName+"\t密码:"+password+"\t真实姓名:"+trueName+"\t性别:"+sex+"\t邮箱:"
				+email+"\t电话号码:"+telephone+"\t生日："+birthday+"\t地址:"+address+"\t创建日期:"+createDate;
	}
	
	/**
	 * 获取主键
	 * @return 主键
	 */
	@Id @GeneratedValue
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	@Column(length=20,name="trueName",nullable=true)
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	@Column(length=10,name="sex",nullable=true)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(length=20,name="email",nullable=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length=20,name="telephone",nullable=true)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name="birthday",nullable=true)
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(length=20,name="address",nullable=true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(length=20,nullable=false,name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		,mappedBy="admin",fetch=FetchType.LAZY)
	public Set<Problem> getProblems() {
		return problems;
	}

	public void setProblems(Set<Problem> problems) {
		this.problems = problems;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="admin",fetch=FetchType.LAZY)
	public Set<Notice> getNotices() {
		return notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}
		,mappedBy="admin",fetch=FetchType.LAZY)
	public Set<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(Set<Match> matchs) {
		this.matchs = matchs;
	}
	
	
}




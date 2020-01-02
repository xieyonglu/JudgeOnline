package org.xyl.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_otherInfo")
public class OtherInfo{

	private Long otherInfoId;
	private String sex;
	private String telephone;
	private Date birthday;
	private String province;//省
	private String city;//市
	private String area;//县
	private String trueName;
	
	private String eduDegree;
	private String occDirectory;//occupation
	private String position;
	private String income;
	private String hobby;
	
	private User user;
	
	public OtherInfo(){}
	
	public OtherInfo(String telephone,Date birthday,String province,String city,String area,String trueName,
			String eduDegree,String occDirectory,String position,String income,String hobby){
		this.telephone=telephone;
		this.birthday=birthday;
		this.province=province;
		this.city=city;
		this.area=area;
		this.trueName=trueName;
		this.eduDegree=eduDegree;
		this.occDirectory=occDirectory;
		this.position=position;
		this.income=income;
		this.hobby=hobby;
	}
	
	@Override
	public String toString(){
		return "性别:"+sex+"\t电话号码："+telephone+"\t出生年月:"+birthday+"\t所在地"+province+"-"+city+"-"+area+"\t真实姓名"+trueName+
				"\t教育程度:"+eduDegree+"\t职业方向:"+occDirectory+"\t职位:"+position+"\t月收入:"+income+"\t兴趣爱好:"+hobby;
	}
	
	/**
	 * 获取主键
	 * @return
	 */
	@Id @GeneratedValue
	public Long getOtherInfoId() {
		return otherInfoId;
	}
	
	public void setOtherInfoId(Long otherInfoId) {
		this.otherInfoId = otherInfoId;
	}
	
	@Column(name="sex",length=10)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="telephone",length=20)
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name="birthday")
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name="province",length=10)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name="city",length=10)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="area",length=10)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	@Column(name="trueName",length=20)
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	@Column(name="eduDegree",length=20)
	public String getEduDegree() {
		return eduDegree;
	}
	
	public void setEduDegree(String eduDegree) {
		this.eduDegree = eduDegree;
	}
	
	@Column(name="occDirectory",length=20)
	public String getOccDirectory() {
		return occDirectory;
	}
	
	public void setOccDirectory(String occDirectory) {
		this.occDirectory = occDirectory;
	}
	
	@Column(name="position",length=20)
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Column(name="income",length=20)
	public String getIncome() {
		return income;
	}
	
	public void setIncome(String income) {
		this.income = income;
	}
	
	@Column(name="hobby",length=60)
	public String getHobby() {
		return hobby;
	}
	
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@OneToOne(mappedBy="otherInfo",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}

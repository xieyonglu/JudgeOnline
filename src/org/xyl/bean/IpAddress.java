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
@Table(name="t_ipAddress")
public class IpAddress {

	private Long ipAddressId;
	private String from;
	private String to;
	private String from_ip1;
	private String from_ip2;
	private String from_ip3;
	private String from_ip4;
	private String from_ip5;
	private String from_ip6;
	private String from_ip7;
	private String from_ip8;
	
	private String to_ip1;
	private String to_ip2;
	private String to_ip3;
	private String to_ip4;
	private String to_ip5;
	private String to_ip6;
	private String to_ip7;
	private String to_ip8;
	
	private String type;
	private Date createDate;
	
	private Match match;
	
	public IpAddress(){}
	
	public IpAddress(String from,String to,String type,Date createDate){
		this.from=from;
		this.to=to;
		this.type=type;
		this.createDate=createDate;
	}
	
	@Override
	public String toString(){
		return "ID:"+ipAddressId+"\tFrom:"+from+"\tTo:"+to+"\ttype:"+type+"\t创建日期:"+createDate;
	}
	
	/**
	 * 获取主键
	 * @return 主键
	 */
	@Id @GeneratedValue
	public Long getIpAddressId() {
		return ipAddressId;
	}

	public void setIpAddressId(Long ipAddressId) {
		this.ipAddressId = ipAddressId;
	}

	@Column(length=50,nullable=false,name="from0")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Column(length=50,nullable=false,name="to0")
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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

	@Column(length=5,nullable=true,name="from_ip1")
	public String getFrom_ip1() {
		return from_ip1;
	}

	public void setFrom_ip1(String fromIp1) {
		from_ip1 = fromIp1;
	}

	@Column(length=5,nullable=true,name="from_ip2")
	public String getFrom_ip2() {
		return from_ip2;
	}

	public void setFrom_ip2(String fromIp2) {
		from_ip2 = fromIp2;
	}

	@Column(length=5,nullable=true,name="from_ip3")
	public String getFrom_ip3() {
		return from_ip3;
	}

	public void setFrom_ip3(String fromIp3) {
		from_ip3 = fromIp3;
	}

	@Column(length=5,nullable=true,name="from_ip4")
	public String getFrom_ip4() {
		return from_ip4;
	}

	public void setFrom_ip4(String fromIp4) {
		from_ip4 = fromIp4;
	}

	@Column(length=5,nullable=true,name="from_ip5")
	public String getFrom_ip5() {
		return from_ip5;
	}

	public void setFrom_ip5(String fromIp5) {
		from_ip5 = fromIp5;
	}

	@Column(length=5,nullable=true,name="from_ip6")
	public String getFrom_ip6() {
		return from_ip6;
	}

	public void setFrom_ip6(String fromIp6) {
		from_ip6 = fromIp6;
	}

	@Column(length=5,nullable=true,name="from_ip7")
	public String getFrom_ip7() {
		return from_ip7;
	}

	public void setFrom_ip7(String fromIp7) {
		from_ip7 = fromIp7;
	}

	@Column(length=5,nullable=true,name="from_ip8")
	public String getFrom_ip8() {
		return from_ip8;
	}

	public void setFrom_ip8(String fromIp8) {
		from_ip8 = fromIp8;
	}

	@Column(length=5,nullable=true,name="to_ip1")
	public String getTo_ip1() {
		return to_ip1;
	}

	public void setTo_ip1(String toIp1) {
		to_ip1 = toIp1;
	}

	@Column(length=5,nullable=true,name="to_ip2")
	public String getTo_ip2() {
		return to_ip2;
	}

	public void setTo_ip2(String toIp2) {
		to_ip2 = toIp2;
	}

	@Column(length=5,nullable=true,name="to_ip3")
	public String getTo_ip3() {
		return to_ip3;
	}

	public void setTo_ip3(String toIp3) {
		to_ip3 = toIp3;
	}

	@Column(length=5,nullable=true,name="to_ip4")
	public String getTo_ip4() {
		return to_ip4;
	}

	public void setTo_ip4(String toIp4) {
		to_ip4 = toIp4;
	}

	@Column(length=5,nullable=true,name="to_ip5")
	public String getTo_ip5() {
		return to_ip5;
	}

	public void setTo_ip5(String toIp5) {
		to_ip5 = toIp5;
	}

	@Column(length=5,nullable=true,name="to_ip6")
	public String getTo_ip6() {
		return to_ip6;
	}

	public void setTo_ip6(String toIp6) {
		to_ip6 = toIp6;
	}

	@Column(length=5,nullable=true,name="to_ip7")
	public String getTo_ip7() {
		return to_ip7;
	}

	public void setTo_ip7(String toIp7) {
		to_ip7 = toIp7;
	}

	@Column(length=5,nullable=true,name="to_ip8")
	public String getTo_ip8() {
		return to_ip8;
	}

	public void setTo_ip8(String toIp8) {
		to_ip8 = toIp8;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="matchId")
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
}




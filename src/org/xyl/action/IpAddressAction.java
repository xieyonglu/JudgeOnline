package org.xyl.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.IpAddress;
import org.xyl.bean.Match;
import org.xyl.iservice.IIpAddressService;
import org.xyl.iservice.IMatchService;
import org.xyl.util.IPAddressUtil;
import org.xyl.util.PageBean;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("ipAddressAction")
@Scope("prototype")
public class IpAddressAction extends ActionSupport{

	@Resource private IMatchService matchService;
	@Resource private IIpAddressService ipAddressService;
	
	private Match match;
	private IpAddress ipAddress;
	
	private PageBean<IpAddress> pageBean;
	private String condition1;
	private String condition2;
	
	public void validateInsertIpAddress(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String matchId=request.getParameter("matchId");
		
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
		}
		
		if("ipv4".equals(action)){
			if(!(IPAddressUtil.checkIPV4(ipAddress.getFrom_ip1())&&
				IPAddressUtil.checkIPV4(ipAddress.getFrom_ip2())&&
				IPAddressUtil.checkIPV4(ipAddress.getFrom_ip3())&&
				IPAddressUtil.checkIPV4(ipAddress.getFrom_ip4())&&
					
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip1())&&
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip2())&&
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip3())&&
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip4()))){
				this.addFieldError("ipAddress.ipaddressv4", "IPV4地址错误，必须在0~255之间！");
				ActionContext.getContext().put("urlAction", "ip/insert_ipv4.jsp");
			}else{
				String from=ipAddress.getFrom_ip1()+"."+ipAddress.getFrom_ip2()+"."+ipAddress.getFrom_ip3()+"."+ipAddress.getFrom_ip4();
				String to=ipAddress.getTo_ip1()+"."+ipAddress.getTo_ip2()+"."+ipAddress.getTo_ip3()+"."+ipAddress.getTo_ip4();
				if(!IPAddressUtil.compareIpAddress(from, to)){
					this.addFieldError("ipAddress.ipaddressv4", "from应该小于等于to!");
					ActionContext.getContext().put("urlAction", "ip/insert_ipv4.jsp");
				}else{
					List<IpAddress> ipList=ipAddressService.findIpByFromToMatch(from,to,Long.parseLong(matchId));
					if(ipList!=null&&ipList.size()>0){
						this.addFieldError("ipAddress.ipaddressv4", "该IPV4已经存在!");
						ActionContext.getContext().put("urlAction", "ip/insert_ipv4.jsp");
					}
				}
			}
		}else if("ipv6".equals(action)){
			if(!(IPAddressUtil.checkIPV6(ipAddress.getFrom_ip1())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip2())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip3())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip4())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip5())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip6())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip7())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip8())&&
			   
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip1())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip2())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip3())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip4())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip5())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip6())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip7())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip8()))){
			   this.addFieldError("ipAddress.ipaddressv6", "IPV6地址错误，必须在0000~ffff之间！");
			   ActionContext.getContext().put("urlAction", "ip/insert_ipv6.jsp");
			}else{
				String from=ipAddress.getFrom_ip1()+":"+ipAddress.getFrom_ip2()+":"+ipAddress.getFrom_ip3()+":"+ipAddress.getFrom_ip4()+":"+
						ipAddress.getFrom_ip5()+":"+ipAddress.getFrom_ip6()+":"+ipAddress.getFrom_ip7()+":"+ipAddress.getFrom_ip8();
				String to=ipAddress.getTo_ip1()+":"+ipAddress.getTo_ip2()+":"+ipAddress.getTo_ip3()+":"+ipAddress.getTo_ip4()+":"+
						ipAddress.getTo_ip5()+":"+ipAddress.getTo_ip6()+":"+ipAddress.getTo_ip7()+":"+ipAddress.getTo_ip8();
				if(!IPAddressUtil.compareIpAddress(from, to)){
					this.addFieldError("ipAddress.ipaddressv6", "from应该小于等于to!");
					ActionContext.getContext().put("urlAction", "ip/insert_ipv6.jsp");
				}else{
					List<IpAddress> ipList=ipAddressService.findIpByFromToMatch(from,to,Long.parseLong(matchId));
					if(ipList!=null&&ipList.size()>0){
						this.addFieldError("ipAddress.ipaddressv6", "该IPV6已经存在!");
						ActionContext.getContext().put("urlAction", "ip/insert_ipv6.jsp");
					}
				}
			}
		}
		
		//ActionContext.getContext().put("urlAction", "ip/insert_ip.jsp");
	}
	
	//插入一条管理员信息
	public String insertIpAddress(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String matchId=request.getParameter("matchId");
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
		}
		String from="0:0:0:0";
		String to="0:0:0:0";
		String type="ipv4";
		
		if("ipv4".equals(action)){
			from=ipAddress.getFrom_ip1()+"."+ipAddress.getFrom_ip2()+"."+ipAddress.getFrom_ip3()+"."+ipAddress.getFrom_ip4();
			to=ipAddress.getTo_ip1()+"."+ipAddress.getTo_ip2()+"."+ipAddress.getTo_ip3()+"."+ipAddress.getTo_ip4();
			type="ipv4";
		}else if("ipv6".equals(action)){
			from=ipAddress.getFrom_ip1()+":"+ipAddress.getFrom_ip2()+":"+ipAddress.getFrom_ip3()+":"+ipAddress.getFrom_ip4()+":"+
				ipAddress.getFrom_ip5()+":"+ipAddress.getFrom_ip6()+":"+ipAddress.getFrom_ip7()+":"+ipAddress.getFrom_ip8();
			to=ipAddress.getTo_ip1()+":"+ipAddress.getTo_ip2()+":"+ipAddress.getTo_ip3()+":"+ipAddress.getTo_ip4()+":"+
				ipAddress.getTo_ip5()+":"+ipAddress.getTo_ip6()+":"+ipAddress.getTo_ip7()+":"+ipAddress.getTo_ip8();
			type="ipv6";
		}
		
		ipAddress.setFrom(from);
		ipAddress.setTo(to);
		ipAddress.setType(type);
		ipAddress.setCreateDate(new Date());
		//match.getIpAddresses().add(ipAddress);
		ipAddress.setMatch(match);
		
		
		ipAddressService.addIpAddress(ipAddress);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//删除指定Id的管理员
	public String deleteIpAddress(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Long ipAddressId=Long.parseLong(request.getParameter("ipAddressId"));
		ipAddressService.deleteIpAddressById(ipAddressId);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	
	public void validateUpdateIpAddress(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String matchId=request.getParameter("matchId");
		
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
		}
		
		if("ipv4".equals(action)){
			if(!(IPAddressUtil.checkIPV4(ipAddress.getFrom_ip1())&&
				IPAddressUtil.checkIPV4(ipAddress.getFrom_ip2())&&
				IPAddressUtil.checkIPV4(ipAddress.getFrom_ip3())&&
				IPAddressUtil.checkIPV4(ipAddress.getFrom_ip4())&&
					
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip1())&&
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip2())&&
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip3())&&
				IPAddressUtil.checkIPV4(ipAddress.getTo_ip4()))){
				this.addFieldError("ipAddress.ipaddressv4", "IPV4地址错误，必须在0~255之间！");
				ActionContext.getContext().put("urlAction", "ip/update_ipv4.jsp");
			}else{
				String from=ipAddress.getFrom_ip1()+"."+ipAddress.getFrom_ip2()+"."+ipAddress.getFrom_ip3()+"."+ipAddress.getFrom_ip4();
				String to=ipAddress.getTo_ip1()+"."+ipAddress.getTo_ip1()+"."+ipAddress.getTo_ip1()+"."+ipAddress.getTo_ip1();
				if(!IPAddressUtil.compareIpAddress(from, to)){
					this.addFieldError("ipAddress.ipaddressv4", "from应该小于等于to!");
					ActionContext.getContext().put("urlAction", "ip/update_ipv4.jsp");
				}else{
					List<IpAddress> ipList=ipAddressService.findIpByFromToMatch(from,to,Long.parseLong(matchId));
					if(ipList!=null){
						for(int i=0;i<ipList.size();i++){
							if(ipList.get(i)!=null&&ipList.get(i).getIpAddressId()!=ipAddress.getIpAddressId()){
								this.addFieldError("ipAddress.ipaddressv4", "该IPV4已经存在!");
								ActionContext.getContext().put("urlAction", "ip/update_ipv4.jsp");
								break;
							}
						}
					}
				}
				
			}
		}else if("ipv6".equals(action)){
			if(!(IPAddressUtil.checkIPV6(ipAddress.getFrom_ip1())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip2())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip3())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip4())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip5())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip6())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip7())&&
			   IPAddressUtil.checkIPV6(ipAddress.getFrom_ip8())&&
			   
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip1())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip2())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip3())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip4())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip5())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip6())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip7())&&
			   IPAddressUtil.checkIPV6(ipAddress.getTo_ip8()))){
			   this.addFieldError("ipAddress.ipaddressv6", "IPV6地址错误，必须在0000~ffff之间！");
			   ActionContext.getContext().put("urlAction", "ip/update_ipv6.jsp");
			}else{
				String from=ipAddress.getFrom_ip1()+":"+ipAddress.getFrom_ip2()+":"+ipAddress.getFrom_ip3()+":"+ipAddress.getFrom_ip4()+":"+
						ipAddress.getFrom_ip5()+":"+ipAddress.getFrom_ip6()+":"+ipAddress.getFrom_ip7()+":"+ipAddress.getFrom_ip8();
				String to=ipAddress.getTo_ip1()+":"+ipAddress.getTo_ip2()+":"+ipAddress.getTo_ip3()+":"+ipAddress.getTo_ip4()+":"+
						ipAddress.getTo_ip5()+":"+ipAddress.getTo_ip6()+":"+ipAddress.getTo_ip7()+":"+ipAddress.getTo_ip8();
				if(!IPAddressUtil.compareIpAddress(from, to)){
					this.addFieldError("ipAddress.ipaddressv6", "from应该小于等于to!");
					ActionContext.getContext().put("urlAction", "ip/update_ipv6.jsp");
				}else{
					List<IpAddress> ipList=ipAddressService.findIpByFromToMatch(from,to,Long.parseLong(matchId));
					if(ipList!=null){
						for(int i=0;i<ipList.size();i++){
							if(ipList.get(i)!=null&&ipList.get(i).getIpAddressId()!=ipAddress.getIpAddressId()){
								this.addFieldError("ipAddress.ipaddressv6", "该IPV6已经存在!");
								ActionContext.getContext().put("urlAction", "ip/update_ipv6.jsp");
								break;
							}
						}
					}
				}
				
			}
		}
		
	}
	
	//修改比赛信息
	public String updateIpAddress(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String from="0:0:0:0";
		String to="0:0:0:0";
		String type="ipv4";
		String matchId=request.getParameter("matchId");
		
		if(matchId!=null&&matchId.trim().length()>0){
			match=matchService.load(Long.parseLong(matchId));
		}
		
		if("ipv4".equals(action)){
			from=ipAddress.getFrom_ip1()+"."+ipAddress.getFrom_ip2()+"."+ipAddress.getFrom_ip3()+"."+ipAddress.getFrom_ip4();
			to=ipAddress.getTo_ip1()+"."+ipAddress.getTo_ip2()+"."+ipAddress.getTo_ip3()+"."+ipAddress.getTo_ip4();
			type="ipv4";
		}else if("ipv6".equals(action)){
			from=ipAddress.getFrom_ip1()+":"+ipAddress.getFrom_ip2()+":"+ipAddress.getFrom_ip3()+":"+ipAddress.getFrom_ip4()+":"+
				ipAddress.getFrom_ip5()+":"+ipAddress.getFrom_ip6()+":"+ipAddress.getFrom_ip7()+":"+ipAddress.getFrom_ip8();
			to=ipAddress.getTo_ip1()+":"+ipAddress.getTo_ip2()+":"+ipAddress.getTo_ip3()+":"+ipAddress.getTo_ip4()+":"+
			ipAddress.getTo_ip5()+":"+ipAddress.getTo_ip6()+":"+ipAddress.getTo_ip7()+":"+ipAddress.getTo_ip8();
			type="ipv6";
		}
		
		ipAddress.setFrom(from);
		ipAddress.setTo(to);
		ipAddress.setType(type);
		ipAddress.setCreateDate(new Date());
		ipAddress.setMatch(match);
		
		ipAddressService.updateIpAddress(ipAddress);
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}
	
	//显示某个IP
	public String showIpAddress(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		Long ipAddressId=Long.parseLong(request.getParameter("ipAddressId"));
		String action=request.getParameter("action");
		ipAddress=ipAddressService.load(ipAddressId);
		match=matchService.load(ipAddress.getMatch().getMatchId());
		
		if(action.equals("updatev4")){
			ActionContext.getContext().put("urlAction","ip/update_ipv4.jsp");
		}if(action.equals("updatev6")){
			ActionContext.getContext().put("urlAction","ip/update_ipv6.jsp");
		}else if(action.equals("show")){
			ActionContext.getContext().put("urlAction","ip/show_ip.jsp");
		}
		
		return Action.SUCCESS;
	}

	//分页显示IP信息
	public String pageIpAddress(){
				
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String action=request.getParameter("action");
		String matchId=request.getParameter("matchId");
		
		String pageIndex=request.getParameter("pageIndex");
		String pageSize=request.getParameter("pageSize");
		
		try{
			if(condition1!=null)condition1=new String(condition1.getBytes("ISO-8859-1"));
			if(condition2!=null)condition2=new String(condition2.getBytes("ISO-8859-1"));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		System.out.println(pageIndex+"---------"+pageSize);
		
		if(pageIndex==null||pageIndex.trim().length()==0){
			pageIndex="0";
		}
		if(pageSize==null||pageSize.trim().length()==0){
			pageSize="10";
		}
		
		System.out.println(condition1+"----"+condition2+"---"+pageIndex+"---"+pageSize);
		String hql="from IpAddress as obj where 1=1 ";
		
		if(condition1!=null&&condition1.trim().length()>0&&!"--请选择--".equals(condition1)){
			if(condition2!=null&&condition2.trim().length()>0){
				hql+=" and obj."+condition1+" like '%"+condition2+"%'";
			}
		}
		
		//根据比赛的ID，查找所有的与之比赛相关的IP
		if(matchId!=null&&matchId.trim().length()>0){
			hql=hql+" and obj.match.matchId='"+matchId+"'";
			match=matchService.load(Long.parseLong(matchId));
		}
		
		hql=hql+" order by obj.createDate desc";
		
		System.out.println("hql===="+hql);
		
		pageBean=ipAddressService.pageByCondition(hql, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<IpAddress> list=pageBean.getList();
		
		System.out.println(list.size()+"------------------"+action);
		
		ActionContext.getContext().put("list",list);
		
		ActionContext.getContext().put("urlAction","ip/list_ip.jsp");
		return Action.SUCCESS;
	}
	
	//批量删除
	public String batchDelete(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("str");
		String[] s=str.split("-");
		for(int i=0;i<s.length;i++){
			ipAddressService.deleteIpAddressById(Long.parseLong(s[i]));
		}
		
		ActionContext.getContext().put("urlAction", "inc/success.jsp");
		return Action.SUCCESS;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public IpAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(IpAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public PageBean<IpAddress> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<IpAddress> pageBean) {
		this.pageBean = pageBean;
	}

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}
	
}

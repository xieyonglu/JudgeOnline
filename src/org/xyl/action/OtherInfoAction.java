package org.xyl.action;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xyl.bean.OtherInfo;
import org.xyl.bean.User;
import org.xyl.iservice.IOtherInfoService;
import org.xyl.iservice.IUserService;
import org.xyl.util.ActionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("otherInfoAction")
@Scope("prototype")
public class OtherInfoAction extends ActionSupport implements ModelDriven<OtherInfo>{

	private IUserService userService;
	private IOtherInfoService otherInfoService;
	
	private User user;
	private OtherInfo otherInfo;
	
	@Resource
	public void setUserService(IUserService userService){
		this.userService=userService;
	}
	
	@Resource
	public void setOtherInfoService(IOtherInfoService otherInfoService) {
		this.otherInfoService = otherInfoService;
	}


	public OtherInfo getModel() {
		// TODO Auto-generated method stub
		if(otherInfo==null) otherInfo=new OtherInfo();
		return otherInfo;
	}
	
	public void validateUpdateSelfInfo(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		String userId=request.getParameter("userId");
		if(userId!=null&&userId.trim().length()>0){
			user=userService.load(Long.parseLong(userId));
			ActionContext.getContext().put("user",user);
		}
		
		System.out.println(user.getUserName()+"--------"+userId);
		
		String telephone=otherInfo.getTelephone();
		String trueName=otherInfo.getTrueName();
		
		System.out.println((telephone!=null)+"---"+(telephone.trim().length()>0)+"--"+(telephone.trim().length()<=20));
		System.out.println(telephone.matches("\\d++")+"====="+telephone);
		
		
		System.out.println((trueName!=null)+"---"+(trueName.trim().length()>0)+"--"+(trueName.trim().length()<=20));
		System.out.println(trueName);
		
		
		if(telephone!=null&&(!(telephone.trim().length()==0||telephone.trim().length()>=1&&telephone.trim().length()<=20&&telephone.matches("\\d++")))){
			this.addFieldError("otherInfo.telephone", "手机格式错误，必须是整数，且在0~20之间！");
		}
		if(trueName!=null&&!(trueName.trim().length()>=0&&trueName.trim().length()<=20)){
			this.addFieldError("otherInfo.trueName", "真实姓名格式错误，必须在0~20之间！");
		}
		
		if("update".equals(action)){
			ActionContext.getContext().put("urlAction", "user/updateUserInfo.jsp");
		}else if("insert".equals(action)){
			ActionContext.getContext().put("urlAction", "user/addSelfInfo.jsp");
		}else{
			ActionContext.getContext().put("urlAction", "user/addSelfInfo.jsp");
		}
		
	}
	
	//插入用户其他信息，也即修改用户对应的其他信息
	public String updateSelfInfo(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		Long userId=Long.parseLong(request.getParameter("userId"));
		String action=request.getParameter("action");
		
		String[] hobby=request.getParameterValues("hobby");
		if(hobby!=null){
			for(int i=0;i<hobby.length;i++){
				System.out.print(hobby[i]);
			}
		}else{
			otherInfo.setHobby(null);
		}
		
		System.out.println("-------------------");
		User user=userService.load(userId);
		
		System.out.println("userId=="+userId);
		System.out.println(user);
		
		//获取年月日,并根据年月日获取用户的生日
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		String day=request.getParameter("day");
		
		//获取省市县，并根据省市县获取用户的所在地
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		
		
		System.out.println(year+"----"+month+"---"+day);
		System.out.println(province+"----"+city+"----"+area);
		
		//if(province.equals("请选择")||city.equals("请选择")||area.equals("请选择")){
		if(province.equals("省份")||city.equals("地级市")||area.equals("市、县级市、")){
			otherInfo.setProvince(null);
			otherInfo.setCity(null);
			otherInfo.setArea(null);
		}else{
			otherInfo.setProvince(province);
			otherInfo.setCity(city);
			otherInfo.setArea(area);
		}
		
		if(year.equals("-请选择-")||month.equals("-请选择-")||day.equals("-请选择-")){
			otherInfo.setBirthday(null);
		}else{
			int y=Integer.parseInt(request.getParameter("year"));
			int m=Integer.parseInt(request.getParameter("month"));
			int d=Integer.parseInt(request.getParameter("day"));
			GregorianCalendar gc=new GregorianCalendar(y,m,d);
			Date birthday=gc.getTime();
			otherInfo.setBirthday(birthday);
		}
		
		if(otherInfo.getEduDegree().equals("请选择")){
			otherInfo.setEduDegree(null);
		}
		
		if(otherInfo.getOccDirectory().equals("请选择")){
			otherInfo.setOccDirectory(null);
		}
		
		if(otherInfo.getPosition().equals("请选择")){
			otherInfo.setPosition(null);
		}
		
		if(otherInfo.getIncome().equals("请选择")){
			otherInfo.setIncome(null);
		}
		
		//user.setOtherInfo(otherInfo);
		//System.out.println(user.getUserId()+"==="+user.getOtherInfo());
		//userService.update(user);
		
		otherInfo.setOtherInfoId(user.getOtherInfo().getOtherInfoId());//为OtherInfo设置Id
		otherInfoService.update(otherInfo);
		
		
		if(action.equals("insert")){
			ActionContext.getContext().put("urlAction", "/regSuccess.jsp");
			return ActionUtil.REDIRECT;	
		}else if(action.equals("update")){
			//ActionContext.getContext().put("urlAction", "user/updateUserInfo.jsp?strTitle=修改用户信息&strContent=修改用户信息成功！&success=success");
			ActionContext.getContext().put("urlAction", "inc/success.jsp");
			return ActionUtil.REDIRECT;
		}else{
			ActionContext.getContext().put("urlAction", "user/regSuccess.jsp");
			return ActionUtil.REDIRECT;
		}
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OtherInfo getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(OtherInfo otherInfo) {
		this.otherInfo = otherInfo;
	}
	

}









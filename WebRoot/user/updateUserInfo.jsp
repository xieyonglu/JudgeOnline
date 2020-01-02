<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title></title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addSelfInfo.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/proCityArea.js" charset="gbk"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/success.js"></script>

<script type="text/javascript">
function onchangeYear(){

	with(document.form1){
		var yText=year.options[year.selectedIndex].text;
		var mText=month.options[month.selectedIndex].text;
		var dText=day.options[day.selectedIndex].text;
		if(yText!="-请选择-"){
			addMonth();

			if(mText=="-请选择-"){
				month.options[1].selected=true;
				month.onchange();
				day.options[1].selected=true;
			}else{
				//alert("=========");
				changeDay();
				//month.onchange();
				month.options[1].selected=true;
				day.options[1].selected=true;
			}
		}else{
			month.options.length=1;
			month.options[0].selected=true;
			day.options.length=1;
			day.options[0].selected=true;
		}
	}
}


function birthday(){

	with(document.form1){
		var y=(new Date()).getYear();

		//设置年
		year.options[0]=new Option("-请选择-","-请选择-");
		for(var i=50;i>=0;i--){
			year.options[51-i]=new Option(y-i,y-i);
			//document.write((y-i)+"---"+(51-i)+"<br/>");
		}
	}

}

function addMonth(){
	//设置月
	with(document.form1){
		month.options[0]=new Option("-请选择-","-请选择-");
		month.options[1]=new Option("01","01");
		month.options[2]=new Option("02","02");
		month.options[3]=new Option("03","03");
		month.options[4]=new Option("04","04");
		month.options[5]=new Option("05","05");
		month.options[6]=new Option("06","06");
		month.options[7]=new Option("07","07");
		month.options[8]=new Option("08","08");
		month.options[9]=new Option("09","09");
		month.options[10]=new Option("10","10");
		month.options[11]=new Option("11","11");
		month.options[12]=new Option("12","12");
	}
}

function onchangeMonth(){

	with(document.form1){
		var mText=month.options[month.selectedIndex].text;
		var yText=year.options[year.selectedIndex].text;
		var d=parseInt(mText);
		var y=parseInt(yText);

		//alert("--");

		if(mText!="-请选择-"){
			if(yText!="-请选择-"){	
				changeDay();
			}
		}else{
			day.options.length=1;
			day.options[0].selected=true;
		}

	}
}

function changeDay(){
	with(document.form1){
		var mText=month.options[month.selectedIndex].text;
		var yText=year.options[year.selectedIndex].text;
		var d=parseInt(mText);
		var y=parseInt(yText);

		//alert("day");

		//设置日
		day.options[0]=new Option("-请选择-","-请选择-");
		for(var i=1;i<=31;i++){
			var str=i+"";
			if(i<10)str="0"+str;
				day.options[i]=new Option(str,str);
		}
					
		switch(d){
			case 4: case 6: case 8: case 9: case 11:
				day.options[31]=null;
				break;
			case 2:
				day.options[31]=null;
				day.options[30]=null;
				if(!(y%400==0||(y%4==0&&y%100!=0))){//非闰年的判断
					day.options[29]=null;
				}
				break;
		}
		day.options[1].selected=true;
	
	}
}

</script>

<script type="text/javascript">
	$(function(){
		var bir=$(":input[name='bir']")[0].value;
		var birs=String(bir).split("-");
		//for(var i=0;i<birs.length;i++){
		//	alert(birs[i]);
		//}
		$(":input[name='year']").val(birs[0]).change();
		$(":input[name='month']").val(birs[1]).change();
		$(":input[name='day']").val(birs[2]);
	});

	$(function(){
		var hobby=$(":input[name='hdHobby']")[0].value;
		var hs=String(hobby).split(",");
		for(var i=0;i<hs.length;i++){
			hs[i]=trim(hs[i]);
		}
		
		$(":radio[name='otherInfo.sex']").val(['${user.otherInfo.sex}']);
		
		$(":input[name='province']").val(['${user.otherInfo.province}']).change();
		$(":input[name='city']").val(['${user.otherInfo.city}']).change();
		$(":input[name='area']").val(['${user.otherInfo.area}']);
		
		$(":input[name='otherInfo.eduDegree']").val('${user.otherInfo.eduDegree}');
		$(":input[name='otherInfo.occDirectory']").val('${user.otherInfo.occDirectory}');
		$(":input[name='otherInfo.position']").val('${user.otherInfo.position}');
		$(":input[name='otherInfo.income']").val('${user.otherInfo.income}');
		//$(":checkbox").val(["资讯/要文","科技/数码"]);
		
		var ho=document.getElementsByName("hobby");
		for(var i=0;i<ho.length;i++){
			for(var j=0;j<hs.length;j++){
				if(ho[i].value==hs[j]){
					ho[i].checked=true;
				}
			}	
		}
	});
	
</script>


</head>
<body>

<% 
	//String strTitle=request.getParameter("strTitle");
	//String strContent=request.getParameter("strContent");
	//String success=request.getParameter("success");

	//out.println(success);
%>
<script type="text/javascript">
	//alert("开始调用");
	//sAlert(strTitle,strContent);
	//if(!=null){
	//	sAlert(strTitle,strContent);
	//	alert("修改成功！");
	//}else{
	//	alert("修改失败！");
	//}
</script>

<input type="hidden" name="hdHobby" value="${user.otherInfo.hobby}"/>
<input type="hidden" name="bir" value="${user.otherInfo.birthday}"/>

<script type="text/javascript">
function check(){
	var telephone=document.getElementById("telephone");
	var value=telephone.value;
	var value=trim(value);
	if(value!=""&&is_integer(value)==false){
		alert("电话号码必须是整数！");
		telephone.focus();
		telephone.select();
		return false;
	} 
	return true;
}
</script>

<div id="one">

	<div style="border:10px solid green; margin-top:5px;"></div>
	<div style="margin-top:10px;">
	<table border="0" cellspacing="2px" cellpadding="3px" width="100%">
		<tr><td><font size="4px" color="green"><b>&nbsp;请完善你的个人信息</b></font></td></tr>
		<tr><td>&nbsp;&nbsp;完善个人信息有助于找到志同道合的草草好友，同时可享受更多个性化服务</td></tr>
	</table>

	<div style="border:3px solid gray; margin-top:5px;"></div>
	<div style="margin-top:20px;"></div>

	<div id="two">
		<div style="margin-left:20px; background-color:blue; text-align:left;">
		<div style="margin-left:25px; margin-top:10px; float:left; font:bold 14px; color:green;">用户基本信息</div>
	</div>
	<br><br>
	
	<form action="otherInfo_updateSelfInfo.action?action=update" method="post" name="form1">
	<table border="0" cellspacing="5px" cellpadding="5px" width="600">
		<tr>
			<td style="text-align:right;" class="td_1">用户名：</td>
			<td>${user.userName}</td>
		</tr>
		<tr>
			<td style="text-align:right;">性别：</td>
			<td><input type="radio" value="男" name="otherInfo.sex" checked>男&nbsp;&nbsp;
				<input type="radio" value="女" name="otherInfo.sex">女
			</td>
		</tr>
		<tr>
			<td style="text-align:right;">电子邮箱：</td>
			<td><s:property value="user.email"/>
				<a href="#">免费激活</a><a href="#">修改</a>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;">手机：</td>
			<td><input type="text" style="width:140px;" name="otherInfo.telephone" id="telephone" value="${user.otherInfo.telephone}">
			（填写准确手机号可随时享受草草网为手机用户定制的精品服务）</td>
		</tr>
		<tr>
			<td style="text-align:right;">出生日期：</td>
			<td>
				<select style="width:80px;" name="year" onChange="onchangeYear()">
					<option value="-请选择-">-请选择-</option>
				</select>&nbsp;年
				<select style="width:80px;" name="month" onChange="onchangeMonth()">
					<option>-请选择-</option>
				</select>&nbsp;月
				<select style="width:80px;" name="day">
					<option>-请选择-</option>
				</select>&nbsp;日
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">所在地：</td>
		<td>

			<select style="width:80px;" id="province" name="province">
				<option>-请选择-</option>
			</select>&nbsp;省
			<select style="width:80px;" id="city" name="city">
				<option>-请选择-</option>
			</select>&nbsp;市
			<select style="width:80px;" id="area" name="area">
				<option>-请选择-</option>
			</select>&nbsp;县
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">真实姓名：</td>
		<td>
			<input type="text" style="width:300px;" name="otherInfo.trueName" value="${user.otherInfo.trueName}"/>
		</td>
	</tr>

</table>
<br>
	<div style="border:1px dashed gray; margin-top:10px;"></div>
	<div style="margin-left:20px; background-color:blue; text-align:left;">
	<div style="margin-left:25px; margin-top:15px; float:left; font:bold 14px; color:green;">详细信息</div>
	</div>
	<br><br>
<table border="0" cellspacing="5px" cellpadding="5px" width="600">
	<tr>
		<td style="text-align:right;" class="td_1">教育程度：</td>
		<td>
			<select style="width:120px;" name="otherInfo.eduDegree">
				<option>请选择</option>
				<option>高中以下</option>
				<option>高中</option>
				<option>专科</option>
				<option>本科</option>
				<option>硕士研究生</option>
				<option>博士研究生</option>
				<option>博士以上</option>
			</select>
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">职业方向：</td>
		<td>
			<select style="width:200px;" name="otherInfo.occDirectory">
				<option>请选择</option>
				<option>计算机、互联网或电子商务</option>
				<option>通信</option>
				<option>专业服务（咨询、财会或法律等）</option>
				<option>金融</option>
				<option>贸易</option>
				<option>医疗、生化或环保</option>
				<option>交通能源</option>
				<option>船舶</option>
				<option>政府、教育等事业单位</option>
				<option>媒体、出版、文化传媒</option>
				<option>农、林、牧、渔</option>
				<option>产品、设计、艺术类</option>
				<option>娱乐、休闲、服务门业</option>
				<option>其他</option>
			</select>
		</td>
	</tr>

	<tr>
		<td style="text-align:right;">职位：</td>
		<td>
			<select style="width:200px;" name="otherInfo.position">
				<option>请选择</option>
				<option>学生</option>
				<option>农民</option>
				<option>工人</option>
				<option>其他</option>
			</select>
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">月收入：</td>
		<td>
			<select style="width:200px;" name="otherInfo.income">
				<option>请选择</option>
				<option>3000以下</option>
				<option>3000~5000</option>
				<option>5000~8000</option>
				<option>8000~10000</option>
				<option>10000以上</option>
			</select>
		</td>
	</tr>

	<tr>
		<td rowspan="2" style="text-align:right; vertical-align:top;">兴趣爱好：</td>
		<td>
			<input type="checkbox" name="hobby" value="资讯/要文">资讯/要文&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="财经/股票">财经/股票&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="娱乐/时尚">娱乐/时尚&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="视频">视频&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="汽车">汽车
		</td>
	</tr>
	<tr>
		<td>
			<input type="checkbox" name="hobby" value="科技/数码">科技/数码&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="军事/历史">军事/历史&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="教育/读书">教育/读书&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="房产">房产&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="游戏">游戏
		</td>
	</tr>
</table>
<input type="hidden" name="userId" value="${user.userId}"/>

<div style="border:1px dashed gray; margin-top:10px;"></div>
<div style="margin-left:20px; background-color:blue; text-align:left;">
	<div style="margin-left:10px; margin-top:15px; float:left; font:bold 14px; color:green;">
		<input type="submit" value="确定修改" class="button" onClick="return check();"/>
	</div>
</div>
</form>

</div>
</div>
</div>

<script type="text/javascript">	
birthday();
setup();
</script>
</body>
</html>





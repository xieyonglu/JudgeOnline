<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>插入比赛界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>

<style type="text/css">
.formFieldError{
	font-family:verdana,arial,helvetica,sans-serif;
	font-size:12px;
	color:#ff3300;
	margin:1px; 
	padding:4px; 
	vertical-align:bottom;
}
</style>

</head>

<body>

<table border="0" cellspacing="0px" cellpadding="0px" style="width:1030px; text-align:left;">
	<tr><td><img src="<%=request.getContextPath()%>/image/logo_grzx.gif"></td></tr>
</table>

<div style="border:1px solid black; margin-top:10px;"></div>


<div style="width:1030px; height:36px; margin-top:10px; background-color:orange;">
<div style="text-align:left; margin-left:20px; margin-top:9px;">

<table border="0" cellspacing="3px" cellpadding="1px" width="1000px">
	<tr>
		<td><div style="text-align:left; margin-left:5px;">添加比赛</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<form action="match_insertMatch.action" method="post" name="form1">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		<tr><td class="td_1" width="100px">撰写作者</td>
			<td class="td_1">${sessionScope.loginAdmin.userName}&nbsp;</td>
		</tr>
		<tr><td class="td_1">比赛名称</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="match.matchName" value="<s:property value='match.matchName'/>"/>
				<s:fielderror cssClass="formFieldError"><s:param>match.matchName</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">比赛地址</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="match.address" value="<s:property value='match.address'/>"/>
				<s:fielderror cssClass="formFieldError"><s:param>match.address</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">比赛状态</td>
			<td class="td_1">
				<input type="radio" value="0" name="match.state" checked>还没开始
				<input type="radio" value="1" name="match.state">正在进行
				<input type="radio" value="2" name="match.state">已经结束
			</td>
		</tr>
		<tr><td class="td_1">开始时间</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="startDate" value="<s:property value='match.startDate'/>"/>(yyyy-MM-dd HH:mm:ss)
				<s:fielderror cssClass="formFieldError"><s:param>match.startDate</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">结束时间</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="endDate" value="<s:property value='match.endDate'/>"/>(yyyy-MM-dd HH:mm:ss)
				<s:fielderror cssClass="formFieldError"><s:param>match.endDate</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">发布时间</td>
			<td class="td_1">
				<script language="JavaScript">
				<!--
					var myOffset = -2;  
					var currentDate = new Date();  
					var userOffset = currentDate.getTimezoneOffset()/60;  
					var timeZoneDifference = userOffset - myOffset;  
					currentDate.setHours(currentDate.getHours() + timeZoneDifference);  
					document.write(currentDate.toLocaleString());  
				//-->
				</script>
			</td>
		</tr>
		
		<tr>
			<td class="td_1" style="vertical-align:top;">比赛内容<br><br></td>
			<td class="td_1">
				<!--新闻内容错误提示-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>match.content</s:param></s:fielderror>
				</div>
				<textarea style="width:100%; height:300px;" name="match.content" id="editor"><s:property value="match.content"/></textarea>
			</td>
		</tr>
		
		<tr><td>&nbsp;</td>
			<td class="td_1">
				<!--<input type="hidden" name="adminId" value="${sessionScope.loginAdmin.adminId}"/>
				--><input type="submit" value="添加比赛" class="button"/>
			</td>
		</tr>
	</table>
	</form>
	
</div>
</body>
</html>



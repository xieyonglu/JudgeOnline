<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>修改比赛界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>

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

<script type="text/javascript">
$(function(){
	$(":radio[name='match.state']").val(['${match.state}']);
});
</script>
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
		<td><div style="text-align:left; margin-left:5px;">修改比赛</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<form action="match_updateMatch.action" method="post" name="form1">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		<tr><td class="td_1" width="100px">撰写作者</td>
			<td class="td_1">${match.author}&nbsp;</td>
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
				<input type="text" style="width:450px;" name="startDate" value="<s:date name='match.startDate' format="yyyy-MM-dd HH:mm:ss"/>"/>
				<s:fielderror cssClass="formFieldError"><s:param>match.startDate</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">结束时间</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="endDate" value="<s:date name='match.endDate' format="yyyy-MM-dd HH:mm:ss"/>"/>
				<s:fielderror cssClass="formFieldError"><s:param>match.endDate</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">发布时间</td>
			<td class="td_1">
				<s:date name="match.createDate" format="yyyy-MM-dd HH:mm:ss"/>
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
				<input type="hidden" name="match.matchId" value="${match.matchId}"/>
				<input type="hidden" name="match.problemCount" value="${match.problemCount}"/>
				<input type="hidden" name="match.personCount" value="${match.personCount}"/>
				<input type="hidden" name="match.author" value="${match.author}"/>
				<input type="hidden" name="match.createDate" value="<s:date name='match.createDate' format='yyyy-MM-dd HH:mm:ss'/>"/>
				<input type="hidden" name="match.startDate" value="<s:date name='match.startDate' format="yyyy-MM-dd HH:mm:ss"/>"/>
				<input type="hidden" name="match.endDate" value="<s:date name='match.endDate' format="yyyy-MM-dd HH:mm:ss"/>"/>
				<input type="submit" value="修改比赛" class="button"/>
			</td>
		</tr>
	</table>
	</form>
	
</div>
</body>
</html>



<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>查看公告界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>

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
		<td><div style="text-align:left; margin-left:5px;">查看公告</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		
		<tr><td class="td_1">公告标题</td>
			<td class="td_1">${notice.title}</td>
		</tr>
		<tr><td class="td_1" width="100px">撰写作者</td>
			<td class="td_1">${notice.author}&nbsp;</td>
		</tr>
		<tr><td class="td_1">浏览数</td>
			<td class="td_1">${notice.browser}次</td>
		</tr>
		
		<tr><td class="td_1">发布时间</td>
			<td class="td_1"><s:date name="notice.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		
		<tr>
			<td class="td_1" style="vertical-align:top;">公告内容<br><br></td>
			<td class="td_1" style="height:600px; width:880px; vertical-align:top;">
				<!--<textarea style="width:100%; height:500px;" name="notice.content" readonly>${notice.content}</textarea>-->
				<s:property value="notice.content" escape="false"/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>



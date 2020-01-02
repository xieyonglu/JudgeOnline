<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>查看试题测试界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/testData/css/testData.css"/>

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
		<td><div style="text-align:left; margin-left:5px;">查看测试用例</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		
		<tr><td class="td_1">试题标题</td>
			<td class="td_1">${testData.problem.title}</td>
		</tr>
		
		<tr><td class="td_1">发布时间</td>
			<td class="td_1">${testData.createDate}</td>
		</tr>
		
		<tr>
			<td class="td_1" style="vertical-align:top;">输入<br><br></td>
			<td class="td_1">
				<textarea style="width:825px; height:200px;" readonly><s:property value="testData.input"/></textarea>
			</td>
		</tr>
		
		<tr>
			<td class="td_1" style="vertical-align:top;">输出<br><br></td>
			<td class="td_1">
				
				<textarea style="width:100%; height:200px;" readonly><s:property value="testData.output"/></textarea>
			</td>
		</tr>
	</table>
	
</div>
</body>
</html>



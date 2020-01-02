<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>设置试题类型信息</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/type/css/type.css"/>
<style type="text/css">
.formFieldError{
	font-family:verdana,arial,helvetica,sans-serif;
	font-size:12px;
	color:#ff3300;
	margin:1px; 
	padding:4px; 
	vertical-align:bottom;
}

.title{
	margin-top:20px;
	text-align:center;
	height:50px;
	color:red;
	font-size:16px;
}
</style>
</head>

<body>
<br/><br/>
<form action="type_insertType.action" method="post">
<table border="1" cellspacing="2px" cellpadding="3px" width="500px">
	<tr>
		<td class="title" colspan="2">
			添加类型
		</td>
	</tr>
	<tr>
		<td class="td_1" style="margin-top:20px;">&nbsp;</td>
		<td>
			<s:fielderror cssClass="formFieldError"><s:param>typeError</s:param></s:fielderror>&nbsp;
		</td>
	</tr>
	<tr>
		<td class="td_1">类型名称:</td>
		<td>
			<input type="text" class="text" style="width:210px;" name="type.typeName" value="${type.typeName}"/>
		</td>
	</tr>
	<tr>
		<td class="td_1">试题数量</td>
		<td>
			<input type="text" class="text" style="width:210px;" name="type.problemCount" value="0" disabled/>
		</td>
	</tr>
	<tr>
		<td class="td_1">&nbsp;</td>
		<td>
			<input type="submit" value="添加" class="button"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>



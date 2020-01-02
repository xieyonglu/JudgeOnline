<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库信息导出</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog.js"></script>

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

<body style="background-color: #D9DFDD">
<h2>导出数据信息：</h2>
<s:fielderror cssClass="formFieldError"><s:param>exportError</s:param></s:fielderror>
<form action="dbBackUpResert_backup.action" method="post" id="form" name="form">

<input id="path" type="text" name="path" size="30">
<input type=button value="浏览..." onclick="browseFolder('path')">
<input type="submit" value="导出" onclick="return importDataBase();"name="submit">
	
<script type="text/javascript">
	function importDataBase(){
		if(document.form.path.value==""){
			alert("请选择要导出的文件的目录！");
			return false;
		}
	}
</script>
</form>

</body>
</html>
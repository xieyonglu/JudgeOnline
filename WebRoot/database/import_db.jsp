<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>���ݿ���Ϣ����</title>

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
<h2>����������Ϣ��</h2>
<s:fielderror cssClass="formFieldError"><s:param>importError</s:param></s:fielderror>
<form action="dbBackUpResert_revert.action" method="post" enctype="multipart/form-data" id="form" name="form">

<input type="file" name="dataFile" size="30">
<input type="submit" value="����" onclick="return importDataBase();" name="submit">
	
<script type="text/javascript">
	function importDataBase(){
		if(document.form.dataFile.value==""){
			alert("��ѡ��Ҫ������ļ���");
			return false;
		}
	}
</script>
</form>

</body>
</html>
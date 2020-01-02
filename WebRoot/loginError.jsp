<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<style type="text/css">
<!--
td {  font-family: "宋体"; font-size: 9pt; color: #333333; text-decoration: none}
.STYLE1 {color: #FFFFFF}
.formFieldError{
	font-family:verdana,arial,helvetica,sans-serif;
	font-size:12px;
	color:#ff3300;
	margin:1px; 
	padding:4px; 
	vertical-align:bottom;
}

-->
</style>
</head>
<body>
<br><br><br>
<table width='300' border='0' align='center' cellpadding='4' cellspacing='0' bgcolor="#FFCC00" class='border'>
	<tr >
		
    <td height='15' colspan='2' align="center" bgcolor="#CC6600" class='title STYLE1'>操作: 用户评论失败!</td>
</tr>
<tr>
    <td height='23' colspan='2' align="center" class='tdbg'> <br>
      <br>
      	<s:fielderror cssClass="formFieldError"><s:param>commentError</s:param></s:fielderror><br>
      <br> <a href='javascript:onclick=history.go(-1)'>【返回】</a> <br>
      <br></td>
</tr></table>
</body></html>
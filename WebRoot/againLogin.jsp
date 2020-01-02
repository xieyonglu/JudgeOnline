<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<html>
<head>
<style type="text/css">
<!--
td {  font-family: "宋体"; font-size: 9pt; color: #333333; text-decoration: none}
.STYLE1 {color: #FFFFFF}
-->
</style>
</head>
<body>
<br><br><br>
<table width='300' border='0' align='center' cellpadding='4' cellspacing='0' bgcolor="#FFCC00" class='border'>
	<tr >
		
    <td height='15' colspan='2' align="center" bgcolor="#CC6600" class='title STYLE1'>操作: 确认身份失败!</td>
</tr>
<tr>
    <td height='23' colspan='2' align="center" class='tdbg'> <br>
      <br>
      	  session已过期，或你的权限不够...<br>
      <br><a href="${pageContext.request.contextPath}/page/login.jsp" target="_blank">点击</a>，请重新登录！<br>
      <br></td>
</tr></table>
</body></html>
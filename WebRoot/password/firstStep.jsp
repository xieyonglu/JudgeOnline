<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title>��������</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/password/css/password.css">
</head>

<body>

<table align='center' width='300' border='0' cellpadding='4' cellspacing='0' class='border'>
  <tr > 
    <td height='15' colspan='2' bgcolor="#FFCC33" class='title'>�������� &gt;&gt; ��һ���������û���</td>
  </tr>
  <tr> 
    <td height='100' colspan='2' align="center" bgcolor="#FFDF7D" class='tdbg'>
    <form name="form1" method="post" action="getPassword_getPassword.action">
        <table><tr><td><s:fielderror cssClass="formFieldError"><s:param>userNameError</s:param></s:fielderror></td></tr></table>
        <strong> ����������û�����</strong> 
        <input name="userName" type="text" id="UserName" size="20" maxlength="20">
        <br>
        <br>
        <input name="action" type="hidden" id="Action" value="first">
        <input name="Next" type="submit" id="Next" style="cursor:hand;" value="��һ��">
        <input name="Cancel" type="button" id="Cancel" style="cursor:hand;" onClick="window.close();" value=" ȡ�� ">
    </form></td>
  </tr>
</table>

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>��������</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/password/css/password.css">
</head>

<body>

<table align='center' width='300' border='0' cellpadding='4' cellspacing='0' class='border'>
	<tr>
		<td height='15' colspan='2' bgcolor="#FFCC33" class='title'>�������� &gt;&gt; ����������������</td>
	</tr>
	<tr>
		<td height='120' colspan='2' align="center" bgcolor="#FFDF7D" class='tdbg'>
    		<form name="form1" method="post" action="getPassword_changePassword.action?action=three">
		        <table><tr><td><s:fielderror cssClass="formFieldError"><s:param>passwordError</s:param></s:fielderror></td></tr></table>
		        <br/>��&nbsp;��&nbsp;�룺<input type="password" name="password"/><br/> 
        		<br/>ȷ�����룺<input type="password" name="confirmPwd"/><br/>
         		<!--
         			<input name="Cancel" type="button" id="Cancel" style="cursor:hand;" onClick="javascript:window.close();" value="�ر� ">
         		--><br/>
         		<input type="hidden" name="user.userId" value="${user.userId}"/>
         		<input type="submit" id="Cancel" style="cursor:hand;" value="ȷ��"/>
         		<input name="Cancel" type="button" id="Cancel" style="cursor:hand;" onClick="javascript:window.location.href='${pageContext.request.contextPath}/page/front/center.jsp'" value="�ر� ">
    		</form>
    	</td>
    </tr>
</table>

</body>
</html>

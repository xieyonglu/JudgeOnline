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
		<td height='15' colspan='2' bgcolor="#FFCC33" class='title'>�������� &gt;&gt; ��������������ʾ</td>
	</tr>
	<tr>
		<td height='100' colspan='2' align="center" bgcolor="#FFDF7D" class='tdbg'>
    		<form name="form1" method="post" action="">
		        <br/> �û�<span style="color:green;"><b>${user.userName}</b></span>����ã�<br/>
		        <br/>��������ǣ�<strong>${user.password}</strong><br/> 
        		<br/><div style="color:red;"><b>���μǣ�����</b></div><br/>
         		<!--
         			<input name="Cancel" type="button" id="Cancel" style="cursor:hand;" onClick="javascript:window.close();" value="�ر� ">
         		-->
         		<input name="Cancel" type="button" id="Cancel" style="cursor:hand;" onClick="javascript:window.location.href='${pageContext.request.contextPath}/page/front/center.jsp'" value="�ر� ">
    		</form>
    	</td>
    </tr>
</table>

</body>
</html>

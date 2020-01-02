<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>忘记密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/password/css/password.css">

</head>

<body>

<table align='center' width='300' border='0' cellpadding='4' cellspacing='0' class='border'>
	<tr>
		<td height='15' colspan='2' bgcolor="#FFCC33" class='title'>忘记密码 &gt;&gt; 第二步：输入问题答案</td>
	</tr>
	<tr>
		<td height='100' colspan='2' align="center" bgcolor="#FFDF7D" class='tdbg'>
    		<form name="form1" method="post" action="getPassword_getPassword.action">
		        <table><tr><td><s:fielderror cssClass="formFieldError"><s:param>answerError</s:param></s:fielderror></td></tr></table>
		        <br/><strong>问题：</strong>${user.question}<br/><br/>
		        <strong>请输入你的答案：</strong> 
        		<input name="answer" type="text" id="answer" size="20" maxlength="20">
        		<br><br>
        		<input name="action" type="hidden" id="Action" value="second">
        		<input name="userId" type="hidden" id="Action" value="${user.userId}">
        		<input name="Next" type="submit" id="Next" style="cursor:hand;" value="下一步">
         		<input name="Cancel" type="button" id="Cancel" style="cursor:hand;" onClick="window.close();" value="取消 ">
    		</form>
    	</td>
    </tr>
</table>

</body>
</html>

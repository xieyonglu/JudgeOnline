<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@taglib uri="/struts-tags" prefix="s"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>欢迎使用在线评测系统</title>
<style type="text/css">
a{font:10px;text-align:right;}
a:link{text-decoration:none; color:yellow;}
a:visited{text-decoration:none; color:blue;}
a:hover{text-decoration:underline; color:red;}
a:active{text-decoration:none; color:green;}
</style>


<style type="text/css">
A{TEXT-DECORATION: none;}
A:link {color: #000000;}
A:visited {color: #000000}
A:active {color: #000000}
A:hover{COLOR: #CC0000;position: relative; right: 0px; top: 1px}


BODY
{
FONT-SIZE: 9pt;background:#FFFFFF

}
TD
{
FONT-FAMILY:宋体;FONT-SIZE: 9pt;line-height: 150%; 

}
Input
{

}
Button
{
FONT-SIZE: 9pt;HEIGHT: 20px; 
}
Select
{
FONT-SIZE: 9pt;HEIGHT: 20px;

}
.border
{
border: 1px solid #528096;
}
.border2
{
background:#ffffff;BORDER-bottom: #ff9900 1px solid; 
}
.FootBg
{
background:#C2DCE9; 
}
.title
{
background:url(Skin/2/head_bg.GIF);height: 30;

}
.tdbg
{
background:#C2DCE9; 
}

.title_left
{
background:url(Skin/2/left_bg2.gif);height: 34;
}
.tdbg_left
{
background:#AED1E3;
border: 1px solid #528096;
}

.title_right
{
background:url(Skin/2/Right_bg.gif);height: 32;
}

</style>


</head>

<body>

<s:if test="#session.loginUser!=null">
<span style="font-size:18px;"><b>登录用户</b></span>
<div class="articleinfo"></div>
	<p>
		<span style="font-size:18px;"><b>
		欢迎您:<a href="${pageContext.request.contextPath }/page/user/main.jsp" target="_blank">${sessionScope.loginUser.userName}</a>
		</b></span>
		&nbsp;&nbsp;<a href="login_loginOutUser.action" target="_parent">退出</a>
	</p>
</s:if>
<s:else>
<!--
<div class="articleinfo">


<form action="login_loginUser.action" method="post" name="loginForm" >
	<s:fielderror></s:fielderror>
	账号：<input type="text" name="userName" size="7"/><br></br>
	密码：<input type="password" name="password" size="9"/><br></br>
	&nbsp;&nbsp;<input type="submit" value="登录" onclick="check()"/>&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath }/user/register.jsp" target="_blank">注册</a><br></br>
</form>
-->



<table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
<tr>
    <td width="211" valign="top" background="images/left_bg.gif">
		<table width="195" border="0" cellpadding="0" cellspacing="0">
			
		</table>
		<tr>
				<td width="195">
					<img src="images/login.png" alt="登陆1" width="211" height="30" border="0" usemap="#Map" />
				</td>
			</tr>
			<tr>
				<td>
					<table border="0" align="center" cellpadding="0" cellspacing="4">
					<tr>
						<td width="5"></td>
						<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<tr>
							<td>
							<table width='100%' border='0' cellspacing='0' cellpadding='0'>
							
							<form action="login_loginUser.action" method="post">
							<tr>
								<td height='25' align='right'>用&nbsp;&nbsp;户：</td>
								<td width='13' height='25'>
									<input name='userName' type='text' id='UserName' style="width:80px;">
								</td>
								<td width='47' rowspan='2'>
									<input type='image' src='images/login.gif' id='Login' value='登录'>
								</td>
							</tr>
							<tr>
								<td height='25' align='right'>密&nbsp;&nbsp;码：</td>
								<td height='25'>
									<input name='password' type='password' id='Password' style="width:80px;">
								</td>
							</tr>
							<tr align='center'>
								<td colspan='3' height='36'>
									<table width='100%' height='8' border='0' cellpadding='0' cellspacing='0'>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
									<table width='90%' border='0' cellspacing='0' cellpadding='0'>
										<tr><td height='1' bgcolor='#E1E1E1'></td></tr>
										<tr><td height='1' bgcolor='#FFFFFF'></td></tr>
									</table>
									<table width='100%' height='8' border='0' cellpadding='0' cellspacing='0'>
										<tr><td></td></tr>
									</table>
									<a href="${pageContext.request.contextPath }/user/register.jsp" target="_blank">
									<img border=0 src='images/Reset.gif'></a>&nbsp;&nbsp;
									<a href="${pageContext.request.contextPath}/password/firstStep.jsp" target="main"><img border=0 src='images/forget.gif'></a>
								</td>
							</tr>
							</form>
							</table>
							</td>
							</tr>
						</table>
						</td>
						<td width="6"></td>
			 		</tr>
					</table>
			</td>
			</tr>
	</td>
	</tr>
</table>
</s:else>

<!--
<a href="${pageContext.request.contextPath}/page/login.jsp" target="_blank">管理员登录</a>
-->

</body>
</html>



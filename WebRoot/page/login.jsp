<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>ACM/ICPC管理平台系统</title>
<link href="css/index.css" rel="stylesheet" type="text/css" />

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

<body scroll="no">
<form action="login_loginAdmin.action">
<table width="708" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="106" class="tab1">&nbsp;</td>
  </tr>
  <tr>
    <td height="200" valign="top" class="tab2"><table width="100%" border="0" cellspacing="2" cellpadding="0">
	  <tr>
        <td width="70"height="20" align="center">&nbsp;</td>
      <td><s:fielderror cssClass="formFieldError"><s:param>loginError</s:param></s:fielderror></td>
      </tr>
	  <tr>
        <td width="70" align="center">用户名:</td>
        <td><input name="userName" type="text" class="id" style="width:160px;" value="<s:property value='userName'/>"/></td>
      </tr>
      <tr>
        <td width="70" align="center">密&nbsp;&nbsp;码:</td>
        <td><input name="password" type="password" class="id" style="width:160px;" value="<s:property value='password'/>"/></td>
      </tr>
      <tr>
        <td width="70" align="center">验证码:</td>
        <td>
        	<table border="0" cellspacing="0px" cellpadding="0px"><tr>
        	<td><input type="text" class="id" size="8" maxlength="6" name="verifycode"/></td>
        	<td style="padding-left:17px;">
        		<img src="<%=request.getContextPath()%>/image.jsp" width="80px" height="20px"/>
        	</td>
			</tr></table>	
		</td>
      </tr>
      <tr>
        <td height="20" align="center">&nbsp;</td>
        <td height="25" align="left"><input type="image" src="images/dx_04.gif" value="提交" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="216" valign="top" class="tab3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center">本系统技术支持由:<a href="http://www.huanghuai.edu.cn/">http://www.huanghuai.edu.cn/</a></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>

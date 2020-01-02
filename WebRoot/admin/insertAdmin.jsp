<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>添加管理员信息</title>
<meta name="author" content="xyl"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/admin.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>


<script type="text/javascript">
	$(function(){
		$(":radio[name='admin.sex']").val(['${admin.sex}']);
		if("${sessionScope.loginAdmin.type}"=='super'){
			$(":input[name='admin.type']").val(['${admin.type}']);
		}
	});
</script>
</head>


<body>
<center>


		<form action="admin_insertAdmin.action" method="post" id="myForm">
			
			<table border="0" cellspacing="3px" cellpadding="5px" width="550px">
				<tr>
					<td colspan="3" style="text-align:center;">添加管理员信息</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">
						<s:fielderror cssClass="formFieldError"><s:param>existError</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td width="50px">用&nbsp;户&nbsp;名:</td>
					<td width="100px"><input type="text" class="text" name="admin.userName" value="${admin.userName}"/></td>
					<td width="450px" height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.userName</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
					<td><input type="password" class="text" name="admin.password" value="${admin.password}"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.password</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input type="password" class="text" name="confirmPwd" value="<s:property value='confirmPwd'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>confirmPwd</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>真实姓名:</td>
					<td><input type="text" class="text" name="admin.trueName" value="<s:property value='admin.trueName'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.trueName</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>性别:</td>
					<td>
						<input type="radio" name="admin.sex" value="男" checked/>男&nbsp;
						<input type="radio" name="admin.sex" value="女"/>女
					</td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.sex</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input type="text" class="text" name="admin.email" value="<s:property value='admin.email'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.email</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>电话号码:</td>
					<td><input type="text" class="text" name="admin.telephone" value="<s:property value='admin.telephone'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.telephone</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>出生日期:</td>
					<td><input type="text" class="text" name="admin.birthday" value="<s:date name='admin.birthday' format='yyyy-MM-dd'/>"
							onclick="SelectDate(this)" readonly="readonly"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.birthday</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>家庭住址:</td>
					<td><input type="text" class="text" name="admin.address" value="${admin.address}"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.address</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>权&nbsp;&nbsp;&nbsp;&nbsp;限:</td>
					<td><select class="text" name="admin.type">
							<option value="select">--请选择--</option>
							<option value="super">超级管理员</option>
							<option value="general">普通管理员</option>
							<option value="vip">V I P 会员</option>
						</select>
					</td>
					<td height="32">&nbsp;
						<s:fielderror cssClass="formFieldError"><s:param>admin.type</s:param></s:fielderror>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td style="text-align:right;">
						<input type="submit" class="button" value="添加"/>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>

</center>

</body>
</html>


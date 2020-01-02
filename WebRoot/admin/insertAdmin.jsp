<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>��ӹ���Ա��Ϣ</title>
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
					<td colspan="3" style="text-align:center;">��ӹ���Ա��Ϣ</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">
						<s:fielderror cssClass="formFieldError"><s:param>existError</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td width="50px">��&nbsp;��&nbsp;��:</td>
					<td width="100px"><input type="text" class="text" name="admin.userName" value="${admin.userName}"/></td>
					<td width="450px" height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.userName</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					<td><input type="password" class="text" name="admin.password" value="${admin.password}"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.password</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>ȷ������:</td>
					<td><input type="password" class="text" name="confirmPwd" value="<s:property value='confirmPwd'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>confirmPwd</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>��ʵ����:</td>
					<td><input type="text" class="text" name="admin.trueName" value="<s:property value='admin.trueName'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.trueName</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>�Ա�:</td>
					<td>
						<input type="radio" name="admin.sex" value="��" checked/>��&nbsp;
						<input type="radio" name="admin.sex" value="Ů"/>Ů
					</td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.sex</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>����:</td>
					<td><input type="text" class="text" name="admin.email" value="<s:property value='admin.email'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.email</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>�绰����:</td>
					<td><input type="text" class="text" name="admin.telephone" value="<s:property value='admin.telephone'/>"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.telephone</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>��������:</td>
					<td><input type="text" class="text" name="admin.birthday" value="<s:date name='admin.birthday' format='yyyy-MM-dd'/>"
							onclick="SelectDate(this)" readonly="readonly"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.birthday</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>��ͥסַ:</td>
					<td><input type="text" class="text" name="admin.address" value="${admin.address}"/></td>
					<td height="32">
						<s:fielderror cssClass="formFieldError"><s:param>admin.address</s:param></s:fielderror>
					</td>
				</tr>
				<tr>
					<td>Ȩ&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					<td><select class="text" name="admin.type">
							<option value="select">--��ѡ��--</option>
							<option value="super">��������Ա</option>
							<option value="general">��ͨ����Ա</option>
							<option value="vip">V I P ��Ա</option>
						</select>
					</td>
					<td height="32">&nbsp;
						<s:fielderror cssClass="formFieldError"><s:param>admin.type</s:param></s:fielderror>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td style="text-align:right;">
						<input type="submit" class="button" value="���"/>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>

</center>

</body>
</html>


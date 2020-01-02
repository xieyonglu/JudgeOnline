<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>添加管理员信息</title>
<meta name="author" content="xyl"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/admin.css"/>

</head>


<body>
<center>
<div id="one">
	<!--two层开始-->
	<div id="two">
		<form action="admin_insertAdmin.action" method="post">
			<table border="0" cellspacing="3px" cellpadding="5px">
				<tr style="text-align:center;">
					<td colspan="2" style="font:15px; color:red;">管理员${admin.userName}的基本信息</td>
				</tr>
				<tr>
					<td>用&nbsp;户&nbsp;名:</td>
					<td><s:property value="admin.userName"/></td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
					<td>
						<script type="text/javascript">
							var password="${admin.password}";
							password=password.substr(0,15);
							document.write(password+"...");
						</script>
					</td>
				</tr>
				<tr>
					<td>真实姓名:</td>
					<td><s:property value="admin.trueName"/></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><s:property value="admin.sex"/></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><s:property value="admin.email"/></td>
				</tr>
				<tr>
					<td>电话号码:</td>
					<td><s:property value="admin.telephone"/></td>
				</tr>
				<tr>
					<td>出生日期:</td>
					<td><s:date name="admin.birthday" format="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td>家庭住址:</td>
					<td><s:property value="admin.address"/></td>
				</tr>
				<tr>
					<td>权&nbsp;&nbsp;&nbsp;&nbsp;限:</td>
					<td>
						<script type="text/javascript">
							var type="${admin.type}";
							if(type=="super")document.write("超级管理员");
							else if(type=="general")document.write("普通管理员");
							else if(type=="vip")document.write("VIP会员");
							else document.write("暂无");
	        			</script>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</center>

</body>
</html>
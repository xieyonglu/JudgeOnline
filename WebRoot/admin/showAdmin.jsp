<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>��ӹ���Ա��Ϣ</title>
<meta name="author" content="xyl"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/css/admin.css"/>

</head>


<body>
<center>
<div id="one">
	<!--two�㿪ʼ-->
	<div id="two">
		<form action="admin_insertAdmin.action" method="post">
			<table border="0" cellspacing="3px" cellpadding="5px">
				<tr style="text-align:center;">
					<td colspan="2" style="font:15px; color:red;">����Ա${admin.userName}�Ļ�����Ϣ</td>
				</tr>
				<tr>
					<td>��&nbsp;��&nbsp;��:</td>
					<td><s:property value="admin.userName"/></td>
				</tr>
				<tr>
					<td>��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					<td>
						<script type="text/javascript">
							var password="${admin.password}";
							password=password.substr(0,15);
							document.write(password+"...");
						</script>
					</td>
				</tr>
				<tr>
					<td>��ʵ����:</td>
					<td><s:property value="admin.trueName"/></td>
				</tr>
				<tr>
					<td>�Ա�:</td>
					<td><s:property value="admin.sex"/></td>
				</tr>
				<tr>
					<td>����:</td>
					<td><s:property value="admin.email"/></td>
				</tr>
				<tr>
					<td>�绰����:</td>
					<td><s:property value="admin.telephone"/></td>
				</tr>
				<tr>
					<td>��������:</td>
					<td><s:date name="admin.birthday" format="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td>��ͥסַ:</td>
					<td><s:property value="admin.address"/></td>
				</tr>
				<tr>
					<td>Ȩ&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					<td>
						<script type="text/javascript">
							var type="${admin.type}";
							if(type=="super")document.write("��������Ա");
							else if(type=="general")document.write("��ͨ����Ա");
							else if(type=="vip")document.write("VIP��Ա");
							else document.write("����");
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
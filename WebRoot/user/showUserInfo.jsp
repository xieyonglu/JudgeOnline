<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title>��ʾ�û���Ϣ</title>
<meta name="author" content="xyl">
<style type="text/css">
body{
	text-align:center;
}

.one{
	width:1000px;
	height:500px;
	background-color:#C9F;
	margin-top:20px;
}
table{
	width:800px;
	/*height:800px;*/
}
td{
	background-color:#9CC;
	font:13px;
	color:blue;
	height:35px;
}

.head{
	background-color:#99F;
	font:bold 15px;
	color:blue;
}
</style>
</head>
<body>

<div class="one">
<div style="margin-top:20px; width:1000px; height:20px; background-color:yellow;"></div>
<br>
<table border="2px solid black" cellspacing="0px" cellpadding="5px">
	<tr>
		<td width="70px" class="head">�û���</td>
		<td width="200px"><s:property value="user.userName"/></td>
		<td width="40px" class="head">�Ա�</td>
		<td width="200px" colspan="3" class="head">����������</td>
		<td width="50px" class="head">����</td>
		<td width="80px" class="head">����ѧ��</td>
		<td width="120px" rowspan="5"><img src="<%=request.getContextPath()%>/upload/user/<s:property value='user.picture'/>" width="120px" height="160px"></td>
	</tr>
	<tr>
		<td rowspan="1" class="head">��ʵ����</td>
		<td rowspan="1"><s:property value="user.otherInfo.trueName"/></td>
		<td rowspan="2"><s:property value="user.otherInfo.sex"/></td>
		
		<td><s:date name="user.otherInfo.birthday" format="yyyy"/>��</td>
		<td><s:date name="user.otherInfo.birthday" format="MM"/>��</td>
		<td><s:date name="user.otherInfo.birthday" format="dd"/>��</td>

		<td rowspan="2"><s:property value="user.otherInfo.province"/></td>
		<td rowspan="2"><s:property value="user.otherInfo.eduDegree"/></td>
	</tr>
	<tr>
		<td class="head">����</td>
		<td>
			<script type="text/javascript">
				var password="${user.password}";
				password=password.substr(0,15);
				document.write(password+"...");
			</script>
		</td>
		<td colspan="2">
			<script type="text/javascript">
				var age="<s:date name='user.otherInfo.birthday' format='yyyy'/>";
				var now=new Date();
				document.write(now.getYear()-age);
			</script>
		</td>
		<td>��</td>
	</tr>

	<tr>
		<td class="head">�˺ſ���</td>
		<td colspan="2"><s:property value="user.userEnable"/></td>
		<td colspan="2" class="head">���ۿ���</td>
		<td colspan="3"><s:property value="user.commentEnable"/></td>
	</tr>
	<tr>
		<td class="head">�ֻ�</td>
		<td colspan="2"><s:property value="user.otherInfo.telephone"/></td>
		<td colspan="2" class="head">����</td>
		<td colspan="3"><s:property value="user.email"/></td>
	</tr>
	<tr>
		<td class="head">סַ</td>
		<td colspan="8">
			<s:property value="user.otherInfo.province"/>ʡ
			<s:property value="user.otherInfo.city"/>��
			<s:property value="user.otherInfo.area"/>��
		</td>
	</tr>
	<tr>
		<td class="head">����ʱ��</td>
		<td colspan="8"><s:date name="user.createDate" format="yyyy-MM-dd HH:ss:mm"/></td>
	</tr>

	<tr>
		<td rowspan="3" class="head">��ϸ��Ϣ</td>
		<td class="head">��ҵ���</td>
		<td colspan="3" class="head">ְλ</td>
		<td colspan="3" class="head">������</td>
		<td class="head">����</td>
	</tr>
	<tr>
		<td><s:property value="user.otherInfo.occDirectory"/></td>
		<td colspan="3"><s:property value="user.otherInfo.position"/></td>
		<td colspan="3"><s:property value="user.otherInfo.income"/></td>
		<td><s:property value="user.point"/>0</td>
	</tr>
	<tr>
		<td class="head">��Ȥ����</td>
		<td colspan="7"><s:property value="user.otherInfo.hobby"/></td>
	</tr>
	<tr>
		<td class="head">�ܱ�����</td>
		<td colspan="8"><s:property value="user.question"/></td>
	</tr>
	<tr>
		<td class="head">�ܱ���</td>
		<td colspan="8"><s:property value="user.answer"/></td>
	</tr>
	
</table>
</div>
</body>
</html>



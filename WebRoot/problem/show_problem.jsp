<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>�鿴�������</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>

</head>

<body>

<table border="0" cellspacing="0px" cellpadding="0px" style="width:1030px; text-align:left;">
	<tr><td><img src="${pageContext.request.contextPath}/image/logo_grzx.gif"></td></tr>
</table>

<div style="border:1px solid black; margin-top:10px;"></div>


<div style="width:1030px; height:36px; margin-top:10px; background-color:orange;">
<div style="text-align:left; margin-left:20px; margin-top:9px;">

<table border="0" cellspacing="3px" cellpadding="1px" width="1000px">
	<tr>
		<td><div style="text-align:left; margin-left:5px;">�鿴����</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		<tr><td class="td_1" width="100px">׫д����</td>
			<td class="td_1">${problem.admin.userName}</td>
		</tr>
		<tr><td class="td_1">�������</td>
			<td class="td_1">${problem.title}</td>
		</tr>
		<tr><td class="td_1">������Դ</td>
			<td class="td_1">${problem.source}</td>
		</tr>
		
		<tr><td class="td_1">�Ѷ�ϵ��</td>
			<td class="td_1">${problem.hardFactor}</td>
		</tr>
		
		<tr><td class="td_1">�������</td>
			<td class="td_1">${problem.type.typeName}</td>
		</tr>
		
		<tr><td class="td_1">ʱ������</td>
			<td class="td_1">${problem.timeLimit}&nbsp;(MS)</td>
		</tr>
		<tr><td class="td_1">�ڴ�����</td>
			<td class="td_1">${problem.memoryLimit}&nbsp;(KB)</td>
		</tr>
		<tr><td class="td_1">����ʱ��</td>
			<td class="td_1"><s:date name="problem.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr><td class="td_1">�ύ��</td>
			<td class="td_1">${problem.passCount}/${problem.submitCount}</td>
		</tr>
		
		<tr>
			<td class="td_1" style="vertical-align:top;">��������<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.description}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">����<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.input}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">���<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.output}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">��������<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.caseInput}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">�������<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.caseOutput}
			</td>
		</tr>
	</table>
</div>
</body>
</html>



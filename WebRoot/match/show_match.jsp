<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>��ʾ��������</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>

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

<body>

<table border="0" cellspacing="0px" cellpadding="0px" style="width:1030px; text-align:left;">
	<tr><td><img src="<%=request.getContextPath()%>/image/logo_grzx.gif"></td></tr>
</table>

<div style="border:1px solid black; margin-top:10px;"></div>


<div style="width:1030px; height:36px; margin-top:10px; background-color:orange;">
<div style="text-align:left; margin-left:20px; margin-top:9px;">

<table border="0" cellspacing="3px" cellpadding="1px" width="1000px">
	<tr>
		<td><div style="text-align:left; margin-left:5px;">��ʾ����</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		<tr><td class="td_1" width="100px">׫д����</td>
			<td class="td_1">${match.author}&nbsp;</td>
		</tr>
		<tr><td class="td_1">��������</td>
			<td class="td_1">${match.matchName}</td>
		</tr>
		<tr><td class="td_1">������ַ</td>
			<td class="td_1">${match.address}</td>
		</tr>
		<tr><td class="td_1">����״̬</td>
			<td class="td_1">
				<script type="text/javascript">
					var state=${match.state};
					if(state=="0"){
						document.write("��δ��ʼ");
					}else if(state=="1"){
						document.write("<div style='color:red;'><b>���ڽ���...</b></div>");
					}else{
						document.write("�Ѿ�����");
					}
	        	</script>
			</td>
		</tr>
		<tr><td class="td_1">��ʼʱ��</td>
			<td class="td_1"><s:date name="match.startDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr><td class="td_1">����ʱ��</td>
			<td class="td_1"><s:date name="match.endDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr><td class="td_1">����ʱ��</td>
			<td class="td_1"><s:date name="match.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr><td class="td_1">�������</td>
			<td class="td_1">${match.problemCount}</td>
		</tr>
		<tr><td class="td_1">ѡ�ָ���</td>
			<td class="td_1">${match.personCount}</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">��������<br><br></td>
			<td class="td_1">
				<!--�������ݴ�����ʾ-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>match.content</s:param></s:fielderror>
				</div>
				<textarea style="width:100%; height:300px;" name="match.content" id="editor"><s:property value="match.content"/></textarea>
			</td>
		</tr>
		
	</table>
	
</div>
</body>
</html>



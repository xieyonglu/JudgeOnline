<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>用户头像管理</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/newsPic/css/newsPic.css"/>

<style type="text/css">
body{
	background-color:#E7FEEF;
	width:1200px;
	height:800px;
	text-align:center;
}

select{
	position:relative;
	font-size:15px;
	line-height:60px;
	border:0px;
	color:red;
}

.button{
	width:80px;
	height:30px;
	border-style:none;
	background-color:yellow;
	color:green;
	font-size:12px;
	cursor:hand;
}

.td_1{
	width:400px;
	font-size:12px;
}

.text{
	border-style:groove;
	width:90px;
	height:25px;
}

.formFieldError{
	font-family:verdana,arial,helvetica,sans-serif;
	font-size:12px;
	color:#ff3300;
	margin:1px; 
	padding:4px; 
	vertical-align:bottom;
}
</style>
<script type="text/javascript">
function show(){
	myForm.image.src=myForm.myPicture.value;
	alert(myForm.myPicture.value+"--"+myForm.image.src);
}
</script>
</head>

<body>
<%
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Cache-Control","no-cache"); 
    response.setDateHeader("Expires",0);
%>
<form action="updateMyPicture.action" method="post" name="myForm" enctype="multipart/form-data">
<table border="0" cellspacing="0px" cellpadding="5px">
	<tr>
		<td class="td_1"><s:fielderror cssClass="formFieldError"><s:param>pictureError</s:param></s:fielderror><td>
	</tr>
	<tr>
		<td class="td_1">
			<img src="${pageContext.request.contextPath}/upload/user/${user.picture}" height="300" width="300" name="image"/>
		</td>
	</tr>
	<tr>
		<td>
			<input type="file" name="myPicture" onchange="show()"/>
			<input type="hidden" name="userId" value="${user.userId}"/>
			<input type="submit" value="修改头像"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>



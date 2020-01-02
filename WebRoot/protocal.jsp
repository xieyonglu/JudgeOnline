<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<html>
<head>
<title></title>
<meta name="author" content="xyl">
<style type="text/css">
body{
background-color:#b0c4de;
margin:0px;
text-align:center;
}
a{font:13px;text-align:right;}
a:link{text-decoration:none; color:yellow;}
a:visited{text-decoration:none; color:blue;}
a:hover{text-decoration:underline; color:red;}
a:active{text-decoration:none; color:green;}

#one{
background-color:#b2e8f5;
width:800px;
height:550px;
}

.text{
width:750px;
height:400px;
}

.button{
border-style:none;
background-color:#0640bh;
width:60px;
height:30px;
}
</style>
</head>
<body>
<div id="one">
<table border="0" cellspacing="2px" cellpadding="6px" width="100%">
	<tr>
		<td width="680px"><img src="image/logo_grzx.gif"/></td>
		<td width="120px;"><a href="">&lt;&lt;&nbsp;返回草草网首页</a></td>
	</tr>
	
	<tr>
		<td colspan="2"><font size="3px" color="#0640bh"><b>请查看用户协议</b></font></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<div style="border:2px solid red;"></div>
			<textarea class="text"><jsp:include page="file/protocal.txt"/></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;">
			<input type="button" class="button" value="关闭" onclick="javascript:window.close()">
		</td>
	</tr>
</table>
</div>
</body>
</html>
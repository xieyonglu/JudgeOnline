<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Insert title here</title>

<style type="text/css">
.show{
	width:500px;
	height:200px;
	background-color:gray;
	text-align:center;
	vertical-align:middle;
	border:1 solid red;
	font-size:20px;
}
</style>
</head>
<body>
	<center>
		<div class="show">
			<div style="margin-top:50px;">
				<div style="font:15px; color:red;"><b>${match.matchName}</b></div>
				<div style="margin-top:10px;">代码提交成功，结果稍后为你公布，<a href="code_pageFindCode.action?matchId=${match.matchId}&action=all">点击</a>可查看！</div>
			</div>
		</div>
	</center>
</body>
</html>
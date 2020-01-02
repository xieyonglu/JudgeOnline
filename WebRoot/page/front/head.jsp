<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用在线评测系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css" type="text/css" media="screen,projection" />

<script type="text/javascript">
	document.ondragstart=function(){return false;}
</script>
</head>

<body style="margin-top:10px;">
<div id="header">
	<div id="top"><img src="${pageContext.request.contextPath}/page/front/images/top.jpg" width="100%" height="70px" /></div>
	<ul id="nav">
		<li>&nbsp;</li>
		<li><a href="${pageContext.request.contextPath}/page/front/center.jsp" target="main">首页</a></li>
		<li><a href="problem_pageProblem.action?action=all" target="main">练习场</a></li>
		<li><a href="type_pageType.action?action=all" target="main">试题分类</a></li>
		<li><a href="sort_pageSort.action?action=all" target="main">在线排名</a></li>
		<li><a href="match_pageMatch.action?action=all" target="main">相关比赛</a></li>
		<li><a href="comment_pageComment.action?action=all" target="main">问与答</a></li>
		<li style="margin-left:300px;">
			<a href="${pageContext.request.contextPath}/page/login.jsp" target="_blank">管理员登录</a>
		</li>
	</ul>
</div>

</body>
</html>



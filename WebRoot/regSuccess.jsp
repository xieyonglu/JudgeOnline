<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title>注册成功</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/regSuccess.css"/>
</head>

<body>
<center>
<div id="one">
	<table border="0" cellspacing="2px" cellpadding="6px" width="100%">
		<tr>
			<td width="800px" rowspan="2"><img src="image/logo_grzx.gif"/></td>
			<td style="text-align:right;"><a href="#">草草网首页</a></td>
		</tr>
		
		<tr>
			<td style="text-align:right;">
				<a href="#">登录</a>|<a href="#">注册</a>
			</td>
		</tr>
	</table>

	<div style="border:10px solid green; margin-top:5px;"></div>
	<div style="margin-top:10px;">
	<table border="0" cellspacing="2px" cellpadding="3px" width="100%">
		<tr><td><font size="4px" color="green"><b>恭喜你注册成功！<b></font></td></tr>
		<tr><td>草草之旅，从这里开始...</td></tr>
	</table>
	<div style="border:3px solid gray; margin-top:5px;"></div>
	<div style="margin-top:20px;"></div>

	<!--two显示层开始-->
	<div id="two">
		<table border="0" cellspacing="25px" cellpadding="5px" width="100%">
			<tr>
				<td class="td_1">
					<a href="#">进入个人中心&nbsp;&gt;&gt;</a>
					<div class="td_x">打造只属于我的资讯门户</div>
				</td>
				<td class="td_1">
					<a href="#">到论坛转转&nbsp;&gt;&gt;</a>
					<div class="td_x">跟踪热点事件发现社会不同</div>
				</td>
			</tr>
			<tr>
				<td class="td_1">
					<a href="#">进入我的博客&nbsp;&gt;&gt;</a>
					<div class="td_x">与草草名博主为邻，撰写心情</div>
				</td>
				<td class="td_1">
					<a href="#">去首页看新闻&nbsp;&gt;&gt;</a>
					<div class="td_x">与全球华人共享新闻 参与互动</div>
				</td>
			</tr>
			<tr>
				<td class="td_1">
					<a href="#">进入微博&nbsp;&gt;&gt;</a>
					<div class="td_x">记录点滴生活，分享精彩世界</div>
				</td>
				<td class="td_1">
					<a href="#">进入读书频道看书&nbsp;&gt;&gt;</a>
					<div class="td_x">海量珍贵图书，任你畅读</div>
				</td>
			</tr>

			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="button" value="进入我的草草主页" class="button" 
						onclick="javascript:window.location='${pageContext.request.contextPath}/page/front/main.jsp'"/>
				</td>
			</tr>
		</table>
	</div>
	<!--two显示层结束-->
</div>
</center>
</body>
</html>
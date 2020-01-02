<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><decorator:title default="装饰器页面"/></title>
		<decorator:head/>
		<link rel="stylesheet" type="text/css" href="Style.css">
	</head>
<body>
	<center>
		<div>
			顶层页面
		</div>
		<hr/>
		<div>
			<ul>
				<li style="float:left; width:100px; height:180px;
					border-right:solid 1px gray; padding-left:10px;
					text-align:left;">
					<font style="font-weight:bold;">歌曲列表</font>
					<br/>myDecorator.jsp<br/>
					<a href="test.jsp">浪子心声</a><br/>
					<a href="#">如果有一天</a><br/>	
					<a href="#">跟我一辈子</a><br/>
					<a href="#">流浪</a><br/>
					<a href="#">天意</a><br/>
					<a href="#">爱火烧不尽</a><br/>
					<a href="#">兄弟</a><br/>
					<a href="#">暗里着迷</a><br/>
					<a href="#">来生缘</a><br/>
				</li>
				<li>
					<decorator:body/>
				</li>
		</div>
		<hr/>
		<div>
			底部页面
		</div>
	</center>

</body>
</html>



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
			<decorator:body/>
		</div>
		<hr/>
		<div>
			底部页面
		</div>
	</center>

</body>
</html>



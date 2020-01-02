<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tiles-tags" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><tiles:getAsString name="title"/></title>
		<link rel="stylesheet" type="text/css" href="Style.css">
	</head>
<body>
	the title is:<tiles:getAsString name="title"/><br>
	<!-- 
	<tiles:insert name="header"/><br>
	<tiles:insert name="content"/><br>
	<tiles:insert name="footer"/><br>
	-->
   <table border="1">
　　<tr><td><tiles:insertAttribute name="header"></tiles:insertAttribute></td></tr>
　　<tr><td><tiles:insertAttribute name="sidebar"></tiles:insertAttribute></td></tr>
　　<tr><td><tiles:insertAttribute name="body"></tiles:insertAttribute></td></tr>
　　<tr><td><tiles:insertAttribute name="footer"></tiles:insertAttribute></td></tr>
　　</table>
</body>
</html>



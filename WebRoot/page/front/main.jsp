<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用在线评测系统</title>
</head>
<frameset rows="115,*" frameborder="no" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/page/front/head.jsp" name="head">
		<frameset cols="230,*">
			<frame src="${pageContext.request.contextPath}/page/front/left.jsp" name="left">
			<frame src="${pageContext.request.contextPath}/page/front/center.jsp"name="main">
		</frameset>
</frameset>
</html>
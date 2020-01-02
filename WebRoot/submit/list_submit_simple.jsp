<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息录入</title>

<style type="text/css">
a{font:12px;}
a:link{text-decoration:none; color:blue;}
a:visited{text-decoration:none; color:blue;}
a:hover{text-decoration:underline; color:red;}
a:active{text-decoration:none; color:green;}

.title{
	font-size:15px;
	color:red;
	text-align:left;
	padding-left:20px;
}

td{
	height:20px;
}
</style>
</head>
<body style="background-color: #D9DFDD">
<center>
<h2>用户<span style="color:red;"><b>${user.userName}</b></span>已经攻克和正在攻克的试题</h2>

<table border="1" cellspacing="0px" cellpadding="0px" width="900px">
	<tr><td colspan="10" class="title"><b>已经攻克</b></td></tr>
	<s:iterator value="#trueList" status="st">
			<s:if test="(#st.index+10)%10==0">
				<tr>
			</s:if>
			<td style="width:100px; height:10px;">
				<a href="problem_findProblem.action?problemId=${problem.problemId}&action=all">
					<s:property value="problem.problemId"/>
				</a>
			</td>
			<s:if test="(#st.index+1)%10==0">
				</tr>
			</s:if>
	</s:iterator>
</table>
<br/><br/>

<table border="1" cellspacing="0px" cellpadding="0px" width="900px">
	<tr><td colspan="10" class="title"><b>正在攻克</b></td></tr>
	<s:iterator value="#falseList" status="st">
			<s:if test="(#st.index+10)%10==0">
				<tr>
			</s:if>
			<td style="width:100px; height:10px;">
				<a href="problem_findProblem.action?problemId=${problem.problemId}&action=all">
					<s:property value="problem.problemId"/>
				</a>
			</td>
			<s:if test="(#st.index+1)%10==0">
				</tr>
			</s:if>
	</s:iterator>
</table>

</center>
</body>
</html>
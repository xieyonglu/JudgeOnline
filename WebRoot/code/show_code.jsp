<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/body.css" type="text/css" media="screen,projection" />

<style type="text/css">
body{
	background-color:#DEE5FA;
}
</style>
</head>
<body>

<center>
	<table border="1" cellpadding="1px" cellspacing="1px" width="900px">
		<tr><td colspan="2" class="title">代码运行结果查看</td></tr>
		<tr>
			<td class="td_1">试题题目</td>
			<td class="td_2">${code.problem.title}</td>
		</tr>
		<tr>
			<td class="td_1">提交用户</td>
			<td class="td_2">${code.user.userName}</td>
		</tr>
		<tr>
			<td class="td_1">代码内容</td>
			<td class="td_2">&nbsp;</td>
		</tr>
		<tr>
			<td class="td_1">&nbsp;</td>
			<td class="td_2">
				<textarea cols="110" rows="30" name="problem.content" readonly>${code.codeText}</textarea>
	 		</td>
		</tr>
		<tr>
			<td class="td_1">运行结果</td>
			<td class="td_2">${code.state}</td>
		</tr>
		<tr>
			<td class="td_1">运行时间</td>
			<td class="td_2">${code.runtime}</td>
		</tr>
		<tr>
			<td class="td_1">消耗内存</td>
			<td class="td_2">${code.memory}</td>
		</tr>
		<tr>
			<td class="td_1">使用语言</td>
			<td class="td_2">${code.language}</td>
		</tr>
		<tr>
			<td class="td_1">代码长度</td>
			<td class="td_2">${code.codeLength}</td>
		</tr>
		<tr>
			<td class="td_1">提交时间</td>
			<td class="td_2"><s:date name="code.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</table>
</center>

</body>
</html>



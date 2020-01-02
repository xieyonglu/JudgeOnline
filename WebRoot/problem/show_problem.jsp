<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>查看试题界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>

</head>

<body>

<table border="0" cellspacing="0px" cellpadding="0px" style="width:1030px; text-align:left;">
	<tr><td><img src="${pageContext.request.contextPath}/image/logo_grzx.gif"></td></tr>
</table>

<div style="border:1px solid black; margin-top:10px;"></div>


<div style="width:1030px; height:36px; margin-top:10px; background-color:orange;">
<div style="text-align:left; margin-left:20px; margin-top:9px;">

<table border="0" cellspacing="3px" cellpadding="1px" width="1000px">
	<tr>
		<td><div style="text-align:left; margin-left:5px;">查看试题</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		<tr><td class="td_1" width="100px">撰写作者</td>
			<td class="td_1">${problem.admin.userName}</td>
		</tr>
		<tr><td class="td_1">试题标题</td>
			<td class="td_1">${problem.title}</td>
		</tr>
		<tr><td class="td_1">试题来源</td>
			<td class="td_1">${problem.source}</td>
		</tr>
		
		<tr><td class="td_1">难度系数</td>
			<td class="td_1">${problem.hardFactor}</td>
		</tr>
		
		<tr><td class="td_1">所属类别</td>
			<td class="td_1">${problem.type.typeName}</td>
		</tr>
		
		<tr><td class="td_1">时间限制</td>
			<td class="td_1">${problem.timeLimit}&nbsp;(MS)</td>
		</tr>
		<tr><td class="td_1">内存限制</td>
			<td class="td_1">${problem.memoryLimit}&nbsp;(KB)</td>
		</tr>
		<tr><td class="td_1">发布时间</td>
			<td class="td_1"><s:date name="problem.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr><td class="td_1">提交率</td>
			<td class="td_1">${problem.passCount}/${problem.submitCount}</td>
		</tr>
		
		<tr>
			<td class="td_1" style="vertical-align:top;">试题描述<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.description}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">输入<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.input}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">输出<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.output}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">样例输入<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.caseInput}
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">样例输出<br><br></td>
			<td class="td_1" style="vertical-align:top; height:200px;">
				${problem.caseOutput}
			</td>
		</tr>
	</table>
</div>
</body>
</html>



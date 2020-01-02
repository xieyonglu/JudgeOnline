<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/body.css" type="text/css" media="screen,projection" />

<style type="text/css">
.title{
	text-align:left;
	font:20px;
	font-wight:bold;
	color:red;
}
</style>

<script type="text/javascript">
function submit(){
	var codeTxt=document.getElementsByName("codetxt").item(0).value;
	var lan=document.getElementsByName("language").item(0);
	var language=lan.options[lan.selectedIndex].text;
	//alert(codeTxt+"--"+language);

	location.href="jugeAction.action?codeTxt="+codeTxt+"&language="+language+"&problemId=1&userId=21&action=juge";
}
</script>

</head>
<body>


<center>
${match.matchName}--��Ŀ��Ϣ
-[<a href="code_pageFindCode.action?action=all&problemId=${problem.problemId}&matchId=${match.matchId}">���н��</a>]
-[<a href="code_pageFindCode.action?action=all&problemId=${problem.problemId}&matchId=${match.matchId}&type=sort">��������</a>]
<s:if test="match.state==1">
	-[��������������]
</s:if>
<s:else>
	-[<a href="comment_findComment.action?matchId=${match.matchId}">��������������</a>]
</s:else>


<br/>
<form action="submit_submitCode.action?flag=match" method="post">
	<table border="0" cellpadding="1px" cellspacing="1px" width="800px">
		<tr>
			<td>${problem.title}</td>
		</tr>
		<tr>
			<td>ʱ�����ƣ�${problem.timeLimit}ms&nbsp;&nbsp;|&nbsp;&nbsp;�ڴ����ƣ�${problem.memoryLimit}kb</td>
		</tr>
		<tr>
			<td>�Ѷȣ�${problem.hardFactor}</td>
		</tr>
		<tr><td class="title">����</td></tr>
		<tr><td style="text-align:left;">${problem.description}</td></tr>
		<tr><td class="title">����</td></tr>
		<tr><td style="text-align:left;">${problem.input}</td></tr>
		<tr><td class="title">���</td></tr>
		<tr><td style="text-align:left;">${problem.output}</td></tr>
		<tr><td class="title">��������</td></tr>
		<tr><td style="text-align:left;">${problem.caseInput}</td></tr>
		<tr><td class="title">�������</td></tr>
		<tr><td style="text-align:left;">${problem.caseOutput}</td></tr>
		<tr>
			<td style="text-align:left;">
				<span class="title">�ϴ��ߣ�</span>${problem.admin.userName}
			</td>
		</tr>
		<tr>
			<td style="text-align:left;">
				<select name="language">
					<option value="java">Java</option>
					<option value="cpp">C++</option>
					<option value="c">C</option>
				</select>
				
				<input type="hidden" name="problem.problemId" value="${problem.problemId}"/>
				<input type="hidden" name="match.matchId" value="${match.matchId}"/>
				<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
				<input type="hidden" name="action" value="juge"/>
				
				<s:if test="match.state==0">������δ��ʼ���㲻���ύ��</s:if>
				<s:elseif test="match.state==2">�Բ��𣬱����Ѿ�������</s:elseif>
				<s:else><input type="submit" value="�ύ"/></s:else>
				<s:fielderror cssClass="formFieldError" theme="simple">
					<s:param>submitError</s:param>
				</s:fielderror>
			</td>
		</tr>
		<tr>
			<td style="text-align:left;">
				<textarea cols="100%" rows="15" name="codetxt"></textarea>
			</td>
		</tr>
	</table>
</form>
</center>
</body>
</html>
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

.show{
	text-align:left;
	padding-left:50px;
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
题目信息
-[<a href="code_pageCode.action?action=all&problemId=${problem.problemId}">运行结果</a>]
-[<a href="code_pageCode.action?action=all&problemId=${problem.problemId}&type=sort">本题排行</a>]
-[<a href="comment_findComment.action?problemId=${problem.problemId}">讨论区</a>]
<br/>
<form action="submit_submitCode.action" method="post">
	<table border="0" cellpadding="1px" cellspacing="1px" width="800px">
		<tr>
			<td>${problem.title}</td>
		</tr>
		<tr>
			<td>时间限制：${problem.timeLimit}ms&nbsp;&nbsp;|&nbsp;&nbsp;内存限制：${problem.memoryLimit}kb</td>
		</tr>
		<tr>
			<td>难度：${problem.hardFactor}</td>
		</tr>
		<tr><td class="title">描述</td></tr>
		<tr><td class="show">${problem.description}	</td></tr>
		<tr><td class="title">输入</td></tr>
		<tr><td class="show">${problem.input}</td></tr>
		<tr><td class="title">输出</td></tr>
		<tr><td class="show">${problem.output}</td></tr>
		<tr><td class="title">样例输入</td></tr>
		<tr><td class="show">${problem.caseInput}</td></tr>
		<tr><td class="title">样例输出</td></tr>
		<tr><td class="show">${problem.caseOutput}</td></tr>
		<tr>
			<td style="text-align:left;">
				<span class="title">上传者：</span>${problem.admin.userName}
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
				<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
				<input type="hidden" name="action" value="juge"/>
				<input type="submit" value="提交"/>
				<!--
					<s:if test="#session.loginUser!=null">
						<input type="submit" value="提交"/>
					</s:if>
					<s:else>
						<font color="red"><b>亲，登录后才可以做题噢。。</b></font>
					</s:else>
				-->
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
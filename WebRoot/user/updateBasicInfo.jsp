<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title></title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/updateSelfInfo.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/proCityArea.js" charset="gbk"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/success.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js" charset="gbk"></script>

<script type="text/javascript">
$(function(){
	$(":input[name='passQuestion']").val(['${user.question}']);
	$(":input[name='passQuestion']").each(function(){
		if($(this).val()=="请选择一个问题"){
			$(":input[name='passQuestion']").val(['自定义问题']).change();
		}
	});
});

</script>


</head>
<body>
<div id="one">

	<div style="border:10px solid green; margin-top:5px;"></div>
	<div style="margin-top:10px; text-align:left;">
		<table border="0" cellspacing="2px" cellpadding="3px" width="100%">
			<tr><td class="td_1"><font size="4px" color="green"><b>&nbsp;修改个人基本信息</b></font></td></tr>
		</table>
	</div>

	<div style="border:3px solid gray; margin-top:5px;"></div>
	<div style="margin-top:20px;"></div>

	<div id="two">
		<div style="margin-left:20px; text-align:left;">
			<div style="margin-left:25px; margin-top:20px; flozt:left; font:bold 14px; color:green;">用户基本信息</div>
			</div>
	
		<div id="left">
			<form action="user_updateUser.action" method="post" name="MyForm">
				<table border="0" cellspacing="3px" cellpadding="2px;">
					<tr>
						<td>电子邮箱</td>
						<td class="td_1"><input type="text" size="20" class="text" name="user.email" id="email" value="<s:property value='user.email'/>"/>
						</td>
					</tr>
					<tr>
						<td>用户名</td>
						<td class="td_1"><input type="text" size="20" class="text" name="user.userName" id="userName" disabled value="<s:property value='user.userName'/>"/></td>
					</tr>
					<tr>
						<td>密码</td>
						<td class="td_1"><input type="password" size="20" class="text" name="user.password" id="password"/></td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td class="td_1"><input type="password" size="20" class="text" name="confirmPwd"/></td>
					</tr>
					<tr>
						<td style="text">密保问题</td>
						<td class="td_1">
							<select name="passQuestion" class="text" onchange="setVisible()">
								<option value="请选择一个问题">请选择一个问题</option>
								<option value="你的启蒙老师叫什么">你的启蒙老师叫什么</option>
								<option value="你的第一个手机号码是多少？">你的第一个手机号码是多少？</option>
								<option value="你最好的朋友叫什么？">你最好的朋友叫什么？</option>
								<option value="你的出生地是哪里？">你的出生地是哪里？</option>
								<option value="你的宠物叫什么？">你的宠物叫什么？</option>
								<option value="你最喜欢的明星是谁？">你最喜欢的明星是谁？</option>
								<option value="你母亲的生日是什么时候？">你母亲的生日是什么时候？</option>
								<option value="你的身份证最后6位数字是什么？">你的身份证最后6位数字是什么？</option>
								<option value="你最喜欢的休闲运动是什么？">你最喜欢的休闲运动是什么？</option>
								<option value="你高中在哪所学校就读？">你高中在哪所学校就读？</option>
								<option value="自定义问题">自定义问题</option>
							</select>
						</td>
					</tr>
					<tr style="display:none;" id="xyl_1">
						<td>自定义问题</td>
						<td><input type="text" size="20" class="text" name="user.question" id="selfDefine" value="<s:property value='user.question'/>"/></td>
					</tr>
					<tr>
						<td>答案</td>
						<td class="td_1">
							<input type="text" size="20" class="text" name="user.answer" id="answer" value="<s:property value='user.answer'/>"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="td_1">
							<input type="hidden" name="user.userId" value="<s:property value='user.userId'/>"/>
							<input type="submit" value="确认修改" id="update" name="submit"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		
		<div id="right">
			<table border="0" cellspacing="2px" cellpadding="1px" width="300">
				<tr>
					<td class="td_2"><span id="Email_Info">
						<s:fielderror cssClass="formFieldError"><s:param>user.email</s:param></s:fielderror>
					</span></td>
				</tr>
				<tr>
					<td class="td_2"><span id="UserName_Info">
						<s:fielderror cssClass="formFieldError"><s:param>user.userName</s:param></s:fielderror>
					</span></td>
				</tr>
				<tr>
					<td class="td_2"><span id="Password_Info">
						<s:fielderror cssClass="formFieldError"><s:param>user.password</s:param></s:fielderror>
					</span></td>
				</tr>
				<tr>
					<td class="td_2"><s:fielderror cssClass="formFieldError"><s:param>confirmPwd</s:param></s:fielderror></td>
				</tr>
				<tr>
					<td class="td_2"><s:fielderror cssClass="formFieldError"><s:param>questionError</s:param></s:fielderror></td>
				</tr>
				<tr id="xyl_2" style="display:none;">
					<td class="td_2"><span id="selfDefine_Info"></span></td>
				</tr>
				<tr>
					<td class="td_2"><span id="Answer_Info">
						<s:fielderror cssClass="formFieldError"><s:param>user.answer</s:param></s:fielderror>
					</span></td>
				</tr>
				<tr>
					<td class="td_2">&nbsp;</td>
				</tr>
				<tr>
					<td class="td_2">&nbsp;</td>
				</tr>
			</table>
		</div>
	</div>

</div>


</body>
</html>





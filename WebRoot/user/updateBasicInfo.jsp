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
		if($(this).val()=="��ѡ��һ������"){
			$(":input[name='passQuestion']").val(['�Զ�������']).change();
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
			<tr><td class="td_1"><font size="4px" color="green"><b>&nbsp;�޸ĸ��˻�����Ϣ</b></font></td></tr>
		</table>
	</div>

	<div style="border:3px solid gray; margin-top:5px;"></div>
	<div style="margin-top:20px;"></div>

	<div id="two">
		<div style="margin-left:20px; text-align:left;">
			<div style="margin-left:25px; margin-top:20px; flozt:left; font:bold 14px; color:green;">�û�������Ϣ</div>
			</div>
	
		<div id="left">
			<form action="user_updateUser.action" method="post" name="MyForm">
				<table border="0" cellspacing="3px" cellpadding="2px;">
					<tr>
						<td>��������</td>
						<td class="td_1"><input type="text" size="20" class="text" name="user.email" id="email" value="<s:property value='user.email'/>"/>
						</td>
					</tr>
					<tr>
						<td>�û���</td>
						<td class="td_1"><input type="text" size="20" class="text" name="user.userName" id="userName" disabled value="<s:property value='user.userName'/>"/></td>
					</tr>
					<tr>
						<td>����</td>
						<td class="td_1"><input type="password" size="20" class="text" name="user.password" id="password"/></td>
					</tr>
					<tr>
						<td>ȷ������</td>
						<td class="td_1"><input type="password" size="20" class="text" name="confirmPwd"/></td>
					</tr>
					<tr>
						<td style="text">�ܱ�����</td>
						<td class="td_1">
							<select name="passQuestion" class="text" onchange="setVisible()">
								<option value="��ѡ��һ������">��ѡ��һ������</option>
								<option value="���������ʦ��ʲô">���������ʦ��ʲô</option>
								<option value="��ĵ�һ���ֻ������Ƕ��٣�">��ĵ�һ���ֻ������Ƕ��٣�</option>
								<option value="����õ����ѽ�ʲô��">����õ����ѽ�ʲô��</option>
								<option value="��ĳ����������">��ĳ����������</option>
								<option value="��ĳ����ʲô��">��ĳ����ʲô��</option>
								<option value="����ϲ����������˭��">����ϲ����������˭��</option>
								<option value="��ĸ�׵�������ʲôʱ��">��ĸ�׵�������ʲôʱ��</option>
								<option value="������֤���6λ������ʲô��">������֤���6λ������ʲô��</option>
								<option value="����ϲ���������˶���ʲô��">����ϲ���������˶���ʲô��</option>
								<option value="�����������ѧУ�Ͷ���">�����������ѧУ�Ͷ���</option>
								<option value="�Զ�������">�Զ�������</option>
							</select>
						</td>
					</tr>
					<tr style="display:none;" id="xyl_1">
						<td>�Զ�������</td>
						<td><input type="text" size="20" class="text" name="user.question" id="selfDefine" value="<s:property value='user.question'/>"/></td>
					</tr>
					<tr>
						<td>��</td>
						<td class="td_1">
							<input type="text" size="20" class="text" name="user.answer" id="answer" value="<s:property value='user.answer'/>"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="td_1">
							<input type="hidden" name="user.userId" value="<s:property value='user.userId'/>"/>
							<input type="submit" value="ȷ���޸�" id="update" name="submit"/>
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





<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head><title>�û�ע��</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js" charset="gbk"></script>
</head>

<body>
<center>

<div id="one">
	<table border="0" width="100%" cellspacing="1px" cellpadding="3px">
	<tr>
		<td style="text-align:left;" rowspan="2">
			<img src="../image/logo_grzx.gif">
		</td>
		<td style="text-align:right;"><a href="">�ݲ�����ҳ</a></td>
	</tr>
	<tr>
		<td style="text-align:right;"><a href="">��¼</a>|<a href="">ע��</a></td>
	</tr>
	<tr></tr>
	</table>

	<div style="border:10px solid black; margin-top:20px;"></div>
</div>


<div id="two">
	<table border="0" width="100%" cellspacing="5px" cellpadding="5px">
		<tr>
		<td style="text-align:left;">ע��ݲ�ͨ��֤</td>
		<td style="text-align:right;">�Ѿ��ǲݲ����û�?��ֱ��<a href="">��¼</a></td>
		</tr>
	</table>
	<div style="border:3px solid gray; margin-top:0px;"></div>


<div id="left">

<form action="user_insertUser.action" method="post" name="MyForm">
	<table border="0" cellspacing="3px" cellpadding="2px;">
		<tr>
			<td>��������</td>
			<td class="td_1"><input type="text" size="20" class="text" onfocus="check('email')" onblur="check_2('email')" name="user.email" id="email" value="<s:property value='user.email'/>"/>
			</td>
		</tr>
		<tr>
			<td>�û���</td>
			<td class="td_1"><input type="text" size="20" class="text" onfocus="check('userName')" onblur="check_2('userName')" name="user.userName" id="userName" value="<s:property value='user.userName'/>"/></td>
		</tr>
		<tr>
			<td>����</td>
			<td class="td_1"><input type="password" size="20" class="text" onfocus="check('password')" onblur="check_2('password')" name="user.password" id="password" value="<s:property value='user.password'/>"/></td>
		</tr>
		<tr>
			<td>ȷ������</td>
			<td class="td_1"><input type="password" size="20" class="text" name="confirmPwd" 
			 value="<s:property value='confirmPwd'/>"/></td>
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
			<td><input type="text" size="20" class="text"  onfocus="check('selfDefine')" 
				onblur="check_2('selfDefine')" name="user.question" id="selfDefine" value="<s:property value='user.question'/>"/></td>
		</tr>
		<tr>
			<td>��</td>
			<td class="td_1">
				<input type="text" size="20" class="text" onfocus="check('answer')" 
				onblur="check_2('answer')" name="user.answer" id="answer" value="<s:property value='user.answer'/>"/>
			</td>
		</tr>
		<tr>
			<td>��֤��</td>
			<td class="td_1">
				<table border="0px" cellspacing="0px" cellpadding="0px">
				<tr>
					<td>
					<input type="text" size="10" onfocus="check('confirm')" onblur="check_2('confirm')" 
						name="verCode" id="confirm" value="<s:property value='verCode'/>"
						style="height:25px;"/>
					</td>
					<td style="padding-left:10px;"><img src="../image.jsp" width="70" height="25" id="image"/></td>
					<td style="padding-left:10px;"><a href="javascript:changeImage();">�����壿��һ��</a></td>
				</tr></table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td class="td_1" height="40">
				<input type="checkbox" onclick="setEndiable()" name="accept" checked>
				���Ѿ��Ķ���ͬ��<a href="${pageContext.request.contextPath}/protocal.jsp">���ݲ�ʹ��Э�顷</a>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td class="td_1">
				<input type="submit" value="" id="register" name="submit"/>
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
			<td class="td_2"><span id="Confirm_Info">
				<s:fielderror cssClass="formFieldError"><s:param>verCodeError</s:param></s:fielderror>
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
</center>
</body>
</html>
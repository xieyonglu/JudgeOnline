<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>�޸Ĺ������</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/eWebEditor.js"></script>

<style type="text/css">
.formFieldError{
	font-family:verdana,arial,helvetica,sans-serif;
	font-size:12px;
	color:#ff3300;
	margin:1px; 
	padding:4px; 
	vertical-align:bottom;
}
</style>

<script type="text/javascript">
/*��ʼ����������е��Ѷ�ϵ��*/
function initHardFactor(){
	var hard=document.getElementById("hardFactor");
	hard.options[0]=new Option("--��ѡ��--","");
	for(var i=1;i<=5;i++){
			hard.options[i]=new Option(i,i);
	}
}
</script>
</head>

<body>

<table border="0" cellspacing="0px" cellpadding="0px" style="width:1030px; text-align:left;">
	<tr><td><img src="<%=request.getContextPath()%>/image/logo_grzx.gif"></td></tr>
</table>

<div style="border:1px solid black; margin-top:10px;"></div>


<div style="width:1030px; height:36px; margin-top:10px; background-color:orange;">
<div style="text-align:left; margin-left:20px; margin-top:9px;">

<table border="0" cellspacing="3px" cellpadding="1px" width="1000px">
	<tr>
		<td><div style="text-align:left; margin-left:5px;">�޸Ĺ���</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<form action="notice_updateNotice.action" method="post" name="form1">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		
		<tr><td class="td_1">�������</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="notice.title" value="${notice.title}"/>
				<s:fielderror cssClass="formFieldError" theme="simple"><s:param>notice.title</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1" width="100px">׫д����</td>
			<td class="td_1">${notice.author}</td>
		</tr>
		<tr><td class="td_1">�����</td>
			<td class="td_1">${notice.browser}��</td>
		</tr>
		
		<tr><td class="td_1">����ʱ��</td>
			<td class="td_1">
				<s:date name="notice.createDate" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		
		<!--
			<tr>
				<td class="td_1" style="vertical-align:top;">��������<br><br></td>
				<td class="td_1">
					�������ݴ�����ʾ
					<div style="width:800px; height:25px; background-color:yellow;">
						<s:fielderror cssClass="formFieldError"><s:param>notice.content</s:param></s:fielderror>
					</div>
					<textarea style="width:100%; height:500px;" name="notice.content" id="editor"><s:property value="notice.content"/></textarea>
				</td>
			</tr>
		-->
		
		<tr>
			<td class="td_1" style="vertical-align:top;">��������<br><br></td>
			<td class="td_1">
			
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>notice.content</s:param></s:fielderror>
				</div>
				<input type='hidden' name='notice.content' value='${notice.content}'/>
				<script language=javascript>
					//document.write ("<INPUT type='hidden' name='notice.content' value='<s:property value='post.content'/>'>");
					document.write ("<IFRAME ID='eWebEditor1' src='${pageContext.request.contextPath}/eWebEditor/ewebeditor.htm?id=notice.content&style=" + URLParams["style"] + "' frameborder='0' scrolling='no' width='100%' height='650px'></IFRAME>");
					setTimeout("setValue();",1000);
				</script>
			</td>	
		</tr>
		
		<tr><td>&nbsp;</td>
			<td class="td_1">
				<!--<input type="hidden" name="adminId" value="${notice.admin.adminId}"/>
				-->
				<input type="hidden" name="notice.noticeId" value="${notice.noticeId}"/>
				<input type="hidden" name="notice.browser" value="${notice.browser}"/>
				<input type="hidden" name="notice.author" value="${notice.author}"/>
				<input type="hidden" name="notice.createDate" value="<s:date name='notice.createDate' format='yyyy-MM-dd HH:mm:ss'/>"/>
				<input type="submit" value="�޸Ĺ���" class="button"/>
			</td>
		</tr>
	</table>
	</form>
	
</div>

</body>
</html>



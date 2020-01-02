<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>插入公告界面</title>
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

.nobr br{
	display:none
}

</style>

<script type="text/javascript">
/*初始化添加试题中的难度系数*/
function initHardFactor(){
	var hard=document.getElementById("hardFactor");
	hard.options[0]=new Option("--请选择--","");
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
		<td><div style="text-align:left; margin-left:5px;">添加公告</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<form action="notice_insertNotice.action" method="post" name="form1">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		
		<tr><td class="td_1">公告标题</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="notice.title" value="${notice.title}"/>
				<s:fielderror cssClass="formFieldError"><s:param>notice.title</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1" width="100px">撰写作者</td>
			<td class="td_1">${sessionScope.loginAdmin.userName}&nbsp;</td>
		</tr>
		<tr><td class="td_1">浏览数</td>
			<td class="td_1">0次</td>
		</tr>
		
		<tr><td class="td_1">发布时间</td>
			<td class="td_1">
				<script language="JavaScript">
				<!--
					var myOffset = -2;  
					var currentDate = new Date();  
					var userOffset = currentDate.getTimezoneOffset()/60;  
					var timeZoneDifference = userOffset - myOffset;  
					currentDate.setHours(currentDate.getHours() + timeZoneDifference);  
					document.write(currentDate.toLocaleString());  
				//-->
				</script>  
			</td>
		</tr>
		
		<!--
			<tr>
				<td class="td_1" style="vertical-align:top;">试题描述<br><br></td>
				<td class="td_1">
					新闻内容错误提示
					<div style="width:800px; height:25px; background-color:yellow;">
						<s:fielderror cssClass="formFieldError"><s:param>notice.content</s:param></s:fielderror>
					</div>
					<textarea style="width:100%; height:1000px;" name="notice.content" id="editor"><s:property value="notice.content"/></textarea>
				</td>
			</tr>
		-->
		<tr>
			<td class="td_1" style="vertical-align:top;">公告内容<br><br></td>
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
				<!--<input type="hidden" name="adminId" value="${sessionScope.loginAdmin.adminId}"/>
				--><input type="submit" value="发布公告" class="button"/>
			</td>
		</tr>
	</table>
	</form>
	
</div>

</body>
</html>



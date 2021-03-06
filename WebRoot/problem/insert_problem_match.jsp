<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>插入比赛试题界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
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
$(function(){
	$(":input[name='problem.hardFactor']").val("${problem.hardFactor}");
	$(":input[name='problem.sequence']").val("${problem.sequence}");
});

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
		<td><div style="text-align:left; margin-left:5px;">
			<span style="color:red;"><b>${match.matchName}</b></span>试题添加
		</div></td>
	</tr>
</table>

</div>
</div>
<s:action name="type_findAllType" namespace="/" executeResult="false" id="type"></s:action>

<div style="width:900px; background-color:b0c4de;">
	<form action="problem_insertMatchProblem.action" method="post" name="form1">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		<tr><td class="td_1" width="100px">撰写作者</td>
			<td class="td_1">${sessionScope.loginAdmin.userName}</td>
		</tr>
		<tr><td class="td_1">试题标题</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="problem.title" value="${problem.title}"/>
				<s:fielderror cssClass="formFieldError"><s:param>problem.title</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">试题来源</td>
			<td class="td_1">
				<input type="text" style="width:450px;" name="problem.source" value="${problem.source}"/>
				<s:fielderror cssClass="formFieldError"><s:param>problem.source</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">试题顺序</td>
			<td class="td_1">
				<select name="problem.sequence">
					<option value="0">--请选择--</option>
					<option value="1">problem A</option>
					<option value="2">problem B</option>
					<option value="3">problem C</option>
					<option value="4">problem D</option>
					<option value="5">problem E</option>
					<option value="6">problem F</option>
					<option value="7">problem G</option>
					<option value="8">problem H</option>
					<option value="9">problem I</option>
					<option value="10">problem J</option>
					<option value="11">problem K</option>
					<option value="12">problem L</option>
					<option value="13">problem M</option>
					<option value="14">problem N</option>
					<option value="15">problem O</option>
				</select>
				<s:fielderror cssClass="formFieldError"><s:param>problem.sequence</s:param></s:fielderror>
			</td>
		</tr>
		
		<tr><td class="td_1">难度系数</td>
			<td class="td_1">
				<select name="problem.hardFactor" id="hardFactor" style="width:150px;">
						<option value="">--请选择--</option>
				</select>
				<script type="text/javascript">initHardFactor();</script>
				<s:fielderror cssClass="formFieldError"><s:param>problem.hardFactor</s:param></s:fielderror>
			</td>
		</tr>
		
		<tr><td class="td_1">所属类别</td>
			<td class="td_1">
				<s:select list="#type.typeList" headerKey="--请选择--" headerValue="--请选择--" listKey="typeId" listValue="typeName" cssStyle="width:150px;" name="typeName" theme="simple" value="typeName"/>
				<s:fielderror cssClass="formFieldError" theme="simple"><s:param>problem.typeName</s:param></s:fielderror>
			</td>
		</tr>
		
		<tr><td class="td_1">时间限制</td>
			<td class="td_1">
				<input type="text" name="problem.timeLimit" value="<s:property value='problem.timeLimit'/>" style="width:150px; height:20px;"/>&nbsp;(MS)
				<s:fielderror cssClass="formFieldError"><s:param>problem.timeLimit</s:param></s:fielderror>
			</td>
		</tr>
		<tr><td class="td_1">内存限制</td>
			<td class="td_1">
				<input type="text" name="problem.memoryLimit" value="<s:property value='problem.memoryLimit'/>" style="width:150px; height:20px;"/>&nbsp;(KB)
				<s:fielderror cssClass="formFieldError"><s:param>problem.memoryLimit</s:param></s:fielderror>
			</td>
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
		
		<tr>
			<td class="td_1" style="vertical-align:top;">试题描述<br><br></td>
			<td class="td_1">
				<!--新闻内容错误提示-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>problem.description</s:param></s:fielderror>
				</div>
				<!--<textarea style="width:100%; height:200px;" name="problem.description" id="editor"><s:property value="problem.description"/></textarea>
				-->
				<script language=javascript>
					document.write ("<INPUT type='hidden' name='problem.description' value='${problem.description}'>");
					document.write ("<IFRAME ID='eWebEditor1' src='${pageContext.request.contextPath}/eWebEditor/ewebeditor.htm?id=problem.description&style=" + URLParams["style"] + "' frameborder='0' scrolling='no' width='100%' height='350px'></IFRAME>");
					setTimeout("setValue();",1000);
				</script>
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">输入<br><br></td>
			<td class="td_1">
				<!--新闻内容错误提示-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>problem.input</s:param></s:fielderror>
				</div>
				<!--<textarea style="width:100%; height:200px;" name="problem.input" id="editor"><s:property value="problem.input"/></textarea>
				-->
				<script language=javascript>
					document.write ("<INPUT type='hidden' name='problem.input' value='${problem.input}'>");
					document.write ("<IFRAME ID='eWebEditor1' src='${pageContext.request.contextPath}/eWebEditor/ewebeditor.htm?id=problem.input&style=" + URLParams["style"] + "' frameborder='0' scrolling='no' width='100%' height='350px'></IFRAME>");
					setTimeout("setValue();",1000);
				</script>
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">输出<br><br></td>
			<td class="td_1">
				<!--新闻内容错误提示-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>problem.output</s:param></s:fielderror>
				</div>
				<!--<textarea style="width:100%; height:200px;" name="problem.output" id="editor"><s:property value="problem.output"/></textarea>		
			-->
				<script language=javascript>
					document.write ("<INPUT type='hidden' name='problem.output' value='${problem.output}'>");
					document.write ("<IFRAME ID='eWebEditor1' src='${pageContext.request.contextPath}/eWebEditor/ewebeditor.htm?id=problem.output&style=" + URLParams["style"] + "' frameborder='0' scrolling='no' width='100%' height='350px'></IFRAME>");
					setTimeout("setValue();",1000);
				</script>
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">样例输入<br><br></td>
			<td class="td_1">
				<!--新闻内容错误提示-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>problem.caseInput</s:param></s:fielderror>
				</div>
				<!--<textarea style="width:100%; height:200px;" name="problem.caseInput" id="editor"><s:property value="problem.caseInput"/></textarea>
				-->
				<script language=javascript>
					document.write ("<INPUT type='hidden' name='problem.caseInput' value='${problem.caseInput}'>");
					document.write ("<IFRAME ID='eWebEditor1' src='${pageContext.request.contextPath}/eWebEditor/ewebeditor.htm?id=problem.caseInput&style=" + URLParams["style"] + "' frameborder='0' scrolling='no' width='100%' height='350px'></IFRAME>");
					setTimeout("setValue();",1000);
				</script>
			</td>
		</tr>
		<tr>
			<td class="td_1" style="vertical-align:top;">样例输出<br><br></td>
			<td class="td_1">
				<!--新闻内容错误提示-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>problem.caseOutput</s:param></s:fielderror>
				</div>
				<!--<textarea style="width:100%; height:200px;" name="problem.caseOutput" id="editor"><s:property value="problem.caseOutput"/></textarea>		
			-->
				
				
				<script language=javascript>
					document.write ("<INPUT type='hidden' name='problem.caseOutput' value='${problem.caseOutput}'>");
					document.write ("<IFRAME ID='eWebEditor1' src='${pageContext.request.contextPath}/eWebEditor/ewebeditor.htm?id=problem.caseOutput&style=" + URLParams["style"] + "' frameborder='0' scrolling='no' width='100%' height='350px'></IFRAME>");
					setTimeout("setValue();",1000);
				</script>
			</td>
		</tr>
		<tr><td>&nbsp;</td>
			<td class="td_1">
				<input type="hidden" name="match.matchId" value="${match.matchId}"/>
				<input type="submit" value="添加试题" class="button"/>
			</td>
		</tr>
	</table>
	</form>
	
</div>
</body>
</html>



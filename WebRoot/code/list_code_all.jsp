<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pager.js" charset="gbk"></script>

<script type="text/javascript">
$(function(){
	$(":input[name='conditionC']").val("${conditionC}");
	$(":input[name='conditionD']").val("${conditionD}");
});
$(function(){
	var conditionA=document.getElementsByName("conditionA").item(0).value;
	var conditionB=document.getElementsByName("conditionB").item(0).value;
	var conditionC=document.getElementsByName("conditionC").item(0).value;
	var conditionD=document.getElementsByName("conditionD").item(0).value;
			
	var totalPage=document.getElementsByName("totalPage").item(0).value;
	var currentPage=document.getElementsByName("currentPage").item(0).value;
	var pageSize=document.getElementsByName("select").item(0).value;
			
	var str="code_pageFindCode.action?conditionA="+conditionA+"&conditionB="+conditionB+
			"&conditionC="+conditionC+"&conditionD="+conditionD+
			//"&totalPage="+totalPage+"&currentPage="+currentPage+"&pageSize="+pageSize+
			"&action=all";
	document.getElementsByName("actionURL").item(0).value=str;

	$("#find").click(function(){
		var conditionA=document.getElementsByName("conditionA").item(0).value;
		var conditionB=document.getElementsByName("conditionB").item(0).value;
		var conditionC=document.getElementsByName("conditionC").item(0).value;
		var conditionD=document.getElementsByName("conditionD").item(0).value;
		
		var totalPage=document.getElementsByName("totalPage").item(0).value;
		var currentPage=document.getElementsByName("currentPage").item(0).value;
		var pageSize=document.getElementsByName("select").item(0).value;
		
		var str="code_pageFindCode.action?conditionA="+conditionA+"&conditionB="+conditionB+
			"&conditionC="+conditionC+"&conditionD="+conditionD+
			//"&totalPage="+totalPage+"&currentPage="+currentPage+"&pageSize="+pageSize+
			"&action=all";
		//alert(str);
		location.href=str;
	});
});
</script>
</head>
<body>

<input type="hidden" name="actionURL" value="#"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	没有运行结果信息
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1200" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">所有运行结果信息</th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				<!--请输入关键字:
				<select name="condition1">
					<option value="--请选择--">--请选择--</option>
					<option value="userName">用户名</option>	
					<option value="trueName">真实姓名</option>
					<option value="sex">性别</option>
					<option value="type">权限</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="查询" id="inquire"/>
				-->
				
				题目：<input type="text" name="conditionA" value="${conditionA}"/>
				用户名:<input type="text" name="conditionB" value="${conditionB}"/>
				编程语言:<select name="conditionC">
					  	<option value="all">全部</option>
					  	<option value="java">Java</option>
					  	<option value="cpp">C++</option>
					  	<option value="c">C</option>
					  </select>
				结果:<select name="conditionD">
					  	<option value="all">全部</option>
					  	<option value="rightAnswer">rightAnswer</option>
					  	<option value="wrongAnswer">wrongAnswer</option>
					  	<option value="compileError">compileError</option>
					  	<option value="timeOut">timeOut</option>
					  	<option value="memoryOut">memoryOut</option>
					  </select>
				<input type="button" value="查找" id="find"/>
				
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>&nbsp;</td>
      </tr>
      <tr align="center" bgcolor="#799AE1">
        <td width="10%" align="center" class="txlHeaderBackgroundAlternate">运行号</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>用户</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>题目</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>结果</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>时间</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>内存</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>语言</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>代码长度</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>提交时间</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
	        <td width="10%" align=center class=txlrow>${runNumber}</td>
	        <td width="10%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<a href="submit_findAllSubmit.action?userId=${user.userId}">${user.userName}</a>
	        </td>
	        <td width="20%" align=center class=txlrow>${problem.title}</td>
	        <td width="10%" align=center class=txlrow>${state}</td>
	        <td width="10%" align=center class=txlrow>${runtime}</td>
	        <td width="10%" align=center class=txlrow>${memory}</td>
	        <td width="5%" align=center class=txlrow>${language}</td>
	       	<td width="10%" align=center class=txlrow>${codeLength}</td>
	       	<td width="15%" align=center class=txlrow>&nbsp;
	        	<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	    </tr>
	  </s:iterator>
     
      
    
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>
          &nbsp;
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan=16 align=center class=txlrow></td>
      </tr>
      <tr bgcolor="#DEE5FA">
      	<td colspan="2">	
           	共${pageBean.allRow}条记录，当前第${pageBean.currentPage}/${pageBean.totalPage}页
        </td>
      	<td colspan="4"><script type="text/javascript">newPage();</script>
      	</td>
      	<td>
      		<select name="select" id="select">
      			<option value="10">每页10个</option>
      			<option value="15">每页15个</option>
      			<option value="20">每页20个</option>
      			<option value="25">每页25个</option>
      			<option value="30">每页30个</option>
      		</select>
      	</td>
      	<td colspan="2" style="text-align:left;">
      		转到第<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />页 
        	<input type="button" value="跳转" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
</tbody>
</table>
</FORM>
</s:if>

</BODY>
</HTML>




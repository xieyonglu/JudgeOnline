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
	û�����н����Ϣ
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1200" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">�������н����Ϣ</th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				<!--������ؼ���:
				<select name="condition1">
					<option value="--��ѡ��--">--��ѡ��--</option>
					<option value="userName">�û���</option>	
					<option value="trueName">��ʵ����</option>
					<option value="sex">�Ա�</option>
					<option value="type">Ȩ��</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="��ѯ" id="inquire"/>
				-->
				
				��Ŀ��<input type="text" name="conditionA" value="${conditionA}"/>
				�û���:<input type="text" name="conditionB" value="${conditionB}"/>
				�������:<select name="conditionC">
					  	<option value="all">ȫ��</option>
					  	<option value="java">Java</option>
					  	<option value="cpp">C++</option>
					  	<option value="c">C</option>
					  </select>
				���:<select name="conditionD">
					  	<option value="all">ȫ��</option>
					  	<option value="rightAnswer">rightAnswer</option>
					  	<option value="wrongAnswer">wrongAnswer</option>
					  	<option value="compileError">compileError</option>
					  	<option value="timeOut">timeOut</option>
					  	<option value="memoryOut">memoryOut</option>
					  </select>
				<input type="button" value="����" id="find"/>
				
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>&nbsp;</td>
      </tr>
      <tr align="center" bgcolor="#799AE1">
        <td width="10%" align="center" class="txlHeaderBackgroundAlternate">���к�</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>�û�</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>��Ŀ</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>���</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>ʱ��</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>�ڴ�</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>����</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>���볤��</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>�ύʱ��</td>
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
           	��${pageBean.allRow}����¼����ǰ��${pageBean.currentPage}/${pageBean.totalPage}ҳ
        </td>
      	<td colspan="4"><script type="text/javascript">newPage();</script>
      	</td>
      	<td>
      		<select name="select" id="select">
      			<option value="10">ÿҳ10��</option>
      			<option value="15">ÿҳ15��</option>
      			<option value="20">ÿҳ20��</option>
      			<option value="25">ÿҳ25��</option>
      			<option value="30">ÿҳ30��</option>
      		</select>
      	</td>
      	<td colspan="2" style="text-align:left;">
      		ת����<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />ҳ 
        	<input type="button" value="��ת" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
</tbody>
</table>
</FORM>
</s:if>

</BODY>
</HTML>




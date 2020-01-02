<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pager.js" charset="gbk"></script>

<script type="text/javascript">
$(function(){
	$(":input[name='condition1']").val("${condition1}");
	$(":input[name='condition2']").val("${condition2}");
});
</script>
</head>
<body>

<input type="hidden" name="actionURL" value="problem_pageProblem.action"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	û��������Ϣ
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1500px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">������Ϣ����</th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				������ؼ���:
				<select name="condition1">
					<option value="--��ѡ��--">--��ѡ��--</option>
					<option value="problemId">���</option>	
					<option value="title">����</option>
					<option value="hardFactor">�Ѷ�</option>
					<option value="author">�ϴ���</option>
					<option value="createDate">��������</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="��ѯ" id="inquire"/>
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>&nbsp;</td>
      </tr>
      <tr align="center" bgcolor="#799AE1">
      	<td width="5%" align="center" class="txlHeaderBackgroundAlternate">ѡ��</td>
        <td width="6%" align="center" class="txlHeaderBackgroundAlternate">���</td>
        <td width="6%" align="center" class=txlHeaderBackgroundAlternate>���</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>����</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>�Ѷ�</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>�ϴ���</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>��������</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>��������</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>�鿴����</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>��������</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="5%" align="center" class="txlrow">
      			<input type="checkbox" name="checkList" value="${problemId}">
      		</td>
	        <td width="6%" align=center class=txlrow>
	        	<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
	        </td>
	        <td width="6%" align=center class=txlrow>
	        	<s:property value="problemId"/>
	        </td>
	        <td width="20%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<a href="problem_findProblem.action?problemId=${problemId}&action=show">
	        		<s:property value="title"/>
	        	</a>
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	<s:property value="hardFactor"/>
	        </td>
	         <td width="10%" align=center class=txlrow>
	        	<s:property value="admin.userName"/>
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<a href="testData_pageTestData.action?problemId=${problemId}">������������</a>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<a href="code_pageCode.action?action=admin&problemId=${problemId}">�鿴����</a>
	        </td>
	        <td width="20%" align=center class=txlrow>
	        	<img src="${pageContext.request.contextPath}/image/update.gif" width="16" height="16" />
					<a href="problem_findProblem.action?problemId=${problemId}&action=show">�鿴</a>&nbsp;&nbsp;
	        	<img src="${pageContext.request.contextPath}/image/update.gif" width="16" height="16" />
					<a href="problem_findProblem.action?problemId=${problemId}&action=update">�༭</a>&nbsp;&nbsp;
				<img src="${pageContext.request.contextPath}/image/delete.gif" width="16" height="16" />
					<a href="problem_deleteProblem.action?problemId=${problemId}">ɾ��</a>
	        </td>
	    </tr>
	  </s:iterator>
     
      
    
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>
        	<span class="tablebody2">
          		<input type="checkbox" id="selectAll"/>ȫѡ
          		<input type="checkbox" id="reverse"/>��ѡ
          	</span>
          	<input type="button" value="���" class="but" onclick="window.location.href='${pageContext.request.contextPath}/problem/insert_problem.jsp'"/>
          	<input type="button" value="ɾ��" class="but" onclick="batchDelete('problem_batchDelete.action')"/>
        </td>
      </tr>
      
      
      <tr bgcolor="#DEE5FA">
        <td colspan=16 align=center class=txlrow></td>
      </tr>
      <tr bgcolor="#DEE5FA">
      	<td colspan="2">	
           	��${pageBean.allRow}����¼����ǰ��${pageBean.currentPage}/${pageBean.totalPage}ҳ
        </td>
      	<td colspan="4">
      		<script type="text/javascript">newPage();</script>
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
      	<td style="text-align:left;" colspan="3">
      		ת����<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />ҳ 
        	<input type="button" value="��ת" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
	  </tbody></table>
</FORM>
</s:if>


</BODY>
</HTML>




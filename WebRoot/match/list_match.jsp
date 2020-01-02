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

<input type="hidden" name="actionURL" value="match_pageMatch.action?action=admin"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	û�б�����Ϣ
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1800px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
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
					<option value="matchName">��������</option>	
					<option value="state">״̬</option>
					<option value="author">׫д��</option>
					<option value="startDate">��ʼʱ��</option>
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
        <td width="5%" align="center" class="txlHeaderBackgroundAlternate">���</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>��������</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>����״̬</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>����״̬</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>�������</td>
       
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>IP����</td>
       
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>׫д��</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>��ʼʱ��</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>����ʱ��</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>��������</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>��������</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="5%" align="center" class="txlrow">
      			<input type="checkbox" name="checkList" value="${matchId}">
      		</td>
	        <td width="5%" align=center class=txlrow>
	        	<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
	        </td>
	        <td width="15%" align=center class=txlrow>
	        	<a href="match_showMatch.action?matchId=${matchId}&action=show">
	        		<s:property value="matchName"/>
	        	</a>
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	<script type="text/javascript">
					var state=${state};
					if(state=="0"){
						document.write("��δ��ʼ&nbsp;&nbsp;&nbsp;&nbsp;");
					}else if(state=="1"){
						document.write("<div style='color:red;'><b>���ڽ���...</b></div>");
					}else{
						document.write("�Ѿ�����&nbsp;&nbsp;&nbsp;&nbsp;");
					}
	        	</script>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<a href="match_setMatchState.action?matchId=${matchId}&state=0">δ��ʼ</a> |
	        	<a href="match_setMatchState.action?matchId=${matchId}&state=1">������</a> |
	        	<a href="match_setMatchState.action?matchId=${matchId}&state=2">����</a>
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	<!--<a href="match_showMatch.action?matchId=${matchId}&action=problem">�������</a>-->
	        	<a href="problem_pageProblem.action?action=match&matchId=${matchId}">�������</a>
	        </td>
	        
	        
	        <td width="5%" align=center class=txlrow>
	        	<a href="ipAddress_pageIpAddress.action?action=match&matchId=${matchId}">IP����</a>
	        </td>
	       
	        
	        <td width="10%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	${author}
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<s:date name="startDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<s:date name="endDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<img src="${pageContext.request.contextPath}/image/update.gif" width="16" height="16" />
					<a href="match_showMatch.action?matchId=${matchId}&action=update">�༭</a>&nbsp;&nbsp;
				<img src="${pageContext.request.contextPath}/image/delete.gif" width="16" height="16" />
					<a href="match_deleteMatch.action?matchId=${matchId}">ɾ��</a>
	        </td>
	    </tr>
	  </s:iterator>
    
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>
        	<span class="tablebody2">
          		<input type="checkbox" id="selectAll"/>ȫѡ
          		<input type="checkbox" id="reverse"/>��ѡ
          	</span>
          	<input type="button" value="���" class="but" onclick="window.location.href='${pageContext.request.contextPath}/match/insert_match.jsp'"/>
          	<input type="button" value="ɾ��" class="but" onclick="batchDelete('match_batchDelete.action')"/>
        </td>
      </tr>
      
      
      <tr bgcolor="#DEE5FA">
        <td colspan=16 align=center class=txlrow></td>
      </tr>
      <tr bgcolor="#DEE5FA">
      	<td colspan="2">	
           	��${pageBean.allRow}����¼����ǰ��${pageBean.currentPage}/${pageBean.totalPage}ҳ
        </td>
      	<td colspan="5">
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
      	<td style="text-align:left; text-align:left;" colspan="4">
      		ת����<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />ҳ 
        	<input type="button" value="��ת" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
	  </tbody></table>
</FORM>
</s:if>

</BODY>
</HTML>




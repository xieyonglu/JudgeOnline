<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pager.js" charset="gbk"></script>
</head>
<body>

<input type="hidden" name="actionURL" value="code_pageCode.action?action=all&problemId=${problem.problemId}&type=sort"/>
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
        <th align=center colspan=16 style="height: 23px">
        	<span style="color:red;"><b>${problem.title}</b></span>��������
        </th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				������ؼ���:
				<select name="condition1">
					<option value="--��ѡ��--">--��ѡ��--</option>
					<option value="userName">�û���</option>	
					<option value="trueName">��ʵ����</option>
					<option value="sex">�Ա�</option>
					<option value="type">Ȩ��</option>
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
      	<td width="5%" align="center" class="txlHeaderBackgroundAlternate">����</td>
        <td width="10%" align="center" class="txlHeaderBackgroundAlternate">���к�</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>�û�</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>��Ŀ</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>���</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>ʱ��</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>�ڴ�</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>���볤��</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>����</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>�ύʱ��</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="5%" align=center class=txlrow>
      			<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
      		</td>
	        <td width="10%" align=center class=txlrow>${runNumber}</td>
	        <td width="10%" align=center bgcolor="#DEE5FA" class=txlrow>${user.userName}</td>
	        <td width="15%" align=center class=txlrow>${problem.title}</td>
	        <td width="10%" align=center class=txlrow>${state}</td>
	        <td width="10%" align=center class=txlrow>${runtime}</td>
	        <td width="10%" align=center class=txlrow>${memory}</td>
	        <td width="10%" align=center class=txlrow>${codeLength}</td>
	        <td width="5%" align=center class=txlrow>${language}</td>
	        <td width="15%" align=center class=txlrow>
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
      	<td colspan="4">
      		<script type="text/javascript">newPage();</script>
      	</td>
      	<td>
      		<select name="select" id="select">
      			<option value="1">ÿҳ1��</option>
      			<option value="2">ÿҳ2��</option>
      			<option value="3">ÿҳ3��</option>
      			<option value="4">ÿҳ4��</option>
      			<option value="5">ÿҳ5��</option>
      		</select>
      	</td>
      	<td style="text-align:center;" colspan="3">
      		ת����<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />ҳ 
        	<input type="button" value="��ת" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
	  </tbody></table>
</FORM>
</s:if>

</BODY>
</HTML>




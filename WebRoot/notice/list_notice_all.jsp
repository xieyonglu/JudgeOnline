<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqueryauto.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/pager.js" charset="gbk"></script>

<script type="text/javascript">
$(function(){
	$(":input[name='condition1']").val("${condition1}");
	$(":input[name='condition2']").val("${condition2}");
});
</script>
</head>
<body>

<input type="hidden" name="actionURL" value="index_search.action?action=all"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<form name="form1" method="post" action="index_search.action">
  <table width="900px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">������Ϣ��ѯ</th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				<!--
				������ؼ���:
				<select name="condition1">
					<option value="--��ѡ��--">--��ѡ��--</option>				
					<option value="title">����</option>
					<option value="author">����</option>
					<option value="createDate">��������</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="��ѯ" id="inquire"/>
				-->
				
				<table border="0" cellpadding="0px" cellspacing="3px" style="width:800px;">
					<tr>
						<td width="50px">�����룺</td>
						<td width="500px">
							<input type="hidden" name="condition1" value=""/>
							<input type="text" id="word" name="condition2" 
								style="width:620px; height:20px; padding-left:0px;" value="${condition}"/></td>
					   	<td width="100px"><input type="button" id="inquire" value="����" class="search"/></td>
					</tr>
					<tr><td><div id="auto">*</div></td></tr>
				</table>

			</div>
        </td>
      </tr>
      

      <tr bgcolor="#DEE5FA">
        <td colspan="5" align="center" class=txlrow>&nbsp;</td>
      </tr>
      <tr align="center" bgcolor="#799AE1">
      	<td width="10%" align="center" class="txlHeaderBackgroundAlternate">���</td>
        <td width="40%" align="center" class=txlHeaderBackgroundAlternate>�������</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>����</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>�����</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>��������</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="10%" align=center class=txlrow>
	        	<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
	        </td>
	        <td width="40%" style="text-align:left; padding-left:20px;" class=txlrow>
	        	<a href="notice_showNotice.action?action=all&noticeId=${noticeId}">
	        		${title}
	        	</a>
	        </td>
	        <td width="20%" align=center class=txlrow>
	        	<s:property value="author"/>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<s:property value="browser"/>
	        </td>
	        
	        <td width="20%" align=center class=txlrow>
	        	<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>    
	    </tr>
	    <tr bgcolor="#DEE5FA">
	    	<td colspan="5" style="height:100px; padding-top:2px; vertical-align:top;">
	    		<div style="font-size:12px; font-weight:normal;">${content}...</div>
	    	</td>
	    </tr>
	  </s:iterator>
      
    
      <tr bgcolor="#DEE5FA">
       <td colspan="5">&nbsp;</td>
      </tr>

      <tr bgcolor="#DEE5FA">
      	<td colspan="3">	
           	��${pageBean.allRow}����¼����ǰ��${pageBean.currentPage}/${pageBean.totalPage}ҳ
        	<script type="text/javascript">newPage();</script>
      	</td>
      	<td colspan="2">
      		<select name="select" id="select">
      			<option value="10">ÿҳ10��</option>
      			<option value="15">ÿҳ15��</option>
      			<option value="20">ÿҳ20��</option>
      			<option value="25">ÿҳ25��</option>
      			<option value="30">ÿҳ30��</option>
      		</select>
      	
      		ת����<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />ҳ 
        	<input type="button" value="��ת" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
      
   
	  </tbody></table>
</form>

</BODY>
</HTML>




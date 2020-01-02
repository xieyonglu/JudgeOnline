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

<input type="hidden" name="actionURL" value="comment_pageComment.action?action=all"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBeanProblem.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBeanProblem.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBeanProblem.pageSize'/>"/>

<s:if test="#list.size()==0">
	没有评论信息
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1000px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">
        	问与答
        </th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				请输入关键字:
				<select name="condition1">
					<option value="--请选择--">--请选择--</option>
					<option value="problemId">题号</option>
					<option value="title">标题</option>
					<option value="admin.userName">上传者</option>
					<option value="createDate">创建日期</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="查询" id="inquire"/>
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>&nbsp;</td>
      </tr>
      <tr align="center" bgcolor="#799AE1">
        <td width="6%" align="center" class="txlHeaderBackgroundAlternate">序号</td>
        <td width="6%" align="center" class=txlHeaderBackgroundAlternate>题号</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>标题</td>
        
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>上传者</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>创建日期</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="10%" align="center" class="txlrow">
      			<s:property value="#st.getIndex()+1+(pageBeanProblem.currentPage-1)*pageBeanProblem.pageSize"/>
      		</td>
	        <td width="10%" align=center class=txlrow>
	        	<s:property value="problemId"/>
	        </td>
	        <td width="20%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<a href="comment_findComment.action?problemId=${problemId}">
	        		<s:property value="title"/>
	        	</a>
	        </td>
	        <td width="20%" align=center class=txlrow>
	        	<s:property value="admin.userName"/>
	        </td>
	        <td width="20%" align=center class=txlrow>
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
           	共${pageBeanProblem.allRow}条记录，当前第${pageBeanProblem.currentPage}/${pageBeanProblem.totalPage}页
        </td>
      	<td colspan="2">
      		<script type="text/javascript">newPage();</script>
      	</td>
      	<td>
      		<select name="select" id="select">
      			<option value="10">每页10个</option>
      			<option value="15">每页15个</option>
      			<option value="20">每页20个</option>
      			<option value="25">每页25个</option>
      			<option value="30">每页30个</option>
      		</select>
      	
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




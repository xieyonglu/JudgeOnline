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
	没有运行结果信息
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1200" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">
        	<span style="color:red;"><b>${problem.title}</b></span>本题排行
        </th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				请输入关键字:
				<select name="condition1">
					<option value="--请选择--">--请选择--</option>
					<option value="userName">用户名</option>	
					<option value="trueName">真实姓名</option>
					<option value="sex">性别</option>
					<option value="type">权限</option>
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
      	<td width="5%" align="center" class="txlHeaderBackgroundAlternate">排名</td>
        <td width="10%" align="center" class="txlHeaderBackgroundAlternate">运行号</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>用户</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>题目</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>结果</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>时间</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>内存</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>代码长度</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>语言</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>提交时间</td>
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
           	共${pageBean.allRow}条记录，当前第${pageBean.currentPage}/${pageBean.totalPage}页
        </td>
      	<td colspan="4">
      		<script type="text/javascript">newPage();</script>
      	</td>
      	<td>
      		<select name="select" id="select">
      			<option value="1">每页1个</option>
      			<option value="2">每页2个</option>
      			<option value="3">每页3个</option>
      			<option value="4">每页4个</option>
      			<option value="5">每页5个</option>
      		</select>
      	</td>
      	<td style="text-align:center;" colspan="3">
      		转到第<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />页 
        	<input type="button" value="跳转" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
	  </tbody></table>
</FORM>
</s:if>

</BODY>
</HTML>




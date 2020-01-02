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

<input type="hidden" name="actionURL" value="submit_pageSubmit.action?userId=${sessionScope.loginUser.userId}&state=0&action=user"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	没有试题信息
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1050px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">正在攻克--试题信息管理</th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				请输入关键字:
				<select name="condition1">
					<option value="--请选择--">--请选择--</option>
					<option value="problem.title">标题</option>	
					<option value="problem.hardFactor">难度</option>
					<option value="passCount">通过次数</option>
					<option value="submitCount">提交次数</option>
					<option value="firstTrueDate">成功提交</option>
					<option value="lastSubmitDate">最后提交</option>
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
        <td width="7%" align="center" class="txlHeaderBackgroundAlternate">序号</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>题号</td>
        <td width="20%" align="center" class=txlHeaderBackgroundAlternate>标题</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>难度</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>通过次数</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>提交次数</td>
        <td width="14%" align="center" class=txlHeaderBackgroundAlternate>成功提交</td>
        <td width="14%" align="center" class=txlHeaderBackgroundAlternate>最后提交</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>查看运行</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="7%" align="center" class="txlrow">
      			<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
      		</td>
	        <td width="10%" align=center class=txlrow>
	        	${problem.problemId}
	        </td>
	        
	        <td width="20%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<a href="#">
	        		${problem.title}
	        	</a>
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	${problem.hardFactor}
	        </td>
	        
	        <td width="10%" align=center class=txlrow>
	        	${passCount}
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	${submitCount}
	        </td>
	        
	        <td width="14%" align=center class=txlrow>
	        	<s:date name="firstTrueDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        <td width="14%" align=center class=txlrow>
	        	<s:date name="lastSubmitDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        
	        <td width="10%" align=center class=txlrow>
	        	<a href="code_pageCode.action?action=user&userId=${sessionScope.loginUser.userId}&problemId=${problem.problemId}">查看运行</a>
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
	  </tbody></table>
</FORM>
</s:if>


</BODY>
</HTML>




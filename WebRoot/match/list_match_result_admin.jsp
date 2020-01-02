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

<input type="hidden" name="actionURL" value="match_pageMatch.action?action=result"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	没有比赛信息
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="1500px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">比赛信息结果管理</th>
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
      	<td width="5%" align="center" class="txlHeaderBackgroundAlternate">选定</td>
        <td width="5%" align="center" class="txlHeaderBackgroundAlternate">序号</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>比赛名称</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>状态</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>撰写者</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>开始时间</td>
        <td width="8%" align="center" class=txlHeaderBackgroundAlternate>排行统计</td>
        <td width="8%" align="center" class=txlHeaderBackgroundAlternate>报表一览</td>
        <td width="8%" align="center" class=txlHeaderBackgroundAlternate>查看试题</td>
        <td width="8%" align="center" class=txlHeaderBackgroundAlternate>所有运行</td>
        <td width="8%" align="center" class=txlHeaderBackgroundAlternate>导出Excel</td>
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
	        
	        <td width="10%" align=center class=txlrow>
	        	<script type="text/javascript">
					var state=${state};
					if(state=="0"){
						document.write("还未开始&nbsp;&nbsp;&nbsp;&nbsp;");
					}else if(state=="1"){
						document.write("<div style='color:red;'><b>正在进行...</b></div>");
					}else{
						document.write("已经结束&nbsp;&nbsp;&nbsp;&nbsp;");
					}
	        	</script>
	        </td>
	         <td width="10%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	${author}
	        </td>
	        <td width="15%" align=center class=txlrow>
	        	<s:date name="startDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        
	        <td width="8%" align=center class=txlrow>
	        	<a href="form_pageForm.action?matchId=${matchId}">排行统计</a>
	        </td>
	        <td width="8%" align=center class=txlrow>
	        	<a href="match_showMatch.action?matchId=${matchId}&action=form">报表一览</a>
	        </td>
	        <td width="8%" align=center class=txlrow>
	        	<a href="problem_pageMatchProblem.action?matchId=${matchId}&action=run">查看试题</a>
	        </td>
	        <td width="8%" align=center class=txlrow>
	        	<a href="code_pageCode.action?matchId=${matchId}&action=admin">所有运行</a>
	        </td>
	        <td width="8%" align=center class=txlrow>
	        	<a href="handleExcel_exportFormExcel.action?matchId=${matchId}">导出Excel</a>
	        </td>
	    </tr>
	  </s:iterator>
     
      
    
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>
        	<span class="tablebody2">
          		<input type="checkbox" id="selectAll"/>全选
          		<input type="checkbox" id="reverse"/>反选
          	</span>
          	<input type="button" value="添加" class="but" onclick="window.location.href='${pageContext.request.contextPath}/match/insert_match.jsp'"/>
          	<input type="button" value="删除" class="but" onclick="batchDelete('match_batchDelete.action')"/>
        </td>
      </tr>
      
      <tr bgcolor="#DEE5FA">
        <td colspan=16 align=center class=txlrow></td>
      </tr>
      <tr bgcolor="#DEE5FA">
      	<td colspan="3">	
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




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

<input type="hidden" name="actionURL" value="match_pageMatch.action?action=all&state=1"/>
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
  <table width="900px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">
        	正在进行--比赛信息管理
        	[<a href="match_pageMatch.action?action=all">近期比赛</a>]
        	[<a href="match_pageMatch.action?action=all&state=1">现在进行</a>]
        </th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				请输入关键字:
				<select name="condition1">
					<option value="--请选择--">--请选择--</option>
					<option value="--请选择--">--请选择--</option>
					<option value="matchName">名称</option>	
					<option value="state">状态</option>
					<option value="address">地点</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="查询" id="inquire"/>
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>&nbsp;</td>
      </tr>
		<tr bgcolor="#DEE5FA">
		<td>
      <s:iterator value="#list" status="st">
					<table border="0" cellspacing="3px" cellpadding="5px" width="100%">
						<tr>
							<td colspan="2">
								<span style="color:blue; font-size:15px;"><b>
									<a href="problem_pageMatchProblem.action?matchId=${matchId}">
									${matchName}</a>[<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>]
								</b></span>
							</td>
						</tr>
						<tr>
							<td>开始时间：<s:date name="startDate" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td>结束时间：<s:date name="endDate" format="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td>比赛状态：<span style="color:red;"><b>
								<script type="text/javascript">
									var state=${state};
									if(state=="0"){
										document.write("还未开始");
									}else if(state=="1"){
										document.write("正在进行...");
									}else{
										document.write("已经结束");
									}
								</script>
							</b></span></td>
							<td>比赛地点：${address}</td>
						</tr>
						<tr>
							<td colspan="2">题目描述：${content}</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
	  </s:iterator>
	  </td>
     </tr>
      
    
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>&nbsp;
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan=16 align=center class=txlrow></td>
      </tr>
      <tr bgcolor="#DEE5FA">
      	<td>
      		<table border="0" cellspacing="0px" cellpadding="0px" width="100%">
      			<tr>
      				<td width="200px">共${pageBean.allRow}条记录，当前第${pageBean.currentPage}/${pageBean.totalPage}页</td>
      			
      			
      				<td width="500px"><script type="text/javascript">newPage();</script></td>
      			
      				<td width="150px">
      					<select name="select" id="select">
			      			<option value="10">每页10个</option>
			      			<option value="15">每页15个</option>
			      			<option value="20">每页20个</option>
			      			<option value="25">每页25个</option>
			      			<option value="30">每页30个</option>
			      		</select>
      				</td>
      			
      				<td width="150px">
      					转到第<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />页 
        				<input type="button" value="跳转" width="37" height="15" id="go" class="go"/>
      				</td>
      			</tr>
      		</table>

        </td>
      </tr>
	  </tbody>
</table>
</FORM>
</s:if>


</BODY>
</HTML>




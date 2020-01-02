<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>问题的所有评论</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pager.js" charset="gbk"></script>

<style type="text/css">

.td_1{
	text-align:left;
	font-size:15px;
	color:red;
}

.txt{
	text-align:left;
	vertical-align:top;
	padding-left:10px;
	padding-top:10px;
	font-size:15px;
	color:green;
	background-color:#eee;
}
</style>
</head>
<body>
<input type="hidden" name="actionURL" value="comment_findComment.action?action=admin&matchId=${match.matchId}"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>


<center>

<span style="color:red;"><b>${match.matchName}</b></span>讨论区[管理员]<br/><br/>

<s:if test="#list.size()==0">
	还没有评论信息，赶快抢沙发
</s:if>
<s:if test="#list.size()!=0">
	<table border="0" width="900" cellspacing="0px" cellpadding="0px">
		<s:iterator value="#list" status="st">
			<tr>
				<td class="td_1">用户:<s:property value="user.userName"/></td>
				<td class="td_1">评论时间:<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="td_1">
					<s:property value="floor"/>楼
					[回复数:${replyCount}]
				</td>
				<td class="td_1"><a href="comment_deleteComment.action?commentId=${commentId}">删除</a></td>
			</tr>
			<tr>
				<td colspan="4" height="100px" class="txt">
					<s:property value="content"/>
				</td>
			</tr>
			<tr>
				<td colspan="5" height="50px">&nbsp;</td>
			</tr>
		</s:iterator>
	</table>
	
	<table border="0px" width="900px" cellspacing="0px" cellpadding="0px">
		<tr>
	      	<td width="200px" class="td_1">
	           	共${pageBean.allRow}条记录，当前第${pageBean.currentPage}/${pageBean.totalPage}页
	        </td>
	      	<td width="450px" class="td_1">
	      		<script type="text/javascript">newPage();</script>
	      	</td>
	      	<td width="250px" class="td_1">
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
	</table>
</s:if>
</center>
</body>
</html>
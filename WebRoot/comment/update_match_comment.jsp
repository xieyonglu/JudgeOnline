<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>修改评论界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/problem/css/problem.css"/>

<style type="text/css">
.formFieldError{
	font-family:verdana,arial,helvetica,sans-serif;
	font-size:12px;
	color:#ff3300;
	margin:1px; 
	padding:4px; 
	vertical-align:bottom;
}
</style>

</head>

<body>

<table border="0" cellspacing="0px" cellpadding="0px" style="width:1030px; text-align:left;">
	<tr><td><img src="<%=request.getContextPath()%>/image/logo_grzx.gif"></td></tr>
</table>

<div style="border:1px solid black; margin-top:10px;"></div>


<div style="width:1030px; height:36px; margin-top:10px; background-color:orange;">
<div style="text-align:left; margin-left:20px; margin-top:9px;">

<table border="0" cellspacing="3px" cellpadding="1px" width="1000px">
	<tr>
		<td><div style="text-align:left; margin-left:5px;">修改比赛评论</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<form action="comment_updateComment.action" method="post" name="form1">
	<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
		
		<tr><td class="td_1">比赛名称</td>
			<td class="td_1">${comment.match.matchName}</td>
		</tr>
		<tr><td class="td_1" width="100px">比赛地点</td>
			<td class="td_1">${comment.match.address}&nbsp;</td>
		</tr>
		
		<tr><td class="td_1">楼数</td>
			<td class="td_1">${comment.floor}楼</td>
		</tr>
		<tr><td class="td_1">回复数</td>
			<td class="td_1">${comment.replyCount}次</td>
		</tr>
		
		<tr><td class="td_1">发布时间</td>
			<td class="td_1"><s:date name="comment.createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		
		<tr>
			<td class="td_1" style="vertical-align:top;">试题描述<br><br></td>
			<td class="td_1">
				<!--新闻内容错误提示-->
				<div style="width:800px; height:25px; background-color:yellow;">
					<s:fielderror cssClass="formFieldError"><s:param>comment.content</s:param></s:fielderror>
				</div>
				<textarea style="width:100%; height:500px;" name="comment.content" id="editor"><s:property value="comment.content"/></textarea>
			</td>
		</tr>
		
		<tr><td>&nbsp;</td>
			<td class="td_1">
				<input type="hidden" name="userId" value="${comment.user.userId}"/>
				<input type="hidden" name="matchId" value="${comment.match.matchId}"/>
				<input type="hidden" name="comment.commentId" value="${comment.commentId}"/>
				<input type="hidden" name="comment.floor" value="${comment.floor}" readonly/>
				<input type="hidden" name="comment.createDate" value="<s:date name='comment.createDate' format='yyyy-MM-dd HH:mm:ss'/>" readonly/>
				<input type="hidden" name="comment.replyCount" value="${comment.replyCount}" readonly/>
				<input type="submit" value="修改评论" class="button"/>
			</td>
		</tr>
	</table>
	</form>
	
</div>
</body>
</html>



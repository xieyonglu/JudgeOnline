<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>�������������</title>
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
<input type="hidden" name="actionURL" value="comment_findComment.action?action=user&problemId=${problem.problemId}&userId=${user.userId}"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>


<center>
������������<br/>
��Ŀ��${problem.title}&nbsp;&nbsp;
[<a href="comment_findComment.action?problemId=${problem.problemId}">��������</a>]<br/>

<s:if test="#list.size()==0">
	��Ŀǰ����������Ϣ��
</s:if>
<s:if test="#list.size()!=0">
	<form method="post" action="#">
		<table border="0" width="900px">
			<s:iterator value="#list" status="st">
				<tr>
					<td class="td_1">�û�:<s:property value="user.userName"/></td>
					<td class="td_1">����ʱ��:<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="td_1"><s:property value="floor"/><!--<s:property value="#st.index+1"/>-->¥</td>
					<td class="td_1">�ظ���:${replyCount}</td>
					<td class="td_1">
						<a href="comment_deleteComment.action?commentId=${commentId}">ɾ��</a>
						<a href="comment_showComment.action?commentId=${commentId}&action=update">�޸�</a>
					</td>
				</tr>
				<tr>
					<td colspan="5" height="100px" class="txt">
						<s:property value="content"/>
					</td>
				</tr>
				<tr>
					<td colspan="5" height="50px">&nbsp;</td>
				</tr>
			</s:iterator>
		</table>
	</form>
	<table border="0px" width="900px" cellspacing="0px" cellpadding="0px">
		<tr>
	      	<td width="200px" class="td_1">
	           	��${pageBean.allRow}����¼����ǰ��${pageBean.currentPage}/${pageBean.totalPage}ҳ
	        </td>
	      	<td width="450px" class="td_1">
	      		<script type="text/javascript">newPage();</script>
	      	</td>
	      	<td width="250px" class="td_1">
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
	</table>
</s:if>
</center>
</body>
</html>
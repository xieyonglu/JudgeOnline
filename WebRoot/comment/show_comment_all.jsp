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
.formFieldError{
	font-family:verdana,arial,helvetica,sans-serif;
	font-size:12px;
	color:#ff3300;
	margin:1px; 
	padding:4px; 
	vertical-align:bottom;
}

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

<script type="text/javascript">
function reply(index){
	var reply=document.getElementById(index);
	if(reply.style.display=="block"){
		reply.style.display="none";
	}else{
		reply.style.display="block";
	}
}
</script>
</head>


<body>

<input type="hidden" name="actionURL" value="comment_findComment.action?action=all&problemId=${problem.problemId}"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>


<center>
������������<br/>
��Ŀ��${problem.title}
<br/>
<s:if test="#list.size()==0">
	��û��������Ϣ���Ͽ���ɳ��
</s:if>
<s:if test="#list.size()!=0">
	<table border="0" width="900px" cellspacing="0px" cellpadding="0px">
		<s:iterator value="#list" status="st">
			<tr>
				<td class="td_1">�û�:<s:property value="user.userName"/></td>
				<td class="td_1">����ʱ��:<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="td_1">
					<s:property value="floor"/>¥<!--<s:property value="#st.index+1"/>
					--><a href="#" onclick="reply(${st.index})">�ظ�</a>
					<s:property value="replyCount"/>��
				</td>
			</tr>
			<tr>
				<td colspan="3" height="100px" class="txt">
					<s:property value="content"/>
				</td>
			</tr>
			<tr style="display:none;" id="${st.index}">
				<td colspan="3">
					<form action="comment_insertCommentReply.action" method="post">
						<table border="0" width="100%" cellspacing="0px" cellpadding="0px">
							<tr bgcolor="#DEE5FA">
								<td colspan="3" height="100px">
									<textarea cols="118" rows="5" name="comment.content"></textarea>					
								</td>
							</tr>
							<tr bgcolor="#DEE5FA">
								<td colspan="3" style="text-align:right; padding-right:20px;">
									<input type="hidden" name="commentId" value="${commentId}"/>
									<input type="hidden" name="problemId" value="${problem.problemId}"/>
									<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
									<input type="submit" value="�ظ�����"/>
								</td>
							</tr>
						</table>
					</form>
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
	        	<input type="button" value="��ת" width="37" height="15" id="go"/>
	        </td>
      </tr>
	</table>
</s:if>



<form action="comment_insertComment.action" method="post">
	<table border="1" width="900px" height="300px" cellspacing="0px" cellpadding="0px">
		<tr>
			<td style="text-align:left;">
				<s:fielderror cssClass="formFieldError" theme="simple">
					<s:param>commentError</s:param>
				</s:fielderror>
			</td>
		</tr>
		<tr>
			<td><textarea name="comment.content" cols="125" rows="20"></textarea></td>
		</tr>
		<tr>
			<td style="text-align:right;">
				<input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"/>
				<input type="hidden" name="problemId" value="${problem.problemId}"/>
				<input type="submit" value="����"/>
			</td>
		</tr>
	</table>
	</form>
</center>
</body>
</html>







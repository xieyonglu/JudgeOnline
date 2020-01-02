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

function adminLimit(userId,limit){
	var adminLimit=document.getElementById(limit).value;
	location.href="user_adminLimit.action?userId="+userId+"&limit="+adminLimit;
}
</script>
</head>
<body>

<input type="hidden" name="actionURL" value="user_pageUser.action"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	没有管理员信息
</s:if>
<s:if test="#list.size()!=0">
  <table width="1300px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">用户信息管理</th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				请输入关键字:
				<select name="condition1">
					<option value="--请选择--">--请选择--</option>
					<option value="userName">用户名</option>
					<option value="email">邮箱</option>
					<option value="question">密保问题</option>
					<option value="answer">密保答案</option>
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
      	<td width="7%" align="center" class="txlHeaderBackgroundAlternate">选定</td>
        <td width="10%" align="center" class="txlHeaderBackgroundAlternate">序号</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>用户名</td>
        <td width="10%" align="center" class=txlHeaderBackgroundAlternate>邮箱</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>创建日期</td>
        <td width="6%" align="center" class=txlHeaderBackgroundAlternate>账号设置</td>
        <td width="6%" align="center" class=txlHeaderBackgroundAlternate>评论设置</td>
        <td width="15%" align="center" class=txlHeaderBackgroundAlternate>权限设置</td>
        <td width="23%" align="center" class=txlHeaderBackgroundAlternate>基本操作</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="7%" align="center" class="txlrow">
      			<input type="checkbox" name="checkList" value="${userId}">
      		</td>
	        <td width="10%" align=center class=txlrow>
	        	<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
	        </td>
	        <td width="10%" align=center class=txlrow>
	        	<a href="user_showUser.action?userId=${userId}&action=show"><s:property value="userName"/></a>
	        </td>
	        <td width="10%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<s:property value="email"/>
	        </td>
	        <td width="15%" align=center class=txlrow>
	        	<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
	        </td>
	        <td width="6%" align=center class=txlrow>
	        	<s:if test="userEnable">
					<a href="user_userEnableSet.action?userId=${userId}&type=unEnable">禁用</a>
				</s:if>
				<s:else>
					<a href="user_userEnableSet.action?userId=${userId}&type=enable">启用</a>
				</s:else>
	        </td>
	        <td width="6%" align=center class=txlrow>
	        	<s:if test="commentEnable">
					<a href="user_commentEnableSet.action?userId=${userId}&type=unEnable">禁用</a>
				</s:if>
				<s:else>
					<a href="user_commentEnableSet.action?userId=${userId}&type=enable">启用</a>
				</s:else>
	        </td>
	        <td width="15%" align="center" class="txlrow">
		        <select id="limit<s:property value='#st.index'/>">
					<option value="--select--">--请选择--</option>
					<option value="super">超级管理员</option>
					<option value="general">普通管理员</option>
					<option value="vip">VIP会员</option>
				</select>
				<input type="button" value="确定" onclick="adminLimit('${userId}','limit<s:property value='#st.index'/>')"/>		
			</td>
	        <td width="23%" align=center class=txlrow>
	        	<img src="${pageContext.request.contextPath}/image/update.gif" width="16" height="16" />
					<a href="user_showUser.action?userId=${userId}&action=basic">编辑[1]</a>&nbsp; &nbsp;
					
				<img src="${pageContext.request.contextPath}/image/update.gif" width="16" height="16" />
					<a href="user_showUser.action?userId=${userId}&action=update">编辑[2]</a>&nbsp; &nbsp;
					
				<img src="${pageContext.request.contextPath}/image/delete.gif" width="16" height="16" />
					<a href="user_deleteUser.action?userId=${userId}">删除</a>
	        </td>
	    </tr>
	  </s:iterator>
     
      
    
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>
        	<span class="tablebody2">
          		<input type="checkbox" id="selectAll"/>全选
          		<input type="checkbox" id="reverse"/>反选
          	</span>
          	<input type="button" value="删除" class="but" onclick="batchDelete('user_batchDeal.action?flag=delete')"/>
          	<input type="button" value="账号可用" class="but" onclick="batchDeal('user_batchDeal.action?flag=userEnable')"/>
          	<input type="button" value="账号禁用" class="but" onclick="batchDeal('user_batchDeal.action?flag=userUnable')"/>
          	<input type="button" value="评论可用" class="but" onclick="batchDeal('user_batchDeal.action?flag=commentEnable')"/>
          	<input type="button" value="评论禁用" class="but" onclick="batchDeal('user_batchDeal.action?flag=commentUnable')"/>
        </td>
      </tr>
      
      <tr bgcolor="#DEE5FA">
        <td colspan=16 align=center class=txlrow></td>
      </tr>
      <tr bgcolor="#DEE5FA">
      	<td colspan="2">	
           	共${pageBean.allRow}条记录，当前第${pageBean.currentPage}/${pageBean.totalPage}页
        </td>
      	<td colspan="5">
      		<script type="text/javascript">newPage();</script>
      	</td>
      	<td style="text-align:right;">
      		<select name="select" id="select">
      			<option value="10">每页10个</option>
      			<option value="15">每页15个</option>
      			<option value="20">每页20个</option>
      			<option value="25">每页25个</option>
      			<option value="30">每页30个</option>
      		</select>
      	</td>
      	<td style="text-align:center;">
      		转到第<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />页 
        	<input type="button" value="跳转" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
</tbody></table>
</s:if>


</BODY>
</HTML>




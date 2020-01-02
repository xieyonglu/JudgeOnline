<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.xyl.bean.*"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'form.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.td_1{
			text-align:center;
		}
	</style>

  </head>
  
  <body style="background-color: #D9DFDD">
  	<s:if test="form=='table'">
  		<center>
		    <b><span style="color:red">${match.matchName}</span>中的数据报表如下</b><br/>
		    <table border="1" cellspacing="0px" cellpadding="5px" style="margin-top:20px;">
		    	<tr>
		    		<td width="100px" class="td_1">序号</td>
		    		<td width="300px" class="td_1">试题</td>
		    		<td width="100px" class="td_1">合计</td>
		    		<td width="500px" class="td_1">人员</td>
		    	</tr>
		    <s:iterator value="#request.formMap" id="column" status="st">
		    	<tr>
			    	<td style="text-align:center;"><s:property value="#st.index+1"/></td>
			    	<td><s:property value="key"/></td>
			    	<td style="text-align:center;"><s:property value="value.size()"/></td>
			    	<td>
			    		<s:iterator value="value" status="st">
			    			<s:property value="userName"/>
			    			&nbsp;&nbsp;&nbsp;&nbsp;
			    			<s:if test="(#st.index+1)%5==0"><br/></s:if>
			    		</s:iterator>&nbsp;
			    	</td>
		    	</tr>
		    </s:iterator>
		    </table>
    	</center>
  	</s:if>
  	<s:else>
  		<center>
			<table>
				<tr>
					<td align="center"><img id="reportPic" src="${requestScope.graphURL}" width="800" height="400" border="0" usemap="${requestScope.filename}"></td>
				</tr>
			</table>
		</center>
  	</s:else>
  	
  </body>
</html>

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

var hour;
var minute;
var seconds;
var interval;
$(function(){
	
	if("${match.state}"=="1"){
		var end="${match.endDate}";
		var now=new Date();	
		var endTime=new Date(end.substring(0,4),end.substring(5,7)-1,end.substring(8,10),end.substring(11,13),end.substring(14,16),end.substring(17,19));
		var cha=endTime.getTime()-now.getTime();	
		hour=parseInt(cha/3600000);
		minute=parseInt(cha%3600000/60000);
		seconds=parseInt(cha%3600000%60000/1000);
		
		
		//alert(hour+"--"+minute+"--"+seconds)
		if(hour<=0&&minute<=0&&seconds<=0){
				//alert("ʱ�䵽000������");
		}else{
			interval=setInterval("time()",1000);
		}
		
	}
	
});

function time(){

	seconds--;
	if(seconds<0)seconds=0;
	if(seconds==0){
		if(minute>0)seconds=59;
		minute--;
		if(minute<0)minute=0;
		if(minute==0){
			if(hour>0)minute=59;
			hour--;
			if(hour<0)hour=0;
			if(hour==0&&minute==0&&seconds==0){
				alert("ʱ�䵽������");
				clearInterval(interval);
				//location.href="match_setMatchState.action?matchId=${match.matchId}&state=2&action=match";
			}
		}
	}
	document.getElementById("time").innerHTML=hour+":"+minute+":"+seconds;		
};
</script>
</head>
<body>

<input type="hidden" name="actionURL" value="problem_pageMatchProblem.action?matchId=${match.matchId}"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	û��������Ϣ��
</s:if>
<s:if test="#list.size()!=0">



<form name="form1" method="post" action="">
  <table width="980px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr bgcolor="#DEE5FA">
        <td colspan=16 style="text-align:left; padding-left:20px; height:30px">
        	����״̬:
        	<s:if test="match.state==1">
        		�Ѿ���ʼ...&nbsp;&nbsp;<span id="time" style="color:red; font:15px;"><b>��������ʱ</b></span>
			</s:if>
			<s:elseif test="match.state==2">�Ѿ�����</s:elseif>
        	<s:else>��δ��ʼ</s:else>
        	<span style="padding-left:50px;">��������/�Ŷӣ�${match.personCount}</span>
        </td>
        
      </tr>
      <tr>
        <th align=center colspan=16 style="height: 23px">
        	<span style="color:red;"><b>${match.matchName}</b></span>����������Ϣ
        	-[<a href="form_pageForm.action?matchId=${match.matchId}">��������</a>]
        	-[<a href="code_pageFindCode.action?matchId=${match.matchId}&action=all">���н��</a>]
        	<s:if test="match.state==1">
        		-[������̳]
			</s:if>
			<s:else>
				-[<a href="comment_findComment.action?matchId=${match.matchId}">������̳</a>]
        	</s:else>
        	
        </th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				������ؼ���:
				<select name="condition1">
					<option value="--��ѡ��--">--��ѡ��--</option>
					<option value="problemId">���</option>	
					<option value="title">����</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="��ѯ" id="inquire"/>
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>&nbsp;</td>
      </tr>
      <tr align="center" bgcolor="#799AE1">
        <td width="20%" align="center" class="txlHeaderBackgroundAlternate">���</td>
        <td width="30%" align="center" class=txlHeaderBackgroundAlternate>���</td>
        <td width="50%" align="center" class=txlHeaderBackgroundAlternate>����</td>
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		
	        <td width="20%" align=center class=txlrow>
	        	<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
	        </td>
	        <td width="30%" align=center class=txlrow>
	        	<s:property value="sequence"/>
	        </td>
	        <td width="50%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<a href="problem_findMatchProblem.action?problemId=${problemId}&action=all&matchId=${match.matchId}">
	        		<s:property value="title"/>
	        	</a>
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
      	<td>	
           	��${pageBean.allRow}����¼����ǰ��${pageBean.currentPage}/${pageBean.totalPage}ҳ
        </td>
      	<td>
      		<script type="text/javascript">newPage();</script>
      	</td>
      	<td>
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
	  </tbody></table>
</FORM>
</s:if>


</BODY>
</HTML>




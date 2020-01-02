<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script type="text/javascript">
function writeForm(problem){
	var s=problem.split("|");
	var sum=0;
	
	if(s[1]==undefined){
		s[0]="";
		s[1]="";
		sum="";
	}else if(s[0]!="0"){
		sum=parseInt(s[0])+parseInt(s[1])*20;
	}
	document.write("<div style='color:blue;'><b>"+sum+"</b><div/>");
	document.write("(<span style='color:green;'>"+s[0]+"</span>+<span style='color:red;'><b>"+s[1]+"</b></span>*20"+")");
}

</script>
</head>
<body>

<input type="hidden" name="actionURL" value="form_pageForm.action?matchId=${match.matchId}"/>
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
  <table width="2000px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=19 style="height: 23px">${match.matchName}排名统计</th>
      </tr>
     
      <tr bgcolor="#DEE5FA">
        <td colspan="19" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				请输入关键字:
				<select name="condition1">
					<option value="--请选择--">--请选择--</option>
					<option value="userName">用户名</option>	
					<option value="problemCount">试题总数</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="查询" id="inquire"/>
			</div>
        </td>
      </tr>
      
       <tr bgcolor="#DEE5FA">
        <td colspan="19" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; text-align:left;">
				<span style="padding-left:50px;">参赛人数/团队：<span style="font:15px bold; color:red;">${match.personCount}</span></span>
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="19" align="center" class=txlrow>&nbsp;</td>
      </tr>
      <tr align="center" bgcolor="#799AE1">
      	<td width="5%" align="center" class="txlHeaderBackgroundAlternate">名次</td>
        <td width="5%" align="center" class="txlHeaderBackgroundAlternate">用户名</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>试题总数</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>耗时(m)</td>
        <!--<script type="text/javascript">
        	for(var i=0;i<"${match.problemCount}";i++){
        			var array=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'];
        			document.write('<td width="5%" align="center" class="txlHeaderBackgroundAlternate">');
        			document.write(array[i]);
        			document.write('</td>');
        			
        	}
        </script>-->
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>A</td>
       	<td width="5%" align="center" class=txlHeaderBackgroundAlternate>B</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>C</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>D</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>E</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>F</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>G</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>H</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>I</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>J</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>K</td>
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>L</td>  
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>M</td>  
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>N</td>  
        <td width="5%" align="center" class=txlHeaderBackgroundAlternate>O</td>  
        
      	
      </tr>
		  
      <s:iterator value="#list" status="st">
      	<tr bgcolor="#DEE5FA">
      		<td width="5%" align="center" class="txlrow">
      			<s:property value="#st.getIndex()+1+(pageBean.currentPage-1)*pageBean.pageSize"/>
      		</td>
	        <td width="5%" align=center class=txlrow>
	        	${userName}
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	${problemCount}
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	${totalTime}
	        </td>
	        <!--
	        <script type="text/javascript">
        		for(var i=0;i<"${match.problemCount}";i++){
        			var array=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'];
        			document.write('<td width="5%" align="center" class="txlrow">');
        			switch(i){
        				case 0:writeForm("<s:property value='problemA'/>");break;
        				case 1:writeForm("<s:property value='problemB'/>");break;
        				case 2:writeForm("<s:property value='problemC'/>");break;
        				case 3:writeForm("<s:property value='problemD'/>");break;
        				case 4:writeForm("<s:property value='problemE'/>");break;
        				case 5:writeForm("<s:property value='problemF'/>");break;
        				case 6:writeForm("<s:property value='problemG'/>");break;
        				case 7:writeForm("<s:property value='problemH'/>");break;
        				case 8:writeForm("<s:property value='problemI'/>");break;
        				case 9:writeForm("<s:property value='problemJ'/>");break;
        				case 10:writeForm("<s:property value='problemK'/>");break;
        			    case 11:writeForm("<s:property value='problemL'/>");break;
        				case 12:writeForm("<s:property value='problemM'/>");break;
        				case 13:writeForm("<s:property value='problemN'/>");break;
        				case 14:writeForm("<s:property value='problemOss'/>");break;
        				default:writeForm("<s:property value=''/>");break;
        			}
        		    document.write('</td>');
        			
        		}
        	</script>
        	-->
      		
	        <td width="5%" align=center class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemA'/>");</script>
	        </td>
	        <td width="5%" align=center class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemB'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemC'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemD'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemE'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemF'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemG'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemH'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemI'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemG'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemK'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemL'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemM'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemN'/>");</script>
	        </td>
	        <td width="5%" align=center bgcolor="#DEE5FA" class=txlrow>
	        	<script type="text/javascript">writeForm("<s:property value='problemO'/>");</script>
	        </td>
	    </tr>
	  </s:iterator>
     
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
      	<td colspan="1">
      		<select name="select" id="select">
      			<option value="10">每页10个</option>
      			<option value="15">每页15个</option>
      			<option value="20">每页20个</option>
      			<option value="25">每页25个</option>
      			<option value="30">每页30个</option>
      		</select>
      	</td>
      	<td style="text-align:left;" colspan="12">
      		转到第<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />页 
        	<input type="button" value="跳转" width="37" height="15" id="go" class="go"/>
        </td>
      </tr>
	  </tbody></table>
</FORM>
</s:if>

</BODY>
</HTML>




<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"">
<title>Insert title here</title>

<script type="text/javascript">
function jumpAction(){
	//var start=document.getElementById("start").value;
	//var end=document.getElementById("end").value;
	var typeObj=document.getElementById("type");
	//var timeObj=document.getElementById("time");
	var formObj=document.getElementById("form");
	
	var type=typeObj.options[typeObj.selectedIndex].value;
	//var time=timeObj.options[timeObj.selectedIndex].value;
	var form=formObj.options[formObj.selectedIndex].value;
	
	var url="report_report.action?matchId=${match.matchId}&type="+type+"&form="+form;
	window.open(url,'myFrame');
}

</script>
</head>
<body style="background-color: #D9DFDD; text-align:center;">
	<s:form action="chartStatAnalyseAction" name="reportForm" target="myFrame">
		<table border="0" width="900px" style="text-align:left;" cellspacing="0px" cellpadding="5px">
			<tr>
				<td width="100px">
					<select id="type">
						<option value="success">成功</option>
						<option value="failure">失败</option>
					</select>
				</td>
				
				<td width="100px">
					<select id="form">
						<option value="table">表格</option>
						<option value="pie">饼图</option>
						<option value="timeSeries">时序图</option>
						<option value="line">折线图</option>
						<option value="bar">柱形图</option>
					</select>
				</td>
				<td><input type="button" value="报表查询" onclick="jumpAction()"/></td>
			</tr>
			
		</table>
	</s:form>
	
	<table width="900px" border="0" align="center" cellpadding="4" cellspacing="0" class="border_tabel" id="print">
		<tr>
			<td align="center" valign="top" width="100%">
				<iframe height="450" id="myFrame" name="myFrame" frameborder="0" width="100%" scrolling="yes" marginheight="0" src="about:blank">
				
				</iframe>
			</td>
		</tr>
	</table>
</body>
</html>










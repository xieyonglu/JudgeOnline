<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>修改比赛界面</title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ip/css/ip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>

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

<script type="text/javascript">
$(function(){
	$(":radio[name='match.state']").val(['${match.state}']);
});
</script>
</head>

<body>

<div style="width:900px; height:36px; margin-top:10px; background-color:orange;">
<div style="text-align:left; margin-left:20px; margin-top:9px;">

<table border="0" cellspacing="3px" cellpadding="1px" width="900px">
	<tr>
		<td><div style="text-align:left; margin-left:5px;">修改IPV4</div></td>
	</tr>
</table>

</div>
</div>

<div style="width:900px; background-color:b0c4de;">
	<form action="ipAddress_updateIpAddress.action?action=ipv4" method="post" name="form1">
		<table border="1px solid black" cellspacing="0px" cellpadding="5px" width="100%">
			<tr><td class="td_1" width="100px">&nbsp;</td>
				<td class="td_1">添加IPV4</td>
			</tr>
			<tr><td class="td_1" width="100px">&nbsp;</td>
				<td class="td_1"><s:fielderror cssClass="formFieldError"><s:param>ipAddress.ipaddressv4</s:param></s:fielderror>&nbsp;</td>
			</tr>
			<tr><td class="td_1" width="100px">FROM</td>
				<td class="td_1">
					<input type="text" name="ipAddress.from_ip1" value="${ipAddress.from_ip1}" class="text"/>
					<input type="text" name="ipAddress.from_ip2" value="${ipAddress.from_ip2}" class="text"/>
					<input type="text" name="ipAddress.from_ip3" value="${ipAddress.from_ip3}" class="text"/>
					<input type="text" name="ipAddress.from_ip4" value="${ipAddress.from_ip4}" class="text"/>
				</td>
			</tr>
			<tr><td class="td_1" width="100px">TO</td>
				<td class="td_1">
					<input type="text" name="ipAddress.to_ip1" value="${ipAddress.to_ip1}" class="text"/>
					<input type="text" name="ipAddress.to_ip2" value="${ipAddress.to_ip2}" class="text"/>
					<input type="text" name="ipAddress.to_ip3" value="${ipAddress.to_ip3}" class="text"/>
					<input type="text" name="ipAddress.to_ip4" value="${ipAddress.to_ip4}" class="text"/>
				</td>
			</tr>
			<tr><td class="td_1">&nbsp;</td>
				<td class="td_1">
					<input type="hidden" name="matchId" value="${match.matchId}"/>
					<input type="hidden" name="ipAddress.ipAddressId" value="${ipAddress.ipAddressId}"/>
					<input type="submit" value="修改IPV4" class="button"/>
				</td>
			</tr>	
		</table>
	</form>
	
</div>


</body>
</html>



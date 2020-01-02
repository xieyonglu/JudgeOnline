<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head><title>公告信息显示</title>
<meta name="author" content="xyl">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>


<style type="text/css">
body{
	width:900px;
	height:800px;
	text-align:center;
}
#one{
	width:900px;
	height:900px;
	background-color:#CCC;
}

#two{
	width:800px;
	height:900px;
	float:left;
	background-color:#CCC;
	text-align:center;
}

.big{
	font-size:20;
	color:red;
}
.small{
	font-size:15;
	color:green;
}

</style>

<script type="text/javascript">
$(function(){
	$("#big").click(function(){
		//alert("大字号！");
		$("#content").removeClass("small").addClass("big");
	});
	$("#small").click(function(){
		//alert("小字号！");
		$("#content").removeClass("big").addClass("small");
	});
});
</script>
</head>


<body>


<div id="one">
	<!--two层开始-->
	<div id="two2">
		<div style="background-color:#ffffff; width:850px; height:800px; margin-top:10px;">
			<table border="0" cellspacing="3px" cellpadding="5px" width="100%">
				<tr>
					<td colspan="2">
						<div style="text-align:center; font:bold 20px; margin-top:20px;">
							<s:property value="notice.title" escape="false"/>
						</div>
					</td>
				</tr>
				<tr>
					<td class="td_1">
						<s:date name="news.createDate" format="yyyy-MM-dd HH:mm"/>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="td_1">来源：XX网 作者：<s:property value="notice.admin.userName"/></td>
					<td class="td_1" style="text-align:right;">
						<s:property value="notice.getBrowser()"/>人参与
						打印 转发 字号 <a href="#" id="big">T</a> <a href="#" id="small">t</a>
					</td>
				</tr>
			</table>

			<hr width="100%" color="gray"/>
			<div style="text-align:left; margin:10px; width=100%; height:600px; background-color:#ffffff;" id="content">
				<s:property value="notice.content" escape="false"/>
			</div>
			<!--
			<div style="width:97%; height:30px; background-color:gray;text-align:center;">
				[1][2][3][4]...7..[16][17]
			</div>
			-->
		</div>
    </div>
	<!--two层结束-->

	
</div><!--one层结束-->

</body>
</html>
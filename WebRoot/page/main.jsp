<%@ page language="java" %>
<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.io.*" %>
<%
int temp = 0;
long star = 0;
long end = 0;
long ttime = 0;
Date before = new Date();
star = before.getTime();
for(int i=0;i<100000; i++)
{
temp=1+1;
}
Date after = new Date();
end = after.getTime();
ttime = end-star ;
%>

<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0"> 
<TITLE>JSP̽��</TITLE>
<style>
<!--
BODY
{
	margin:0 auto;
	padding-top: 10px;
	FONT-FAMILY: ����;
	FONT-SIZE: 9pt
}
TD
{
	FONT-SIZE: 9pt;
}
TR
{
    HEIGHT: 18px;
}
A
{
	COLOR: #000000;
	TEXT-DECORATION: none
}
A:hover
{
	COLOR: #3F8805;
	TEXT-DECORATION: underline
}
.input
{
	BORDER: #111111 1px solid;
	FONT-SIZE: 9pt;
	BACKGROUND-color: #F8FFF0
}
.backs
{
	BACKGROUND-COLOR: #3F8805;
	COLOR: #ffffff;

}
.backq
{
	BACKGROUND-COLOR: #EEFEE0
}
.backc
{
	BACKGROUND-COLOR: #3F8805;
	BORDER: medium none;
	COLOR: #ffffff;
	HEIGHT: 18px;
	font-size: 9pt
}
.fonts
{
	COLOR: #3F8805
}
-->
</STYLE>
</head>

<body>



<% out.print("<font class=fonts>���������йز���</font>");%>
<table border=0 width=100% cellspacing=0 cellpadding=0 bgcolor="#3F8805">
<tr>
  <td>
<table width=100% border=0 align="center" cellpadding=0 cellspacing=1 >
          <tr bgcolor="#EEFEE0" >
          <td align=left width=150>&nbsp;��������</td><td width=300>&nbsp;<%= request.getServerName() %></td>
          </tr>
	  <tr bgcolor="#EEFEE0" >
		<td align=left>&nbsp;������IP</td><td>&nbsp;<%= request.getRemoteAddr() %></td>
	  </tr>
	  <tr bgcolor="#EEFEE0" >
		<td align=left>&nbsp;�������˿�</td><td>&nbsp;<%= request.getServerPort() %></td>
	  </tr>
	  <tr bgcolor="#EEFEE0" >
		<td align=left>&nbsp;������ʱ��</td><td>&nbsp;<% out.println(new java.util.Date()); %></td>
	  </tr>
	  <tr bgcolor="#EEFEE0" >
		<td align=left>&nbsp;���ļ�·��</td><td>&nbsp;<%=request.getPathTranslated() %></td>
	  </tr>
	  <tr bgcolor="#EEFEE0" >
		<td align=left>&nbsp;��������������</td><td>&nbsp;<% String publish=getServletContext().getServerInfo(); out.println(publish); %></td>
	  </tr>
	  <tr bgcolor="#EEFEE0" >
		<td align=left>&nbsp;HTTP��������</td><td>&nbsp;<%=request.getProtocol() %></td>
	  </tr>
<tr bgcolor="#EEFEE0" >
		<td align=left>&nbsp;����������</td><td>&nbsp;<%=request.getInputStream() %></td>
	  </tr>
	</table>
<table border=0 width=100% cellspacing=1 cellpadding=0>
  <tr bgcolor="#EEFEE0" >
    <td width="150" align=left>&nbsp;��վ��Ŀ¼</td>
    <td width="300"><%=request.getRealPath(".")%></td>
  </tr>
  <tr bgcolor="#EEFEE0" >
    <td align=left>&nbsp;Ŀǰ����ҳ��</td>
    <td><%=request.getRequestURI()%></td>
  </tr>
  <tr bgcolor="#EEFEE0" >
    <td align=left>&nbsp;Ŀǰ������ҳ���ַ</td>
    <td><%=request.getRequestURL()%></td>
  </tr>
  <tr bgcolor="#EEFEE0" >
    <td align=left>&nbsp;JVM�ڴ�</td>
    <td>�ڴ�������<%=Runtime.getRuntime().totalMemory()/1024/1024%>MB|����ڴ棺<%=Runtime.getRuntime().maxMemory()/1024/1024%>MB|�����ڴ棺<%=Runtime.getRuntime().freeMemory()/1024/1024%>MB</td>
  </tr>
</table></td></tr>
</table>
<br>
<font class=fonts>����������</font>

</table>
<br>�� �������ļ��ϴ��͹������
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#3F8805" width="100%">
	<tr  class=backs align=center><td width=320>�� �� �� ��</td><td width=130>֧�ּ��汾</td></tr>
        <tr class=backq>
		<td align=left>&nbsp;(Servlet�ϴ�)<font color=#888888>&nbsp;(�ļ��ϴ�)</font></td>
		<td align=left>&nbsp;<b><font color="red">֧��Servlet�ϴ�</font></b></td>
	</tr>
        <tr class=backq>
          <td align=left>&nbsp;(javabean�ϴ�)<font color=#888888>&nbsp;(�ļ��ϴ�)</font></td>
          <td align=left>&nbsp;<b><font color="red">֧��javabean�ϴ�</font></b></td>
        </tr>
</table>
<BR>
<font class=fonts>JSP�ű����ͺ������ٶȲ���</font><br>
<table class=backq border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#3F8805" width="100%">
  <tr  class=backs align=center>
	<td width=320>��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</td><td width=130>���ʱ��</td></tr>
  </tr>

  <form action="/jspcheck.jsp" method=post>
    
    <tr>
      <td align=left>&nbsp;<font color=red>������ʹ�õ���̨������</font>�����ʱ��</td><td>&nbsp;<font color=red><%=ttime %>����</font></td>
    </tr>
  </form>
</table>
<table border=0 width=450 cellspacing=0 cellpadding=0>
<tr><td align=center>
</td></tr>
</table>
</body> 
</html>
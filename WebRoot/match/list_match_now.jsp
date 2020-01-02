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
</script>
</head>
<body>

<input type="hidden" name="actionURL" value="match_pageMatch.action?action=all&state=1"/>
<input type="hidden" name="con1" value="<s:property value='condition1'/>"/>
<input type="hidden" name="con2" value="<s:property value='condition2'/>"/>
<input type="hidden" name="totalPage" value="<s:property value='pageBean.totalPage'/>"/>
<input type="hidden" name="currentPage" value="<s:property value='pageBean.currentPage'/>"/>
<input type="hidden" name="pageSize" value="<s:property value='pageBean.pageSize'/>"/>

<s:if test="#list.size()==0">
	û�б�����Ϣ
</s:if>
<s:if test="#list.size()!=0">
<form name="form1" method="post" action="">
  <table width="900px" border="0" align=center cellpadding=2 cellspacing=1 bordercolor="#799AE1" class=tableBorder>
    <tbody>
      <tr>
        <th align=center colspan=16 style="height: 23px">
        	���ڽ���--������Ϣ����
        	[<a href="match_pageMatch.action?action=all">���ڱ���</a>]
        	[<a href="match_pageMatch.action?action=all&state=1">���ڽ���</a>]
        </th>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>
        	<div style="background-color:#DEE5FA; width:100%; height:30px; text-align:center; margin-top:10px;">
				������ؼ���:
				<select name="condition1">
					<option value="--��ѡ��--">--��ѡ��--</option>
					<option value="--��ѡ��--">--��ѡ��--</option>
					<option value="matchName">����</option>	
					<option value="state">״̬</option>
					<option value="address">�ص�</option>
				</select>&nbsp;
				<input type="text" name="condition2"/>&nbsp;
				<input type="button" value="��ѯ" id="inquire"/>
			</div>
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align="center" class=txlrow>&nbsp;</td>
      </tr>
		<tr bgcolor="#DEE5FA">
		<td>
      <s:iterator value="#list" status="st">
					<table border="0" cellspacing="3px" cellpadding="5px" width="100%">
						<tr>
							<td colspan="2">
								<span style="color:blue; font-size:15px;"><b>
									<a href="problem_pageMatchProblem.action?matchId=${matchId}">
									${matchName}</a>[<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>]
								</b></span>
							</td>
						</tr>
						<tr>
							<td>��ʼʱ�䣺<s:date name="startDate" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td>����ʱ�䣺<s:date name="endDate" format="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<td>����״̬��<span style="color:red;"><b>
								<script type="text/javascript">
									var state=${state};
									if(state=="0"){
										document.write("��δ��ʼ");
									}else if(state=="1"){
										document.write("���ڽ���...");
									}else{
										document.write("�Ѿ�����");
									}
								</script>
							</b></span></td>
							<td>�����ص㣺${address}</td>
						</tr>
						<tr>
							<td colspan="2">��Ŀ������${content}</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
	  </s:iterator>
	  </td>
     </tr>
      
    
      <tr bgcolor="#DEE5FA">
        <td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>&nbsp;
        </td>
      </tr>
      <tr bgcolor="#DEE5FA">
        <td colspan=16 align=center class=txlrow></td>
      </tr>
      <tr bgcolor="#DEE5FA">
      	<td>
      		<table border="0" cellspacing="0px" cellpadding="0px" width="100%">
      			<tr>
      				<td width="200px">��${pageBean.allRow}����¼����ǰ��${pageBean.currentPage}/${pageBean.totalPage}ҳ</td>
      			
      			
      				<td width="500px"><script type="text/javascript">newPage();</script></td>
      			
      				<td width="150px">
      					<select name="select" id="select">
			      			<option value="10">ÿҳ10��</option>
			      			<option value="15">ÿҳ15��</option>
			      			<option value="20">ÿҳ20��</option>
			      			<option value="25">ÿҳ25��</option>
			      			<option value="30">ÿҳ30��</option>
			      		</select>
      				</td>
      			
      				<td width="150px">
      					ת����<input type="text" size="4" name="go" style="height:18px; width:25px; border:1px solid #999999;" />ҳ 
        				<input type="button" value="��ת" width="37" height="15" id="go" class="go"/>
      				</td>
      			</tr>
      		</table>

        </td>
      </tr>
	  </tbody>
</table>
</FORM>
</s:if>


</BODY>
</HTML>




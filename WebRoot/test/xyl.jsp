<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>   

<html>
<head>   
     <s:head theme="xhtml"/>   
     <sx:head parseContent="true"/> 
     <style type="text/css">
     	.w{
     		width:5000px;
     	}
     </style>  
</head>  

<body>

<sx:datetimepicker name="hiredate" label="��Ӷʱ��" cssClass="w" displayFormat="yyyy-MM-dd HH:mm:ss"/>
<sx:datetimepicker name="birthday" label="��������" displayFormat="yyyy/MM/dd"/><br/>
<sx:datetimepicker name="recordtime" label="��¼ʱ��" type="time" language="en_us" displayFormat="yyyy-MM-dd HH:mm:ss"/><br/>

<sx:datetimepicker name="date" label="��ʽʱ��" toggleType="explode" language="zh-cn" displayFormat="yyyy-MM-dd" /><br/>



</body>
</html>

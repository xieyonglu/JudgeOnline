<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>   

<html>
<head>   
     <s:head theme="xhtml"/>   
     <sx:head parseContent="true"/>   
</head>  

<body>
<sx:tree id="struts" label="struts" showRootGrid="true" showGrid="true">
	<sx:treenode label="struts" id ="1">
		<sx:treenode label="c1" id="11"/>
		<sx:treenode label="c2" id="12" />
		<sx:treenode label="c3" id="13" />
	</sx:treenode> 
		<sx:treenode label="spring" id ="2" >
		<sx:treenode label="c1" id="11" /> 
		<sx:treenode label="c2" id="12" />
		<sx:treenode label="c3" id="13" />
	</sx:treenode>  
	<sx:treenode label="hibernate" id ="3" >
		<sx:treenode label="c1" id="11" /> 
		<sx:treenode label="c2" id="12" /> 
		<sx:treenode label="c3" id="13" />
	</sx:treenode>
	</sx:tree>


</body>
</html>

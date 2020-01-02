<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
<title></title>
<meta name="author" content="xyl">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addSelfInfo.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/proCityArea.js" charset="gbk"></script>

<script type="text/javascript">
function onchangeYear(){

	with(document.form1){
		var yText=year.options[year.selectedIndex].text;
		var mText=month.options[month.selectedIndex].text;
		var dText=day.options[day.selectedIndex].text;
		if(yText!="-��ѡ��-"){
			addMonth();

			if(mText=="-��ѡ��-"){
				month.options[1].selected=true;
				month.onchange();
				day.options[1].selected=true;
			}else{
				//alert("=========");
				changeDay();
				//month.onchange();
				month.options[1].selected=true;
				day.options[1].selected=true;
			}
		}else{
			month.options.length=1;
			month.options[0].selected=true;
			day.options.length=1;
			day.options[0].selected=true;
		}
	}
}


function birthday(){

	with(document.form1){
		var y=(new Date()).getYear();
		//������
		year.options[0]=new Option("-��ѡ��-","-��ѡ��-");
		for(var i=50;i>=0;i--){
			year.options[51-i]=new Option(y-i,y-i);
			//document.write((y-i)+"---"+(51-i)+"<br/>");
		}
	}

}

function addMonth(){
	//������
	with(document.form1){
		month.options[0]=new Option("-��ѡ��-","-��ѡ��-");
		month.options[1]=new Option("01","01");
		month.options[2]=new Option("02","02");
		month.options[3]=new Option("03","03");
		month.options[4]=new Option("04","04");
		month.options[5]=new Option("05","05");
		month.options[6]=new Option("06","06");
		month.options[7]=new Option("07","07");
		month.options[8]=new Option("08","08");
		month.options[9]=new Option("09","09");
		month.options[10]=new Option("10","10");
		month.options[11]=new Option("11","11");
		month.options[12]=new Option("12","12");
	}
}

function onchangeMonth(){

	with(document.form1){
		var mText=month.options[month.selectedIndex].text;
		var yText=year.options[year.selectedIndex].text;
		var d=parseInt(mText);
		var y=parseInt(yText);

		//alert("--");

		if(mText!="-��ѡ��-"){
			if(yText!="-��ѡ��-"){	
				changeDay();
			}
		}else{
			day.options.length=1;
			day.options[0].selected=true;
		}

	}
}

function changeDay(){
	with(document.form1){
		var mText=month.options[month.selectedIndex].text;
		var yText=year.options[year.selectedIndex].text;
		var d=parseInt(mText);
		var y=parseInt(yText);

		//alert("day");

		//������
		day.options[0]=new Option("-��ѡ��-","-��ѡ��-");
		for(var i=1;i<=31;i++){
			var str=i+"";
			if(i<10)str="0"+str;
				day.options[i]=new Option(str,str);
		}
					
		switch(d){
			case 4: case 6: case 8: case 9: case 11:
				day.options[31]=null;
				break;
			case 2:
				day.options[31]=null;
				day.options[30]=null;
				if(!(y%400==0||(y%4==0&&y%100!=0))){//��������ж�
					day.options[29]=null;
				}
				break;
		}
		day.options[1].selected=true;
	
	}
}

</script>
</head>
<body>

<script type="text/javascript">
function check(){
	var telephone=document.getElementById("telephone");
	var trueName=document.getElementById("trueName");
	var value=telephone.value;
	var value=trim(value);
	var trueNameValue=trim(trueName.value);
	
	if(value!=""&&is_integer(value)==false){
		alert("�绰���������������");
		telephone.focus();
		telephone.select();
		return false;
	}

	if(value!=""&&!(value.length>0&&value.length<=20)){
		alert("�绰���������0��20֮�䣡");
		telephone.focus();
		telephone.select();
		return false;
	}

	if(trueNameValue!=""&&!(trueNameValue.length>0&&trueNameValue.length<=20)){
		alert("��ʵ����������0��20֮�䣡");
		trueName.focus();
		trueName.select();
		return false;
	} 
	return true;
}
</script>

<div id="one">
	<table border="0" cellspacing="2px" cellpadding="6px" width="100%">
		<tr>
			<td width="800px" rowspan="2"><img src="../image/logo_grzx.gif"/></td>
			<td style="text-align:right;"><a href="#">�ݲ�����ҳ</a></td>
		</tr>
		
		<tr>
			<td style="text-align:right;">
				<a href="#">��¼</a>|<a href="#">ע��</a>
			</td>
		</tr>
	</table>

	<div style="border:10px solid green; margin-top:5px;"></div>
	<div style="margin-top:10px;">
	<table border="0" cellspacing="2px" cellpadding="3px" width="100%">
		<tr><td><font size="4px" color="green"><b>&nbsp;��������ĸ�����Ϣ</b></font></td></tr>
		<tr><td>&nbsp;&nbsp;���Ƹ�����Ϣ�������ҵ�־ͬ���ϵĲݲݺ��ѣ�ͬʱ�����ܸ�����Ի�����</td></tr>
	</table>

	<div style="border:3px solid gray; margin-top:5px;"></div>
	<div style="margin-top:20px;"></div>

	<div id="two">
		<div style="margin-left:20px; background-color:blue; text-align:left;">
		<div style="margin-left:25px; margin-top:10px; float:left; font:bold 14px; color:green;">�û�������Ϣ</div>
	</div>
	<br><br>

	<form action="otherInfo_updateSelfInfo.action?action=insert" method="post" name="form1">
	<table border="0" cellspacing="5px" cellpadding="5px" width="600">
		<tr>
			<td style="text-align:right;" class="td_1">�û�����</td>
			<td><s:property value="user.userName"/></td>
		</tr>
		<tr>
			<td style="text-align:right;">�Ա�</td>
			<td><input type="radio" value="��" name="otherInfo.sex" checked>��&nbsp;&nbsp;
				<input type="radio" value="Ů" name="otherInfo.sex">Ů
			</td>
		</tr>
		<tr>
			<td style="text-align:right;">�������䣺</td>
			<td><s:property value="user.email"/>
				<a href="#">��Ѽ���</a><a href="#">�޸�</a>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;">�ֻ���</td>
			<td><input type="text" style="width:140px;" name="otherInfo.telephone" id="telephone">
			����д׼ȷ�ֻ��ſ���ʱ���ܲݲ���Ϊ�ֻ��û����Ƶľ�Ʒ����</td>
		</tr>
		<tr>
			<td style="text-align:right;">�������ڣ�</td>
			<td>
				<select style="width:80px;" name="year" onchange="onchangeYear()">
					<option value="-��ѡ��-">-��ѡ��-</option>
				</select>&nbsp;��
				<select style="width:80px;" name="month" onchange="onchangeMonth()">
					<option>-��ѡ��-</option>
				</select>&nbsp;��
				<select style="width:80px;" name="day">
					<option>-��ѡ��-</option>
				</select>&nbsp;��
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">���ڵأ�</td>
		<td>
			<s:fielderror/>
			<select style="width:80px;" id="province" name="province">
				<option>-��ѡ��-</option>
			</select>&nbsp;ʡ
			<select style="width:80px;" id="city" name="city">
				<option>-��ѡ��-</option>
			</select>&nbsp;��
			<select style="width:80px;" id="area" name="area">
				<option>-��ѡ��-</option>
			</select>&nbsp;��
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">��ʵ������</td>
		<td>
			<input type="text" style="width:300px;" name="otherInfo.trueName" id="trueName"/>
		</td>
	</tr>

</table>
<br>
	<div style="border:1px dashed gray; margin-top:10px;"></div>
	<div style="margin-left:20px; background-color:blue; text-align:left;">
	<div style="margin-left:25px; margin-top:15px; float:left; font:bold 14px; color:green;">��ϸ��Ϣ</div>
	</div>
	<br><br>
<table border="0" cellspacing="5px" cellpadding="5px" width="600">
	<tr>
		<td style="text-align:right;" class="td_1">�����̶ȣ�</td>
		<td>
			<select style="width:120px;" name="otherInfo.eduDegree">
				<option>��ѡ��</option>
				<option>��������</option>
				<option>����</option>
				<option>ר��</option>
				<option>����</option>
				<option>˶ʿ�о���</option>
				<option>��ʿ�о���</option>
				<option>��ʿ����</option>
			</select>
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">��ҵ���</td>
		<td>
			<select style="width:200px;" name="otherInfo.occDirectory">
				<option>��ѡ��</option>
				<option>����������������������</option>
				<option>ͨ��</option>
				<option>רҵ������ѯ���ƻ���ɵȣ�</option>
				<option>����</option>
				<option>ó��</option>
				<option>ҽ�ơ������򻷱�</option>
				<option>��ͨ��Դ</option>
				<option>����</option>
				<option>��������������ҵ��λ</option>
				<option>ý�塢���桢�Ļ���ý</option>
				<option>ũ���֡�������</option>
				<option>��Ʒ����ơ�������</option>
				<option>���֡����С�������ҵ</option>
				<option>����</option>
			</select>
		</td>
	</tr>

	<tr>
		<td style="text-align:right;">ְλ��</td>
		<td>
			<select style="width:200px;" name="otherInfo.position">
				<option>��ѡ��</option>
				<option>ѧ��</option>
				<option>ũ��</option>
				<option>����</option>
				<option>����</option>
			</select>
		</td>
	</tr>
	<tr>
		<td style="text-align:right;">�����룺</td>
		<td>
			<select style="width:200px;" name="otherInfo.income">
				<option>��ѡ��</option>
				<option>3000����</option>
				<option>3000~5000</option>
				<option>5000~8000</option>
				<option>8000~10000</option>
				<option>10000����</option>
			</select>
		</td>
	</tr>

	<tr>
		<td rowspan="2" style="text-align:right; vertical-align:top;">��Ȥ���ã�</td>
		<td>
			<input type="checkbox" name="hobby" value="��Ѷ/Ҫ��">��Ѷ/Ҫ��&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="�ƾ�/��Ʊ">�ƾ�/��Ʊ&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="����/ʱ��">����/ʱ��&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="��Ƶ">��Ƶ&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="����">����
		</td>
	</tr>
	<tr>
		<td>
			<input type="checkbox" name="hobby" value="�Ƽ�/����">�Ƽ�/����&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="����/��ʷ">����/��ʷ&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="����/����">����/����&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="����">����&nbsp;&nbsp;
			<input type="checkbox" name="hobby" value="��Ϸ">��Ϸ
		</td>
	</tr>
</table>
<input type="hidden" name="userId" value="<s:property value='user.userId'/>"/>

<div style="border:1px dashed gray; margin-top:10px;"></div>
<div style="margin-left:20px; background-color:blue; text-align:left;">
	<div style="margin-left:10px; margin-top:15px; float:left; font:bold 14px; color:green;">
		<input type="submit" value="�ύ��Ϣ" class="button" onClick="return check();"/>
	</div>
</div>
</form>

</div>
</div>
</div>

<script type="text/javascript">
birthday();
setup();
</script>
</body>
</html>





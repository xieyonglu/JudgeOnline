<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>����ʧ��</title>

<script type="text/javascript">
function sAlert(strTitle,strContent){

	var msgw,msgh,bordercolor; 
	msgw=400;//��ʾ���ڵĿ�� 
	msgh=100;//��ʾ���ڵĸ߶� 
	titleheight=25 //��ʾ���ڱ���߶� 
	bordercolor="#336699";//��ʾ���ڵı߿���ɫ 
	titlecolor="#99CCFF";//��ʾ���ڵı�����ɫ 

	var sWidth,sHeight; 
	sWidth=document.body.offsetWidth; 
	sHeight=screen.height;
	var bgObj=document.createElement("div"); 
	bgObj.setAttribute('id','bgDiv'); 
	bgObj.style.position="absolute"; 
	bgObj.style.top="0"; 
	bgObj.style.background="#777"; 
	bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75"; 
	bgObj.style.opacity="0.6"; 
	bgObj.style.left="0"; 
	bgObj.style.width=sWidth + "px"; 
	bgObj.style.height=sHeight + "px"; 
	bgObj.style.zIndex = "10000"; 

	document.body.appendChild(bgObj); 

	var msgObj=document.createElement("div") 
	msgObj.setAttribute("id","msgDiv"); 
	msgObj.setAttribute("align","center"); 
	msgObj.style.background="white"; 
	msgObj.style.border="1px solid " + bordercolor; 
	msgObj.style.position = "absolute"; 
	msgObj.style.left = "50%"; 
	msgObj.style.top = "50%"; 
	msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif"; 
	msgObj.style.marginLeft = "-225px" ; 
	msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px"; 
	msgObj.style.width = msgw + "px"; 
	msgObj.style.height =msgh + "px"; 
	msgObj.style.textAlign = "center"; 
	msgObj.style.lineHeight ="25px"; 
	msgObj.style.zIndex = "10001"; 

	var title=document.createElement("h4"); 
	title.setAttribute("id","msgTitle"); 
	title.setAttribute("align","right"); 
	title.style.margin="0"; 
	title.style.padding="3px"; 
	title.style.background=bordercolor; 
	title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);"; 
	title.style.opacity="0.75"; 
	title.style.border="1px solid " + bordercolor; 
	title.style.height="18px"; 
	title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif"; 
	title.style.color="white"; 
	title.style.cursor="pointer"; 
	title.title = "����ر�"; 
	title.innerHTML="<table border='0' width='100%'><tr><td align='left'><b>"+ strTitle +"</b></td><td align='right'>�ر�</td></tr></table></div>"; 
	title.onclick=function(){ 
	document.body.removeChild(bgObj); 
	document.getElementById("msgDiv").removeChild(title); 
	document.body.removeChild(msgObj); 
	window.history.go(-1);
	} 
	document.body.appendChild(msgObj); 
	document.getElementById("msgDiv").appendChild(title); 
	var txt=document.createElement("p"); 
	txt.style.margin="1em 0" 
	txt.setAttribute("id","msgTxt"); 
	txt.innerHTML=strContent; 
	document.getElementById("msgDiv").appendChild(txt); 
}

</script>

</head>

<body>

<script type="text/javascript">
sAlert("��������","�Բ��𣬲����������뷵�أ�");
</script>
</body>


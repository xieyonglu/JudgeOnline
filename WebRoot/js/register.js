function check(str){
	var email_I=document.getElementById("Email_Info");
	var userName_I=document.getElementById("UserName_Info");
	var password_I=document.getElementById("Password_Info");
	var answer_I=document.getElementById("Answer_Info");
	var selfDefine_I=document.getElementById("selfDefine_Info");
	var confirm_I=document.getElementById("Confirm_Info");

	if(str=="email"){
		email_I.innerHTML="<img src='../image/alert.jpg'/>��������ĳ��������ַ����������ĵ�¼�˺ţ�Ҳ�������һ�����";
	}
	if(str=="userName"){
		userName_I.innerHTML="<img src='../image/alert.jpg'/>1-20�����ġ�Ӣ�ġ����֡�-��_��ɣ�������������Ϊ��ͷ��ע��󲻿��޸�";
	}
	if(str=="password"){
		password_I.innerHTML="<img src='../image/alert.jpg'/>1-20λ�ַ�������ʹ�ÿո��û���";
	}
	if(str=="selfDefine"){
		selfDefine_I.innerHTML="<img src='../image/alert.jpg'/>�������Զ������⣬����2���ַ�";
	}
	if(str=="answer"){
		answer_I.innerHTML="<img src='../image/alert.jpg'/>������𰸣�����2���ַ�";
	}
	if(str=="confirm"){
		confirm_I.innerHTML="<img src='../image/alert.jpg'/>��������֤��";
	}

}

function check_2(str){
	var email_I=document.getElementById("Email_Info");
	var userName_I=document.getElementById("UserName_Info");
	var password_I=document.getElementById("Password_Info");
	var answer_I=document.getElementById("Answer_Info");
	var selfDefine_I=document.getElementById("selfDefine_Info");
	var confirm_I=document.getElementById("Confirm_Info");

	
	if(str=="email"){
		var email=document.getElementById("email").value;
		
		//���ܵ����������ɵ����飬���������
		var arr=["ac","com","net","org","edu","gov","mil","ac\.cn","com\.cn","edu\.cn","net\.cn","org\.cn"];
		var temp_str=arr.join("|");
		//ͨ���ַ�������ʽ����������ʽ
		var reg_str="^[0-9a-zA-Z](\\w|-)*@\\w+\\.("+temp_str+")$";
		var reg=new RegExp(reg_str);

		if(reg.test(email)){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"json_checkUserOrEmail.action?email="+email,
				data:"email="+email,
				success:function(data){
					if(data.result==1){
						email_I.innerHTML="<img src='../image/error.gif'/>�������Ѿ�����";
					}else{
						email_I.innerHTML="<img src='../image/true.jpg'/>";
					}
				},
				error:function(){
					email_I.innerHTML="<img src='../image/true.jpg'/>";
				}
			});
		}else{
			email_I.innerHTML="<img src='../image/error.gif'/>�����������ַ";
		}
	}
	if(str=="userName"){
		var userName=document.getElementById("userName").value;
		//var reg_str="^\\w{6,20}$";
		//var reg=new RegExp(reg_str);
		//if(reg.test(userName)){
		if(userName.length>=1&&userName.length<=20){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"json_checkUserOrEmail.action?userName="+userName,
				data:"userName="+userName,
				success:function(data){
					if(data.result==1){
						userName_I.innerHTML="<img src='../image/error.gif'/>���û��Ѿ�����";
					}else{
						userName_I.innerHTML="<img src='../image/true.jpg'/>";
					}
				},
				error:function(){
					userName_I.innerHTML="<img src='../image/true.jpg'/>";
				}
			});
		}else{
			userName_I.innerHTML="<img src='../image/error.gif'/>�������û���";
		}
	}
	if(str=="password"){
		var password=document.getElementById("password").value;
		/*var reg_str="^\\w{6,20}$";
		var reg=new RegExp(reg_str);
		if(reg.test(password)){*/
		if(password.length>=1&&password.length<=20){
			password_I.innerHTML="<img src='../image/true.jpg'/>";
		}else{
			password_I.innerHTML="<img src='../image/error.gif'/>����������";
		}
	}
	if(str=="selfDefine"){
		var selfDefine=document.getElementById("selfDefine").value;
		if(selfDefine.length>=2&&selfDefine.length<=20){
			selfDefine_I.innerHTML="<img src='../image/true.jpg'/>";
		}else{
			selfDefine_I.innerHTML="<img src='../image/error.gif'/>�������Զ�������";
		}
	}
	if(str=="answer"){
		var answer=document.getElementById("answer").value;
		if(answer.length>=2&&answer.length<=20){
			answer_I.innerHTML="<img src='../image/true.jpg'/>";
		}else{
			answer_I.innerHTML="<img src='../image/error.gif'/>�������";
		}
	}
	if(str=="confirm"){
		var confirm=document.getElementById("confirm").value;
		var reg_str="^\\w{4}$";
		var reg=new RegExp(reg_str);
		if(reg.test(confirm)){
			confirm_I.innerHTML="";
		}else{
			confirm_I.innerHTML="<img src='../image/error.gif'/>��������֤��";
		}
	}
	
}

function setVisible(){
	var xyl_1=document.getElementById("xyl_1");
	var xyl_2=document.getElementById("xyl_2");

	var str=document.MyForm.passQuestion.value;
	if(str=="�Զ�������"){
		xyl_1.style.display="block";
		xyl_2.style.display="block";
	}else{
		xyl_1.style.display="none";
		xyl_2.style.display="none";
	}
	
}

function setEndiable(){
	if(document.MyForm.accept.checked){
		document.MyForm.submit.disabled=false;
	}else{
		document.MyForm.submit.disabled=true;
	}
}

function changeImage(){//���»�ȡ��֤�ַ�
	document.getElementById("image").src = document.getElementById("image").src + '?';
	//��������ͼƬ�����¼������ͼƬ��֤��ĸ���,����"a"Ϊ��ʾͼƬ��ID
}



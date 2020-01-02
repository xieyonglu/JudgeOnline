function check(str){
	var email_I=document.getElementById("Email_Info");
	var userName_I=document.getElementById("UserName_Info");
	var password_I=document.getElementById("Password_Info");
	var answer_I=document.getElementById("Answer_Info");
	var selfDefine_I=document.getElementById("selfDefine_Info");
	var confirm_I=document.getElementById("Confirm_Info");

	if(str=="email"){
		email_I.innerHTML="<img src='../image/alert.jpg'/>请输入你的常用邮箱地址，它将是你的登录账号，也可用来找回密码";
	}
	if(str=="userName"){
		userName_I.innerHTML="<img src='../image/alert.jpg'/>1-20个中文、英文、数字、-、_组成，不允许以数字为开头，注册后不可修改";
	}
	if(str=="password"){
		password_I.innerHTML="<img src='../image/alert.jpg'/>1-20位字符，不能使用空格、用户名";
	}
	if(str=="selfDefine"){
		selfDefine_I.innerHTML="<img src='../image/alert.jpg'/>请输入自定义问题，至少2个字符";
	}
	if(str=="answer"){
		answer_I.innerHTML="<img src='../image/alert.jpg'/>请输入答案，至少2个字符";
	}
	if(str=="confirm"){
		confirm_I.innerHTML="<img src='../image/alert.jpg'/>请输入验证码";
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
		
		//可能的域名类别组成的数组，增加灵活性
		var arr=["ac","com","net","org","edu","gov","mil","ac\.cn","com\.cn","edu\.cn","net\.cn","org\.cn"];
		var temp_str=arr.join("|");
		//通过字符串的形式构造正则表达式
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
						email_I.innerHTML="<img src='../image/error.gif'/>该邮箱已经存在";
					}else{
						email_I.innerHTML="<img src='../image/true.jpg'/>";
					}
				},
				error:function(){
					email_I.innerHTML="<img src='../image/true.jpg'/>";
				}
			});
		}else{
			email_I.innerHTML="<img src='../image/error.gif'/>请输入邮箱地址";
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
						userName_I.innerHTML="<img src='../image/error.gif'/>该用户已经存在";
					}else{
						userName_I.innerHTML="<img src='../image/true.jpg'/>";
					}
				},
				error:function(){
					userName_I.innerHTML="<img src='../image/true.jpg'/>";
				}
			});
		}else{
			userName_I.innerHTML="<img src='../image/error.gif'/>请输入用户名";
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
			password_I.innerHTML="<img src='../image/error.gif'/>请输入密码";
		}
	}
	if(str=="selfDefine"){
		var selfDefine=document.getElementById("selfDefine").value;
		if(selfDefine.length>=2&&selfDefine.length<=20){
			selfDefine_I.innerHTML="<img src='../image/true.jpg'/>";
		}else{
			selfDefine_I.innerHTML="<img src='../image/error.gif'/>请输入自定义问题";
		}
	}
	if(str=="answer"){
		var answer=document.getElementById("answer").value;
		if(answer.length>=2&&answer.length<=20){
			answer_I.innerHTML="<img src='../image/true.jpg'/>";
		}else{
			answer_I.innerHTML="<img src='../image/error.gif'/>请输入答案";
		}
	}
	if(str=="confirm"){
		var confirm=document.getElementById("confirm").value;
		var reg_str="^\\w{4}$";
		var reg=new RegExp(reg_str);
		if(reg.test(confirm)){
			confirm_I.innerHTML="";
		}else{
			confirm_I.innerHTML="<img src='../image/error.gif'/>请输入验证码";
		}
	}
	
}

function setVisible(){
	var xyl_1=document.getElementById("xyl_1");
	var xyl_2=document.getElementById("xyl_2");

	var str=document.MyForm.passQuestion.value;
	if(str=="自定义问题"){
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

function changeImage(){//重新获取验证字符
	document.getElementById("image").src = document.getElementById("image").src + '?';
	//单击触发图片重载事件，完成图片验证码的更换,其中"a"为显示图片的ID
}



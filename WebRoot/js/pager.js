//--��ҳר��js--��ʼ--
function jumpPage(pageIndex){
	//alert("��ҳ:"+currentPage+"--"+actionURL+"***"+condition1+"***"+condition2);
	//alert(condition1.value+"====================="+condition2.value);
	var actionURL=$(":input[name='actionURL']").val();
	var condition1=$(":input[name='con1']").val();
	var condition2=$(":input[name='con2']").val();
	//alert(condition1+"====================="+condition2+"-----------------"+actionURL);
	
	var select=document.getElementById("select");//.item(0).value;
	var pageSize=0;
	for(var i=0;i<select.length;i++){
		if(select[i].selected==true){
			pageSize=select[i].value;
			//alert(pageSize);
		}
	}

	//pageSize=document.getElementById("pageSize").value();
	if(actionURL.indexOf("?")!=-1){
		
		location.href=actionURL+"&pageIndex="+pageIndex+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
	}else{
		
		location.href=actionURL+"?pageIndex="+pageIndex+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
	}
	
}

function newPage(){
	
	var n=5;//��ʾ��ť�ĸ���
	var currentPage=parseInt($(":input[name='currentPage']").val());
	var totalPage=parseInt($(":input[name='totalPage']").val());
	
	if(currentPage!=1){
		document.write("<input type='button' class='digitButton' value='��ҳ' onclick='jumpPage("+1+")'>&nbsp;");
		document.write("<input type='button' class='digitButton' value='��һҳ' onclick='jumpPage("+(currentPage-1)+")'>&nbsp;");
	}
	
	if(currentPage!=0){
		if(currentPage>=1&&currentPage<=parseInt((n+1)/2)){
			for(i=1;i<=n&&i<=totalPage;i++){
				if(i==currentPage){
					document.write("<input type='button' class='digitButtonUn' value="+i+" disabled onclick='jumpPage("+i+")'>&nbsp;");
				}else{
					document.write("<input type='button' class='digitButtonEn' value="+i+" onclick='jumpPage("+i+")'>&nbsp;");
				}		
			}
		}else if(currentPage>=parseInt((n+3)/2)&&(currentPage<=parseInt((totalPage-parseInt((n+2)/2))))){
			for(i=currentPage-parseInt((n-1)/2);i<=(currentPage+parseInt(n/2))&&i<=totalPage;i++){
				if(i==currentPage){
					document.write("<input type='button' class='digitButtonUn' value="+i+" disabled onclick='jumpPage("+i+")'>&nbsp;");
				}else{
					document.write("<input type='button' class='digitButtonEn' value="+i+" onclick='jumpPage("+i+")'>&nbsp;");
				}
			}
		}else if(currentPage>=(totalPage-parseInt(n/2))&&(currentPage<=totalPage)){

			for(i=totalPage-n+1;i<=totalPage;i++){

				if(i>=1){//��ֹiС�ڵ���0�����
					if(i==currentPage){
						document.write("<input type='button' class='digitButtonUn' value="+i+" disabled onclick='jumpPage("+i+")'>&nbsp;");
					}else{
						document.write("<input type='button' class='digitButtonEn' value="+i+" onclick='jumpPage("+i+")'>&nbsp;");
					}
				}
			}
		}	
	}
	//alert(currentPage+"===="+totalPage);
	if(currentPage!=totalPage){
		//alert(currentPage+"---"+totalPage);
		
		document.write("<input type='button' class='digitButton' value='��һҳ' onclick='jumpPage("+(parseInt(currentPage)+1)+")'/>&nbsp;");
		document.write("<input type='button' class='digitButton' value='βҳ' onclick='jumpPage("+totalPage+")'/>&nbsp;");
	}
}
//��ҳר��js--����
$(function(){
	
	//onkeyup="if(isNaN(value)) execCommand('undo')"
	//onafterpaste="if(isNaN(value))execCommand('undo')"
	$(":input[name='go']").keyup(function(){
		if(isNaN(this.value)){
			//$(":input[name='go']").val("");
			//�Ȱѷ����ֵĶ��滻�����������ֺ�.  
			this.value = this.value.replace(/[^\d.]/g,"");  
			//���뱣֤��һ��Ϊ���ֶ�����.  
			this.value = this.value.replace(/^\./g,"");  
			//��ֻ֤�г���һ��.��û�ж��.  
			this.value = this.value.replace(/\.{2,}/g,".");  
			//��֤.ֻ����һ�Σ������ܳ����������� 
			this.value = this.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		}
	});
	
	$("#go").click(function(){
		var currentPage=$(":input[name='go']").val();
		var pageSize=$(":input[name='pageSize']").val();
		var condition1=$(":input[name='con1']").val();
		var condition2=$(":input[name='con2']").val();
		var actionURL=$(":input[name='actionURL']").val();
		
		if(actionURL.indexOf("?")!=-1){		
			location.href=actionURL+"&pageIndex="+currentPage+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
		}else{
			location.href=actionURL+"?pageIndex="+currentPage+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
		}
	});
	
});

$(function(){
	$("#inquire").click(function(){
		
		var condition1=$(":input[name='condition1']").val();
		var condition2=$(":input[name='condition2']").val();
		var actionURL=$(":input[name='actionURL']").val();
		
		var pageSize=$(":input[name='select']").val();
		var currentPage=$(":input[name='currentPage']").val();
		var totalPage=$(":input[name='totalPage']").val();
		
		if(actionURL.indexOf("?")!=-1){	
			location.href=actionURL+"&pageIndex="+currentPage+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
		}else{
			location.href=actionURL+"?pageIndex="+currentPage+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
		}
		
	});
});

$(function(){
	$("#select").change(function(){
		var pageSize=$(":input[name='select']").val();
		var condition1=$(":input[name='condition1']").val();
		var condition2=$(":input[name='condition2']").val();
		var actionURL=$(":input[name='actionURL']").val();
		var currentPage=$(":input[name='currentPage']").val();
		var totalPage=$(":input[name='totalPage']").val();
		
		//alert("---"+pageSize);
		if(actionURL.indexOf("?")!=-1){
			location.href=actionURL+"&pageIndex="+currentPage+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
		}else{
			location.href=actionURL+"?pageIndex="+currentPage+"&pageSize="+pageSize+"&condition1="+condition1+"&condition2="+condition2;
		}
		
	});
});



//ҳ��ռ���ʱ����ʼ��һЩ����
$(function(){
	//alert("����������������������");
	var pageSize=$(":input[name='pageSize']").val();
	//alert("---"+pageSize);
	if(pageSize!=null){
		$("#select").val(pageSize);
	}
	
});
//--------------------------------------------------------------------
//ȫѡ,ȫ��ѡ�뷴ѡ
$(function(){
	
	//ȫѡ��ȫ��ѡ
	$("#selectAll").click(function(){
		if($(this).attr("checked")){
			//alert("ѡ��");
			$(":input[name='checkList']").attr("checked",true);
		}else{
			//alert("û��ѡ��");
			$(":input[name='checkList']").attr("checked",false);
		}
		
	});
	
	//��ѡ
	$("#reverse").click(function(){
		$(":input[name='checkList']").each(function(){
			$(this).attr("checked",!$(this).attr("checked"));
		});
	});
	
});

//��������
function batchDeal(urlAction){
	
	var pageNumber=$("#currentPage").val();
	var pageSize=$("#pageSize").val();
	var str="";
	var count=0;
	var check_boxes=document.getElementsByName("checkList");
	
	for(var i=0;i<check_boxes.length;i++){
		if(check_boxes[i].type=="checkbox"&&check_boxes[i].checked){
			str=str+check_boxes[i].value+"-";
			count++;
		}
	}
	if(count!=0){
		location.href=urlAction+"&str="+str;
	}else{
		alert("��ѡ��Ҫ����ļ�¼��");
	}
}

function batchDelete(urlAction){
	//alert("---------"+urlAction);
	
	var pageNumber=$("#currentPage").val();
	var pageSize=$("#pageSize").val();
	var str="";
	var count=0;
	var check_boxes=document.getElementsByName("checkList");
	//alert("length="+check_boxes.length);
	for(var i=0;i<check_boxes.length;i++){
		if(check_boxes[i].type=="checkbox"&&check_boxes[i].checked){
			str=str+check_boxes[i].value+"-";
			count++;
		}
	}
	//alert(str+"---"+count);
	if(count!=0){
		if(confirm("ȷ��ɾ����"+count+"����¼")){
			//alert("ɾ��");
			if(urlAction.indexOf("?")!=-1){
				//alert(urlAction+"&str="+str);
				location.href=urlAction+"&str="+str;
			}else{
				//alert(urlAction+"?str="+str);
				location.href=urlAction+"?str="+str;
			}
			
		}else{
			//alert("��ɾ��");
		}
		
	}else{
		alert("��ѡ��Ҫɾ���ļ�¼��");
	}
}
$(function(){
	$("#batchDelete").click(function(){
		
		var pageNumber=$("#currentPage").val();
		var pageSize=$("#pageSize").val();
		var str="";
		var count=0;
		var check_boxes=document.getElementsByName("checkList");
		alert("length="+check_boxes.length);
		for(var i=0;i<check_boxes.length;i++){
			if(check_boxes[i].type=="checkbox"&&check_boxes[i].checked){
				str=str+check_boxes[i].value+"-";
				count++;
			}
		}
		alert(str+"---"+count);
		if(count!=0){
			if(confirm("ȷ��ɾ����"+count+"����¼")){
				alert("ɾ��");
				//location.href="user_batchDelete.action?str="+str;
			}else{
				alert("��ɾ��");
			}
			
		}else{
			alert("��ѡ��Ҫɾ���ļ�¼��");
		}
	});
});

//---------------------ϵͳ�ṩ�ķ���
//ȫѡ
function selectAll(){
	var arrObj=document.all;
	for(var i=0;i<arrObj.length;i++){
		if(typeof arrObj[i].type!="undefined"&&arrObj[i].type=='checkbox')
			arrObj[i].checked=true;
	}  
}

//ȫ��ѡ
function  unSelectAll(){
	var arrObj=document.all;
	for(var i=0;i<arrObj.length;i++){
		if(typeof arrObj[i].type!="undefined"&&arrObj[i].type=='checkbox')
			arrObj[i].checked=false;
	}
}

//��ѡ
function reverseSelectAll(){
	var arrObj=document.all;
	for(var i=0;i<arrObj.length;i++){
		if(typeof arrObj[i].type!="undefined"&&arrObj[i].type=='checkbox'){
			arrObj[i].checked=!arrObj[i].checked;
		}
	} 
}















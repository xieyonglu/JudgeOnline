//--分页专用js--开始--
function jumpPage(pageIndex){
	//alert("分页:"+currentPage+"--"+actionURL+"***"+condition1+"***"+condition2);
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
	
	var n=5;//显示按钮的个数
	var currentPage=parseInt($(":input[name='currentPage']").val());
	var totalPage=parseInt($(":input[name='totalPage']").val());
	
	if(currentPage!=1){
		document.write("<input type='button' class='digitButton' value='首页' onclick='jumpPage("+1+")'>&nbsp;");
		document.write("<input type='button' class='digitButton' value='上一页' onclick='jumpPage("+(currentPage-1)+")'>&nbsp;");
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

				if(i>=1){//防止i小于等于0的情况
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
		
		document.write("<input type='button' class='digitButton' value='下一页' onclick='jumpPage("+(parseInt(currentPage)+1)+")'/>&nbsp;");
		document.write("<input type='button' class='digitButton' value='尾页' onclick='jumpPage("+totalPage+")'/>&nbsp;");
	}
}
//分页专用js--结束
$(function(){
	
	//onkeyup="if(isNaN(value)) execCommand('undo')"
	//onafterpaste="if(isNaN(value))execCommand('undo')"
	$(":input[name='go']").keyup(function(){
		if(isNaN(this.value)){
			//$(":input[name='go']").val("");
			//先把非数字的都替换掉，除了数字和.  
			this.value = this.value.replace(/[^\d.]/g,"");  
			//必须保证第一个为数字而不是.  
			this.value = this.value.replace(/^\./g,"");  
			//保证只有出现一个.而没有多个.  
			this.value = this.value.replace(/\.{2,}/g,".");  
			//保证.只出现一次，而不能出现两次以上 
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



//页面刚加载时，初始化一些数据
$(function(){
	//alert("启动，，，，，，，，，");
	var pageSize=$(":input[name='pageSize']").val();
	//alert("---"+pageSize);
	if(pageSize!=null){
		$("#select").val(pageSize);
	}
	
});
//--------------------------------------------------------------------
//全选,全不选与反选
$(function(){
	
	//全选与全不选
	$("#selectAll").click(function(){
		if($(this).attr("checked")){
			//alert("选中");
			$(":input[name='checkList']").attr("checked",true);
		}else{
			//alert("没有选中");
			$(":input[name='checkList']").attr("checked",false);
		}
		
	});
	
	//反选
	$("#reverse").click(function(){
		$(":input[name='checkList']").each(function(){
			$(this).attr("checked",!$(this).attr("checked"));
		});
	});
	
});

//批量处理
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
		alert("请选择要处理的记录！");
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
		if(confirm("确定删除这"+count+"条记录")){
			//alert("删除");
			if(urlAction.indexOf("?")!=-1){
				//alert(urlAction+"&str="+str);
				location.href=urlAction+"&str="+str;
			}else{
				//alert(urlAction+"?str="+str);
				location.href=urlAction+"?str="+str;
			}
			
		}else{
			//alert("不删除");
		}
		
	}else{
		alert("请选择要删除的记录！");
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
			if(confirm("确定删除这"+count+"条记录")){
				alert("删除");
				//location.href="user_batchDelete.action?str="+str;
			}else{
				alert("不删除");
			}
			
		}else{
			alert("请选择要删除的记录！");
		}
	});
});

//---------------------系统提供的方法
//全选
function selectAll(){
	var arrObj=document.all;
	for(var i=0;i<arrObj.length;i++){
		if(typeof arrObj[i].type!="undefined"&&arrObj[i].type=='checkbox')
			arrObj[i].checked=true;
	}  
}

//全不选
function  unSelectAll(){
	var arrObj=document.all;
	for(var i=0;i<arrObj.length;i++){
		if(typeof arrObj[i].type!="undefined"&&arrObj[i].type=='checkbox')
			arrObj[i].checked=false;
	}
}

//反选
function reverseSelectAll(){
	var arrObj=document.all;
	for(var i=0;i<arrObj.length;i++){
		if(typeof arrObj[i].type!="undefined"&&arrObj[i].type=='checkbox'){
			arrObj[i].checked=!arrObj[i].checked;
		}
	} 
}















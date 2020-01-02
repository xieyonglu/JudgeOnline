//表示当前高亮的节点
var highlightindex = -1;
var timeoutId;

$(document).ready(function(){
	
    var wordInput = $("#word");
    var wordInputOffset = wordInput.offset();
    
    //隐藏自动补全框
    $("#auto").hide().css("border","1px black solid").css("position","relative")
    .css("top",wordInputOffset.top+wordInput.height()+5+"px")
    .css("left",wordInputOffset.left)
    .css("z-index",100)
    .css("position","absolute")
    .css("background","white")
    .width(wordInput.width()+2);
    
    //添加键盘按下并弹起的事件
    wordInput.keyup(function(event){
        //处理文本框中的键盘事件
        var myEvent = event||window.event;
        var keyCode = myEvent.keyCode;
        
        //如果输入的是字母，退格，delete，空格或者数字键，应该将文本框中的最新信息发送给服务器
        if((keyCode >= 65 && keyCode<=90) || (keyCode >= 48 && keyCode <= 57) ||(keyCode>=96 && keyCode<=105) || keyCode == 46 || keyCode == 8 || keyCode == 32){
            
            //获取文本框的内容
            var wordText = $("#word").val();
            var autoNode = $("#auto");
            
            if(wordText!=""){
	            //将文本框中的内容发送到服务器端
	            //对上次未完成的延时操作进行取消
	            clearTimeout(timeoutId);
	            //对于服务器端进行交互延迟500ms，避免快速打字造成的频繁请求
	            timeoutId = setTimeout(function(){
	            	$.post("notice_auto.action",{word:escape(wordText)},function(data){
		                //将dom对象data转换成JQuery的对象
		                var jqueryObj = $(data);
		                //先找到所有的word节点
		                var wordNodes = jqueryObj.find("word");
		                //遍历所有的word节点，取出单词内容将单词内容添加到弹出框中
		                
		                autoNode.html(" ");
		                wordNodes.each(function(i){
		                    //获取单词内容
		                    var wordNode = $(this);
		                    //新建div节点将单词内容加入到新建的节点中,将新建的节点加入到弹出框的节点中
		                    var newDivNode = $("<div>").attr("id",i);
		                    //newDivNode.html(wordNode.text()).appendTo(autoNode);
		                    
		                    var xyl=wordNode.text().substr(0,wordText.length)+"<b>"+wordNode.text().substr(wordText.length)+"</b>";
		                    newDivNode.html(xyl).appendTo(autoNode);
		                    newDivNode.css("padding-left","10px").css("padding-top","5px").css("padding-bottom","5px");
		                    
		                    //增加鼠标进入事件，高亮节点;
		                    newDivNode.mouseover(function(){
		                        if(highlightindex != -1){
		                            $("#auto").children("div").eq(highlightindex).css("background-color","white");
		                        }
		                        highlightindex = $(this).attr("id");
		                        $(this).css("background-color","gray");
		                        
		                        $("#word").val($("#auto").children("div").eq(highlightindex).text());
		                    });
		                    
		                    //增加鼠标移出事件，取消当前高亮节点
		                    newDivNode.mouseout(function(){
		                        $(this).css("background-color","white");
		                    });
		                    
		                    //增加鼠标点击事件，可以进行补全
		                    newDivNode.click(function(){
		                        var comText = $(this).text();
		                        $("#auto").hide();
		                        highlightindex=-1;
		                        $("#word").val(comText);            
		                    });
		                       
		                });
		                //如果服务器端有数据返回，则显示弹出框
		                if(wordNodes.length>0){
		                    autoNode.show();
		                }else{
		                	autoNode.hide();
		                	highlightindex=-1;
		                } 
	            	},"xml");
	            },500);
            
            }else{
                autoNode.hide();
                highlightindex=-1;
            }
        }else if(keyCode == 38 || keyCode==40){
        	
        	var autoNodes = $("#auto").children("div");
        	
            //如果输入的是向上38向下40按键
            if(keyCode == 38){
                //up
            	//autoNodes = $("#auto").children("div");
                if(highlightindex !=-1){
                    autoNodes.eq(highlightindex).css("background-color","white");
                    highlightindex--;
                }else{
                    highlightindex = autoNodes.length -1;
                }
                
                if(highlightindex == -1){
                    //如果修改索引值以后index变成-1，则将索引中指向最后一个元素
                    highlightindex = autoNodes.length -1;
                }
                //让现在被高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color","gray");
            }
            if(keyCode == 40){
                //down
                //var autoNodes = $("#auto").children("div");
                if(highlightindex !=-1){
                    autoNodes.eq(highlightindex).css("background-color","white");
                }
                highlightindex++;
                if(highlightindex == autoNodes.length){
                    //如果修改索引值以后index变成-1，则将索引中指向最后一个元素
                    highlightindex = 0;
                }
                //让现在被高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color","gray");
            }
            
            $("#word").val(autoNodes.eq(highlightindex).text());
            
        }else if(keyCode == 13){
            //如果按下的是回车
            //下拉框有高亮的内容
            if(highlightindex !=-1){
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();
                highlightindex=-1;
                $("#word").val(comText);
            }
            //下拉框没有高亮的内容
            else{
                $("#auto").hide();
                //让文本框失去焦点
                $("#word").get(0).blur();
            }
        }
        
    });
    
    //添加文本域失去焦点事件
    wordInput.blur(function(event){
    	$("#auto").hide();
    });
    
})

               
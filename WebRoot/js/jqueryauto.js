//��ʾ��ǰ�����Ľڵ�
var highlightindex = -1;
var timeoutId;

$(document).ready(function(){
	
    var wordInput = $("#word");
    var wordInputOffset = wordInput.offset();
    
    //�����Զ���ȫ��
    $("#auto").hide().css("border","1px black solid").css("position","relative")
    .css("top",wordInputOffset.top+wordInput.height()+5+"px")
    .css("left",wordInputOffset.left)
    .css("z-index",100)
    .css("position","absolute")
    .css("background","white")
    .width(wordInput.width()+2);
    
    //��Ӽ��̰��²�������¼�
    wordInput.keyup(function(event){
        //�����ı����еļ����¼�
        var myEvent = event||window.event;
        var keyCode = myEvent.keyCode;
        
        //������������ĸ���˸�delete���ո�������ּ���Ӧ�ý��ı����е�������Ϣ���͸�������
        if((keyCode >= 65 && keyCode<=90) || (keyCode >= 48 && keyCode <= 57) ||(keyCode>=96 && keyCode<=105) || keyCode == 46 || keyCode == 8 || keyCode == 32){
            
            //��ȡ�ı��������
            var wordText = $("#word").val();
            var autoNode = $("#auto");
            
            if(wordText!=""){
	            //���ı����е����ݷ��͵���������
	            //���ϴ�δ��ɵ���ʱ��������ȡ��
	            clearTimeout(timeoutId);
	            //���ڷ������˽��н����ӳ�500ms��������ٴ�����ɵ�Ƶ������
	            timeoutId = setTimeout(function(){
	            	$.post("notice_auto.action",{word:escape(wordText)},function(data){
		                //��dom����dataת����JQuery�Ķ���
		                var jqueryObj = $(data);
		                //���ҵ����е�word�ڵ�
		                var wordNodes = jqueryObj.find("word");
		                //�������е�word�ڵ㣬ȡ���������ݽ�����������ӵ���������
		                
		                autoNode.html(" ");
		                wordNodes.each(function(i){
		                    //��ȡ��������
		                    var wordNode = $(this);
		                    //�½�div�ڵ㽫�������ݼ��뵽�½��Ľڵ���,���½��Ľڵ���뵽������Ľڵ���
		                    var newDivNode = $("<div>").attr("id",i);
		                    //newDivNode.html(wordNode.text()).appendTo(autoNode);
		                    
		                    var xyl=wordNode.text().substr(0,wordText.length)+"<b>"+wordNode.text().substr(wordText.length)+"</b>";
		                    newDivNode.html(xyl).appendTo(autoNode);
		                    newDivNode.css("padding-left","10px").css("padding-top","5px").css("padding-bottom","5px");
		                    
		                    //�����������¼��������ڵ�;
		                    newDivNode.mouseover(function(){
		                        if(highlightindex != -1){
		                            $("#auto").children("div").eq(highlightindex).css("background-color","white");
		                        }
		                        highlightindex = $(this).attr("id");
		                        $(this).css("background-color","gray");
		                        
		                        $("#word").val($("#auto").children("div").eq(highlightindex).text());
		                    });
		                    
		                    //��������Ƴ��¼���ȡ����ǰ�����ڵ�
		                    newDivNode.mouseout(function(){
		                        $(this).css("background-color","white");
		                    });
		                    
		                    //����������¼������Խ��в�ȫ
		                    newDivNode.click(function(){
		                        var comText = $(this).text();
		                        $("#auto").hide();
		                        highlightindex=-1;
		                        $("#word").val(comText);            
		                    });
		                       
		                });
		                //����������������ݷ��أ�����ʾ������
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
        	
            //��������������38����40����
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
                    //����޸�����ֵ�Ժ�index���-1����������ָ�����һ��Ԫ��
                    highlightindex = autoNodes.length -1;
                }
                //�����ڱ����������ݱ�ɺ�ɫ
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
                    //����޸�����ֵ�Ժ�index���-1����������ָ�����һ��Ԫ��
                    highlightindex = 0;
                }
                //�����ڱ����������ݱ�ɺ�ɫ
                autoNodes.eq(highlightindex).css("background-color","gray");
            }
            
            $("#word").val(autoNodes.eq(highlightindex).text());
            
        }else if(keyCode == 13){
            //������µ��ǻس�
            //�������и���������
            if(highlightindex !=-1){
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();
                highlightindex=-1;
                $("#word").val(comText);
            }
            //������û�и���������
            else{
                $("#auto").hide();
                //���ı���ʧȥ����
                $("#word").get(0).blur();
            }
        }
        
    });
    
    //����ı���ʧȥ�����¼�
    wordInput.blur(function(event){
    	$("#auto").hide();
    });
    
})

               
function checkboxselect(itemname,checkstatus) { 
	if(!itemname) 
		return; 
	if(!itemname.length) {  
		itemname.checked=checkstatus; 
	} else {  
		for(var i=0;i<itemname.length;i++) {   
			itemname[i].checked=checkstatus;  
		} 
	}
}

/*
judge the checkbox if checked
@author Created by zhousp(gzzhousp@gmail.com) 2006.10.30 11:43
*/
function hasChecked(itemname){
	if(!itemname)return;
	var intcount = itemname.length;
	if(itemname.length){
		for(var i=0;i<intcount;i++) {   
			if(itemname[i].checked){
				return true;
			}  
		} 
	}else{
		if(itemname.checked){
			return true
		}	
	}
	return false;
}
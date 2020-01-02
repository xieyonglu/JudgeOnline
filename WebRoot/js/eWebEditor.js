var URLParams = new Object() ;
var aParams = document.location.search.substr(1).split('&') ;
for (i=0 ; i < aParams.length ; i++) {
	var aParam = aParams[i].split('=') ;
	URLParams[aParam[0]] = aParam[1] ;
}

URLParams["style"] = (URLParams["style"]) ? URLParams["style"].toLowerCase() : "popup";

var objField=eval("opener.document."+URLParams["form"] + "." + URLParams["field"]);

function doSave(){
	objField.value = eWebEditor1.getHTML();
	self.close();
}

function setValue(){
	try{
		if (eWebEditor1.bInitialized){
			eWebEditor1.setHTML(objField.value);
		}else{
			setTimeout("setValue();",1000);
		}
	}
	catch(e){
		setTimeout("setValue();",1000);
	}
}


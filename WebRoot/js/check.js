//������ո�
function left_trim(str){
	var i;
	for(i=0;i<str.length;i++){
		if(str.charAt(i)!=" ")
		break;
	}
	str=str.substring(i,str.length);
	return str;
}
//����Ҳ�ո�
function right_trim(str){
	var i;
	for(i=str.length-1;i>=0;i--){
		if(str.charAt(i)!=" ")
		break;
	}
	str=str.substring(0,i+1);
	return str;
}

//������ҿո�
function trim(str){
	return left_trim(right_trim(str));
}

//�ж��ַ����Ƿ�Ϊ����
function is_integer(str){
	var integer_str="0123456789";
	for(var i=0;i<str.length;i++){
		if(integer_str.indexOf(str.charAt(i))==-1){
			return false;
		}
	}
	return true;
}


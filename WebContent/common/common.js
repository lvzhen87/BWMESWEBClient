function openPage(url,subPageName ){	
	var sFeature = "dialogWidth:800px;dialogHeight:450px;status:0;resizable:1;help:0";
	window.open(url,subPageName,"width=800px,heigth=450px");
}

function openDialog(url){
	var sFeature = "dialogWidth:"+ screen.width +"px;dialogHeight:"+(screen.height - 100 )+"px;status:no;resizable:no;help:no;";
 	var parameters = new Array();  
	/*
	 * parameters.id  = 1;
	parameters.refid   = 2;
	parameters.bizname = 3;
	*/
	
	var result = window.showModalDialog(url,parameters,sFeature);
	return result
	/*  
	  var obj = window.dialogArguments;
      id = obj.id;
      docid = obj.refid;
      bizname = obj.bizname;
      issave = obj.issave;
      */ 
}	

function openFlowPage(url,parameters){
	var sFeature = "dialogWidth:"+screen.width+"px;dialogHeight:"+screen.height+"px;status:no;resizable:no;help:no;";
	var result = window.showModalDialog(url,parameters,sFeature);
	return result;
	
}


function openRepairTimePage(url,widthPara,heightPara){
	var sFeature = "dialogWidth:"+ widthPara +"px;dialogHeight:"+ heightPara +"px;status:no;resizable:no;help:no;";
 	var parameters = new Array();  
	var result = window.showModalDialog(url,parameters,sFeature);
	return result
}	


function closePage(){
	 
	var parameters = new Array();  
	parameters.id  = 1;
	parameters.refid   = 2;
	parameters.bizname = 3;
	window.returnValue = parameters ;//1;  	var result = window.showModalDialog(url,parameters,sFeature);  alert(result.bizname) ȡ����ֵ
	
	
}

function convertRequestUrl(requestUrl){
	if( requestUrl.indexOf("=")>0){
		requestUrl = requestUrl +"&data="+new Date();
	}else  if(requestUrl.indexOf("?")>0  ){
		requestUrl = requestUrl +"data="+new Date();
    }else{
    	requestUrl = requestUrl +"?data="+new Date();
    }
	return requestUrl;
	
}

function ajaxCall(requestUrl){
	requestUrl = convertRequestUrl(requestUrl)
	$.ajax({
		  url: requestUrl,
		
		  success: callback
	});
}

function getSelectTagText(selectTagName){
	
	SelectTagObj = document.getElementById(selectTagName);	
	
    selectIndex  = SelectTagObj.selectedIndex;
	
    selectValue  = SelectTagObj.options[ selectIndex ].text
  
    return  selectValue

}

function showErrorMsg(errorMsg){
	alert(errorMsg);
}


function clone(Obj) {   
    var buf;   
    if (Obj instanceof Array) {   
        buf = [];  
        var i = Obj.length;   
        while (i--) {   
            buf[i] = clone(Obj[i]);   
        }   
        return buf;   
    }else if (Obj instanceof Object){   
        buf = {};  
        for (var k in Obj) {  
            buf[k] = clone(Obj[k]);   
        }   
        return buf;   
    }else{   
        return Obj;   
    }   
} 

/*
 * 
 * percentNumber > standardNumber  ���� 1
 * percentNumber == standardNumber  ���� 0
 * percentNumber < standardNumber  ����  -1
 * */
function comparePercent(percentNumber , standardNumber ){
	if(percentNumber !="" && percentNumber !=null && standardNumber != null && standardNumber != "")
	{
		index= percentNumber.indexOf("%");
		if( index < 0 ){
			index = percentNumber.length;
		}
		percentStr= percentNumber.substring(0,index);
        ftpFloat = parseFloat(percentStr);
	   
	    index1= standardNumber.indexOf("%");
	    if( index1 < 0 ){
			index1 = standardNumber.length;
		}
	    standardStr= standardNumber.substring(0,index1);
        standardFloat = parseFloat(standardStr);
	   
		if(ftpFloat> standardFloat ){
		   	return 1;
		}else if( ftpFloat == standardFloat ) {
			return 0;
		}else {
			return -1
		}
	}						    
}

function ajaxAsyncCallObject(requestUrl){
	requestUrl = convertRequestUrl(requestUrl)
	isRepeat =$.ajax({
	   url: requestUrl
	   ,async:false
	});
	resultDataList=eval(isRepeat.responseText)
	return resultDataList[0];
}

function ajaxAsyncCallList(requestUrl){
	requestUrl = convertRequestUrl(requestUrl)
	isRepeat =$.ajax({
	   url: requestUrl
	   ,async:false
	});
	resultDataList=eval(isRepeat.responseText)
	return resultDataList;
}


function isNumber(txt){
     var re = /^(\d)*$/g;
     return re.test(txt);
}

function isLetter(txt){
    var re = /^([a-z]|[_]|[A-Z])+\d*$/g;
    return re.test(txt);
}

function isFloat(txt){
    var re = /^[1-9]+(\d)*\.?(\d)*$/g;
    return re.test(txt);
}

function trimAll(strSrc){
	return  stringReplace(	strSrc,/\s+/,"");
}

function stringReplace(strSrc,re,replaceWord){
	return strSrc.replace(re,replaceWord );

}



function getWeek(now){
	
	var day=now.getDay();
	week = "";
	switch (day){
		case 1:
		week="星期一";
		break;
		case 2:
		week="星期二";
		break;
		case 3:
		week="星期三";
		break;
		case 4:
		week="星期四";
		break;
		case 5:
		week="星期五";
		break;
		case 6:
		week="星期六";
		break; 
		default:
		week="星期日"
		break;
	}
	return week;
}




function setSelectedOptionByText(selectID ,showText  ){
	 
	 
	memberqueston = document.getElementById(selectID)
 
	for( i = 0 ; i< memberqueston.options.length  ; i++)
	{				 
		
		if(memberqueston.options[i].innerHTML == showText){
				memberqueston.options[i].selected = true
		}
	 
	}
}


function getSelectOptions(selectTagName){
	
	SelectTagObj = document.getElementById(selectTagName);	
	
    selectIndex  = SelectTagObj.selectedIndex;
	
    selectValue  = SelectTagObj.options[ selectIndex ].text
  
    return  selectValue

}

function getSelectOptionsValue(selectTagName){
	
	SelectTagObj = document.getElementById(selectTagName);	
	
    selectIndex  = SelectTagObj.selectedIndex;
	
    selectValue  = SelectTagObj.options[ selectIndex ].value;
  
    return  selectValue

}
function printBarcode(vin){
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	var tf = fso.CreateTextFile("C://output.prn", true);
	tf.Write(vin);
	tf.Close();
	tf = fso.CreateTextFile("c:/printBC.bat", true);
	str = "copy c:\\Output.prn COM1";
	tf.WriteLine(str);
	tf.Close();

	var objShell = new ActiveXObject("WScript.Shell");
	var iReturnCode = objShell.Run("c:/printBC.bat", 0, true);
}


function playErrorMusic() 
{ 
	//document.all.bgs.src="Music/scanPartError.mp3"; 
	document.all.bgs.src="Music/smsb.wav"; 
} 

function playOKMusic() 
{ 
	//document.all.bgs.src="Music/scanPartOK.mp3";
	document.all.bgs.src="Music/smcg.wav";
}
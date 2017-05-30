	var htmlData = "";
	var orginalHtmlData = "";
	var code1List;

	function getCode1List() {
		 
//		  $.post("defect.sp?toListCode1" , function(result1) {
//			  alert( $( result1 ).result);
//		  });
			 
		
//		  jQuery.ajax({ 
//			  url: "defect.sp?toListCode1", 
//			  dataType: "json", 
//			  success: function(results) { 
//			  alert(results.name); 
//			  } });

	
		  jQuery.ajax({ 
		  url: "defect.sp?toListCode1", 
		  async: false,
		  success: function(data) { 
//		  var parsedJson = jQuery.parseJSON(data); 
			  var map=eval('('+data+')');
			  var optionstring = "";
			  var i;
//			  alert(map.result[0].codeLevel1); 
			  for (i=0; i<map.result.length; i++) {
                  var array = map.result[i];
//                  alert(array);
                  
//                  for (var j = 0; j < jsonObj.length; j++) {
                	  
                      optionstring += "<option value="+array.codeLevel1+">" + array.codeLevel1 + "</option>";
//                  }
                 
              }
//			  $("#codeLevel1Group").html("<li class='list-group-item'>请选择...</option> "+optionstring);
			  $("#codeLevel1Group").html(optionstring);

		  } });

		//alert("aa");
		/*$("#codeLevel1Group").ul({

			//minimumInputLength: 1,
			placeholder : '请选择一个表单',
			ajax : {
				delay : 250,
				url : "defect.sp?toListCode1",
				dataType : 'json',
				processResults : function(data) {
					alert(data.result);
					return {
						
						results : data.result
					};
				},
				cache : false
			},
			escapeMarkup : function(markup) {
				return markup;
			}
		});*/
	}
	function getListLevel1() {

		  jQuery.ajax({ 
		  url: "defect.sp?toListLevel1", 
		  async: false,
		  success: function(data) { 
//			  alert(data);
//		  var parsedJson = jQuery.parseJSON(data); 
			  var map=eval('('+data+')');
//			  alert(map);
			  var optionstring = "";
			  var i;
			  for (i=0; i<map.result.length; i++) {
                var array = map.result[i];
//                alert(array.level1);
                 optionstring += "<option value="+array.level1+">" + array.level1 + "</option>";

            }
			  $("#selLevel1").html(optionstring);

		  } });

	}
	
	function getListLevel2(level1) {

		jQuery.ajax({ 
			  type: 'POST',
			  async: false,
			  url: "defect.sp?toListLevel2", 
			  data: [{name:"level1",value:level1}],
			  
			  success: function(data) { 
//				  alert(data);
				  var map=eval('('+data+')');
				  
				  var optionstring = "";
				  var i;
				  for (i=0; i<map.result.length; i++) {
	                var array = map.result[i];             	  
	                    optionstring += "<option value="+array.level2+">" + array.level2 + "</option>";
	               
	            }
				  $("#selLevel2").html(optionstring);

			  } });

	}
	
	function getListLevel3(level1, level2) {

		jQuery.ajax({ 
			  type: 'POST',
			  async: false,
			  url: "defect.sp?toListLevel3", 
			  data: [{name:"level1",value:level1}, {name:"level2",value:level2}],
			  
			  success: function(data) { 
//				  alert(data);
				  var map=eval('('+data+')');
				  
				  var optionstring = "";
				  var i;
				  for (i=0; i<map.result.length; i++) {
	                var array = map.result[i];             	  
	                    optionstring += "<option value="+array.level3+">" + array.level3 + "</option>";
	               
	            }
				  $("#selLevel3").html(optionstring);

			  } });

	}
	
	function getListLevel4(level1, level2, level3) {

		jQuery.ajax({ 
			  type: 'POST',
			  async: false,
			  url: "defect.sp?toListLevel4", 
			  data: [{name:"level1",value:level1}, {name:"level2",value:level2}, {name:"level3",value:level3}],
			  
			  success: function(data) { 
//				  alert(data);
				  var map=eval('('+data+')');
				  
				  var optionstring = "";
				  var i;
				  for (i=0; i<map.result.length; i++) {
	                var array = map.result[i];             	  
	                    optionstring += "<option value="+array.level4+">" + array.level4 + "</option>";
	               
	            }
				  $("#selLevel4").html(optionstring);

			  } });

	}
	
	function getListLevel5(level1, level2, level3, level4) {

		jQuery.ajax({ 
			  type: 'POST',
			  async: false,
			  url: "defect.sp?toListLevel5", 
			  data: [{name:"level1",value:level1}, {name:"level2",value:level2}, {name:"level3",value:level3}, {name:"level4",value:level4}],
			 
			  success: function(data) { 
//				  alert(data);
				  var map=eval('('+data+')');
				  
				  var optionstring = "";
				  var i;
				  for (i=0; i<map.result.length; i++) {
	                var array = map.result[i];             	  
	                    optionstring += "<option value="+array.level5+">" + array.level5 + "</option>";
	               
	            }
				  $("#selLevel5").html(optionstring);

			  } });

	}
	
	function getCode2List(code1) {

		jQuery.ajax({ 
		  type: 'POST',
		  async: false,
		  url: "defect.sp?toListCode2", 
		  data: [{name:"codeLevel1",value:code1}],
		  
		  success: function(data) { 
//			  alert(data);
			  var map=eval('('+data+')');
			  
			  var optionstring = "";
			  var i;
			  for (i=0; i<map.result.length; i++) {
                var array = map.result[i];             	  
                    optionstring += "<option value="+array.codeLevel2+">" + array.codeLevel2 + "</option>";
               
            }
			  $("#codeLevel2Group").html(optionstring);

		  } });

	}
	
	function getPrev(currentVIN) {

		jQuery.ajax({ 
		  type: 'POST',
		  async: false,
		  url: "defect.sp?toPrevVIN", 
		  data: [{name:"currentvin",value:currentVIN}],
		  
		  success: function(data) { 
//			  alert(data);
			  var map=eval('('+data+')');
			  
			  var optionstring = "";
			  var i;
			  for (i=0; i<map.result.length; i++) {
                var array = map.result[i];    
//                alert(array.prevvin);
                    optionstring += array.prevvin;
               
            }
			  $("#vin").val(optionstring);

		  } });

	}

	
	function getNext(currentVIN) {

		jQuery.ajax({ 
		  type: 'POST',
		  async: false,
		  url: "defect.sp?toNextVIN", 
		  data: [{name:"currentvin",value:currentVIN}],
		  
		  success: function(data) { 
//			  alert(data);
			  var map=eval('('+data+')');
			  
			  var optionstring = "";
			  var i;
			  for (i=0; i<map.result.length; i++) {
                var array = map.result[i];    
//                alert(array.nextvin);
                    optionstring += array.nextvin;
               
            }
			  $("#vin").val(optionstring);

		  } });

	}

	
	$(document).ready(function() {
		getCode1List();
		getListLevel1();				
	});
	
	$("#codeLevel1Group").change(function(){
		var code1=$("#codeLevel1Group").val(); 
//		alert(code1);
		getCode2List(code1);
	});
	
	$("#selLevel1").change(function(){
		var level1=$("#selLevel1").val(); 
//		alert(level1);
		$("#selLevel2").empty(); 
		$("#selLevel3").empty(); 
		$("#selLevel4").empty(); 
		$("#selLevel5").empty(); 
		getListLevel2(level1);
	});
	
	$("#selLevel2").change(function(){
		var level1=$("#selLevel1").val(); 
		var level2=$("#selLevel2").val(); 
//		alert(level1 + " "+level2);
		$("#selLevel3").empty(); 
		$("#selLevel4").empty(); 
		$("#selLevel5").empty();
		getListLevel3(level1, level2);
	});
	
	$("#selLevel3").change(function(){
		var level1=$("#selLevel1").val(); 
		var level2=$("#selLevel2").val(); 
		var level3=$("#selLevel3").val();
//		alert(level1 + " "+level2  + " "+level3);
		$("#selLevel4").empty(); 
		$("#selLevel5").empty();
		getListLevel4(level1, level2, level3);
	});
	
	$("#selLevel4").change(function(){
		var level1=$("#selLevel1").val(); 
		var level2=$("#selLevel2").val(); 
		var level3=$("#selLevel3").val();
		var level4=$("#selLevel4").val();
		$("#selLevel5").empty();
//		alert(level1 + " "+level2  + " "+level3+ " "+level4);
		getListLevel5(level1, level2, level3, level4);
	});
	
	//点击行事件
	$("#topNtb tbody tr").live("click",function(){
		//选中行
//		var radio = $(this).find("td input[type=radio]");
		var radio = $(this).find("td").eq(1).text();
		var array = radio.split("/");
//		alert(array[0]);
//		for(var i=0; i<$("#selLevel1").options.length; i++){  
//		if($("#selLevel1").options[i].innerHTML == nextYear){  
//			$("#selLevel1").options[i].selected = true;  
//			break;  
//		 }  
//		}  
		if(array[0]!="")
		{
			setSelectChecked("selLevel1", array[0]);
			
			getListLevel2(array[0]);
			getListLevel3(array[0], array[1]);
			getListLevel4(array[0], array[1], array[2]);	
			getListLevel5(array[0], array[1], array[2], array[3]);
		}
		

	
//		alert(array[4]);
		setSelectChecked("selLevel2", array[1]);
		setSelectChecked("selLevel3", array[2]);
		setSelectChecked("selLevel4", array[3]);
		setSelectChecked("selLevel5", array[4] );	

		if(array[5]!="")
		{
			setSelectChecked("codeLevel1Group", array[5] );
			
			getCode2List(array[5] );
			
		}
		if(array[6]!="")
		{
			setSelectChecked("codeLevel2Group", array[6] );
		}

	});
	
	function setSelectChecked(selectId, checkValue){  
	var select = document.getElementById(selectId);  
	for(var i=0; i<select.options.length; i++){  
		if(select.options[i].innerHTML == checkValue){  
	     select.options[i].selected = true;  
//	     select.options[i];
//	     alert(checkValue);
	         break;  
	       }  
	  }  
	};  
	
	function prev()
	{
		var currentVIN=$("#vin").val(); 
//		alert(currentVIN);
//		loadpage('defect.sp?toMainPage','缺陷录入');
		refresh();
		getPrev(currentVIN);
	};

	function next()
	{
		var currentVIN=$("#vin").val(); 
//		alert(currentVIN);
//		loadpage('defect.sp?toMainPage','缺陷录入');
		refresh();
		getNext(currentVIN);
	};
	
	function refresh()
	{
		$("#vechileNum").attr("value", null);
		$("#orderNum").attr("value", null);
		$("#shiftA").attr("checked",false);
		$("#shiftB").attr("checked",false);
		$("#shiftC").attr("checked",false);
		$("#isAccessTrue").attr("checked",false);
		$("#isAccessFalse").attr("checked",false);
		$("#checkDate").attr("value", null);;
		$("#memo").attr("value", null);;
		$("#defectLevelA").attr("checked",false);
		$("#defectLevelB").attr("checked",false);
		$("#defectLevelC").attr("checked",false);
		$("#defectLevelD").attr("checked",false);
		setSelectChecked("dep", "请选择");
		$("#selLevel2").empty();
		$("#selLevel2").empty(); 
		$("#selLevel3").empty(); 
		$("#selLevel4").empty(); 
		$("#selLevel5").empty(); 
		$("#codeLevel2Group").empty(); 
		getCode1List();
		getListLevel1();	
		
	}

	function change()
	{
		var code1=$("#codeLevel1Group").val(); 

		var code2 = $("#defectCode2").val(); 
		var i;
		var j;
		var optionstring = "";
		getCode2List(code1);
		if(code2!=null && code2!="")
		{
//		$("#codeLevel2Group")
		var length = document.getElementById('codeLevel2Group').options.length;
		var purityTypeO = document.getElementById('codeLevel2Group').options;
		var arr = new Array();
		
		for(i=0;i<Number(length);i++){
		var val = purityTypeO[i].value;
		 if(val!=null && val.indexOf(code2)!=-1)
		 {
			 arr.push(val);
		 }
		
		 }

		$("#codeLevel2Group").empty(); 
		
		for(j=0; arr!=null && j<arr.length; j++)
		{
			 optionstring += "<option value="+arr[j]+">" + arr[j] + "</option>";
			
		}
		$("#codeLevel2Group").html(optionstring); 
		}
		else
		{
			var code1=$("#codeLevel1Group").val(); 
			getCode2List(code1);
		}
		
	}
	
	function showDialog()
	{
//		 BootstrapDialog.show({
//	            message: 'Hi Apple!'
//	        });
	}
	

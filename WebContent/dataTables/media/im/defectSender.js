var pageTable;
var inputTblRs = [];
var form;

var tableName = "querytable";
var requestURL = "DefectSenderController.sp";
function refreshTable() {
	$('#'+tableName).ajax.reload();
}
function tableInit() {
	pageTable = $('#'+tableName)
			.DataTable(
					{
						ordering : false,
						searching : false,
						bStateSave:true,
						"aLengthMenu": [[10, 100, 500,1000, -1], [10, 100, 500,1000, "所有"]],   
						"iDisplayLength":100,   
						search   : {
						    "caseInsensitive": false
						  },
						processing : true,
						info : true,
						paging : true,
						scrollY : 450,
						scrollX : true ,
						bScrollCollapse: true,
						order : [ [ 0, "asc" ] ],
						"bAutoWidth" : true,
						lengthChange : false,
						"language" : {
							"lengthMenu" : "每页显示 _MENU_ 记录",
							"zeroRecords" : "数据库中没有记录",
							"info" : "第 _PAGE_页（ 共_PAGES_页）共_MAX_条记录",
							"infoEmpty" : "没有记录",
							"infoFiltered" : "(从_MAX_条记录中查询)",
							"sSearch" : "查询",
							"paginate" : {
								"previous" : "前一页",
								"next" : "后一页"
							}
						},
						scroller : {
							loadingIndicator : true
						},
						scrollCollapse : false,
						columnDefs : [ {
							"title" : "ID",
							"targets" : [ 0 ],
							"visible" : false,
							"searchable" : false,
							"orderable" : false
						}
						
						,{
							"title" : "勾选",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "问题描述",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "问题等级",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "检查时间",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						} 
						
						],
						data:inputTblRs});
};

function defectSend(){
	
	 defectDesc = "";
	 gates = "";
	 $("input[type='checkbox']").each(function(){
		   flag = $(this).attr("checked");
		   if( flag == "checked" ){ 
			   selectID =  $(this).attr('id')  ;
			   if( selectID != undefined ){
				    descID =  selectID +"_desc" ;
				    descc =   $( "#"+descID).text();
				    defectDesc = defectDesc + descc+";;";
			   }
			   if(selectID == undefined ){
				   gate =  $(this).attr('value');
				   
				   gates = gates + gate +";;" ;
			   }
	 	   	  
	 	   }
	 });
	
 
	 defectDesc =  encodeURI(encodeURI(defectDesc));
	 gates =  encodeURI(encodeURI(gates));
	 
	// var data1 =   [  {name:"defectDesc",value:defectDesc}  ,   {name:"receiveQuanlityGate",value:gates}    ] ;
	 $.ajax({
		 url : requestURL+"?save&defectDesc="+defectDesc+"&receiveQuanlityGate="+gates+"&t="+Math.random(),
		 type: 'POST',  
         //data: data1,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		 success : function(data) {
			 $("#modifyModal1").modal("hide");
			 operationConfirm(data);
		 }
			
		});
	 
}

getTableData=function(){
	inputTblRs = [];
	defectDesc1  = $("#defectDesc").val(); 
	defectDesc1 =  encodeURI(encodeURI(defectDesc1));
	 form = new FormData($("#pageform")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		 url : requestURL+"?toList&defectDesc="+defectDesc1+"&t="+Math.random(),
		 type: 'POST',  
         data: form,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		success : function(data) {
			var datas = eval(data);
			if(datas == null)
			{
				return;
			}
			var k = 0;
			$.each(
					datas,
					function(i, item) {
						var row = [];
						row[0] = item[0];
						// "<label> <input id='"+item[0]+"' type='checkbox'>请打勾 </label>" 
						row[1] = "<label> <input id='"+item[0]+"' type='checkbox' value='td'> </label>";
						row[2] = "<span id='"+ item[0] +"_desc'>" 	+ item[1] 	+ " </span>";
						row[3] = "<span >" 	+ item[2] 	+ " </span>";
						row[4] = "<span >" 	+ item[3] 	+ " </span>";
						 
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			pageTable.destroy();
			tableInit();
		
};
queryList=function(){
	getTableData();
};
	


$(document).ready(function() {
	tableInit();
	getTableData();
					
});

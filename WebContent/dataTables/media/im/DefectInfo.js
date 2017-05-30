var pageTable;
var inputTblRs = [];
var form;

var tableName = "querytable";
var requestURL = "DefectRepairController.sp";
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
//defect_car_key	site_num	brand_name	VIN_NUM	quality_gate_1	quality_gate_2	operation_defect_key	defect_code_key	level1	level2	level3	level4	level5	defect_name	defect_level	responsible_party	input_user	input_time	repair_user	repair_time	repair_opinion
						
						columnDefs : [ {
							"title" : "ID",
							"targets" : [ 0 ],
							"visible" : false,
							"searchable" : false,
							"orderable" : false
						}
						
						,{
							"title" : "<label><input type='checkbox' id='selectFull' onclick='selectFull()'></label>",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						} 
						,{
							"title" : "操作",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						},{
							"title" : "品牌",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "工厂",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "业务单元",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "VIN号",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "订单号",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}
					 , {
							"title" : "一级质量门",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "二级质量门",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "问题等级",
							"targets" : [ 10 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "责任属性",
							"targets" : [ 11 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "缺陷描述",
							"targets" : [ 12 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						} ,{
							"title" : "录入人",
							"targets" : [ 13 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "录入时间",
							"targets" : [ 14 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}
						
						],
						data:inputTblRs});
};

function selectFull(){
	 flag = $("#selectFull").attr("checked");
	 if(flag != "checked" ){
		 flag = false;
	 }
	 $("input[type='checkbox']").each(function(){
		 if( $(this).attr('id') !="selectFull")  { 
			 $(this).attr('checked' ,flag); 
	 	}
	 });
}

getTableData=function(){
	inputTblRs = [];
	 form = new FormData($("#pageform")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		 url : requestURL+"?toList&t="+Math.random(),
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
						row[0] = item[0] ;//"<label> <input type='checkbox'>请打勾 </label>" ;
					
						row[1] = " <input id='"+item[0]+"' type='checkbox'> ";	
						row[2] = " <a id='"+item[0]+"-edit' href='"+requestURL+"?toAddOrEdit&key="+item[0] +
								"' data-toggle='modal' class='btn btn-info btn-xs' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='返工处理'></i></a>" ;
								
						row[3] = "<span >" 	+ item[1] 	+ " </span>";
						row[4] = "<span >" 	+ item[2] 	+ " </span>";
						row[5] = "<span >" 	+ item[3] 	+ " </span>";
						row[6] = "<span >" 	+ item[4] 	+ " </span>";
						row[7] = "<span >" 	+ item[5] 	+ " </span>";
						row[8] = "<span >" 	+ item[6] 	+ " </span>";
						row[9] = "<span >" 	+ item[7] 	+ " </span>";
						row[10] = "<span >" 	+ item[8] 	+ " </span>";
						row[11] = "<span >" 	+ item[9] 	+ " </span>";
						row[12] = "<span >" 	+ item[10] 	+ " </span>";
						row[13] = "<span >" 	+ item[11] 	+ " </span>";
						row[14] = "<span >" 	+ item[12] 	+ " </span>";
						
						
						
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

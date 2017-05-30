var workCalandertb;
var inputTblRs = [];
var form;
function refreshData() {
	workCalandertb.ajax.reload();
}
function getWorkCalanderInit() {
	
	
	workCalandertb = $('#workCalandertb')
			.DataTable(
					{
						ordering : true,
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
						//scrollX: true,
						bScrollCollapse: true,
						order : [ [ 0, "asc" ] ],
						"bAutoWidth" : true,
						lengthChange : true,
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
						}, {
							"title" : "订单号",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "VIN",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "车型",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true

						}, {
							"title" : "颜色",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "站点",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "过站时间",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						
						}, {
							"title" : "操作员",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "CSN",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}],
						data:inputTblRs});
};
getWorkCalanderData=function(){
	inputTblRs = [];
	 form = new FormData($("#workCalander")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "passStationHistory.sp?toList&t="+Math.random(),
		 type: 'POST',  
         data: form,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		success : function(data) {
			var datas = eval(data);
			var k = 0;
			$.each(
					datas,
					function(i, item) {
						var row = [];
						console.log(item.key);
						row[0] = item.key;
						row[1] = "<span id='"
							+ item.key
							+ "-order_number'>"
							+ item.order_number
							+ "</span>";
						row[2] = "<span  id='"
								+ item.key
								+ "-vin'>"
								+ item.vin
								+ " </span>";
						row[3] = "<span id='"
							+ item.key
							+ "-car_type'>"
							+ item.car_type
							+ "</span>";
						row[4] = "<span id='"
							+ item.key
							+ "-color'>"
							+ item.color
							+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-station'>"
							+ item.station
							+ "</span>";
						row[6] = "<span id='"
							+ item.key
							+ "-entry_time'>"
							+ item.entry_time
							+ "</span>";
						row[7] = "<span id='"
							+ item.key
							+ "-username'>"
							+ item.username
							+ "</span>";
						row[8] = "<span id='"
							+ item.key
							+ "-csn'>"
							+ item.csn
							+ "</span>";
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			workCalandertb.destroy();
			getWorkCalanderInit();
		
};
queryWorkCalander=function(){
	getWorkCalanderData();
};
	


$(document).ready(function() {
	getWorkCalanderInit();
	getWorkCalanderData();
					
});

var unitHistoryTable;
var inputTblRs = [];
var form;
var instanceTable;
function getUnitHistoryInit() {
	
	
	unitHistoryTable = $('#unitHistoryTable')
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
							"title" : "工艺名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "工序名称",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "产线",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "工位",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "工序开始时间",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},  {
							"title" : "工序开始人",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},  {
							"title" : "工序结束时间",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},  {
							"title" : "工序结束人",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}],
						data:inputTblRs});
};




getUnitHistoryData=function(){
	inputTblRs = [];
	 form = new FormData($("#stationControl")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "stationControl.sp?toListUnitHistories&t="+Math.random(),
		 type: 'POST',  
         data: form,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		success : function(data) {
			var datas = eval(data);
			if(datas==null)
				{
				return;
				}
			var k = 0;
			$.each(
					datas,
					function(i, item) {
						var row = [];
						console.log(item.key);
						row[0] = item.key ;
						row[1] = "<span  id='"
								+ item.key
								+ "-routeName'>"
								+ item.routeName
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-routeStepName'>"
								+ item.routeStepName
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-productionLineName'>"
								+ item.productionLineName
								+ "</span>";
						row[4] = "<span id='"
								+ item.key
								+ "-workCenterName'>"
								+ item.workCenterName
								+ "</span>";
						row[5] = "<span id='"
								+ item.key
								+ "-startedTime'>"
								+ item.startedTime
								+ "</span>";
						row[6] = "<span id='"
								+ item.key
								+ "-startedUserName'>"
								+ item.startedUserName
								+ "</span>";
						row[7] = "<span id='"
								+ item.key
								+ "-completedTime'>"
								+ item.completedTime
								+ "</span>";
						row[8] = "<span id='"
								+ item.key
								+ "-completedUserName'>"
								+ item.completedUserName
								+ "</span>";
						
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			unitHistoryTable.destroy();
			getUnitHistoryInit();
		
};







queryUnitHistory=function(){
	getUnitHistoryData();
};


$(document).ready(function() {
	getUnitHistoryInit();
	
	getUnitHistoryData();
					
});

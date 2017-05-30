var sitetb;
var inputTblRs = [];
var form;
function refreshData() {
	workCalendartb.ajax.reload();
}
function getWorkCalendarInit() {
	
	workCalendartb = $('#workCalendartb')
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
							"title" : "日期",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "车间名称",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "生产线",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}, {
							"title" : "班次名称",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "开始时间",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "结束时间",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "类型",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}
						],
						data:inputTblRs});
};
function getworkCalendarData(){
	inputTblRs = [];
	 form = new FormData($("#workCalendar")[0]);
	 form.append("queryNum", $("#setNum").val());
 	$.ajax({
		url : "workCalendar.sp?toList&t="+Math.random(),
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
						row[0] = item.key;
						row[1] = "<span  id='"
								+ item.key
								+ "-producedDate'>"
								+ item.producedDate
								+ " </span>";						
						row[2] = "<span id='"
								+ item.key
								+ "-v_areaName'>"
								+ item.v_areaName
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-v_productionlineName'>"
								+ item.v_productionlineName
								+ "</span>";
						row[4] = "<span id='"
							+ item.key
							+ "-classes'>"
							+ item.classes
							+ "</span>";	
						row[5] = "<span id='"
							+ item.key
							+ "-startDate'>"
							+ item.startDate
							+ "</span>";
						row[6] = "<span id='"
							+ item.key
							+ "-endDate'>"
							+ item.endDate
							+ "</span>";	
						row[7] = " <a id='"+item.key+"-edit' href='workCalendar.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteWorkCalendar)'><i class='fa fa-ban' title='删除'></i></a>" ;				 
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			workCalendartb.destroy();
			getWorkCalendarInit();
		
};
queryWorkCalendar=function(){
	getWorkCalendarData();
};	

$(document).ready(function() {
 	getWorkCalendarInit();
 	getworkCalendarData();
					
});

$('#datetimepicker').datetimepicker();
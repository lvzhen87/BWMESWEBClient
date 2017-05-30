var plantb;
var inputTblRs = [];
var form;
function refreshData() {
	plantb.ajax.reload();
}
function getPlanInit() {
	plantb = $('#plantb').DataTable({
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
					"title" : "#",
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false,
					"orderable" : false
				}, {
					"title" : "电池包ID号",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "零件号",
					"targets" : [ 2 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "零件描述",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "订单号",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "计划上线日期",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "上线顺序号",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "工位",
					"targets" : [ 7 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "进站时间",
					"targets" : [ 8 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "操作员",
					"targets" : [ 9 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}
				
				
				
				
				
				],
				data:inputTblRs});
};
getPlanData=function(){
	inputTblRs = [];
	
    inputOrderNum = $("#orderNum").val();
	inputPartNum  = $("#partNum").val() ;
	if(  inputOrderNum == "" && inputPartNum =="" ){
		alert("请输入查询条件");
		return ;
	}
	
	 form = new FormData($("#plan")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "plan.sp?eleOrderList&t="+Math.random(),
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
						row[0] = "<input type='radio' id='" 
								+ item.eleID
								+ "-key' name='selectPlan' value='"
							+ item.eleID 
							+ "'>";
						row[1] = "<span  id='"
							+ item.eleID
							+ "-parentNumber'>"
							+ item.eleID
							+ " </span>";
						row[2] = "<span  id='"
								+ item.partNum
								+ "-effectDate'>"
								+ item.partNum
								+ " </span>";
						row[3] = "<span id='"
								+ item.partDesc
								+ "-factoryNum'>"
								+ item.partDesc
								+ "</span>";
						row[4] = "<span id='"
								+ item.orderNum
								+ "-lineNum'>"
								+ item.orderNum
								+ "</span>";
						row[5] = "<span id='"
								+ item.startTime
								+ "-orderNum'>"
								+ item.startTime
								+ "</span>";
						row[6] = "<span id='"
								+ item.csn
								+ "-partNum'>"
								+ item.csn
								+ "</span>";
						row[7] = "<span id='"
							+ item.station
							+ "-partstation'>"
							+ item.station
							+ "</span>";
						row[8] = "<span id='"
							+ item.entryTime
							+ "-partentryTime'>"
							+ item.entryTime
							+ "</span>";
						row[9] = "<span id='"
							+ item.scanUser
							+ "-scanUser'>"
							+ item.scanUser
							+ "</span>";
						
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
		plantb.destroy();
		getPlanInit();
		
};
queryPlan=function(){
	$("#thisOrder").val(-1);
	getPlanData();
};



$(document).ready(function() {
	getPlanInit();
	//getPlanData();
	
});
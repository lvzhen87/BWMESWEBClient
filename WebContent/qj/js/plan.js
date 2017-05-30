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
					"title" : "计划号",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "计划开始日期",
					"targets" : [ 2 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "计划完成日期",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "车型",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "车型描述",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "数量",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "颜色",
					"targets" : [ 7 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "VIN8",
					"targets" : [ 8 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "动力总成类型",
					"targets" : [ 9 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "特殊订单",
					"targets" : [ 10 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},  {
					"title" : "生产线号",
					"targets" : [ 11 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "车种",
					"targets" : [ 12 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "备注",
					"targets" : [ 13 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "计划状态",
					"targets" : [ 14 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}],
				data:inputTblRs});
};
getPlanData=function(){
	inputTblRs = [];
	 form = new FormData($("#plan")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "plan.sp?planList&t="+Math.random(),
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
								+ item.key 
								+ "-key' name='selectPlan' value='"
							+ item.key 
							+ "'>";
						row[1] = "<span  id='"
								+ item.key
								+ "-planId'>"
								+ item.planId
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-planStartTime'>"
								+ item.planStartTime
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-planEndTime'>"
								+ item.planEndTime
								+ "</span>";
						row[4] = "<span id='"
								+ item.key
								+ "-vsn'>"
								+ item.vsn
								+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-vsndesc'>"
							+ item.vsnDesc
							+ "</span>";
					
						
						row[6] = "<span id='"
								+ item.key
								+ "-quantity'>"
								+ item.quantity
								+ "</span>";
						row[7] = "<span id='"
							+ item.key
							+ "-color'>"
							+ item.color
							+ "</span>";
						
						row[8] = "<span id='"
							+ item.key
							+ "-vin8'>"
							+ item.vin8
							+ "</span>";
						row[9] = "<span id='"
							+ item.key
							+ "-engine_type'>"
							+ item.engineType
							+ "</span>";
						row[10] = "<span id='"
							+ item.key
							+ "-special_order'>"
							+ item.specialOrder
							+ "</span>";
						
						row[11] = "<span id='"
							+ item.key
							+ "-lineid'>"
							+ item.lineId
							+ "</span>";
						row[12] = "<span id='"
							+ item.key
							+ "-carProject'>"
							+ item.carProject
							+ "</span>";
					
						row[13] = "<span id='"
							+ item.key
							+ "-comment'>"
							+ item.comment
							+ "</span>";
						row[14] = "<span id='"
								+ item.key
								+ "-status'>"
								+ item.status
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
	getPlanData();
	
});
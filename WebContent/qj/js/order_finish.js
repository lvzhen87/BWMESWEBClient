var ordertb;
var orderItemtb;
var inputTblRs = [];
var form;
function refreshData() {
	ordertb.ajax.reload();
}
function getOrderInit() {
	ordertb = $('#ordertb').DataTable({
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
				columnDefs : [  {
					"title" : "#",
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false,
					"orderable" : false
				},{
					"title" : "CSN",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "VIN号",
					"targets" : [ 2 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "计划开始时间",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "计划交货时间",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "备注",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "计划状态",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "操作",
					"targets" : [ 7 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : false
				}],
				data:inputTblRs
			});
};
getOrderData=function(){
	inputTblRs = [];
	 form = new FormData($("#order")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "plan.sp?orderFinish&t="+Math.random(),
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
						row[0] = "<input type='checkbox' id='" 
							+ item.key 
							+ "-key' name='selectOrder' value='"
							+ item.key 
							+ "'>";
						row[1] = "<span id='"
							+ item.key
							+ "-csn'>"
							+ item.csn
							+ "</span>";
						row[2] = "<span  id='"
								+ item.key
								+ "-orderNumber'>"
								+ item.orderNumber
								+ " </span>";
						row[3] = "<span id='"
								+ item.key
								+ "-enteredTime'>"
								+ item.enteredTime
								+ "</span>";
						row[4] = "<span id='"
								+ item.key
								+ "-promisedTime'>"
								+ item.promisedTime
								+ "</span>";
						row[5] = "<span id='"
								+ item.key
								+ "-note'>"
								+ item.note
								+ "</span>";
						row[6] = "<span id='"
								+ item.key
								+ "-status'>"
								+ item.status
								+ "</span>";
						row[7] ="<a href='#' class='btn btn-warning btn-xs' data-toggle='modal' onclick='upStep("+ item.key  +
								")'><i class='fa fa-arrow-up' title='上移'></i></a>" +
								" <a href='#' class='btn btn-warning btn-xs' data-toggle='modal' onclick='downStep("+ item.key +
								")'><i class='fa fa-arrow-down' title='下移'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			ordertb.destroy();
			getOrderInit();
		
};
queryOrder=function(){
	$("#thisOrder").val(-1);
	getOrderData();
};

$(document).ready(function() {
	getOrderInit();
	getOrderData();
	
});
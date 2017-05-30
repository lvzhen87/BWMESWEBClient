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
					"title" : "VIN号",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "订单号",
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
					"title" : "车型描述",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "颜色",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "动力总成型号",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "特殊订单",
					"targets" : [ 7 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "车种",
					"targets" : [ 8 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "订单开始日期",
					"targets" : [ 9 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "订单结束日期",
					"targets" : [ 10 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "当前站点",
					"targets" : [ 11 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "操作员",
					"targets" : [ 12 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "车辆状态",
					"targets" : [ 13 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "车辆上线顺序",
					"targets" : [ 14 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "进站时间",
					"targets" : [ 15 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},{
					"title" : "操作",
					"targets" : [ 16 ],
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
		url : "plan.sp?orderList&t="+Math.random(),
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
							+ "-vin'>"
							+ item.vin
							+ "</span>";
						row[2] = "<span  id='"
								+ item.key
								+ "-orderNumber'>"
								+ item.orderNumber
								+ " </span>";
						row[3] = "<span id='"
								+ item.key
								+ "-carType'>"
								+ item.carType
								+ "</span>";
						row[4] = "<span id='"
								+ item.key
								+ "-carTypeDesc'>"
								+ item.carTypeDesc
								+ "</span>";
						row[5] = "<span id='"
								+ item.key
								+ "-color'>"
								+ item.color
								+ "</span>";
						row[6] = "<span id='"
								+ item.key
								+ "-engineType'>"
								+ item.engineType
								+ "</span>";
						row[7] = "<span id='"
							+ item.key
							+ "-specialOrder'>"
							+ item.specialOrder
							+ "</span>";
						row[8] = "<span id='"
							+ item.key
							+ "-carProject'>"
							+ item.carProject
							+ "</span>";
						row[9] = "<span id='"
							+ item.key
							+ "-planStartTime'>"
							+ item.planStartTime
							+ "</span>";
						row[10] = "<span id='"
							+ item.key
							+ "-planEndTime'>"
							+ item.planEndTime
							+ "</span>";
						row[11] = "<span id='"
							+ item.key
							+ "-station'>"
							+ item.station
							+ "</span>";
						row[12] = "<span id='"
							+ item.key
							+ "-username'>"
							+ item.userName
							+ "</span>";
						row[13] = "<span id='"
							+ item.key
							+ "-status'>"
							+ item.status
							+ "</span>";
						row[14] = "<span id='"
							+ item.key
							+ "-csn'>"
							+ item.csn
							+ "</span>";
						row[15] = "<span id='"
							+ item.key
							+ "-entryTime'>"
							+ item.entryTime
							+ "</span>";
					
						
						row[16] ="<a href='#' class='btn btn-warning btn-xs' data-toggle='modal' onclick='upStep("+ item.key  +
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
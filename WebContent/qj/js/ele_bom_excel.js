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
					"title" : "父零件号",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "父零件描述",
					"targets" : [ 2 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "子零件号",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "子零件描述",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "装配工位",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "零件消耗用量",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}],
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
		url : "qjbom.sp?eleBomList&t="+Math.random(),
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
								+ item.parentNumber + '##' + item.sunNumber
								+ "-key' name='selectPlan' value='"
							+ item.parentNumber 
							+ "'>";
						row[1] = "<span  id='"
							+ item.parentNumber
							+ "-parentNumber'>"
							+ item.parentNumber
							+ " </span>";
						row[2] = "<span  id='"
								+ item.parentDesc
								+ "-effectDate'>"
								+ item.parentDesc
								+ " </span>";
						row[3] = "<span id='"
								+ item.sunNumber
								+ "-factoryNum'>"
								+ item.sunNumber
								+ "</span>";
						row[4] = "<span id='"
								+ item.sunDesc
								+ "-lineNum'>"
								+ item.sunDesc
								+ "</span>";
						row[5] = "<span id='"
								+ item.station
								+ "-orderNum'>"
								+ item.station
								+ "</span>";
						row[6] = "<span id='"
								+ item.partCount
								+ "-partNum'>"
								+ item.partCount
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
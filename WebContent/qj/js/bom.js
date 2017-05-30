var bomtb;
var bomItemtb;
var consumptionPlantb;
var alternateBomItemtb;
var inputTblRs = [];
var form;
function refreshData() {
	bomtb.ajax.reload();
}
function getBomInit() {
	
	
	bomtb = $('#bomtb')
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
							"title" : "#",
							"targets" : [ 0 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}, {
							"title" : "BOM编号",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "BOM名称",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "BOM分类",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "BOM版本",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "BOM单位",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : false
						}],
						data:inputTblRs});
};
getBomData=function(){
	inputTblRs = [];
	 form = new FormData($("#bom")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "qjbom.sp?toList&t="+Math.random(),
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
								+ "-key' name='selectBOM' value='"
								+ item.key 
								+ "'>";
						row[1] = "<span  id='"
								+ item.key
								+ "-bomNumber'>"
								+ item.bomNumber
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-bomName'>"
								+ item.bomName
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-category'>"
								+ item.category
								+ "</span>";
						row[4] = "<span id='"
							+ item.key
							+ "-bomRevision'>"
							+ item.bomRevision
							+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-uom'>"
							+ item.uom
							+ "</span>";
						row[6] = " <a id='"+item.key+"-edit' href='bom.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteBOM)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			bomtb.destroy();
			getBomInit();
		
};
queryBom=function(){
	getBomData();
	$("#thisBom").val(-1);
	$("#thisBomItem").val(-1);
	getBomItemData(-1);
	getConsumptionPlanData(-1, -1);
	getAlternateBomItemData(-1, -1);
	$("#addBomItemButton").hide();
	$("#addConsumptionPlanButton").hide();
	$("#addAlternateBomItemButton").hide();
};
	
function getBomItemInit() {
	
	
	bomItemtb = $('#bomItemtb')
			.DataTable(
					{
						retrieve: true,
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
							"title" : "BOMItem名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "对应物料编号",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "对应物料版本",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "消耗数量",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "单位",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : false
						}],
						data:inputTblRs});
};
getBomItemData=function(bomKey){
	inputTblRs = [];
	$.ajax({
		url : "bom.sp?loadBomItem&t="+Math.random(),
		 type: 'POST',  
         data: {bomKey: bomKey},  
         dataType:'json',
         async: false,  
         cache: false,  
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
								+ "-key' name='selectBOMItem' value='"
								+ item.key 
								+ "'>";
						row[1] = "<span  id='"
								+ item.key
								+ "-bomItemName'>"
								+ item.bomItemName
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-partNumber'>"
								+ item.partNumber
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-partRevision'>"
								+ item.partRevision
								+ "</span>";
						row[4] = "<span id='"
							+ item.key
							+ "-quantity'>"
							+ item.quantity
							+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-uom'>"
							+ item.uom
							+ "</span>";
						row[6] = " <a id='"+item.key+"-edit' href='bom.sp?toAddOrEditBomItem&bomKey=" + $('#thisBom').val() + "&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete2("+ $('#thisBom').val() + "," + item.key + ",deleteBomItem)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			bomItemtb.destroy();
			getBomItemInit();
		
};

function getConsumptionPlanInit() {
	
	
	consumptionPlantb = $('#consumptionPlantb')
			.DataTable(
					{
						retrieve: true,
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
							"title" : "消耗信息名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "最低消耗数量",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "目标消耗数量",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "最高消耗数量",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "单位",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getConsumptionPlanData=function(bomKey, bomItemKey){
	inputTblRs = [];
	$.ajax({
		url : "bom.sp?loadConsumptionPlan&t="+Math.random(),
		 type: 'POST',  
         data: {bomKey:bomKey,bomItemKey:bomItemKey},  
         dataType:'json',
         async: false,  
         cache: false,  
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
								+ "-consumptionPlanName'>"
								+ item.consumptionPlanName
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-lowerAmount'>"
								+ item.lowerAmount
								+ "</span>";
						row[3] = "<span id='"
							+ item.key
							+ "-targetAmount'>"
							+ item.targetAmount
							+ "</span>";
						row[4] = "<span id='"
							+ item.key
							+ "-upperAmount'>"
							+ item.upperAmount
							+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-uom'>"
							+ item.uom
							+ "</span>";
						row[6] =" <a id='"+item.key+"-edit' href='bom.sp?toAddOrEditConsumptionPlan&bomKey="+ $('#thisBom').val() +"&bomItemKey="+ $('#thisBomItem').val()+"&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>"+
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete3("+ $('#thisBom').val() + "," + $('#thisBomItem').val() + "," + item.key + ",deleteConsumptionPlan)'><i class='fa fa-ban' title='删除'></i></a>" ;			
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			consumptionPlantb.destroy();
			getConsumptionPlanInit();
		
};


function getAlternateBomItemInit() {
	
	
	alternateBomItemtb = $('#alternateBomItemtb')
			.DataTable(
					{
						retrieve: true,
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
							"title" : "替代料名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "替代料物料编号",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "替代料物料版本",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "替代料最大替换比例",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getAlternateBomItemData=function(bomKey, bomItemKey){
	inputTblRs = [];
	$.ajax({
		url : "bom.sp?loadAlternateBomItem&t="+Math.random(),
		 type: 'POST',  
         data: {bomKey:bomKey,bomItemKey:bomItemKey},  
         dataType:'json',
         async: false,  
         cache: false,  
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
								+ "-alternateBomItemName'>"
								+ item.alternateBomItemName
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-partNumber'>"
								+ item.partNumber
								+ "</span>";
						row[3] = "<span id='"
							+ item.key
							+ "-partRevision'>"
							+ item.partRevision
							+ "</span>";
						row[4] = "<span id='"
							+ item.key
							+ "-maxReplacementPercent'>"
							+ item.maxReplacementPercent
							+ "</span>";
						row[5] =" <a id='"+item.key+"-edit' href='bom.sp?toAddOrEditAlternateBomItem&bomKey="+ $('#thisBom').val() +"&bomItemKey="+ $('#thisBomItem').val()+"&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>"+
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete3("+ $('#thisBom').val() + "," + $('#thisBomItem').val() + "," + item.key + ",deleteAlternateBomItem)'><i class='fa fa-ban' title='删除'></i></a>" ;			
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			alternateBomItemtb.destroy();
			getAlternateBomItemInit();
		
};


$(document).ready(function() {
	getBomInit();
	getBomItemInit();
	getConsumptionPlanInit();
	getAlternateBomItemInit();
	getBomData();
					
});

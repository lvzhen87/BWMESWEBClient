var parttb;
var inputTblRs = [];
var form;
function refreshData() {
	parttb.ajax.reload();
}
function getPartInit() {
	
	
	parttb = $('#parttb')
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
							"title" : "物料编号",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "物料名称",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "物料版本号",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "计量方式（计件/计量）",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "物料类型",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "物料单位",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "物料呆滞时长",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "启用时间",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "失效时间",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 10 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}],
						data:inputTblRs});
};
getPartData=function(){
	inputTblRs = [];
	 form = new FormData($("#part")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "qjpart.sp?toList&t="+Math.random(),
		 type: 'POST',  
         data: form,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,  
		success : function(data) {
			var datas = eval(data);
			if (datas==null) {
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
								+ "-partNumber'>"
								+ item.partNumber
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-partName'>"
								+ item.partName
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-partRevision'>"
								+ item.partRevision
								+ "</span>";
						row[4] = "<span id='"
								+ item.key
								+ "-consumptionType'>"
								+ item.consumptionType
								+ "</span>";
						row[5] = "<span id='"
								+ item.key
								+ "-category'>"
								+ item.category
								+ "</span>";
						row[6] = "<span id='"
								+ item.key
								+ "-uom'>"
								+ item.uom
								+ "</span>";
						row[7] = "<span id='"
								+ item.key
								+ "-shelfLife'>"
								+ item.shelfLife
								+ "</span>";
						row[8] = "<span id='"
								+ item.key
								+ "-effectStart'>"
								+ item.effectStart
								+ "</span>";
						row[9] = "<span id='"
								+ item.key
								+ "-effectEnd'>"
								+ item.effectEnd
								+ "</span>";
						row[10] = " <a id='"+item.key+"-edit' href='qjpart.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' data-backdrop='static' class='btn btn-info btn-xs' " +
								" data-target='#modifyPart'><i class='fa fa-cog' title='修改'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' data-backdrop='static' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deletePart)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			parttb.destroy();
			getPartInit();
		
};
queryPart=function(){
	getPartData();
};
	


$(document).ready(function() {
	getPartInit();
	getPartData();
					
});

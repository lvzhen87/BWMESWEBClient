var itemcontanier;
var inputTblRs = [];
var form;
function refreshData() {
	itemcontanier.ajax.reload();
}
function getDataInit() {
	
	
	itemcontanier = $('#itemcontanier')
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
							"title" : "点名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "点地址",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "组名称",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "驱动名称",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "是否监控",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "方法名称",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "分组名称",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "点位顺序",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getData=function(){
	inputTblRs = [];
	 form = new FormData($("#querycontanier")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "opcmonitor.sp?toList&t="+Math.random(),
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
						row[1] = "<span  id='"
								+ item.key
								+ "-oPCName'>"
								+ item.oPCName
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-oPCItem'>"
								+ item.oPCItem
								+ "</span>";
						row[3] = "<span id='"
							+ item.key
							+ "-groupID'>"
							+ item.groupID
							+ "</span>";
						row[4] = "<span id='"
							+ item.key
							+ "-deviceName'>"
							+ item.deviceName
							+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-monitor'>"
							+ item.monitor
							+ "</span>";
						row[6] = "<span id='"
							+ item.key
							+ "-oPCFunction'>"
							+ item.oPCFunction
							+ "</span>";
						row[7] = "<span id='"
							+ item.key
							+ "-itemGroup'>"
							+ item.itemGroup
							+ "</span>";
						row[8] = "<span id='"
							+ item.key
							+ "-itemSeq'>"
							+ item.itemSeq
							+ "</span>";
						row[9] = " <a id='"+item.key+"-edit' href='opcmonitor.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteData)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
	itemcontanier.destroy();
			getDataInit();
		
};
queryData=function(){
	getData();
};
	


$(document).ready(function() {
	getDataInit();
	getData();
					
});

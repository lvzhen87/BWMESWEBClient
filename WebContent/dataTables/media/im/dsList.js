var pageTable;
var inputTblRs = [];
var form;

var tableName = "querytable";
var requestURL = "dslist.sp";
function refreshTable() {
	$('#'+tableName).ajax.reload();
}
function tableInit() {
	pageTable = $('#'+tableName)
			.DataTable(
					{
						ordering : false,
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
							"title" : "数据列表名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "数据列表描述",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "数据列表分类",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getTableData=function(){
	inputTblRs = [];
	 form = new FormData($("#pageform")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : requestURL+"?toList&t="+Math.random(),
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
								+ "-carrierNumber'>"
								+ item.listName
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-carrierName'>"
								+ item.listDesc
								+ "</span>";
						row[3] = "<span id='"
								+ item.key
								+ "-category'>"
								+ item.listCategory
								+ "</span>";
					
						row[4] = " <a id='"+item.key+"-edit' href='dslist.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='修改'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteDsList)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			pageTable.destroy();
			tableInit();
		
};
queryList=function(){
	getTableData();
};
	


$(document).ready(function() {
	tableInit();
	getTableData();
					
});

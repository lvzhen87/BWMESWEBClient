var workCentertb;
var inputTblRs = [];
var form;
function refreshData() {
	workCentertb.ajax.reload();
}
function getWorkCenterInit() {
	
	
	workCentertb = $('#qualityGateTB')
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
							"title" : "工厂名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "品牌",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "一级质量门",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "二级质量门",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "层级1",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "层级2",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "层级3",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "层级4",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "层级5",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 10 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getWorkCenterData=function(){
	inputTblRs = [];
	 form = new FormData($("#workCenter")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "OperationQualityPartController.sp?toList&t="+Math.random(),
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
						console.log(item[0]);
						row[0] = item[0];
						row[1] = "<span  id='"
								+ item[0]
								+ "-factory'>"
								+ item[1]
								+ " </span>";
						row[2] = "<span id='"
								+ item[0]
								+ "-brand'>"
								+ item[2]
								+ "</span>";
						
						row[3] = "<span id='"
							+ item[0]
							+ "-gate1'>"
							+ item[3]
							+ "</span>";
						
						row[4] = "<span id='"
							+ item[0]
							+ "-gate2'>"
							+ item[4]
							+ "</span>";
						row[5] = "<span id='"
							+ item[0]
							+ "-l1'>"
							+ item[5]
							+ "</span>";
						row[6] = "<span id='"
							+ item[0]
							+ "-l2'>"
							+ item[6]
							+ "</span>";
						row[7] = "<span id='"
							+ item[0]
							+ "-l3'>"
							+ item[7]
							+ "</span>";
						row[8] = "<span id='"
							+ item[0]
							+ "-l4'>"
							+ item[8]
							+ "</span>";
						row[9] = "<span id='"
							+ item[0]
							+ "-l5'>"
							+ item[9]
							+ "</span>";
						
						row[10] = " <a id='"+item[0]+"-edit' href='OperationQualityPartController.sp?toAddOrEdit&key="+item[0] +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item[0]+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item[0]+",deleteWorkCenter)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			workCentertb.destroy();
			getWorkCenterInit();
		
};
queryList=function(){
	getWorkCenterData();
};
	


$(document).ready(function() {
	getWorkCenterInit();
	getWorkCenterData();
					
});

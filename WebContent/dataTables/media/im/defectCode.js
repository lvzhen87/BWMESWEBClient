var areatb;
var inputTblRs = [];
var form;
function refreshData() {
	areatb.ajax.reload();
}
function getAreaInit() {
	
	
	areatb = $('#areatb')
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
							"title" : "层级1",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "层级2",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "层级3",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},
						{
							"title" : "层级4",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},
						{
							"title" : "层级5",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "操作",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getAreaData=function(){
	inputTblRs = [];
	 form = new FormData($("#area")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "DefectCodeController.sp?toList&t="+Math.random(),
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
								+ "-l1'>"
								+ item.level1
								+ " </span>";
						row[2] = "<span  id='"
							+ item.key
							+ "-l2'>"
							+ item.level2
							+ " </span>";
						row[3] = "<span  id='"
							+ item.key
							+ "-l3'>"
							+ item.level3
							+ " </span>";
						row[4] = "<span  id='"
							+ item.key
							+ "-l4'>"
							+ item.level4
							+ " </span>";
						row[5] = "<span  id='"
							+ item.key
							+ "-l5'>"
							+ item.level5
							+ " </span>";
						 
						row[6] = " <a id='"+item.key+"-edit' href='DefectCodeController.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteArea)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			areatb.destroy();
			getAreaInit();
		
};
queryList=function(){
 
	getAreaData();
};
	


$(document).ready(function() {
	getAreaInit();
	getAreaData();
	$('#areaCollapse').on( 'shown.bs.collapse', function (e) {
        $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
	});		
});

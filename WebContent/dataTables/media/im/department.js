var sitetb;
var inputTblRs = [];
var form;
function refreshData() {
	departmenttb.ajax.reload();
}
function getDepartmentInit() {
	
	
	departmenttb = $('#departmenttb')
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
							"title" : "部门名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "部门描述",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "操作",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getDepartmentData=function(){
	inputTblRs = [];
	 form = new FormData($("#department")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "department.sp?toList&t="+Math.random(),
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
								+ "-d_name'>"
								+ item.d_name
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-describe'>"
								+ item.describe
								+ "</span>";						
						row[3] = " <a id='"+item.key+"-edit' href='department.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteDepartmente)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			departmenttb.destroy();
			getDepartmentInit();
		
};
queryDepartment=function(){
	getDepartmentData();
};
	


$(document).ready(function() {
	 
 	getDepartmentInit();
	getDepartmentData();
					
});

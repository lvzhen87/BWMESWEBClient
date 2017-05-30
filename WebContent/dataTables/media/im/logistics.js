var logisticstb;
var inputTblRs = [];
var form;
function refreshData() {
	usertb.ajax.reload();
}
function getLogisticsInit() {
	
	logisticstb = $('#logisticstb')
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
							"title" : "零件名称",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "包装代码",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, 
						{
							"title" : "零件编号",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},
						{
							"title" : "包装规格",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},
						{
							"title" : "零件类型",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "操作",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}],
						data:inputTblRs});
};
getLogisticsData=function(){
	inputTblRs = [];
	 form = new FormData($("#logistics")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "logistics.sp?toList&t="+Math.random(),
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
								+ "-part_name'>"
								+ item.part_name
								+ " </span>";
						row[2] = "<span  id='"
							+ item.key
							+ "-pack_code'>"
							+ item.pack_code
							+ " </span>";
						row[3] = "<span  id='"
							+ item.key
							+ "-part_no	'>"
							+ item.part_no	
							+ " </span>";
						row[4] = "<span  id='"
							+ item.key
							+ "-pack_size'>"
							+ item.pack_size
							+ " </span>";
						row[5] = "<span  id='"
							+ item.key
							+ "-part_type'>"
							+ item.part_type
							+ " </span>";
						row[6] = " <a id='"+item.key+"-edit' href='logistics.sp?toAddOrEdit&key="+item.key +
						"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
						" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
						" <a id='"+item.key+"-del' href='#'" +
						" data-toggle='modal' class='btn btn-danger btn-xs' " +
						" onclick='confirmDelete("+item.key+",deletedata)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
	logisticstb.destroy();
	getLogisticsInit();
		
};

function getLogisticsDataByName(part_name){
	inputTblRs = [];
	
	$.ajax({
		url : "logistics.sp?getLogisticsByName&t="+Math.random()+"&part_name="+part_name,
		 type: 'POST',  
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
								+ "-part_name'>"
								+ item.part_name
								+ " </span>";
						row[2] = "<span  id='"
							+ item.key
							+ "-pack_code'>"
							+ item.pack_code
							+ " </span>";
						row[3] = "<span  id='"
							+ item.key
							+ "-part_no	'>"
							+ item.part_no	
							+ " </span>";
						row[4] = "<span  id='"
							+ item.key
							+ "-pack_size'>"
							+ item.pack_size
							+ " </span>";
						row[5] = "<span  id='"
							+ item.key
							+ "-part_type'>"
							+ item.part_type
							+ " </span>";
 						row[6]= " <a id='"+item.key+"-edit' href='logistics.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' data-backdrop='static' class='btn btn-info btn-xs' " +
								" data-target='#modifyUser'><i class='fa fa-cog' title='修改'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' data-backdrop='static' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteUser)'><i class='fa fa-ban' title='删除'></i></a>";
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
	        logisticstb.destroy();
			getLogisticsInit();
		
};



queryLogistics=function(){
	getLogisticsData();
};
	




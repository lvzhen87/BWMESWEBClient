var rdcreceivetb;
var inputTblRs = [];
var form;
function refreshData() {
	usertb.ajax.reload();
}
function getRDCReceiveInit() {

	rdcreceivetb = $('#rdcreceivetb')
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
							"title" : "随货票号",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "零件编号",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},
						{
							"title" : "零件名称",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},
						{
							"title" : "零件类型",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},
						{
							"title" : "批次日期",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "流水号",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "供应商",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "收货数量",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "发货人",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "收货时间",
							"targets" : [ 10 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}],
						data:inputTblRs});
};
getRDCReceiveData=function(){
	inputTblRs = [];
	 form = new FormData($("#rdcreceive")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "rdcreceive.sp?toList&t="+Math.random(),
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
								+ "-bill_no'>"
								+ item.bill_no
								+ " </span>";
						row[2] = "<span  id='"
							+ item.key
							+ "-part_no'>"
							+ item.part_no
							+ " </span>";
						row[3] = "<span  id='"
							+ item.key
							+ "-part_name'>"
							+ item.part_name
							+ " </span>";
						row[4] = "<span  id='"
							+ item.key
							+ "-part_type'>"
							+ item.part_type
							+ " </span>";
						row[5] = "<span  id='"
							+ item.key
							+ "-batch_date'>"
							+ item.batch_date
							+ " </span>";
						row[6] = "<span  id='"
							+ item.key
							+ "-seq_no'>"
							+ item.seq_no
							+ " </span>";
						row[7] = "<span  id='"
							+ item.key
							+ "-supplier'>"
							+ item.supplier
							+ " </span>";
						row[8] = "<span  id='"
							+ item.key
							+ "-batch_no'>"
							+ item.batch_no
							+ " </span>";
						row[9] = "<span  id='"
							+ item.key
							+ "-operator'>"
							+ item.operator
							+ " </span>";
						row[10] = "<span  id='"
							+ item.key
							+ "-receive_time'>"
							+ item.receive_time
							+ " </span>";
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
	rdcreceivetb.destroy();
	getRDCReceiveInit();

};

function getRDCReceiveDataByName(bill_no){
	inputTblRs = [];

	$.ajax({
		url : "rdcreceive.sp?getRDCReceiveByName&t="+Math.random()+"&bill_no="+bill_no,
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
							+ "-bill_no'>"
							+ item.bill_no
							+ " </span>";
					row[2] = "<span  id='"
						+ item.key
						+ "-part_no'>"
						+ item.part_no
						+ " </span>";
					row[3] = "<span  id='"
						+ item.key
						+ "-part_name'>"
						+ item.part_name
						+ " </span>";
					row[4] = "<span  id='"
						+ item.key
						+ "-part_type'>"
						+ item.part_type
						+ " </span>";
					row[5] = "<span  id='"
						+ item.key
						+ "-batch_date'>"
						+ item.batch_date
						+ " </span>";
					row[6] = "<span  id='"
						+ item.key
						+ "-seq_no'>"
						+ item.seq_no
						+ " </span>";
					row[7] = "<span  id='"
						+ item.key
						+ "-supplier'>"
						+ item.supplier
						+ " </span>";
					row[8] = "<span  id='"
						+ item.key
						+ "-batch_no'>"
						+ item.batch_no
						+ " </span>";
					row[9] = "<span  id='"
						+ item.key
						+ "-operator'>"
						+ item.operator
						+ " </span>";
					row[10] = "<span  id='"
						+ item.key
						+ "-deliver_time'>"
						+ item.deliver_time
						+ " </span>";
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
	        rdcreceivetb.destroy();
			getRDCReceiveInit();

};



queryRDCReceive=function(){
	getRDCReceiveData();
};

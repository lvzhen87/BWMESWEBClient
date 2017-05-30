var sitetb;
var inputTblRs = [];
var form;
function refreshData() {
	certificate.ajax.reload();
}
function getCertificateInit() {
	
	
	certificatetb = $('#certificatetb')
			.DataTable(
					{
						ordering : true,
						searching : false,
						bStateSave:true,
						"aLengthMenu": [[10, 100, 500,1000, -1], [10, 100, 500,1000, "所有"]],   
						"iDisplayLength":100,   
						search   : {
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
							"title" : "企业代码",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "企业名称",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "车辆生产单位名称",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "车型编码",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "企业名称",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "车辆型号",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "车辆品牌",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "车身颜色",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "操作",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : false,
							"orderable" : false
						}],
						data:inputTblRs});
};
getCertificateData=function(){
	inputTblRs = [];
	 form = new FormData($("#certificate")[0]);
	 form.append("queryNum", $("#setNum").val());
	$.ajax({
		url : "certificate.sp?toList&t="+Math.random(),
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
								+ item.enterpriseCode
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-describe'>"
								+ item.enterpriseName
								+ "</span>";				
						row[3] = "<span id='"
							+ item.key
							+ "-describe'>"
							+ item.vehicleProUnitName
							+ "</span>";			
						row[4] = "<span id='"
							+ item.key
							+ "-describe'>"
							+ item.vehicleModel
							+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-describe'>"
							+ item.enterpriseName
							+ "</span>";
						row[6] = "<span id='"
							+ item.key
							+ "-describe'>"
							+ item.carModel
							+ "</span>";
						row[7] = "<span id='"
							+ item.key
							+ "-describe'>"
							+ item.trademark
							+ "</span>";
						row[8] = "<span id='"
							+ item.key
							+ "-describe'>"
							+ item.biwColour
							+ "</span>";
					 
						row[9] = " <a id='"+item.key+"-edit' href='certificate.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
								" <a id='"+item.key+"-copy' href='certificate.sp?toCopy&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='复制'></i></a>" +
								" <a id='"+item.key+"-del' href='#'" +
								" data-toggle='modal' class='btn btn-danger btn-xs' " +
								" onclick='confirmDelete("+item.key+",deleteCertificate)'><i class='fa fa-ban' title='删除'></i></a>" ;
						
						inputTblRs[k] = row;
						k = k + 1;
							});
			}
		});
			certificatetb.destroy();

			getCertificateInit();
		
};
queryCertificate=function(){
	getCertificateData();
};
queryCertificateInfo=function(){
	var key = $('#vin').val();
 
	if( key == ""){
		return ;
	}
	//alert( 'certificate.sp?toMainPageInfo&vin='+key  +Math.random() );
  	loadpage('certificate.sp?toMainPageInfo&vin='+key  +'&t='+Math.random(),'合格证查询');
};
	


$(document).ready(function() {
  	getCertificateInit();
  	getCertificateData();
					
});

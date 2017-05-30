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
							"title" : "生产企业",
							"targets" : [ 1 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "车辆型号",
							"targets" : [ 2 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						}, {
							"title" : "发动机型号",
							"targets" : [ 3 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "燃料类型",
							"targets" : [ 4 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "排量",
							"targets" : [ 5 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "额定功率",
							"targets" : [ 6 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "变速器类型",
							"targets" : [ 7 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "驱动形式",
							"targets" : [ 8 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "整车整备质量",
							"targets" : [ 9 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "最大设计总质量",
							"targets" : [ 10 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "市区工况",
							"targets" : [ 11 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "综合工况",
							"targets" : [ 12 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "市郊工况",
							"targets" : [ 13 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "标准1",
							"targets" : [ 14 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "阶段1",
							"targets" : [ 15 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "日期1",
							"targets" : [ 16 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "限制1",
							"targets" : [ 17 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "阶段2",
							"targets" : [ 18 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "日期2",
							"targets" : [ 19 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "限制2",
							"targets" : [ 20 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "车型编码",
							"targets" : [ 21 ],
							"visible" : true,
							"searchable" : true,
							"orderable" : true
						},{
							"title" : "操作",
							"targets" : [ 22 ],
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
		url : "CertConfigFuelController.sp?toList&t="+Math.random(),
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
								+ "-producecompany'>"
								+ item.producecompany
								+ " </span>";
						row[2] = "<span id='"
								+ item.key
								+ "-cartype'>"
								+ item.cartype
								+ "</span>";				
						row[3] = "<span id='"
							+ item.key
							+ "-driveengine'>"
							+ item.driveengine
							+ "</span>";			
						row[4] = "<span id='"
							+ item.key
							+ "-fueltype'>"
							+ item.fueltype
							+ "</span>";
						row[5] = "<span id='"
							+ item.key
							+ "-displacement'>"
							+ item.displacement
							+ "</span>";
						row[6] = "<span id='"
							+ item.key
							+ "-powerrate'>"
							+ item.powerrate
							+ "</span>";
						row[7] = "<span id='"
							+ item.key
							+ "-transtype'>"
							+ item.transtype
							+ "</span>";
						row[8] = "<span id='"
							+ item.key
							+ "-drivetype'>"
							+ item.drivetype
							+ "</span>";
						row[9] = "<span id='"
							+ item.key
							+ "-completecarmass'>"
							+ item.completecarmass
							+ "</span>";
						row[10] = "<span id='"
							+ item.key
							+ "-maxdesignmass'>"
							+ item.maxdesignmass
							+ "</span>";
						row[11] = "<span id='"
							+ item.key
							+ "-cityoil'>"
							+ item.cityoil
							+ "</span>";
						row[12] = "<span id='"
							+ item.key
							+ "-alloil'>"
							+ item.alloil
							+ "</span>";
						row[13] = "<span id='"
							+ item.key
							+ "-suburboil'>"
							+ item.suburboil
							+ "</span>";
						row[14] = "<span id='"
							+ item.key
							+ "-countrystandard'>"
							+ item.countrystandard
							+ "</span>";
						row[15] = "<span id='"
							+ item.key
							+ "-stage1'>"
							+ item.stage1
							+ "</span>";
						row[16] = "<span id='"
							+ item.key
							+ "-stagestarttimeyear1'>"
							+ item.stagestarttimeyear1
							+ "</span>";
						row[17] = "<span id='"
							+ item.key
							+ "-limiteoil1'>"
							+ item.limiteoil1
							+ "</span>";
						row[18] = "<span id='"
							+ item.key
							+ "-stage'>"
							+ item.stage
							+ "</span>";
						row[19] = "<span id='"
							+ item.key
							+ "-stagestarttimeyear'>"
							+ item.stagestarttimeyear
							+ "</span>";
						row[20] = "<span id='"
							+ item.key
							+ "-limiteoil'>"
							+ item.limiteoil
							+ "</span>";
						row[21] = "<span id='"
							+ item.key
							+ "-carmodelcode'>"
							+ item.carmodelcode
							+ "</span>";
						
						row[22] = " <a id='"+item.key+"-edit' href='CertConfigFuelController.sp?toAddOrEdit&key="+item.key +
								"' data-toggle='modal' class='btn btn-info btn-xs'  data-backdrop='static' " +
								" data-target='#modifyModal'><i class='fa fa-cog' title='编辑'></i></a>" +
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
	var key = $('#carmodelcode').val();
 
	if( key == ""){
		return ;
	}
	//alert( 'certificate.sp?toMainPageInfo&vin='+key  +Math.random() );
  	loadpage('CertConfigCertController.sp?toMainPageInfo&carmodelcode='+key  +'&t='+Math.random(),'合格证查询');
};
	


$(document).ready(function() {
  	getCertificateInit();
  	getCertificateData();
					
});

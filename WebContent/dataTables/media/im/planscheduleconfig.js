var planscheduleconfigTable;
var inputTblRs = [];
var form;

function getPlanScheduleConfigInit() {
	planscheduleconfigTable = $('#planscheduleconfigTable').DataTable(
			{
				ordering : true,
				searching : false,
				bStateSave : true,
				"aLengthMenu" : [ [ 10, 100, 500, 1000, -1 ],
						[ 10, 100, 500, 1000, "所有" ] ],
				"iDisplayLength" : 100,
				search : {
					"caseInsensitive" : false
				},
				processing : true,
				info : true,
				paging : true,
				scrollY : 450,
				// scrollX: true,
				bScrollCollapse : true,
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
					"title" : "#",
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false,
					"orderable" : false
				}, {
					"title" : "工厂",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "车间",
					"targets" : [ 2 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "车型",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "属性名称",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "子属性名称",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "操作",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				} ],
				data : inputTblRs
			});
};
getPlanScheduleConfigData = function() {
	inputTblRs = [];
	form = new FormData($("#woScheduleConfig")[0]);
	form.append("queryNum", $("#setNum").val());
	$
			.ajax({
				url : "planscheduleconfig.sp?toList&t=" + Math.random(),
				type : 'POST',
				data : form,
				dataType : 'json',
				async : false,
				cache : false,
				processData : false,
				contentType : false,
				success : function(data) {
					var datas = eval(data);
					var k = 0;
					$
							.each(
									datas,
									function(i, item) {
										var row = [];
										console.log(item.key);
										row[0] = "<span><input type='radio' name='selectPlanSchedule'  id='"
												+ item.key
												+ "' value='"
												+ item.key + "'> </span>";
										row[1] = "<span  id='" + item.key
												+ "-factory'>" + item.factory
												+ " </span>";
										row[2] = "<span id='" + item.key
												+ "-workshop'>"
												+ item.workshop + "</span>";
										row[3] = "<span id='" + item.key
												+ "-vehicle_type'>"
												+ item.vehicle_type
												+ "</span>";
										row[4] = "<span id='" + item.key
												+ "-attru_name'>"
												+ item.attru_name
												+ "</span>";
										row[5] = "<span id='" + item.key
												+ "-subattru_name'>" + item.subattru_name
												+ "</span>";
										if (item.contentNumber > 0) {
											row[6] = " <a id='"
													+ item.key
													+ "-edit' href='planscheduleconfig.sp?toAddOrEdit&key="
													+ item.key
													+ "' data-toggle='modal' data-backdrop='static'  class='btn btn-info btn-xs' disabled "
													+ " data-target='#modifyPlanScheduleConfig'><i class='fa fa-cog ' title='修改'></i></a>"
													+ " <a id='"
													+ item.key
													+ "-del' href='#'"
													+ " data-toggle='modal' data-backdrop='static'  class='btn btn-danger btn-xs' disabled "
													+ " onclick='confirmDelete("
													+ item.key
													+ ",deletePlanScheduleConfig)'><i class='fa fa-ban' title='删除'></i></a>";
										} else {
											row[6] = " <a id='"
													+ item.key
													+ "-edit' href='planscheduleconfig.sp?toAddOrEdit&key="
													+ item.key
													+ "' data-toggle='modal' data-backdrop='static'  class='btn btn-info btn-xs' "
													+ " data-target='#modifyPlanScheduleConfig'><i class='fa fa-cog' title='修改'></i></a>"
													+ " <a id='"
													+ item.key
													+ "-del' href='#'"
													+ " data-toggle='modal' data-backdrop='static'  class='btn btn-danger btn-xs' "
													+ " onclick='confirmDelete("
													+ item.key
													+ ",deletePlanScheduleConfig)'><i class='fa fa-ban' title='删除'></i></a>";

										}

										inputTblRs[k] = row;
										k = k + 1;
									});
				}
			});
	planscheduleconfigTable.destroy();
	getPlanScheduleConfigInit();

};


function queryWorkshop(factoryName) {

	  jQuery.ajax({ 
	  url: "planscheduleconfig.sp?toListWorkshop",  
	  async: false,
	  data: {factoryName : factoryName},
	  type : 'POST',
//	  dataType : 'json',
//	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	  success: function(data) { 
//		  alert(data);
//	  var parsedJson = jQuery.parseJSON(data); 
		  var map=eval('('+data+')');
//		  alert(map);
//		  alert(map.result.length);
		  var optionstring = "";
		  var i;
		  for (i=0; i<map.result.length; i++) {
//			  alert(map.result.length);
          var array = map.result[i];
//          alert(array.workshop);
           optionstring += "<option value="+array.workshop+">" + array.workshop + "</option>";

      }
		  $("#workshop").html(optionstring);

	  } });

}

function queryAttru(factoryName, workShopName) {

	  jQuery.ajax({ 
	  url: "planscheduleconfig.sp?toListAttru",  
	  async: false,
	  data: {factoryName : factoryName, workShopName : workShopName},
	  type : 'POST',
//	  dataType : 'json',
//	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	  success: function(data) { 
//		  alert(data);
//	  var parsedJson = jQuery.parseJSON(data); 
		  var map=eval('('+data+')');
//		  alert(map);
//		  alert(map.result.length);
		  var optionstring = "";
		  var i;
		  for (i=0; i<map.result.length; i++) {
//			  alert(map.result.length);
      var array = map.result[i];
//      alert(array.workshop);
       optionstring += "<option value="+array.attru_name+">" + array.attru_name + "</option>";

  }
		  $("#attru_name").html(optionstring);
	  } });

}

function querySubAttru(factoryName, workShopName, attruName) {

	  jQuery.ajax({ 
	  url: "planscheduleconfig.sp?toListSubAttru",  
	  async: false,
	  data: {factoryName : factoryName, workShopName : workShopName, attruName : attruName},
	  type : 'POST',
//	  dataType : 'json',
//	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	  success: function(data) { 
//		  alert(data);
//	  var parsedJson = jQuery.parseJSON(data); 
		  var map=eval('('+data+')');
//		  alert(map);
//		  alert(map.result.length);
		  var optionstring = "";
		  var i;
		  for (i=0; i<map.result.length; i++) {
//			  alert(map.result.length);
        var array = map.result[i];
//        alert(array.workshop);
         optionstring += "<option value="+array.subattru_name+">" + array.subattru_name + "</option>";

    }
		  $("#subattru_name").html(optionstring);
	  } });

}

//function queryWorkshop(factoryName) {
//	$.post("planschedulecon.sp?toListWorkshop", {
//		factoryName : factoryName
//	});
//};
$(document).ready(function() {
	getPlanScheduleConfigInit();

	getPlanScheduleConfigData();

});

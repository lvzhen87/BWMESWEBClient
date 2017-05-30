var planscheduledataTable;
var inputTblRs = [];
var form;

function getPlanScheduleDataInit() {
	planscheduledataTable = $('#planscheduledataTable').DataTable(
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
					"title" : "序号",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "工厂",
					"targets" : [ 2 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "VIN号",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "车型",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "颜色",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "是否特殊车辆",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "CSN号",
					"targets" : [ 7 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},  {
					"title" : "计划完成时间",
					"targets" : [ 8 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},  {
//					"title" : "总装理论上线时间",
//					"targets" : [ 9 ],
//					"visible" : true,
//					"searchable" : true,
//					"orderable" : true
//				},  {
					"title" : "总装理论下线时间",
					"targets" : [ 9 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},  {
//					"title" : "涂装理论上线时间",
//					"targets" : [ 11 ],
//					"visible" : true,
//					"searchable" : true,
//					"orderable" : true
//				},  {
					"title" : "涂装理论下线时间",
					"targets" : [ 10 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				},  {
//					"title" : "焊装理论上线时间",
//					"targets" : [ 13 ],
//					"visible" : true,
//					"searchable" : true,
//					"orderable" : true
//				},  {
					"title" : "焊装理论下线时间",
					"targets" : [ 11 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}],
				data : inputTblRs
			});
};
getPlanScheduleExeData = function() {
	inputTblRs = [];
	form = new FormData($("#woScheduledata")[0]);
	
	//alert(form);
//	form.append("queryNum", $("#setNum").val());
	$
			.ajax({
				url : "planscheduleexe.sp?toList&t=" + Math.random(),
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
												+ "-seq'>" + item.seq
												+ " </span>";
										row[2] = "<span id='" + item.key
												+ "-factory'>"
												+ item.factory + "</span>";
										row[3] = "<span id='" + item.key
												+ "-vin'>"
												+ item.vin
												+ "</span>";
										row[4] = "<span id='" + item.key
												+ "-vehicletype'>"
												+ item.vehicletype
												+ "</span>";
										row[5] = "<span id='" + item.key
												+ "-color'>" + item.color
												+ "</span>";
										row[6] = "<span id='" + item.key
												+ "-isSpecial'>"
												+ item.isSpecial
												+ "</span>";
										row[7] = "<span id='" + item.key
												+ "-csn'>"
												+ item.csn
												+ "</span>";
										row[8] = "<span id='" + item.key
												+ "-completetime'>" + item.completetime
												+ "</span>";
//										row[9] = "<span id='" + item.key
//												+ "-abOnline'>"
//												+ item.abOnline
//												+ "</span>";
										row[9] = "<span id='" + item.key
												+ "-abOffline'>"
												+ item.abOffline
												+ "</span>";
//										row[11] = "<span id='" + item.key
//												+ "-pbOnline'>" + item.pbOnline
//												+ "</span>";
										row[10] = "<span id='" + item.key
												+ "-pbOffline'>"
												+ item.pbOffline
												+ "</span>";
//										row[13] = "<span id='" + item.key
//												+ "-wbOnline'>"
//												+ item.wbOnline
//												+ "</span>";
										row[11] = "<span id='" + item.key
												+ "-wbOffline'>" + item.wbOffline
												+ "</span>";

										
										inputTblRs[k] = row;
										k = k + 1;
									});
				}
			});
	planscheduledataTable.destroy();
	getPlanScheduleDataInit();

};

getPlanScheduleExeResData = function() {
	inputTblRs = [];
	form = new FormData($("#woScheduledata")[0]);
	
	//alert(form);
//	form.append("queryNum", $("#setNum").val());
	$
			.ajax({
				url : "planscheduleexe.sp?toExecute&t=" + Math.random(),
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
												+ "-seq'>" + item.seq
												+ " </span>";
										row[2] = "<span id='" + item.key
												+ "-factory'>"
												+ item.factory + "</span>";
										row[3] = "<span id='" + item.key
												+ "-vin'>"
												+ item.vin
												+ "</span>";
										row[4] = "<span id='" + item.key
												+ "-vehicletype'>"
												+ item.vehicletype
												+ "</span>";
										row[5] = "<span id='" + item.key
												+ "-color'>" + item.color
												+ "</span>";
										row[6] = "<span id='" + item.key
												+ "-isSpecial'>"
												+ item.isSpecial
												+ "</span>";
										row[7] = "<span id='" + item.key
												+ "-csn'>"
												+ item.csn
												+ "</span>";
										row[8] = "<span id='" + item.key
												+ "-completetime'>" + item.completetime
												+ "</span>";
//										row[9] = "<span id='" + item.key
//												+ "-abOnline'>"
//												+ item.abOnline
//												+ "</span>";
										row[9] = "<span id='" + item.key
												+ "-abOffline'>"
												+ item.abOffline
												+ "</span>";
//										row[11] = "<span id='" + item.key
//												+ "-pbOnline'>" + item.pbOnline
//												+ "</span>";
										row[10] = "<span id='" + item.key
												+ "-pbOffline'>"
												+ item.pbOffline
												+ "</span>";
//										row[13] = "<span id='" + item.key
//												+ "-wbOnline'>"
//												+ item.wbOnline
//												+ "</span>";
										row[11] = "<span id='" + item.key
												+ "-wbOffline'>" + item.wbOffline
												+ "</span>";

										
										inputTblRs[k] = row;
										k = k + 1;
									});
				}
			});
	planscheduledataTable.destroy();
	getPlanScheduleDataInit();

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
	getPlanScheduleDataInit();

	getPlanScheduleExeData();

});

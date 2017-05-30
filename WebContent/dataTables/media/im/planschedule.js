var planscheduleTable;
var planscheduleSubAttTable;
var conditionTable;
var inputTblRs = [];
var form;

function getPlanScheduleInit() {
	planscheduleTable = $('#planscheduleTable').DataTable(
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
					"title" : "属性名称",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "属性字段",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
//				}, {
//					"title" : "批次数量",
//					"targets" : [ 5 ],
//					"visible" : true,
//					"searchable" : true,
//					"orderable" : true
				}, {
					"title" : "优先级",
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
getPlanScheduleData = function() {
	inputTblRs = [];
	form = new FormData($("#woSchedule")[0]);
	form.append("queryNum", $("#setNum").val());
	$
			.ajax({
				url : "planschedule.sp?toList&t=" + Math.random(),
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
												+ "-name'>"
												+ item.name
												+ "</span>";
										row[4] = "<span id='" + item.key
												+ "-attribute'>"
												+ item.attribute
												+ "</span>";
//										row[5] = "<span id='" + item.key
//												+ "-batch'>" + item.batch
//												+ "</span>";
										row[5] = "<span id='" + item.key
												+ "-pripority'>"
												+ item.pripority
												+ "</span>";
										if (item.contentNumber > 0) {
											row[6] = " <a id='"
													+ item.key
													+ "-edit' href='planschedule.sp?toAddOrEdit&key="
													+ item.key
													+ "' data-toggle='modal' data-backdrop='static'  class='btn btn-info btn-xs' disabled "
													+ " data-target='#modifyPlanSchedule'><i class='fa fa-cog ' title='修改'></i></a>"
													+ " <a id='"
													+ item.key
													+ "-del' href='#'"
													+ " data-toggle='modal' data-backdrop='static'  class='btn btn-danger btn-xs' disabled "
													+ " onclick='confirmDelete("
													+ item.key
													+ ",deletePlanSchedule)'><i class='fa fa-ban' title='删除'></i></a>";
										} else {
											row[6] = " <a id='"
													+ item.key
													+ "-edit' href='planschedule.sp?toAddOrEdit&key="
													+ item.key
													+ "' data-toggle='modal' data-backdrop='static'  class='btn btn-info btn-xs' "
													+ " data-target='#modifyPlanSchedule'><i class='fa fa-cog' title='修改'></i></a>"
													+ " <a id='"
													+ item.key
													+ "-del' href='#'"
													+ " data-toggle='modal' data-backdrop='static'  class='btn btn-danger btn-xs' "
													+ " onclick='confirmDelete("
													+ item.key
													+ ",deletePlanSchedule)'><i class='fa fa-ban' title='删除'></i></a>";

										}

										inputTblRs[k] = row;
										k = k + 1;
									});
				}
			});
	planscheduleTable.destroy();
	getPlanScheduleInit();

};

function getplanscheduleSubAttTableInit() {
	planscheduleSubAttTable = $('#planscheduleSubAttTable').DataTable(
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
				scrollY : 225,
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
					"title" : "ID",
					"targets" : [ 0 ],
					"visible" : false,
					"searchable" : false,
					"orderable" : false
				}, {
					"title" : "子属性名称",
					"targets" : [ 1 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "子属性字段",
					"targets" : [ 2 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "子优先级",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "批次数量",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "操作",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}],
				data : inputTblRs
			});
};
getplanscheduleSubAttData = function(parentKey) {
	inputTblRs = [];
	form = new FormData($("#woSchedule")[0]);
	form.append("parentKey", parentKey);
	$
			.ajax({
				url : "planschedulesub.sp?toList&t=" + Math.random(),
				type : 'POST',
				data : form,
				dataType : 'json',
				async : false,
				cache : false,
				processData : false,
				contentType : false,
				success : function(data) {
					var datas = eval(data);
					if (datas == null) {
						return;
					}

					var k = 0;
					$
							.each(
									datas,
									function(i, item) {
										var row = [];
										console.log(item.key);
										row[0] = item.key;
										
										row[1] = "<span  id='" + item.key
												+ "-subname'>" + item.subname
												+ " </span>";
										row[2] = "<span id='" + item.key
												+ "-subattribute'>"
												+ item.subattribute + "</span>";
										row[3] = "<span id='" + item.key
												+ "-subpripority'>"
												+ item.subpripority + "</span>";
										row[4] = "<span id='" + item.key
												+ "-subBatch'>"
												+ item.subBatch + "</span>";
										if (item.used) {

											row[5] = " <a id='"
													+ item.key
													+ "-edit' href='planschedulesub.sp?toAddOrEdit&key="
													+ item.key
													+ "&parentKey="
													+ parentKey
													+ "' data-toggle='modal' data-backdrop='static'  class='btn btn-info btn-xs' "
													+ " data-target='#modifyPlanScheduleSubAttTable' disabled='disabled'><i class='fa fa-cog' title='修改'></i></a>"
													+ " <a id='"
													+ item.key
													+ "-del' href='#'"
													+ " data-toggle='modal' data-backdrop='static'  class='btn btn-danger btn-xs' "
													+ " onclick='confirmDelete("
													+ item.key												
													+ ",deleteSubAttru)' disabled='disabled'><i class='fa fa-ban' title='删除'></i></a>";
										} else {
											row[5] = " <a id='"
													+ item.key
													+ "-edit' href='planschedulesub.sp?toAddOrEdit&key="
													+ item.key
													+ "&parentKey="
													+ parentKey
													+ "' data-toggle='modal' data-backdrop='static'  class='btn btn-info btn-xs' "
													+ " data-target='#modifyPlanScheduleSubAttTable'><i class='fa fa-cog' title='修改'></i></a>"
													+ " <a id='"
													+ item.key
													+ "-del' href='#'"
													+ " data-toggle='modal' data-backdrop='static'  class='btn btn-danger btn-xs' "
													+ " onclick='confirmDelete("
													+ item.key
													+ ",deleteSubAttru)'><i class='fa fa-ban' title='删除'></i></a>";
										}

										inputTblRs[k] = row;
										k = k + 1;
									});
				}
			});
	planscheduleSubAttTable.destroy();
	getplanscheduleSubAttTableInit();

};

function getConditionInit() {
	conditionTable = $('#conditionTable').DataTable(
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
				scrollY : 225,
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
					"title" : "ID",
					"targets" : [ 0 ],
					"visible" : false,
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
					"title" : "类型",
					"targets" : [ 3 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "禁止条件A",
					"targets" : [ 4 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "禁止条件B",
					"targets" : [ 5 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : " 间隔数",
					"targets" : [ 6 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}, {
					"title" : "操作",
					"targets" : [ 7 ],
					"visible" : true,
					"searchable" : true,
					"orderable" : true
				}],
				data : inputTblRs
			});
};
getConditionData = function() {
	inputTblRs = [];
	form = new FormData($("#woSchedule")[0]);
//	form.append("atDefinitionKey", atDefinitionKey);
	$
			.ajax({
				url : "planschedulecon.sp?toList&t=" + Math.random(),
				type : 'POST',
				data : form,
				dataType : 'json',
				async : false,
				cache : false,
				processData : false,
				contentType : false,
				success : function(data) {
					var datas = eval(data);
					if (datas == null) {
						return;
					}
					var k = 0;
					$
							.each(
									datas,
									function(i, item) {
										var row = [];
										console.log(item.key);
										row[0] = item.key;
										row[1] = "<span  id='" + item.key
												+ "-factory'>" + item.factory
												+ " </span>";
										row[2] = "<span id='" + item.key
												+ "-workshop'>"
												+ item.workshop + "</span>";
										row[3] = "<span id='" + item.key
												+ "-type'>"
												+ item.type + "</span>";
										row[4] = "<span id='" + item.key
												+ "-subattru_a'>" + item.subattru_a
												+ "</span>";
										row[5] = "<span id='" + item.key
												+ "-subattru_b'>" + item.subattru_b
												+ "</span>";
										row[6] = "<span id='" + item.key
												+ "-interval'>" + item.interval
												+ "</span>";
										row[7] = " <a id='"
												+ item.key
												+ "-edit' href='planschedulecon.sp?toAddOrEdit&key="
												+ item.key
												+ "' data-toggle='modal' data-backdrop='static' class='btn btn-info btn-xs' "
												+ " data-target='#modifyCondition'><i class='fa fa-cog' title='修改'></i></a>"
												+ " <a id='"
												+ item.key
												+ "-del' href='#'"
												+ " data-toggle='modal' data-backdrop='static' class='btn btn-danger btn-xs' "
												+ " onclick='confirmDelete("
												+ item.key
												+ ",deleteCondition)'><i class='fa fa-ban' title='删除'></i></a>";

										inputTblRs[k] = row;
										k = k + 1;
									});
				}
			});
	conditionTable.destroy();
	getConditionInit();

};

deleteSubAttru = function(key) {
	$.post("planschedulesub.sp?del", {
		key : key
	}, function(result) {
		operationConfirm(result);
	});

	getplanscheduleSubAttData(parentKey);
	getplanscheduleSubAttData(parentKey);
};

deleteCondition = function(key) {
	$.post("planschedulecon.sp?del", {
		key : key
	}, function(result) {
		operationConfirm(result);
	});
//	getConditionInit();
	getConditionData();
	
	getConditionData();
};

queryPlanSchedule = function() {
	getPlanScheduleData();
//	queryATColumnDefinition(-1);
//	queryATIndexDefinition(-1);
};
queryPlanScheduleSubAtt = function(parentKey) {
	getplanscheduleSubAttData(parentKey);
};
queryATIndexDefinition = function(atDefinitionKey) {
	getATIndexDefinitionData(atDefinitionKey);
};

function queryWorkshop(factoryName) {

	  jQuery.ajax({ 
	  url: "planschedulecon.sp?toListWorkshop",  
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
		  $("#workshop1").html(optionstring);

	  } });

}

function querySubAttru(factoryName, workShopName, type) {

	  jQuery.ajax({ 
	  url: "planschedulecon.sp?toListSubAttru",  
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
//        alert(array.workshop);
         optionstring += "<option value="+array.subattru_a+">" + array.subattru_a + "</option>";

    }
		  $("#subattru_a").html(optionstring);
		  if(type !=2)
		  {
			  $("#subattru_b").html(optionstring);
		  }
		  
	  } });

}

//function queryWorkshop(factoryName) {
//	$.post("planschedulecon.sp?toListWorkshop", {
//		factoryName : factoryName
//	});
//};
$(document).ready(function() {
	
	getConditionInit();
	getConditionData();
	
	getPlanScheduleInit();



	getPlanScheduleData();


	getplanscheduleSubAttTableInit();
	getplanscheduleSubAttData();
});

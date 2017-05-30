<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	<%@ include
	file="/common/includeJS.inc.jsp"%>
	<script type="text/javascript" src="dataTables/media/im/planschedule.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="woSchedule" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">工厂:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="factory" id="factory"
							value="${vo.factory}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">车间:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="workshop" id="workshop"
							value="${vo.workshop}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">属性名称:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="name" id="name"
							value="${vo.name}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success pull-right" type="button" onclick="queryPlanSchedule()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>


<section class="panel col-lg-12">
	<header class="panel-heading">
		排程属性 <span> <a href="planschedule.sp?toAddOrEdit"
		data-backdrop="static"
			data-toggle="modal" class="btn btn-primary  btn-xs"
			data-target="#modifyPlanSchedule"><i class="fa fa-plus"
				title="新增"></i></a>
		</span> <span class="tools pull-right" onclick="adjust(planscheduleTable)"> <a href="javascript:;"
			class="fa fa-chevron-down"></a>
		</span>
			<c:import url="/common/setNum.jsp"></c:import>
	</header>
	<div class="panel-body">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-condensed"
				id="planscheduleTable">
				
			</table>
		</div>
	</div>
</section>

	<section class="panel col-lg-5" >
		<header class="panel-heading">
			子排程属性<span> <a  data-backdrop="static" data-toggle="modal"
				id="addPlanscheduleSubAttBtn"
				onclick="dopost('subAttruModal','planschedulesub.sp?toAddOrEdit')"
				class="btn btn-primary  btn-xs"
				data-target="#modifyPlanScheduleSubAttTable"><i class="fa fa-plus"
					title="新增"></i></a>
			</span> <span class="tools pull-right" onclick="adjust(planscheduleSubAttTable)"> <a href="javascript:;"
				class="fa fa-chevron-down"></a>
			</span>
		</header>
		<div class="panel-body">
			<div class="col-md-12">
				<table class="table table-hover table-striped table-condensed"
					id="planscheduleSubAttTable">
					
				</table>
			</div>
		</div>
	</section>
	
	
	<br>
	
	<section class="panel col-lg-7" >
		<header class="panel-heading">
	    	禁止条件
	    	<!--  <span>
	        <a href="planschedulecon.sp?toAddOrEdit" data-backdrop="static" data-toggle="modal" id="addConditionBtn" class="btn btn-primary  btn-xs"
			data-target="#modifyCondition"><i class="fa fa-plus"
				title="新增"></i></a>
			</span>-->
			<span>
	         	<a data-backdrop="static" data-toggle="modal" id="addConditionBtn" onclick="dopost1('conModal','planschedulecon.sp?toAddOrEdit')"
					class="btn btn-primary  btn-xs" data-target="#modifyCondition"><i class="fa fa-plus" title="新增"></i></a>
			</span>
	    	<span class="tools pull-right" onclick="adjust(conditionTable)">
	        	<a href="javascript:;" class="fa fa-chevron-down"></a>
	       	</span>
	    </header>
		<div class="panel-body">
			<div class="col-md-12" >
				<table class="table table-hover table-striped table-condensed" id="conditionTable">
				</table>
			</div>
		</div>
	</section>



<!-- ADD OR EDIT -->
<div aria-hidden="true" aria-labelledby="modifyPlanSchedule"
	role="dialog" tabindex="-1" id="modifyPlanSchedule" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>

<div aria-hidden="true" aria-labelledby="modifyPlanScheduleSubAttTable"
	role="dialog" tabindex="-1" id="modifyPlanScheduleSubAttTable"
	class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="subAttruModal"></div>
	</div>
</div>

<div aria-hidden="true" aria-labelledby="modifyCondition"
	role="dialog" tabindex="-1" id="modifyCondition"
	class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="conModal"></div>
	</div>
</div>


<script type="text/javascript">
var parentKey = -1;
var atIndexDefinitionKey = -1;

//解决重复加载时不更新数据问题
$("#modifyPlanSchedule").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});

//解决重复加载时不更新数据问题
$("#modifyPlanScheduleSubAttTable").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
}); 


$("#modifyCondition").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
}); 

deletePlanSchedule = function(key)
{
	$.post("planschedule.sp?del",{key:key},function(result){
    	operationConfirm(result);
    	getPlanScheduleData();
    	queryPlanScheduleSubAtt(-1);
    	//queryATIndexDefinition(-1);
    	$("#addPlanscheduleSubAttBtn").hide();
    	//$("#addATIndexDefinitionBtn").hide();
  	});
}

$("#planscheduleTable tbody tr").die().live("click",function(){});
//点击行事件
$("#planscheduleTable tbody tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
	parentKey = radio.val();
	//判断是否有数据
	if(!radio.is(":checked"))
	{
		return ;
	}
//	alert("AAAA  "+parentKey);
	queryPlanScheduleSubAtt(parentKey);
//	queryATIndexDefinition(atDefinitionKey);
	$("#addPlanscheduleSubAttBtn").show();
//	$("#addATIndexDefinitionBtn").show();
})


function dopost(tModal,url)
{
	$("#"+tModal).load(url,{parentKey:parentKey});
}
function dopost1(tModal,url)
{
	$("#"+tModal).load(url);
}
$(document).ready(function(){
	$("#addPlanscheduleSubAttBtn").hide();
	//$("#addATIndexDefinitionBtn").hide();
});


</script>

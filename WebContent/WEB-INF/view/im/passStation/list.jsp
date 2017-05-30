<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	<%@ include
	file="/common/includeJS.inc.jsp"%>
	<script type="text/javascript" src="dataTables/media/im/planscheduleexe.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="woScheduledata" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">排程日期:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="completetime" id="completetime"
							value="${vo.completetime}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success pull-right" type="button" onclick="getPlanScheduleExeData()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
					
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success pull-right" type="button" onclick="getPlanScheduleExeResData()" style=" padding-left: 25px; padding-right: 25px; ">排程</button>
					</div>
					
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success pull-right" type="button" onclick="getPlanScheduleExeData()" style=" padding-left: 25px; padding-right: 25px; ">确认</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>


<section class="panel col-lg-12">
	<!--  <header class="panel-heading">
		车型排程属性配置 <span> <a href="planscheduleconfig.sp?toAddOrEdit"
		data-backdrop="static"
			data-toggle="modal" class="btn btn-primary  btn-xs"
			data-target="#modifyPlanScheduleConfig"><i class="fa fa-plus"
				title="新增"></i></a>
		</span> <span class="tools pull-right" onclick="adjust(planscheduleconfigTable)"> <a href="javascript:;"
			class="fa fa-chevron-down"></a>
		</span>
			<c:import url="/common/setNum.jsp"></c:import>
	</header>-->
	<div class="panel-body">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-condensed"
				id="planscheduledataTable">
				
			</table>
		</div>
	</div>
</section>






<script type="text/javascript">
var parentKey = -1;
var atIndexDefinitionKey = -1;

//解决重复加载时不更新数据问题
$("#modifyPlanScheduleConfig").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});


queryPlanScheduledata = function()
{
	var time = $('#name').val();
	
	//$.post("planscheduleexe.sp?del",{time : time},function(){
    	//operationConfirm(result);
    	getPlanScheduleConfigDataRes(time);
    	//queryPlanScheduleSubAtt(-1);
    	//queryATIndexDefinition(-1);
    	//$("#addPlanscheduleSubAttBtn").hide();
    	//$("#addATIndexDefinitionBtn").hide();
  	//});
}

deletePlanScheduleConfig = function(key)
{
	$.post("planscheduleconfig.sp?del",{key:key},function(result){
    	operationConfirm(result);
    	getPlanScheduleConfigData();
    	//queryPlanScheduleSubAtt(-1);
    	//queryATIndexDefinition(-1);
    	//$("#addPlanscheduleSubAttBtn").hide();
    	//$("#addATIndexDefinitionBtn").hide();
  	});
}

</script>

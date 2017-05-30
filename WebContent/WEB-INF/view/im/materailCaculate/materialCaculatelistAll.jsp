<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/common/include.inc.jsp"%>
<%@ include file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/materialCaculateAll.js"></script>



<!--pickers css-->
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datepicker/css/datepicker-custom.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-timepicker/css/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datetimepicker/css/datetimepicker-custom.css" />


<!--pickers plugins-->
<script type="text/javascript"
	src="js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript"
	src="js/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript"
	src="js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>


<script src="js/pickers-init.js"></script>


<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="plan" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">计划号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="planId"
							value="${vo.planId}"></input>
					</div>
					<label class="col-lg-1 col-sm-1 control-label">开始日期:</label>
				<div class="col-lg-2 col-sm-2">
					<input type="text" class="form-control default-date-picker" name="planStartTime"></input>
				</div>
				<label class="col-lg-1 col-sm-1 control-label">结束日期:</label>
				<div class="col-lg-2 col-sm-2">
					<input type="text" class="form-control default-date-picker" name="planEndTime"></input>
				</div>
				<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
					<button class="btn btn-success" type="button" onclick="queryPlan()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
				</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">
    	
		<input type="hidden" id="thisPlan" value="-1">
    	<span class="tools pull-right" onclick="adjust(plantb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
       	<c:import url="/common/setNum.jsp"></c:import>
    </header>
	<div class="panel-body">
	<div id="alertMessage"></div>
		<div class="col-md-12" >
			<table class="table table-hover table-striped table-condensed" id="plantb">
			</table>
		</div>
	</div>
	
	
	
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">
    	<span>
         	<a class="btn btn-success" type="button" href="javascript:;" onclick="caculateMaterial()" style=" padding-left: 25px; padding-right: 25px; ">需求计算</a>
		</span>
		 
    </header>
	<div class="panel-body">
	<div id="alertMessage"></div>
		<div class="col-md-12" >
			<table class="table table-hover table-striped table-condensed" id="plantb1">
			</table>
		</div>
	</div>
</section>

<script type="text/javascript">
///////////////
var planKey;
$('input[id=myfile]').change(function() {
    $('#photoCover').val($(this).val());
  });
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  
$("#selectModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal"); 
});  


//点击行事件
$("#plantb tbody tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
	planKey = radio.val();
	$("#thisPlan").val(planKey);
})

var checkedPlan = function()
{
	var checkedPlanId = $("#thisPlan").val()+'-key';
	$("#"+checkedPlanId).attr("checked","checked");
}

$(document).ready(function(){
});
////////////////新增//////////////////
var decompose= function(){
	var key = $("input[name='selectPlan']:checked").val();
	if(key == null || key == "undefined"){
		bootbox.alert("请选择一条要分解的计划");
		return;
	} 
	var status=$("#"+key+"-status").html();
	if(status=="分解"){
		bootbox.alert("您选择的计划已分解，不能重复分解");
		return ;
	}
	
	$.post("plan.sp?decompose",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
			$("#thisPlan").val(-1);
    		getPlanData();
		}
		bootbox.alert(result);
  	});
}
</script>

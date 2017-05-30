<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%@ include	file="/common/includeJS.inc.jsp"%>


<!-- 
	计划调整 与 发布

 -->


<script type="text/javascript" src="qj/js/order.js"></script>
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
		<form id="order" class="form-horizontal adminex-form">
			<div class="form-group">
				<label class="col-lg-1 col-sm-1 control-label">订单号:</label>
				<div class="col-lg-2 col-sm-2">
					<input type="text" class="form-control" name="orderNumber"></input>
				</div>
				<label class="col-lg-1 col-sm-1 control-label">VIN:</label>
				<div class="col-lg-2 col-sm-2">
					<input type="text" class="form-control" name="vin"></input>
				</div>
				<label class="col-lg-1 col-sm-1 control-label">计划状态:</label>
				<div class="col-lg-2 col-sm-2">
					<select id="typeCode" name="status" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						<option value="-100">全部</option>
						<option value="0">分解</option>
						<option value="1">锁定</option>
						<option value="2">发布</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-1 col-sm-1 control-label">下线开始日期:</label>
				<div class="col-lg-2 col-sm-2">
					<input type="text" class="form-control default-date-picker" name="planStartTime"></input>
				</div>
				<label class="col-lg-1 col-sm-1 control-label">下线结束日期:</label>
				<div class="col-lg-2 col-sm-2">
					<input type="text" class="form-control default-date-picker" name="planEndTime"></input>
				</div>
				<div class="col-sm-2" style=" width: 130px; padding-left: 100px; ">
					<button class="btn btn-success" type="button" onclick="queryOrder()" style="padding-left: 25px; padding-right: 25px; ">查询</button>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading"
		style="padding-left: 35px; padding-right: 35px;">
		<span> 
			<a class="btn btn-success" type="button"
				href="javascript:void(0);" onclick="hold()"
				style="padding-left: 25px; padding-right: 25px;">锁定
			</a>
		</span>
		<!-- <span> 
			<a class="btn btn-success" type="button"
				href="javascript:void(0);" onclick="release()"
				style="padding-left: 25px; padding-right: 25px;">解锁
			</a>
		</span>  -->
		<span> 
			<a class="btn btn-success" type="button"
				href="javascript:void(0);" onclick="publish()"
				style="padding-left: 25px; padding-right: 25px;">发布
			</a>
		</span> 
		<input type="hidden" id="thisOrder" value="-1"> 
		<span class="tools pull-right" onclick="adjust(ordertb)"> 
			<a href="javascript:;" class="fa fa-chevron-down"></a>
		</span>
		<c:import url="/common/setNum.jsp"></c:import>
	</header>
	<div class="panel-body">
		<div class="col-md-12" >
			<table class="table table-hover table-striped table-condensed" id="ordertb">
			</table>
		</div>
	</div>
</section>


<script type="text/javascript">
var orderKey;
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  
$("#selectModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal"); 
});  

$(document).ready(function(){
});

function upStep (key){
	$.post("plan.sp?up",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
			$("#thisOrder").val(-1);
    		getOrderData();
		}else{
			bootbox.alert(result);
		}
		
  	});
}
function downStep (key){
	$.post("plan.sp?down",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
			$("#thisOrder").val(-1);
    		getOrderData();
		}else{
			bootbox.alert(result);
		}
  	});
}
function hold(){
	var keys=null;
	var error=null;
	$('input:checkbox[name=selectOrder]:checked').each(function(i) {
		var table = $('#ordertb').DataTable();
		var index = table.row($(this).parents('tr')).index();
		var status =  table.cells(index,6).data()[0];
		error = validateStatusForHold(status);
		if(error!=null){
			return false;
		}
		if (0 == i) {
			keys = $(this).val();
		} else {
			keys += ("," + $(this).val());
		}
	});
	if(error!=null){
		bootbox.alert(error);
		return false;
	}
	if(keys==null){
		bootbox.alert("请至少选择一行数据");
		return false;
	}
	$.post("plan.sp?hold",{keys:keys},function(result){
		if(eval(result)=="操作成功"){
    		getOrderData();
		}
		bootbox.alert(result);
  	});
}
function release(){
	var keys=null;
	var error=null;
	$('input:checkbox[name=selectOrder]:checked').each(function(i) {
		var table = $('#ordertb').DataTable();
		var index = table.row($(this).parents('tr')).index();
		var status =  table.cells(index,13).data()[0];
		alert( status )
		error = validateStatusForRelease(status);
		if(error!=null){
			return false;
		}
		if (0 == i) {
			keys = $(this).val();
		} else {
			keys += ("," + $(this).val());
		}
	});
	if(error!=null){
		bootbox.alert(error);
		return false;
	}
	if(keys==null){
		bootbox.alert("请至少选择一行数据");
		return false;
	}
	$.post("plan.sp?release",{keys:keys},function(result){
		if(eval(result)=="操作成功"){
    		getOrderData();
		}
		bootbox.alert(result);
  	});
}
function publish(){
	var keys=null;
	var error=null;
	$('input:checkbox[name=selectOrder]:checked').each(function(i) {
		var table = $('#ordertb').DataTable();
		var index = table.row($(this).parents('tr')).index();
		var status =  table.cells(index,6).data()[0];
		error = validateStatusForPublish(status);
		if(error!=null){
			return false;
		}
		if (0 == i) {
			keys = $(this).val();
		} else {
			keys += ("," + $(this).val());
		}
		
	});
	if(error!=null){
		bootbox.alert(error);
		return false;
	}
	if(keys==null){
		bootbox.alert("请至少选择一行数据");
		return false;
	}
	$.post("plan.sp?publish",{keys:keys},function(result){
		if(eval(result)=="操作成功"){
    		getOrderData();
		}
		bootbox.alert(result);
  	});
}
function validateStatusForPublish(status){
	var error=null;
	if(status.indexOf("锁定")>0){
		error = "您选择的数据包含已锁定的数据，不能发布，请重新选择";
		return error;
	}
	if(status.indexOf("发布")>0){
		error = "您选择的数据包含已发布的数据，不能重复发布，请重新选择";
		return error;
	}
	return error;
}
function validateStatusForHold(status){
	var error=null;
	if(status.indexOf("锁定")>0){
		error = "您选择的数据包含已锁定的数据，不能重复锁定，请重新选择";
		return error;
	}
	if(status.indexOf("发布")>0){
		error = "您选择的数据包含已发布的数据，不能锁定，请重新选择";
		return error;
	}
	return error;
}
function validateStatusForRelease(status){
	var error=null;
	if(status.indexOf("锁定")<=0){
		error = "您选择的数据包含未锁定的数据，不能解锁，请重新选择";
		return error;
	}
	return error;
}
</script>

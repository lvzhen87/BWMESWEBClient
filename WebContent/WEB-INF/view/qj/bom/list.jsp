<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%@ include file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="qj/js/bom.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="bom" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">BOM编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="bomNumber"
							value="${vo.bomNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">BOM名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="bomName"
							value="${vo.bomName}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success" type="button" onclick="queryBom()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">
		<span>
         	<a class="btn btn-success" type="button" href="javascript:loadpage('qjbom.sp?downloadBom','BOM接收')" style=" padding-left: 25px; padding-right: 25px; ">接收</a>
		</span>
    	<span class="tools pull-right" onclick="adjust(bomtb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
       	<c:import url="/common/setNum.jsp"></c:import>
       	<input type="hidden" id="thisBom" value="-1">
       	<input type="hidden" id="thisBomItem" value="-1">
    </header>
	<div class="panel-body">
		<div class="col-md-12" >
			<table id="bomtb" class="table table-hover table-striped table-condensed">
			</table>
		</div>
	</div>
</section>

<section class="panel">
	<header class="panel-heading">
		 Bill Of Materials Items
		 <span>
         	<a href="#" data-toggle="modal"
				class="btn btn-primary  btn-xs" data-backdrop="static" data-target="#modifyModal" id="addBomItemButton"><i class="fa fa-plus"></i></a>
		</span>
    	<span class="tools pull-right" onclick="adjust(bomItemtb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
    </header>
	<div class="panel-body">
		<div class="col-md-12" >
			<table id="bomItemtb" class="table table-hover table-striped table-condensed">
			</table>
		</div>
	</div>
</section>


<!-- ADD OR EDIT -->
<div aria-hidden="true" aria-labelledby="modify" role="dialog"
	tabindex="-1" id="modifyModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>

<!-- SELECT -->
<div aria-hidden="true" aria-labelledby="select" role="dialog"
	tabindex="-1" id="selectModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="selectContent"></div>
	</div>
</div>

<script type="text/javascript">
var bomKey;
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  
$("#selectModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  

//删除BOM
deleteBOM = function(key)
{
	$.post("qjbom.sp?del",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
			$("#thisBom").val("-1");
			$("#thisBomItem").val("-1");
    		getBomData();
    		getBomItemData($("#thisBom").val());
    		getConsumptionPlanData($("#thisBom").val(), $("#thisBomItem").val());
    		getAlternateBomItemData($("#thisBom").val(), $("#thisBomItem").val());
    		$("#addBomItemButton").hide();
    		$("#addConsumptionPlanButton").hide();
    		$("#addAlternateBomItemButton").hide();
		}
		operationConfirm(result);
  	});
}

//删除BOMItem
deleteBomItem = function(bomKey,key)
{
	$.post("qjbom.sp?delBomItem",{bomKey:bomKey,key:key},function(result){
		if(eval(result)=="操作成功")
		{
			$("#thisBomItem").val(-1);
    		getBomItemData($("#thisBom").val());
    		getConsumptionPlanData($("#thisBom").val(), $("#thisBomItem").val());
    		getAlternateBomItemData($("#thisBom").val(), $("#thisBomItem").val());
    		$("#addConsumptionPlanButton").hide();
    		$("#addAlternateBomItemButton").hide();
		}
		operationConfirm(result);
  	});
}
//删除ConsumptionPlan
deleteConsumptionPlan = function(bomKey,bomItemKey,key)
{
	$.post("qjbom.sp?delConsumptionPlan",{bomKey:bomKey,bomItemKey:bomItemKey,key:key},function(result){
		if(eval(result)=="操作成功")
		{
    		getConsumptionPlanData(bomKey,bomItemKey);
		}
		operationConfirm(result);

  	});
}
//删除AlternateBomItem
deleteAlternateBomItem = function(bomKey,bomItemKey,key)
{
	$.post("qjbom.sp?delAlternateBomItem",{bomKey:bomKey,bomItemKey:bomItemKey,key:key},function(result){
		if(eval(result)=="操作成功")
		{
    		getAlternateBomItemData(bomKey,bomItemKey);
		}
		operationConfirm(result);
  	});
}

//BOM点击行事件
$("#bomtb tbody tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
	bomKey = radio.val();
	$("#thisBom").val(bomKey);
	$("#thisBomItem").val(-1);
	getBomItemData($("#thisBom").val());
	getConsumptionPlanData($("#thisBom").val(), $("#thisBomItem").val());
	getAlternateBomItemData($("#thisBom").val(), $("#thisBomItem").val());
	$("#consumptionPlanDataSubContent").empty();
	$("#alternateBomItemDataSubContent").empty();
	$("#addConsumptionPlanButton").hide();
	$("#addAlternateBomItemButton").hide();
	$("#addBomItemButton").show();
	$("#addBomItemButton").attr('href','qjbom.sp?toAddOrEditBomItem&bomKey=' + bomKey);
})
//BOMItem点击行事件
$("#bomItemtb tbody tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
	var bomItemKey = radio.val();
	$("#thisBomItem").val(bomItemKey);
	$("#addConsumptionPlanButton").attr('href','qjbom.sp?toAddOrEditConsumptionPlan&bomKey=' + bomKey + '&bomItemKey=' + bomItemKey);
	$("#addConsumptionPlanButton").show();
	getConsumptionPlanData($("#thisBom").val(), bomItemKey)
	$("#addAlternateBomItemButton").attr('href','qjbom.sp?toAddOrEditAlternateBomItem&bomKey=' + bomKey + '&bomItemKey=' + bomItemKey);
	$("#addAlternateBomItemButton").show();
	getAlternateBomItemData($("#thisBom").val(), bomItemKey)
})

checkedBom = function()
{
	var checkedBomId = $("#thisBom").val()+'-key';
	$("#"+checkedBomId).attr("checked","checked");
	getBomItemData($("#thisBom").val());
	
}

checkedBomItem = function()
{
	var checkedBomItemId = $("#thisBomItem").val()+'-key';
	$("#"+checkedBomItemId).attr("checked","checked");
	getConsumptionPlanData($("#thisBom").val(), $("#thisBomItem").val());
	getAlternateBomItemData($("#thisBom").val(), $("#thisBomItem").val());
}

$(document).ready(function(){
	$("#addBomItemButton").hide();
	$("#addConsumptionPlanButton").hide();
	$("#addAlternateBomItemButton").hide();
});


</script>

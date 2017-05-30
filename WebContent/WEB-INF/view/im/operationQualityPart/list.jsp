<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/operationQualityPart.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="workCenter" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">工厂名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="factoryName"
							value="${vo.factoryName}"></input>
					</div>

					<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">品牌:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="brandName"
							value="${vo.brandName}"></input>
					</div>
				</div>
					<div><br></div>
					<div class="row">
					
						<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">一级质量门:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="qualityGate_1"
								value="${vo.qualityGate_1}"></input>
						</div>
						
						<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">二级质量门:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="qualityGate_2"
								value="${vo.qualityGate_2}"></input>
						</div>
						</div>
						<div><br></div>
						<div class="row">
						
						<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">层级1:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="level1"
								value="${vo.level1}"></input>
						</div>
						
						<div class="col-sm-2">
							<button class="btn btn-success" type="button" onclick="queryList()">查询</button>
						</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading">
    	质量零部件
    	<span>
         	<a href="OperationQualityPartController.sp?toAddOrEdit" data-toggle="modal" data-backdrop="static"
				class="btn btn-primary  btn-xs" data-target="#modifyModal"><i class="fa fa-plus" title="新增"></i></a>
		</span>
    	<span class="tools pull-right" onclick="adjust(workCentertb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
   		<c:import url="/common/setNum.jsp"></c:import>
    </header>
	<div class="panel-body">
		<div class="col-md-12">
			<table id="qualityGateTB" class="table table-hover table-striped table-condensed">
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

<!-- ADD EQUIPMENTS -->
<div aria-hidden="true" aria-labelledby="addEquipmentModal" role="dialog"
	tabindex="-1" id="addEquipmentModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="addEquipmentContent">
		</div>
	</div>
</div>

<script type="text/javascript">
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  

//解决重复加载时不更新数据问题
$("#addEquipmentModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  


deleteWorkCenter = function(key)
{
	$.post("OperationQualityPartController.sp?del",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
    		getWorkCenterData();
		}
		operationConfirm(result);
  	});
}
</script>

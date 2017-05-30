<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	
<script type="text/javascript">
	deleteEquipment = function(key)
	{
		$("#"+key).remove();
	}
	
	toSubmit = function(form) {
		//提交表单
		var $form = $("#form1");
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyModal").modal("hide");
			getWorkCenterData();
			operationConfirm(result);
			
		});
		return false;
	}
	 
</script>
	
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#basic" data-toggle="tab">${invokeType}</a>
			</li>
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" id="form1" action="QualityGateController.sp?save"
			  class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="workCenterNumber"
							class="col-lg-3 col-sm-3 control-label"><span class="text-danger">*</span>一级质量门</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" id="qualityGate_1" name="qualityGate_1" id="qualityGate_1" 
								value="${vo.qualityGate_1 }"placeholder="必填">
							<input type="hidden" name="key"	value="${vo.key }">
						</div>
					</div>
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">二级质量门</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="qualityGate_2"
								value="${vo.qualityGate_2 }">
						</div>
					</div>
					
					
					<a href="utility.sp?toSingleFactoryBrand" data-toggle="modal"
							class="btn btn-success  btn-sm" data-target="#addEquipmentModal">工厂品牌<i class="fa fa-plus" title="新增"></i></a>
					
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">工厂名称</label>
							 
						<div class="col-lg-9">
						
						<input type="hidden"  class="form-control"  id="fbKey" name= "fbKey" value="${vo.fbKey}" >
								
							<input type="text" class="form-control"  readonly="readonly" id="factoryName" name= "factoryName"
								value="${vo.factoryName }">
						</div>
					</div>
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">品牌</label>
						<div class="col-lg-9">
							<input type="text" readonly="readonly" class="form-control" id="brandName" name= "brandName"
								value="${vo.brandName }">
						</div>
					</div>
				</div>
				 
				 
				<div class="form-group">
					<div class="col-lg-offset-9 col-lg-3">
						<button type="button" class="btn btn-primary" onclick="toSubmit()">保存</button>
						<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>


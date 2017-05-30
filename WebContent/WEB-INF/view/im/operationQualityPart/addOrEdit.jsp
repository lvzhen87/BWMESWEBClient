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
		<form role="form" id="form1" action="OperationQualityPartController.sp?save"
			  class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<input type="hidden" name="key"	value="${vo.key }">
					
					<a href="utility.sp?toSingleFactoryBrandQualityGate" data-toggle="modal"
								class="btn btn-success  btn-sm" data-target="#addEquipmentModal">工厂品牌质量门<i class="fa fa-plus" title="新增"></i></a>
						
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
				
				<input type="hidden"  class="form-control"  id="qgKey222" name= "qgKey" value="${vo.qgKey}" >
					<div class="form-group">
						<label for="workCenterNumber"
							class="col-lg-3 col-sm-3 control-label"><span class="text-danger">*</span>1一级质量门</label>
						<div class="col-lg-9">
						
							<input type="text" readonly="readonly" class="form-control" id="qualityGate_1" name="qualityGate_1" id="qualityGate_1" 
								value="${vo.qualityGate_1 }" >
							
						</div>
					</div>
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">二级质量门</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="qualityGate_2" id="qualityGate_2" readonly="readonly"
								value="${vo.qualityGate_2 }">
						</div>
					</div>
					
					
				    <a href="utility.sp?toSinglePart" data-toggle="modal"
								class="btn btn-success  btn-sm" data-target="#addEquipmentModal">层级零件<i class="fa fa-plus" title="新增"></i></a>
				    <input type="hidden" name="qpKey" id= "qpKey"	value="${vo.qpKey }">
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">层级1</label>
						<div class="col-lg-9">
							<input type="text"   readonly="readonly"  class="form-control" name="level1" id="level1"
								value="${vo.level1 }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">层级2</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level2"  readonly="readonly" 
					 id="level2"			value="${vo.level2}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">层级3</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level3"  readonly="readonly" 
					id="level3"			value="${vo.level3 }"> 
						</div>
					</div>
					
				   
				   <div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">层级4</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level4"  readonly="readonly" 
					id="level4"			value="${vo.level4 }">
						</div>
					</div>
					
				   
				   <div class="form-group">
						<label for="workCenterName"
							class="col-lg-3 col-sm-3 control-label">层级5</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level5"  readonly="readonly" 
					id="level5"			value="${vo.level5 }">
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


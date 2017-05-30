<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	
<script type="text/javascript">
	deleteProductionLine = function(key)
	{
		$("#"+key).remove();
	}
	
	toSubmit = function(form) {
			//提交表单
		var $form = $("#form1");
		 
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyModal").modal("hide");
		/* 	getInstructionData(); */
		getLogisticsData();
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
			<li class="active"><a href="#basic" data-toggle="tab">${invokeType}</a>	</li>
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="logistics.sp?save"
			  class="form-horizontal" id="form1">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="areaNumber"
							class="col-lg-3 col-sm-3 control-label">
							<span class="text-danger">*</span>零件名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="part_name" id="part_name"
								value="${vo.part_name}" placeholder="必填">
							<input type="hidden" name="key"	value="${vo.key }">
						</div>
					</div>
						<div class="form-group">
						<label for="areaNumber"
							class="col-lg-3 col-sm-3 control-label">
							<span class="text-danger">*</span>包装代码</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="pack_code" id="pack_code"
								value="${vo.pack_code}" placeholder="必填">
						</div>
					</div>
											<div class="form-group">
						<label for="areaNumber"
							class="col-lg-3 col-sm-3 control-label">
							<span class="text-danger">*</span>零件编号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="part_no" id="part_no"
								value="${vo.part_no}" placeholder="必填">
						</div>
					</div>
																<div class="form-group">
						<label for="areaNumber"
							class="col-lg-3 col-sm-3 control-label">
							<span class="text-danger">*</span>包装规格</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="pack_size" id="pack_size"
								value="${vo.pack_size}" placeholder="必填">
						</div>
					</div>
															<div class="form-group">
						<label for="areaNumber"
							class="col-lg-3 col-sm-3 control-label">
							<span class="text-danger">*</span>零件类型</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="part_type" id="part_type"
								value="${vo.part_type}" placeholder="必填">
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


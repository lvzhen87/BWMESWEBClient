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
			getAreaData();
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
		<form role="form" action="DefectCodeController.sp?save"
			  class="form-horizontal" id="form1">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="areaNumber"
							class="col-lg-3 col-sm-3 control-label"><span class="text-danger">*</span>层级1</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level1" id="level1"
								value="${vo.level1 }" placeholder="必填">
							<input type="hidden" name="key"	value="${vo.key }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaName"
							class="col-lg-3 col-sm-3 control-label">层级2 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level2"
								value="${vo.level2 }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="areaName"
							class="col-lg-3 col-sm-3 control-label">层级3 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level3"
								value="${vo.level3 }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="areaName"
							class="col-lg-3 col-sm-3 control-label">层级4 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level4"
								value="${vo.level4 }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="areaName"
							class="col-lg-3 col-sm-3 control-label">层级5</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="level5"
								value="${vo.level5 }">
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


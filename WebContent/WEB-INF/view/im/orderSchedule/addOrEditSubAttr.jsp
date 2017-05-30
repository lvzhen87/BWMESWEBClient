 <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>

<script type="text/javascript">

	
	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			if(eval(result) != "操作成功")
			{
				operationConfirm(result);
				return ;
			}
			$("#modifyPlanScheduleSubAttTable").modal("hide");
			operationConfirm(result);
			var parentKey = $("#parentKey").val();
			
			queryPlanScheduleSubAtt(parentKey);
		});
		return false;
	}
	
	$("#defaultValue").focus(function() {
		var type = $("#selectColumnType").val();
		if (type == '6') {
			$("#defaultValue").datetimepicker('show');
		} else {
			$("#defaultValue").datetimepicker('remove');
		}
	});
	
	$(document).ready(function(){
		var invokeType = $("#invokeType").html();
		if (invokeType == "编辑")
		{
			$("#selectColumnType").attr("disabled","disabled");
			if($('#selectColumnType').val()==4)
			{
				$('#textLength').removeAttr("readonly");
			}
			else
			{
			$('#textLength').attr("readonly","true");
			}
		}
		
		$('#selectColumnType').change(function(){
			if($('#selectColumnType').val()==4)
				{
					$('#textLength').removeAttr("readonly");
				}
				else
				{
				$('#textLength').attr("readonly","true");
				}
		})
		
	})
	
	
</script>

<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#basic" data-toggle="tab"
				id="invokeType">${invokeType}</a></li>
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="planschedulesub.sp?save"
			onsubmit="return toSubmit(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="namne" class="col-lg-3 col-sm-3 control-label"><span class="text-danger">*</span>子属性名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control " name="subname"
								value="${vo.subname}"  placeholder="必填(以字母开头)" id="subname"></input>
								 <input type="hidden" name="key" value="${vo.key}"></input> 
								 <input type="hidden" name="parentKey" id="parentKey" value="${vo.parentKey}"></input> 
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-lg-3 col-sm-3 control-label">子属性字段</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="subattribute"
								value="${vo.subattribute }">
						</div>
					</div>

					<div class="form-group">
						<label for="columnType" class="col-lg-3 col-sm-3 control-label">子优先级</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="subpripority"
								value="${vo.subpripority }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="subBatch" class="col-lg-3 col-sm-3 control-label">批次数量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="subBatch"
								value="${vo.subBatch }">
						</div>
					</div>
					
				</div>
				<div class="form-group">
					<div class="col-lg-offset-9 col-lg-3">
						<button type="submit" class="btn btn-primary">保存</button>
						<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>


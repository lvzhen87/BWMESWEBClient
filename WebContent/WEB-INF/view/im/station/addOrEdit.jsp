<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	
<script type="text/javascript">
	
	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyModal").modal("hide");
			getStationData();
			operationConfirm(result);
			
		});
		return false;
	}

	checkedValue = function(from)
	{
		if($("#stationNumber").val()=="")
		{
			bootbox.alert("工作站编号不能为空！");
		}
		else
		{
			toSubmit(from);
		}
		return false;
	}
	
	$(document).ready(function(){
		var invokeType = $("#invokeType").html();
		if (invokeType == "编辑")
		{
			//Bug005
			$("#stationType").attr("disabled","disabled");
		}
	})

	
</script>
	
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#basic" data-toggle="tab" id="invokeType">${invokeType}</a>
			</li>
			<li class=""><a href="#uda" data-toggle="tab">自定义属性</a></li>
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="station.sp?save"
			onsubmit="return checkedValue(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="stationNumber"
							class="col-lg-3 col-sm-3 control-label">工作站编号<i class="text-danger">*</i></label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="stationNumber" id="stationNumber"
								value="${vo.stationNumber }" placeholder="必填">
							<input type="hidden" name="key"	value="${vo.key }">
						</div>
					</div>
					<div class="form-group">
						<label for="stationName"
							class="col-lg-3 col-sm-3 control-label">工作站名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="stationName"
								value="${vo.stationName }">
						</div>
					</div>
					<div class="form-group">
						<label for="stationCategory"
							class="col-lg-3 col-sm-3 control-label">工作站分类</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="stationCategory"
								value="${vo.stationCategory }">
						</div>
					</div>
					<div class="form-group">
						<label for="workCenterNumber"
							class="col-lg-3 col-sm-3 control-label">工作中心</label>
						<div class="col-lg-9">
							<div class="input-group m-bot15">
								<input type="text" class="form-control" name="workCenterNumber" id="workCenterNumber" 
								value="${vo.workCenterNumber }"  readonly  placeholder="请选择">
								<span class="input-group-btn">
										<a href="utility.sp?toSelectWorkCenter" data-toggle="modal"
											class="btn btn-default" data-target="#selectModal">
											<i class="fa fa-search" title="选择工作中心"></i>
										</a>									
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="formName"
							class="col-lg-3 col-sm-3 control-label">表单</label>
						<div class="col-lg-9">
							<div class="input-group m-bot15">
								<input type="text" class="form-control" name="formName" id="formName" 
								value="${vo.formName }"  readonly  placeholder="请选择">
								<span class="input-group-btn">
										<a href="utility.sp?selectForm" data-toggle="modal"
											class="btn btn-default" data-target="#selectModal">
											<i class="fa fa-search" title="选择表单"></i>
										</a>									
								</span>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="uda">
					<div class="form-group">
						<label for="uda0"
							class="col-lg-2 col-sm-2 control-label">属性_0</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda0"
								value="${vo.uda0 }">
						</div>
					</div>
					<div class="form-group">
						<label for="uda1"
							class="col-lg-2 col-sm-2 control-label">属性_1</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda1"
								value="${vo.uda1 }">
						</div>
					</div>
					<div class="form-group">
						<label for="uda2"
							class="col-lg-2 col-sm-2 control-label">属性_2</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda2"
								value="${vo.uda2 }">
						</div>
					</div>
					<div class="form-group">
						<label for="uda3"
							class="col-lg-2 col-sm-2 control-label">属性_3</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda3"
								value="${vo.uda3 }">
						</div>
					</div>
					<div class="form-group">
						<label for="uda4"
							class="col-lg-2 col-sm-2 control-label">属性_4</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda4"
								value="${vo.uda4 }">
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


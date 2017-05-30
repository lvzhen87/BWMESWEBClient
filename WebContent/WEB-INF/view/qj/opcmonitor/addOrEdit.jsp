<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script src="js/pickers-init.js"></script>
<script type="text/javascript">
	deleteEquipment = function(key) {
		$("#" + key).remove();
	}

	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyModal").modal("hide");
			getData();
			operationConfirm(result);

		});
		return false;
	}
	checkedValue = function(from) {
		if ($("#workCenterNumber").val() == "") {
			bootbox.alert("工作中心编号不能为空!");
		} else if ($("#equipmentContent").children("tr").length == 0) {
			bootbox.confirm("没有关联设备，是否继续？", function(result) {
				if (result) {
					toSubmit(from);
				}
			});
		} else {
			toSubmit(from);
		}
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
			<!-- <li class=""><a href="#uda" data-toggle="tab">自定义属性</a></li>
			<li class=""><a href="#equipments" data-toggle="tab">设备列表</a></li> -->
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="opcmonitor.sp?save"
			onsubmit="return checkedValue(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					
					<div class="form-group">
						<label for="oPCName"
							class="col-lg-3 col-sm-2 control-label">点名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="oPCName"
								value="${vo.oPCName }"><input type="hidden" name="key"
								value="${vo.key }">
						</div>
					</div>
					<div class="form-group">
						<label for="oPCItem"
							class="col-lg-3 col-sm-2 control-label">点地址</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="oPCItem"
								value="${vo.oPCItem }">
						</div>
					</div>
					<div class="form-group">
						<label for="groupID"
							class="col-lg-3 col-sm-2 control-label">组名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="groupID"
								value="${vo.groupID }">
						</div>
					</div>
					<div class="form-group">
						<label for="deviceName"
							class="col-lg-3 col-sm-2 control-label">驱动名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="deviceName"
								value="${vo.deviceName }">
						</div>
					</div>
					<div class="form-group">
						<label for="monitor"
							class="col-lg-3 col-sm-2 control-label">是否监控</label>
						<div class="col-lg-9">
							<select class="form-control" name="monitor">
							<option
								<c:if test="${vo.monitor eq 'false'}">selected="selected"</c:if>>false</option>
							<option
								<c:if test="${vo.monitor eq 'true'}">selected="selected"</c:if>>true</option>
							</select>
						</div>
						
					</div>
					<div class="form-group">
						<label for="oPCFunction"
							class="col-lg-3 col-sm-2 control-label">方法名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="oPCFunction"
								value="${vo.oPCFunction }">
						</div>
					</div>
					<div class="form-group">
						<label for="itemGroup"
							class="col-lg-3 col-sm-2 control-label">点分组名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="itemGroup"
								value="${vo.itemGroup }">
						</div>
					</div>
					<div class="form-group">
						<label for="itemSeq"
							class="col-lg-3 col-sm-2 control-label">点位顺序</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="itemSeq"
								value="${vo.itemSeq }">
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


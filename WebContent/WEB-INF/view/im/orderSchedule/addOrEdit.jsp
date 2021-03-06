<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>

<script type="text/javascript">
	deletePlanSchedule = function(key)
	{
		$("#"+key).remove();
	}
	
	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		if($("name").val()=="")
			{
			bootbox.alert("表名称不能为空");
			return false;
			}
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyPlanSchedule").modal("hide");
			operationConfirm(result);
			getPlanScheduleData();
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
		<form role="form" action="planschedule.sp?save"
			onsubmit="return toSubmit(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="factory" class="col-lg-3 col-sm-3 control-label"><span class="text-danger" >*</span>工厂</label>
						<div class="col-lg-9">
							<!-- <input type="text" class="form-control" name="factory"
								value="${vo.factory}"  placeholder="必填" id="factoryName"></input>  -->
							<select class="form-control" name="factory" id="factory">
								<c:forEach items="${FACTORY_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.factory eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
							<input type="hidden" name="key" value="${vo.key }"></input>
						</div>
					</div>
					<div class="form-group">
						<label for="workshop" class="col-lg-3 col-sm-3 control-label">车间</label>
						<div class="col-lg-9">
							<select class="form-control" name="workshop">
								<option
									<c:if test="${vo.workshop eq 'WB'}">selected="selected"</c:if>>WB</option>
								<option
									<c:if test="${vo.workshop eq 'PB'}">selected="selected"</c:if>>PB</option>
								<option
									<c:if test="${vo.workshop eq 'AB'}">selected="selected"</c:if>>AB</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-lg-3 col-sm-3 control-label">属性名称</label>
						<div class="col-lg-9">
							
							<input type="text" class="form-control" name="name"
								value="${vo.name }">
						
						</div>

					</div>
					<div class="form-group">
						<label for="attribute"
							class="col-lg-3 col-sm-3 control-label">属性字段</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="attribute"
								value="${vo.attribute }">
							
						</div>
					</div>

					<!-- <div class="form-group">
						<label for="batch" class="col-lg-3 col-sm-3 control-label">批次数量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="batch"
								value="${vo.batch }">
						</div>
					</div> -->

					<div class="form-group">
						<label for="pripority"
							class="col-lg-3 col-sm-3 control-label">优先级</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="pripority"
								value="${vo.pripority }">
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


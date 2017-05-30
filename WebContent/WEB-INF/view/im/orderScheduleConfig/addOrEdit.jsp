<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>

<script type="text/javascript">

	$('#factory').click(function(){
	
		//alert("Select  " + $('#factory').val());
		var factoryVal = $('#factory').val();
		queryWorkshop(factoryVal);
	
	})

	$('#workshop').click(function(){
	
	//	alert("Select  " + $('#workshop').val());
		var wsVal = $('#workshop').val();
		var factoryVal = $('#factory').val();
		queryAttru(factoryVal, wsVal);
	
	})
	
	$('#attru_name').click(function(){
	
		//alert("Select  " + $('#attru_name').val());
		var wsVal = $('#workshop').val();
		var factoryVal = $('#factory').val();
		var attruNameVal = $('#attru_name').val();
		querySubAttru(factoryVal, wsVal, attruNameVal);
	
	})
	
	$('#factory').change(function(){
	
		alert("Change  " + $('#factory').val());
		var factoryVal = $('#factory').val();
		queryWorkshop(factoryVal);
		$("#attru_name").empty(); 
		$("#subattru_name").empty(); 
	})

	$('#workshop').change(function(){
	
	//	alert("Select  " + $('#workshop').val());
		var wsVal = $('#workshop').val();
		var factoryVal = $('#factory').val();
		queryAttru(factoryVal, wsVal);
		$("#subattru_name").empty(); 
	})
	
	$('#attru_name').change(function(){
	
		//alert("Select  " + $('#attru_name').val());
		var wsVal = $('#workshop').val();
		var factoryVal = $('#factory').val();
		var attruNameVal = $('#attru_name').val();
		querySubAttru(factoryVal, wsVal, attruNameVal);
	
	})
	
	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		//if($("name").val()=="")
		//	{
		//	bootbox.alert("表名称不能为空");
		//	return false;
		//	}
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyPlanScheduleConfig").modal("hide");
			operationConfirm(result);
			getPlanScheduleConfigData();
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
		<form role="form" action="planscheduleconfig.sp?save"
			onsubmit="return toSubmit(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="factory" class="col-lg-3 col-sm-3 control-label"><span class="text-danger" >*</span>工厂</label>
						<div class="col-lg-9">
							<select class="form-control" name="factory" id="factory">
								<c:forEach items="${FACTORY_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.factory eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
								<input type="hidden" name="key" value="${vo.key}">
						</div>
					</div>
					<div class="form-group">
						<label for="workshop" class="col-lg-3 col-sm-3 control-label">车间</label>
						<div class="col-lg-9">
							<select class="form-control" name="workshop" id = "workshop">
								<c:forEach items="${WORKSHOP_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.workshop eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-lg-3 col-sm-3 control-label">车型</label>
						<div class="col-lg-9">
							
							<select class="form-control" name="vehicle_type" id = "vehicle_type">
								<c:forEach items="${TYPE_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.vehicle_type eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
						
						</div>

					</div>
					<div class="form-group">
						<label for="attribute"
							class="col-lg-3 col-sm-3 control-label">属性名称</label>
						<div class="col-lg-9">
							<select class="form-control" name="attru_name" id = "attru_name">
								<c:forEach items="${ATTRU_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.attru_name eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
							
						</div>
					</div>

					<div class="form-group">
						<label for="batch" class="col-lg-3 col-sm-3 control-label">子属性名称</label>
						<div class="col-lg-9">
						<select class="form-control" name="subattru_name" id = "subattru_name">
								<c:forEach items="${SUBATTRU_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.subattru_name eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
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


<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>

<script type="text/javascript">


	$('#factory1').click(function(){
	
		
		var factoryVal = $('#factory1').val();
		//alert("Select  " + factoryVal);
		queryWorkshop(factoryVal);

	});
	
	$('#workshop1').click(function(){
	
		//alert("Select  " + $('#workshop1').val());
		var wsVal = $('#workshop1').val();
		var factoryVal = $('#factory1').val();
		var type = $('#selectType').val();
		querySubAttru(factoryVal, wsVal, type);
		
		
	});
	
	$('#factory1').change(function(){

		
		var factoryVal = $('#factory1').val();
		//alert("Select  " + factoryVal);
		queryWorkshop(factoryVal);
		$("#subattru_a").empty(); 
		$("#subattru_b").empty(); 
	});

	$('#selectType').change(function(){

		var wsVal = $('#workshop1').val();
		var factoryVal = $('#factory1').val();
		var type = $('#selectType').val();
		//alert("Select  " + type);
		if(type == 2)
		{
			$('#subattru_b').empty();
			$('#subattru_b').attr("readonly","true");			
		}
		else
		{
			$('#subattru_b').removeAttr("readonly");	
			querySubAttru(factoryVal, wsVal, 1);
			
		}
		
	});
	//$('#workshop1').change(function(){

	//	alert("Select  " + $('#workshop1').val());
	//	var wsVal = $('#workshop1').val();
	//	var factoryVal = $('#factory1').val();
	//	querySubAttru(factoryVal, wsVal);

	//})
	
	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
	//	if($("#factory").val()=="")
	//		{
	//		bootbox.alert("工厂名称不能为空");
	//		}
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyCondition").modal("hide");
			operationConfirm(result);
			//var atDefinitionKey = $("#atDefinitionKey").val();
			getConditionData();
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
		<form role="form" action="planschedulecon.sp?save"
			onsubmit="return toSubmit(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="namne" class="col-lg-3 col-sm-3 control-label"><span
							class="text-danger">*</span>工厂</label>
						<div class="col-lg-9">
							<select class="form-control" name="factory" id="factory1">
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
							<select class="form-control" name="workshop" id = "workshop1">
								<c:forEach items="${WORKSHOP_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.workshop eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
						</div>
					</div>

					
					<div class="form-group">
						<label for="columnName" class="col-lg-3 col-sm-3 control-label">禁止条件类型</label>
						<div class="col-lg-9">
							<select class="form-control" name="type" 
								id="selectType">
								<option value="1"
									<c:if test="${vo.type eq 1}">selected="selected"</c:if>>间隔台数</option>
								<option value="2"
									<c:if test="${vo.type eq 2}">selected="selected"</c:if>>计划提前调整范围</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="subattru_a" class="col-lg-3 col-sm-3 control-label">禁止条件A</label>
						<div class="col-lg-9">			
							<select class="form-control" name="subattru_a" id = "subattru_a">
								<c:forEach items="${A_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.subattru_a eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
						</div>
					</div>
					
					<div class="form-group">
						<label for="subattru_b" class="col-lg-3 col-sm-3 control-label">禁止条件B</label>
						<div class="col-lg-9">			
							<select class="form-control" name="subattru_b" id = "subattru_b">
								<c:forEach items="${B_LIST}" var="o">
									<option value="${o}"
										<c:if test="${vo.subattru_b eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>	
						</div>
					</div>
				
				<div class="form-group">
						<label for="interval" class="col-lg-3 col-sm-3 control-label">批次台数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="interval"
								value="${vo.interval}"></input>
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


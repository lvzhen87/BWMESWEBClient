<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">工序列表</h4>
		<input type="hidden" id="thisRouteKey" value="${routeKey }">
	</header>
	<div class="panel-body">
		<table class="table table-hover table-striped table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>工艺顺序</th>
					<th>工艺说明</th>
				</tr>
			</thead>
			<tbody id="productionLineContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="stepKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectStep()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectStep()
{
	var key = $("input[name='stepKey']:checked").val();
	var routeKey = $("#thisRouteKey").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个工序");
		return;
	}
	$.post("route.sp?getStepByKey",{routeKey:routeKey, key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#stepKey").val(obj.key);
    	$("#stepName").val(obj.stepName);
  	});
	
}

$("#productionLineContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
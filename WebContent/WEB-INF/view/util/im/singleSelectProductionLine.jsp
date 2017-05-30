<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">产线列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<table class="table table-hover table-striped table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>产线编号</th>
					<th>产线名称</th>
				</tr>
			</thead>
			<tbody id="productionLineContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="productionLineKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectProductionLine()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectProductionLine()
{
	var key = $("input[name='productionLineKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个生产线");
		return;
	}
	$.post("productionline.sp?getProductionLineByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#plannedProductionLine").val(obj.productionLineNumber);
  	});
	
}

$("#productionLineContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
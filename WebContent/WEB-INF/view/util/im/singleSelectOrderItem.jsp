<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">排程列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<table class="table table-hover table-striped table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>排程号</th>
					<th>BOM编码</th>
					<th>BOM版本</th>
				</tr>
			</thead>
			<tbody id="orderItemContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="orderItemKey" value="${o.key}"></td>
						<td>${o.orderItem}</td>
						<td>${o.bomName}</td>
						<td>${o.bomRevision}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectOrderItem()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectOrderItem()
{
	var orderNumber = $("#orderNumber").val();
	var key = $("input[name='orderItemKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个排程");
		return;
	}
	$.post("orderitem.sp?getOrderItemByKey",{orderNumber:orderNumber, key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#orderItem").val(obj.orderItem);
    	$("#routeName").val(obj.plannedRoute);
  	});
	
}

$("#orderItemContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
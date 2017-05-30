<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">工单列表</h4>
	</header>
	<div class="panel-body">
		<form action="utility.sp?selectOrder"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">工单号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="orderNumber"
							value="${vo.orderNumber}"></input>
					</div>
					<div class="col-sm-2">
						<button class="btn btn-success" type="submit">查询</button>
					</div>
				</div>
			</div>
		</form>
		<table class="table table-hover table-striped table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>工单号</th>
					<th>工单自定义状态</th>
					<th>工单情况</th>
					<th>工单状态</th>
				</tr>
			</thead>
			<tbody id="orderContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="orderKey" value="${o.key}"></td>
						<td>${o.orderNumber}</td>
						<td>${o.orderState}</td>
						<td>${o.trxState}</td>
						<td>${o.currentStatus}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectOrder()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectOrder()
{
	var key = $("input[name='orderKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个工单");
		return;
	}
	$.post("order.sp?getOrderByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#orderNumber").val(obj.orderNumber);
    	$("#orderItem").attr('value','');
    	$("#routeName").attr('value','');
    	$("#toSelectOrderItem").attr('href','utility.sp?selectOrderItem&orderNumber=' + obj.orderNumber);
    	$("#toSelectOrderItem").removeAttr('disabled');
    	$("#orderItem").attr('placeholder','请选择');
  	});
	
}

$("#orderContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
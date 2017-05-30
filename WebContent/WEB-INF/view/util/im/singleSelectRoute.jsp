<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">工艺路径列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?selectRoute"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">工艺路径编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="routeNumber"
							value="${vo.routeNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2 no-padding">工艺路径名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="routeName"
							value="${vo.routeName}"></input>
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
					<th>工艺路径编号</th>
					<th>工艺路径名称</th>
				</tr>
			</thead>
			<tbody id="routeContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="routeKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectRoute()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectRoute()
{
	var key = $("input[name='routeKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个工艺路径");
		return;
	}
	$.post("route.sp?getRouteByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#plannedRoute").val(obj.routeNumber);
    	$("#routeName").val(obj.routeNumber);
    	$("#routeKey").val(obj.key);
    	$("#toSelectProductionLine").attr('href','utility.sp?selectProductionLine&routeNumber=' + obj.routeNumber);
    	$("#toSelectProductionLine").removeAttr('disabled');
    	$("#plannedProductionLine").attr('placeholder','请选择');
    	$("#toSelectStep").attr('href','utility.sp?selectStep&routeKey=' + obj.key);
  	});
	
}

$("#routeContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
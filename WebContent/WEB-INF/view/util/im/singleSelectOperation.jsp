<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">工艺类型列表</h4>
	</header>
	<div class="panel-body">
		<form action="utility.sp?selectOperation&routeKey=${routeKey }"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">工艺类型编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="operationNumber"
							value="${vo.operationNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2 no-padding">工艺类型名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="operationName"
							value="${vo.operationName}"></input>
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
					<th>工艺类型编号</th>
					<th>工艺类型名称</th>
					<th>工艺类型分类</th>
					<th>返工次数</th>
				</tr>
			</thead>
			<tbody id="operationContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="operationKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
						<td>${o.category}</td>
						<td>${o.cycleDuration}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectOperation()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectOperation (){
	key = $("input[name='operationKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个工艺类型");
		return;
	}
	$.post("operation.sp?getOperationByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
		$("#operationName").val(obj.operationNumber);
		$("#operationKey").val(key);
  	});
	 	
}

$("#operationContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
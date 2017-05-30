<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">生产线列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleProductionLineList"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addProductionLineContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">生产线编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="productionLineNumber"
							id="productionLineNumber" value="${vo.productionLineNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2 no-padding">生产线名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="productionLineName"
							value="${vo.productionLineName}"></input>
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
					<th>生产线编号</th>
					<th>生产线名称</th>
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
			<button type="button" class="btn btn-primary" onclick="confirmSingleProductionLine()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
confirmSingleProductionLine = function()
{
	var key = $("input[name='productionLineKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个生产线");
		return;
	}
	$.post("productionline.sp?getProductionLineByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#productionLineContent").append("<tr id=\""+obj.key+"\"><td>"+obj.productionLineNumber+"<input type=\"hidden\" name=\"productionLineKeys\" value=\""+obj.key+"\"></td><td>"+obj.productionLineName+"</td><td><a href=\"#\" class=\"btn btn-danger  btn-xs\"	onclick=\"deleteProductionLine("+key+")\"><i class=\"fa fa-ban\"  title=\"删除\"></i></a></td></tr>");
  	});
	
}

$("#productionLineContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
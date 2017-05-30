<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">产品列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="package.sp?selectUnit"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">产品序列号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="serialNumber" 
							value="${vo.serialNumber}">
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
					<th>产品序列号</th>
					<th>物料编号</th>
					<th>物料版本</th>
					<th>当前情况</th>
					<th>当前状态</th>
				</tr>
			</thead>
			<tbody id="unitContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="unitSerialNumber" value="${o.serialNumber}"></td>
						<td>${o.serialNumber}</td>
						<td>${o.partNumber}</td>
						<td>${o.partRevision}</td>
						<td>${o.trxState}</td>
						<td>${o.currentStatus}</td>
						<td></td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectUnit()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectUnit()
{
	var serialNumber =  $("input[name='unitSerialNumber']:checked").val();
	if(serialNumber == null || serialNumber == "undefined")
	{
		operationConfirm("必须选择一个产品");
		return;
	}
	$.post("package.sp?getUnitBySerial",{serialNumber:serialNumber},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#partNumber").val(obj.partNumber);
    	$("#serialNumber").val(obj.serialNumber);
    	$("partRevision").val(obj.partRevision);
    	$("currentStatus").val(obj.currentStatus);
    	$("trxState").val(obj.trxState);
		}
  	);
	
}

$("#unitContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
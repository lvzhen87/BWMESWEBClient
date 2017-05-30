<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">批次列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?selectLot"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">批次号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="name"
							value="${vo.name}"></input>
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
					<th>批次号</th>
					<th>所属工单号</th>
					<th>所属排程号</th>
					<th>批次类型</th>
				</tr>
			</thead>
			<tbody id="lotContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="lotKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.orderNumber}</td>
						<td>${o.orderItem}</td>
						<td>${o.lotTypeString}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectLot()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectLot()
{
	var key = $("input[name='lotKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个批次");
		return;
	}
	$.post("lot.sp?getLotByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#lotName").val(obj.name);
  	});
	
}

$("#lotContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
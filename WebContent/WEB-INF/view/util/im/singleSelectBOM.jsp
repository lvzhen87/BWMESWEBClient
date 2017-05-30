<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">BOM列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?selectBOM"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">BOM编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="bomNumber"
							value="${vo.bomNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2 no-padding">BOM名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="bomName"
							value="${vo.bomName}"></input>
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
					<th>BOM编号</th>
					<th>BOM名称</th>
					<th>BOM版本</th>
				</tr>
			</thead>
			<tbody id="bomContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="bomKey" value="${o.key}"></td>
						<td>${o.bomName}</td>
						<td>${o.description}</td>
						<td>${o.bomRevision}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectBom()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectBom()
{
	var key = $("input[name='bomKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个Bom");
		return;
	}
	$.post("bom.sp?getBomByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#bomName").val(obj.bomNumber);
    	$("#bomRevision").val(obj.bomRevision);
  	});
	
}

$("#bomContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
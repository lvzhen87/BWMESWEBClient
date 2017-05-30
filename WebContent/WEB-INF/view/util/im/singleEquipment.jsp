<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">设备列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleEquipmentList"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addEquipmentContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2">设备编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="equipmentNumber"
							id="equipmentNumber" value="${vo.equipmentNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2">设备名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="equipmentName"
							value="${vo.equipmentName}"></input>
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
					<th>设备编号</th>
					<th>设备名称</th>
				</tr>
			</thead>
			<tbody id="equipmentContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="equipmentKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmEquipment()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
confirmEquipment = function()
{
	var key = $("input[name='equipmentKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个设备");
		return;
	}
	$.post("equipment.sp?getEquipmentByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#equipmentContent").append("<tr id=\""+obj.key+"\"><td>"+obj.equipmentNumber+"<input type=\"hidden\" name=\"equipmentKeys\" value=\""+obj.key+"\"></td><td>"+obj.equipmentName+"</td><td><a href=\"#\" class=\"btn btn-danger  btn-xs\"	onclick=\"deleteEquipment("+key+")\"><i class=\"fa fa-ban\"  title=\"删除\"></i></a></td></tr>");
  	});
	
}

$("#equipmentContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
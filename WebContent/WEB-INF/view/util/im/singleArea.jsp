<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">车间列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleAreaList"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addAreaContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">车间编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control no-padding" name="areaNumber"
							id="areaNumber" value="${vo.areaNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2">车间名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="areaName"
							value="${vo.areaName}"></input>
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
					<th>车间编号</th>
					<th>车间名称</th>
				</tr>
			</thead>
			<tbody id="areaContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="areaKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSingleArea()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
confirmSingleArea = function()
{
	var key = $("input[name='areaKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个车间");
		return;
	}
	$.post("area.sp?getAreaByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#areaContent").append("<tr id=\""+obj.key+"\"><td>"+obj.areaNumber+"<input type=\"hidden\" name=\"areaKeys\" value=\""+obj.key+"\"></td><td>"+obj.areaName+"</td><td><a href=\"#\" class=\"btn btn-danger  btn-xs\"	onclick=\"deleteArea("+key+")\"><i class=\"fa fa-ban\"  title=\"删除\"></i></a></td></tr>");
  	});
	
}

$("#areaContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">工作中心列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleWorkCenterForStep&routeKey=${routeKey }"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addWorkCenterContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">工作中心编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control no-padding" name="workCenterNumber"
							id="workCenterNumber" value="${vo.workCenterNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2">工作中心名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="workCenterName"
							value="${vo.workCenterName}"></input>
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
					<th>工作中心编号</th>
					<th>工作中心名称</th>
				</tr>
			</thead>
			<tbody id="workCenterContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="workCenterKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSingleWorkCenter()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
confirmSingleWorkCenter = function()
{
	var key = $("input[name='workCenterKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个工作中心");
		return;
	}
	$.post("workcenter.sp?getWorkCenterByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
		$("#workCenterContent").append("<tr id=\""+obj.key+"\"><td>"+obj.workCenterNumber+"<input type=\"hidden\" name=\"workCenterKeys\" value=\""+obj.key+"\"></td><td>"+obj.workCenterName+"</td><td><a href=\"#\" class=\"btn btn-danger  btn-xs\"	onclick=\"deleteWorkCenter("+key+")\"><i class=\"fa fa-ban\"  title=\"删除\"></i></a></td></tr>");
  	});
	
}

$("#workCenterContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
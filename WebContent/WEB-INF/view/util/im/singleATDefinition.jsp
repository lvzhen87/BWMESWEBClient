<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">自定义表列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleATDefinitionList"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addATDefinitionContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">自定义表名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="atDefinitionName"
							id="productionLineNumber" value="${vo.name}"></input>
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
					<th>自定义表名称</th>
					<th>自定义表分类</th>
				</tr>
			</thead>
			<tbody id="atDefinitionContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="atDefinitionKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.category}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSingleATDefinition()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
confirmSingleATDefinition = function()
{
	var key = $("input[name='atDefinitionKey']:checked").val();

	
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个自定义表");
		return;
	} 
	var existFlag = false;
	$("#exportATDefinitionContent > tr").each(function(i){
		if(this.id == key)
		{
			existFlag = true;
			bootbox.alert("已添加该自定义表，请勿重复添加!");
			return;
		}
	});
	if(!existFlag)
	{
		$.post("atDefinition.sp?getATDefinitionByKey",{key:key},function(result){
			var obj = jQuery.parseJSON(eval(result));
			$("#exportATDefinitionContent").append("<tr id=\""+obj.key+"\"><td>"+obj.name+"<input type=\"hidden\" name=\"atDefinitionKeys\" value=\""+obj.key+"\"></td><td>"+obj.category+"</td><td><a href=\"#\" class=\"btn btn-danger  btn-xs\"	onclick=\"deleteATDefinition("+key+")\"><i class=\"fa fa-ban\"  title=\"删除\"></i></a></td></tr>");
	  	});
	}
	
}

$("#atDefinitionContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
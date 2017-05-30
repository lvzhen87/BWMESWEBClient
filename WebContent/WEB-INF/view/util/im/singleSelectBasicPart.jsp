<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">物料列表</h4>
	</header>
	<div class="panel-body">
		<form action="utility.sp?selectBasicPart"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">物料编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="part_num"
							value="${vo.part_num}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2 no-padding">物料名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control " name="part_name"
							value="${vo.part_name}"></input>
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
					<th>物料名称</th>
					<th>物料编号</th>
				</tr>
			</thead>
			<tbody id="partContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o[0]}">
						<td><input type="radio" name="partKey" value="${o[0]}"></td>
						<td>${o[1]}</td>
						<td><span id="partNum">${o[2]}</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		  
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectPart()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectPart()
{
	var key = $("input[name='partKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个物料");
		return;
	}
	var key = $("#partNum").html();
	alert (key);
	$("#partNumberPara").val(key);
	
}

$("#partContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">表单列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<table class="table table-hover table-striped table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>表单名</th>
				</tr>
			</thead>
			<tbody id="workCenterContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="formKey" value="${o.key}"></td>
						<td>${o.name}</td>
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
	var key = $("input[name='formKey']:checked").val();
	var formName = $("input[name='formKey']:checked").parent().next().html();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个工作中心");
		return;
	}
    $("#formName").val(formName);
	
}

$("#workCenterContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
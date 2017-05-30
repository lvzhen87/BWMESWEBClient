<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">质量零件列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleQualityPartList"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addEquipmentContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2">层级1:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="level1"
							id="level1" value="${vo.level1}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2">层级2:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="level2"
							value="${vo.level2}"></input>
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
					<th>层级1</th>
					<th>层级2</th>
					<th>层级3</th>
					<th>层级4</th>
					<th>层级5</th>
				</tr>
			</thead>
			<tbody id="equipmentContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.qpKey}">
						<td><input type="radio" name="equipmentKey" value="${o.qpKey}"></td>
						<td id="${o.qpKey}_l1" >${o.level1}</td>
						<td id="${o.qpKey}_l2" >${o.level2}</td>
						<td id="${o.qpKey}_l3" >${o.level3}</td>
						<td id="${o.qpKey}_l4" >${o.level4}</td>
						<td id="${o.qpKey}_l5" >${o.level5}</td>
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
		alert("必须选择一条记录");
		return;
	}
 
	level1 = $("#"+key+"_l1").html()  ;
	level2 = $("#"+key+"_l2").html()  ;
	level3 = $("#"+key+"_l3").html()  ;
	level4 = $("#"+key+"_l4").html()  ;
	level5 = $("#"+key+"_l5").html()  ;
	
	$("#qpKey").val(key+"");
   	$("#level1").val( level1);
	$("#level2").val( level2);
	$("#level3").val( level3);
	$("#level4").val( level4);
	$("#level5").val( level5);
}

$("#equipmentContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
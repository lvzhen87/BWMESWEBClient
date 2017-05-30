<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">工厂品牌质量门列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleFactoryBrandQualityGateList"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addEquipmentContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2">工厂名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="factoryName"
							id="factoryName" value="${vo.factoryName}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2">品牌:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="brandName"
							value="${vo.brandName}"></input>
					</div>
				</div>
					
			    <div class="row">	
					<label class="col-sm-2 control-label col-lg-2">质量门1:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="qualityGate_1"
							value="${vo.qualityGate_1}"></input>
					</div>
					
					<label class="col-sm-2 control-label col-lg-2">质量门2:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="qualityGate_2"
							value="${vo.qualityGate_2}"></input>
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
					<th>工厂名称</th>
					<th>品牌</th>
					<th>质量门1</th>
					<th>质量门2</th>
				 	<th>工厂KEY</th>
				 	<th>质量门KEY</th>
				 
				</tr>
			</thead>
			<tbody id="equipmentContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.qgKey}">
						<td><input type="radio" name="equipmentKey" value="${o.qgKey}"></td>
						<td id="${o.qgKey}_factory">${o.factoryName}</td>
						<td id="${o.qgKey}_brand" >${o.brandName}</td>
						<td id="${o.qgKey}_g1" >${o.qualityGate_1}</td>
						<td id="${o.qgKey}_g2" >${o.qualityGate_2}</td>
						<td id="${o.qgKey}_fbKey" >${o.fbKey}</td>
						<td id="${o.qgKey}_qgKey" >${o.qgKey}</td>
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

	factoryName3 = $("#"+key+"_factory").html()  ;
	brandName3 = $("#"+key+"_brand").html()  ;
	qualityGate_11 = $("#"+key+"_g1").html()  ;
	qualityGate_22 = $("#"+key+"_g2").html()  ;
	fbKey1 = $("#"+key+"_fbKey").html()  ;
	qgKey1 = $("#"+key+"_qgKey").html()  ;
 
	
   	$("#factoryName").val( factoryName3);
	$("#brandName").val(  brandName3);
	$("#fbKey").val(fbKey1);
	$("#qgKey222").val(  key +"");
	$("#qualityGate_2").val(  qualityGate_22 );
	$("#qualityGate_1").val(  qualityGate_11 );
	

	
}

$("#equipmentContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
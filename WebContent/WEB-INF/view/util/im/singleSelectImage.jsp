<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">图片列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="operation.sp?selectImage"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'selectContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">图片名称:</label>
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
					<th>图片名称</th>
					<th>图片分类</th>
				</tr>
			</thead>
			<tbody id="imageContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="imageKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.category}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-9 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSelectImage()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
function confirmSelectImage()
{
	var key = $("input[name='imageKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个图片");
		return;
	}
	$.post("image.sp?getImageByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));

    	$("#imageName").val(obj.name);
    	$("#imageKey").val(obj.key);
    	$("#showImage").attr("src", obj.imagePath);
	});
}

$("#imageContent tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
})
</script>
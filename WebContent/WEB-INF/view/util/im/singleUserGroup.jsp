<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<h4 class="modal-title">用户组列表</h4>
		</ul>
	</header>
	<div class="panel-body">
		<form action="utility.sp?singleUserGroupList"
			class="form-horizontal adminex-form" method="post"
			onsubmit="return submitSubForm(this,'addUserGroupContent')">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2 no-padding">用户组编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="name"
							id="name" value="${vo.name}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2 no-padding">用户组名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="description"
							value="${vo.description}"></input>
					</div>
					<div class="col-sm-2">
						<button class="btn btn-success" type="submit">查询</button>
					</div>
				</div>
			</div>
		</form>
		<table class="table table-hover table-striped table-condensed" id="userGroupTable">
			<thead>
				<tr>
					<th>#</th>
					<th>用户组编号</th>
					<th>用户组名称</th>
				</tr>
			</thead>
			<tbody id="userGroupContent">
				<c:forEach items="${list}" var="o">
					<tr id="${o.key}">
						<td><input type="radio" name="userGroupKey" value="${o.key}"></td>
						<td>${o.name}</td>
						<td>${o.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-lg-offset-8 col-lg-4">
			<button type="button" class="btn btn-primary" onclick="confirmSingleuserGroup()" data-dismiss="modal">确认</button>
			<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
		</div>
	</div>
</section>

<script type="text/javascript">
confirmSingleuserGroup = function()
{
	
	var key = $("input[name='userGroupKey']:checked").val();
	if(key == null || key == "undefined")
	{
		operationConfirm("必须选择一个用户组");
		return;
	}
	$.post("usergroup.sp?getUserGroupByKey",{key:key},function(result){
		var obj = jQuery.parseJSON(eval(result));
    	$("#userGroupContent").append("<tr id=\""+obj.key+"\"><td>"+obj.name+"<input type=\"hidden\" name=\"userGroupKeys\" value=\""+obj.key+"\"></td><td>"+obj.description+"</td><td><a href=\"#\" class=\"btn btn-danger  btn-xs\"	onclick=\"deleteUserGroup("+key+")\"><i class=\"fa fa-ban\"  title=\"删除\"></i></a></td></tr>");
  	});
	
}


$("#userGroupTable tbody tr").live("click",function(){
	//选中行
	var radio = $(this).find("td input[type=radio]");
	radio.attr("checked","checked");
	
})
</script>
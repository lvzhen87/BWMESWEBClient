<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%><%@ include
	file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/user.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="user" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2"
						style="width: 100px;">用户名:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="name" id="name"
							value="${vo.name}"></input> <input value="${userName}"
							class="hidden" id="nowUser">
					</div>
					<label class="col-sm-2 control-label col-lg-2"
						style="width: 100px;">用户说明:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="description"
							value="${vo.description}"></input>
					</div>
					<div class="col-sm-2" style="width: 130px; padding-left: 0px;">
						<button class="btn btn-success" type="button"
							onclick="queryUser()"
							style="padding-left: 25px; padding-right: 25px;">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading"
		style="padding-left: 35px; padding-right: 35px;">
		用户 <span> <a href="user.sp?toAddOrEdit" data-toggle="modal"
			data-backdrop="static" class="btn btn-primary  btn-xs"
			data-target="#modifyUser"><i class="fa fa-plus" title="新增"></i></a>
		</span> <span class="tools pull-right" onclick="adjust(usertb)"> <a
			href="javascript:;" class="fa fa-chevron-down"></a>
		</span>
		<c:import url="/common/setNum.jsp"></c:import>
	</header>
	<div class="panel-body">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-condensed"
				id="usertb">

			</table>
		</div>
	</div>
</section>


<!-- ADD OR EDIT -->
<div aria-hidden="true" aria-labelledby="modifyUser" role="dialog"
	tabindex="-1" id="modifyUser" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>


<!--ADD USERGROUP-->
<div aria-hidden="true" aria-labelledby="addUserGroupModal"
	role="dialog" tabindex="-1" id="addUserGroupModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="addUserGroupContent"></div>
	</div>
</div>


<script type="text/javascript">






//解决重复加载时不更新数据问题
$("#modifyUser").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
}); 

deleteUser = function(key)
{
	$.post("user.sp?del",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
    		getUserData();
    		operationConfirm(result);
		}else{
			operationConfirm(result);
		}
  	});
}
$(document).ready(function() {
	var nowUser = $("#nowUser").val();
	getUserInit();
	if(nowUser=="")
	{
	getUserData();
	}
	else{
	getUserDataByName(nowUser);
	}
					
});

</script>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<script src="js/pickers-init.js"></script>	
<script type="text/javascript">
	deleteUser = function(key)
	{
		$("#"+key).remove();
	}
	
	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		if($("#userGroupName").val()=="")
			{
			bootbox.alert("用户组编号不能为空");
			return false;
			}
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyUserGroup").modal("hide");
			operationConfirm(result);
			getUserGroupData();
			
		});
		
		return false;
	}
</script>
	
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#basic" data-toggle="tab">${invokeType}</a>
			</li>
			<li class=""><a href="#uda" data-toggle="tab">自定义属性</a></li>
			<li class=""><a href="#users" data-toggle="tab">用户列表</a></li>
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="usergroup.sp?save"
			onsubmit="return toSubmit(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="namne"
							class="col-lg-3 col-sm-3 control-label"><span class="text-danger">*</span>用户组编号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="name"
								value="${vo.name}"  placeholder="必填" id="userGroupName" <c:if test="${not vo.defaultUserGroup && invokeType=='编辑'}">readonly</c:if>></input>
							<input type="hidden" name="key"	value="${vo.key }"></input>
						</div>
					</div>
					<div class="form-group">
						<label for="description"
							class="col-lg-3 col-sm-3 control-label">用户组名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="description"
								value="${vo.description }">
						</div>
						
					</div>
					<div class="form-group">
						<label for="category"
							class="col-lg-3 col-sm-3 control-label">用户组分类</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="category"
								value="${vo.category }">
						</div>
						
					</div>
				</div>
				<div class="tab-pane" id="uda">
					<div class="form-group">
						<label for="workCenterNumber"
							class="col-lg-2 col-sm-2 control-label">属性_1</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda1"
								value="${vo.uda1 }">
						</div>
					</div>
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-2 col-sm-2 control-label">属性_2</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda2"
								value="${vo.uda2 }">
						</div>
					</div>
					<div class="form-group">
						<label for="workCenterNumber"
							class="col-lg-2 col-sm-2 control-label">属性_3</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda3"
								value="${vo.uda3 }">
						</div>
					</div>
					<div class="form-group">
						<label for="workCenterName"
							class="col-lg-2 col-sm-2 control-label">属性_4</label>
						<div class="col-lg-10">
							<input type="text" class="form-control" name="uda4"
								value="${vo.uda4 }">
						</div>
					</div>
					<c:forEach items="${namedItems}" var="o" varStatus="status">
						<div class="form-group">
							<label for="" class="col-lg-2 col-sm-2 control-label">${o.name}</label>
							<div class="col-lg-10">
								<input class="form-control <c:if test="${o.type eq 6}">form_datetime</c:if>"
									name="namedUDAValue[${status.index}]"
									value="${vo.namedUDAValue[status.index]}">
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="tab-pane" id="users">
					<div class="col-md-12">
						<a href="utility.sp?toSingleUser" data-toggle="modal"
							class="btn btn-success  btn-sm" data-target="#addUserModal"><i class="fa fa-plus" title="新增"></i></a>
					</div>
					<div class="col-md-12">
						<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)"
							color=#987cb9 SIZE=1>
					</div>
					<table class="table table-hover table-striped table-condensed">
						<thead>
							<tr>
								<th>用户编号</th>
								<th>用户名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="userContent">
							<c:forEach items="${vo.users}" var="o">
								<tr id="${o.key}">
									<td>${o.name}<input type="hidden" name="userKeys" value="${o.key}"></td>
									<td>${o.description}</td>
									<td><a href="javascript:deleteUser(${o.key})" class="btn btn-danger  btn-xs"><i class="fa fa-ban"  title="删除"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-9 col-lg-3">
						<button type="submit" class="btn btn-primary">保存</button>
						<button type="button" data-dismiss="modal" class="btn btn-primary">取消</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>


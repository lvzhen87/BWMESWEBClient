<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>



<script src="js/pickers-init.js"></script>


<script type="text/javascript">
	deleteUserGroup = function(key) {
		$("#" + key).remove();
	}

	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		if ($("#userName").val() == "") {
			bootbox.alert("用户名不能为空");
			return false;
		} else if ($("#passwordExpiration").val()=="") {
			bootbox.alert("密码过期时间不能为空");
			return false;
		} else if ($("#userExpiration").val()=="") {
			bootbox.alert("用户过期时间不能为空");
			return false;
		}
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyUser").modal("hide");
			getUserData();
			operationConfirm(result);
		});
		return false;
	};

	$(document).ready(function() {

		var status = $("#status").val();
		if (status == "编辑") {
			$("#password").removeAttr("required");
			$("#password").attr("placeholder", "不修改密码则不填，密码三次之内不能重复");
		}
	});
</script>

<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#basic" data-toggle="tab">${invokeType}</a>
				<input class="hidden" id="status" value="${invokeType}"></li>
			<li class=""><a href="#uda" data-toggle="tab">自定义属性</a></li>
			<li class=""><a href="#userGroups" data-toggle="tab">用户组</a></li>
			<li class=""><a href="#associatedForm" data-toggle="tab">关联表单</a></li>
		</ul>
	</header>

	<div class="panel-body">
		<form role="form" action="user.sp?save"
			onsubmit="return toSubmit(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">

					<div class="form-group">
						<label for="name" class="col-lg-3 col-sm-3 control-label"><span
							class="text-danger">*</span>用户名</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="name"
								value="${vo.name }" size="16" id="userName" placeholder="必填">
							<input type="hidden" name="key" value="${vo.key}"></input>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="col-lg-3 col-sm-3 control-label">用户说明</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="description"
								value="${vo.description}">
						</div>
					</div>

					<div class="form-group">
						<label for="firstName" class="col-lg-3 col-sm-3 control-label">姓</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="firstName"
								value="${vo.firstName}">
						</div>
					</div>

					<div class="form-group">
						<label for="lastName" class="col-lg-3 col-sm-3 control-label">名</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="lastName"
								value="${vo.lastName}">
						</div>
					</div>

					<div class="form-group">
						<label for="note" class="col-lg-3 col-sm-3 control-label">用户备注</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="note"
								value="${vo.note}">
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="col-lg-3 col-sm-3 control-label"><span
							class="text-danger">*</span>用户密码 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="password"
								value="${vo.password}" required placeholder="必填" id="password">
						</div>
					</div>

					<div class="form-group">
						<label for="passwordExpiration"
							class="col-lg-3 col-sm-3 control-label"><span
							class="text-danger">*</span>密码过期日期</label>
						<div class="col-lg-9">
							<input type="text" class="form-control form_datetime" size="16"
								name="passwordExpiration" value="${vo.passwordExpiration}"
								id="passwordExpiration" placeholder="请选择">
						</div>
					</div>

					<div class="form-group">
						<label for=" passwordModifiable"
							class="col-lg-3 col-sm-3 control-label">是否允许修改密码</label>
						<div class="col-lg-9">

							<select class="form-control" name="passwordModifiable">
								<option
									<c:if test="${vo.passwordModifiable}">selected="selected"</c:if>>true</option>
								<option
									<c:if test="${not vo.passwordModifiable}">selected="selected"</c:if>>false</option>
							</select>

						</div>
					</div>
					<div class="form-group">
						<label for="status" class="col-lg-3 col-sm-3 control-label">用户状态</label>
						<div class="col-lg-9">
							<select class="form-control" name="status">
								<option value="Normal"
									<c:if test="${vo.status eq 'Normal'}">selected="selected"</c:if>>Normal</option>
								<option value="Disabled"
									<c:if test="${vo.status eq 'Disabled'}">selected="selected"</c:if>>Disabled</option>
								<option value="ChangePassword"
									<c:if test="${vo.status eq 'ChangePassword'}">selected="selected"</c:if>>ChangePassword</option>
							</select>

						</div>
					</div>
					<div class="form-group">
						<label for="userExpiration"
							class="col-lg-3 col-sm-3 control-label"><span
							class="text-danger">*</span>用户过期时间</label>
						<div class="col-lg-9">
							<input class="form-control form_datetime" size="16" type="text"
								value="${vo.userExpiration}" name="userExpiration"
								id="userExpiration" placeholder="请选择" />
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-lg-3 col-sm-3 control-label">email</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="email"
								value="${vo.email}">
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

				<div class="tab-pane" id="userGroups">
					<input type="hidden" id="userGroupkeys">
					<div class="col-md-12">
						<a href="utility.sp?toSingleUserGroup" data-toggle="modal"
							class="btn btn-success  btn-sm" data-target="#addUserGroupModal"><i
							class="fa fa-plus" title="新增"></i></a>
					</div>
					<div class="col-md-12">
						<HR
							style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
							color=#987cb9 SIZE=1>
					</div>
					<table class="table table-hover table-striped table-condensed">
						<thead>
							<tr>
								<th>用户组编号</th>
								<th>用户组名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="userGroupContent">
							<c:forEach items="${vo.userGroups}" var="o">
								<tr id="${o.key}">
									<td>${o.name}<input type="hidden" name="userGroupKeys"
										value="${o.key}"></td>
									<td>${o.description}</td>
									<td><a href="javascript:deleteUserGroup(${o.key})"
										class="btn btn-danger  btn-xs"><i class="fa fa-ban"
											title="删除"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane" id="associatedForm">

					<div class="form-group">
						<label for="associatedForm" class="col-lg-3 col-sm-3 control-label">关联表单</label>
						<div class="col-lg-9">
							<select class="form-control" name="formKey">
										<option selected="selected" value=-1></option>
									<c:forEach items="${formlist}" var="o">
										<option value="${o.key}" <c:if test="${vo.formKey eq o.key}">selected="selected"</c:if>>${o.name}</option>
									</c:forEach>
							</select>

						</div>
					</div>


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


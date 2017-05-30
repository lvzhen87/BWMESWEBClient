<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	
<script type="text/javascript">
	deleteArea = function(key)
	{
		$("#"+key).remove();
	}
	
	toSubmit = function(form) {
		//提交表单
		var $form = $(form);
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyModal").modal("hide");
			getMenuData();
			operationConfirm(result);
			
		});
		return false;
	}
	checkedValue = function(from)
	{
 		if($("#menuName1").val()=="")
		{
			bootbox.alert("菜单名称不能为空!");
		}		 
		else
		{
			toSubmit(from);
		} 
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
			 
		</ul>
	</header>
	<div class="panel-body">
		 
					
		<form role="form" action="menu.sp?save"
			onsubmit="return checkedValue(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="basic">
					<div class="form-group">
						<label for="menuName"
							class="col-lg-3 col-sm-3 control-label">菜单名称<i class="text-danger">*</i></label>
						<div class="col-lg-9">
						
							<select class="form-control" name="menuName">
								<option>请选择菜单名称</option>
								<c:forEach items="${menus}" var="o">								
								<option value="${o}"
								<c:if test="${vo.menuName eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>
							
						<%-- 	<input type="text" class="form-control" name="menuName" id="menuName1"
								value="${vo.menuName }" placeholder="必填"> --%>
								
							<input type="hidden" name="key"	value="${vo.key }">
						</div>
						
					</div>		
					
										<div class="form-group">
						<label for="menuName"
							class="col-lg-3 col-sm-3 control-label">菜单图标</label>
						<div class="col-lg-9">
						
							<select class="form-control" name="menuIcon">
									<option value="">请选择菜单图标</option>
								<c:forEach items="${icon}" var="o">
								<option value="${o}"
								<c:if test="${vo.menuIcon eq o}">selected="selected"</c:if>>${o}</option>
								</c:forEach>
							</select>

						</div>
						
					</div>	
								
					<div class="form-group">
						<label for="menuSrc"
							class="col-lg-3 col-sm-3 control-label">菜单链接</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="menuSrc"
								value="${vo.menuSrc }">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="growType" class="col-lg-3 col-sm-3 control-label">是否是父级菜单</label>
					<div class="col-lg-9">
						<select  class="form-control" name="v_parentS" >
							<option value="0" <c:if test="${vo.parent == '0'}">selected="selected"</c:if>>否</option>
							<option value="1" <c:if test="${vo.parent == '1'}">selected="selected"</c:if>>是</option>
						</select>
					</div>
				</div>					
				<div class="form-group">
					<label for="growType" class="col-lg-3 col-sm-3 control-label">父级菜单</label>
					<div class="col-lg-9">
						<select  class="form-control" name="pid" >
							<option value="0">无</option>
							<c:forEach items="${ParentMenu}" var="menu" varStatus="status">
								<option value="${menu.key}" <c:if test="${menu.key eq vo.pid}">selected="selected"</c:if>>${menu.menuName}</option>
							</c:forEach>														
						</select>
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


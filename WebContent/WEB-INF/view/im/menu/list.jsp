<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%><%@ include
	file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/menu.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="menu" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">菜单名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="menuName" id="menuName"
							value="${vo.menuName}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">菜单链接:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="menuSrc"
							value="${vo.menuSrc}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success" type="button" onclick="queryMenu()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">
    	菜单
    	<span>
         	<a href="menu.sp?toAddOrEdit" data-toggle="modal" data-backdrop="static"
				class="btn btn-primary  btn-xs" data-target="#modifyModal"><i class="fa fa-plus" title="新增"></i></a>
		</span>
    	<span class="tools pull-right" onclick="adjust(menutb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
       	<c:import url="/common/setNum.jsp"></c:import>
    </header>
	<div class="panel-body">
		<div class="col-md-12">
			<table id="menutb" class="table table-hover table-striped table-condensed">
			</table>
		</div>
	</div>
</section>

<!-- ADD OR EDIT -->
<div aria-hidden="true" aria-labelledby="modify" role="dialog"
	tabindex="-1" id="modifyModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>

<!-- ADD AREAS -->
<div aria-hidden="true" aria-labelledby="addAreaModal" role="dialog"
	tabindex="-1" id="addAreaModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="addAreaContent">
		</div>
	</div>
</div>

<script type="text/javascript">
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  

//解决重复加载时不更新数据问题
$("#addAreaModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  


deleteMenu = function(key)
{
	 
	$.post("menu.sp?del",{key:key},function(result){
		 
    		getMenuData();
    		
		operationConfirm(result);
  	});
}
</script>

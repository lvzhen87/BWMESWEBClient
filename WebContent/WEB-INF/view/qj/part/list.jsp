<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%@ include file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="qj/js/part.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="part" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">物料编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="partNumber"
							 value="${vo.partNumber}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 100px; ">物料名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="partName" 
							value="${vo.partName}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button  type="button" class="btn btn-success" onclick="queryPart()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">
		<span>
         	<a class="btn btn-success" type="button" href="javascript:loadpage('qjpart.sp?downloadPart','物料主数据接收')" style=" padding-left: 25px; padding-right: 25px; ">接收</a>
		</span>
		<span class="tools pull-right" onclick="adjust(parttb)"> 
			<a href="javascript:;" class="fa fa-chevron-down"></a>
		</span>
		<c:import url="/common/setNum.jsp"></c:import>
	</header>
	<div class="panel-body">

		<div class="col-md-12">
		
			<table id="parttb" class="table table-hover table-striped table-condensed">
			</table>
		</div>
	</div>
</section>

<!-- ADD OR EDIT -->
<div aria-hidden="true" aria-labelledby="modifyPart" role="dialog"
	tabindex="-1" id="modifyPart" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>

<script type="text/javascript">


//解决重复加载时不更新数据问题
$("#modifyPart").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
}); 

deletePart = function(key)
{
	$.post("part.sp?del",{key:key},function(result){
    	
    	if(eval(result)=="操作成功")
		{
    		getPartData();
    		operationConfirm(result);
		}else{
			operationConfirm(result);
		}
			
  	});
};
</script>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/qualityPart.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="area" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-1">层级1:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="level1"
							value="${vo.level1}"></input>
					</div>
					
					<label class="col-sm-2 control-label col-lg-1">层级2:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="level2"
							value="${vo.level2}"></input>
					</div>					
					
				</div>	
				
				<div>
				<br>
				</div>	
				<div class="row">
					
					<label class="col-sm-2 control-label col-lg-1">层级3:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="level3"
							value="${vo.level3}"></input>
					</div>
					
					<label class="col-sm-2 control-label col-lg-1">层级4:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="level4"
							value="${vo.level4}"></input>
					</div>
					
							
					<label class="col-sm-2 control-label col-lg-1">层级5:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="level5"
							value="${vo.level5}"></input>
					</div>
					
					
					
					<div class="col-sm-2">
						<button class="btn btn-success" type="button" onclick="queryList()" >查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading">
    	质量零件
    	<span>
         	<a href="QualityPartController.sp?toAddOrEdit" data-toggle="modal"
				class="btn btn-primary  btn-xs" data-backdrop="static" data-target="#modifyModal"><i class="fa fa-plus" title="新增"></i></a>
		</span>
    	<span class="tools pull-right"  onclick="adjust(areatb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
       	<c:import url="/common/setNum.jsp"></c:import>
    </header>
	<div class="panel-body">
		<div class="col-md-12">
			<table id="areatb" class="table table-hover table-striped table-condensed">
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

<!-- ADD PRODUCTIONLINES -->
<div aria-hidden="true" aria-labelledby="addProductionLineModal" role="dialog"
	tabindex="-1" id="addProductionLineModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="addProductionLineContent">
		</div>
	</div>
</div>

<script type="text/javascript">
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  

//解决重复加载时不更新数据问题
$("#addProductionLineModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  


deleteArea = function(key)
{
	$.post("QualityPartController.sp?del",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
    		getAreaData();
		}
		operationConfirm(result);
  	});
}
</script>

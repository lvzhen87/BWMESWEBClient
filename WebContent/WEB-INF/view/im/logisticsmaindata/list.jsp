<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%><%@ include
	file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/logistics.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="logistics" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2"
						style="width: 100px;">零件名称:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="part_name" id="name"
							value="${vo.part_name}"></input> 
							<input value="${userName}"
							class="hidden" id="nowUser">
					</div>
					<label class="col-sm-2 control-label col-lg-2"
						style="width: 100px;">零件类型:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="part_type"
							value="${vo.part_type}"></input>
					</div>
					<div class="col-sm-2" style="width: 130px; padding-left: 0px;">
						<button class="btn btn-success" type="button"
							onclick="queryLogistics()"
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
		物料 主数据<span> <a href="logistics.sp?toAddOrEdit" data-toggle="modal"
			data-backdrop="static" class="btn btn-primary  btn-xs"
			data-target="#modifyModal"><i class="fa fa-plus" title="新增"></i></a>
		</span> <span class="tools pull-right" onclick="adjust(logisticstb)"> <a
			href="javascript:;" class="fa fa-chevron-down"></a>
		</span>
		<c:import url="/common/setNum.jsp"></c:import>
	</header>
	<div class="panel-body">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-condensed"
				id="logisticstb">

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




<script type="text/javascript">






//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
}); 

deletedata = function(key)
{
	$.post("logistics.sp?del",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
    		getLogisticsData();
    		operationConfirm(result);
		}else{
			operationConfirm(result);
		}
  	});
}
$(document).ready(function() {
	var nowUser = $("#nowUser").val();
	getLogisticsInit();
	if(nowUser=="")
	{
	getLogisticsData();
	}
	else{
	getLogisticsDataByName(nowUser);
	}		
});

</script>

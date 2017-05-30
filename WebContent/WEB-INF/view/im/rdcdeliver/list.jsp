<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%><%@ include
	file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/rdcdeliver.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="rdcdeliver" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2"
						style="width: 100px;">随货票号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="bill_no" id="name"
							value="${vo.bill_no}"></input> 
							<input value="${userName}"
							class="hidden" id="nowUser">
					</div>
					<label class="col-sm-2 control-label col-lg-2"
						style="width: 100px;">零件编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="part_no"
							value="${vo.part_no}"></input>
					</div>
					<div class="col-sm-2" style="width: 130px; padding-left: 0px;">
						<button class="btn btn-success" type="button"
							onclick="queryRDCDeliver()"
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
		RDC发货信息查询结果<span> </span>
		<c:import url="/common/setNum.jsp"></c:import>
	</header>
	<div class="panel-body">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-condensed"
				id="rdcdelivertb">

			</table>
		</div>
	</div>
</section>

<script type="text/javascript">
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
}); 
$(document).ready(function() {
	var nowUser = $("#nowUser").val();
	getRDCDeliverInit();
	if(nowUser=="")
	{
	getRDCDeliverData();
	}
	else{
	getRDCDeliverDataByName(nowUser);
	}		
});
</script>

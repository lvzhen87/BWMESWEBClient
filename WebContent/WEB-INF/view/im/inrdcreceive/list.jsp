<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%><%@ include
	file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/inrdcreceive.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="inrdcreceive" class="form-horizontal adminex-form">
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
						style="width: 100px;">错误信息:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="part_no" id="cat-error"></input>
					</div>
						<div  class="col-sm-2" style="display:none;width: 130px; padding-left:0px;">
						<button id="cat-btn" class="btn btn-success" type="button"
							onclick="queryInRDCReceive()"
							style="padding-left: 25px; padding-right: 25px;">查询</button>
					</div>
						</form>

					<div class="col-sm-2" style="width: 130px; padding-left: 240px;">
						<button id="confirm" class="btn btn-success" type="button"
							style="padding-left: 25px; padding-right: 25px;">收货确认</button>
					</div>
				</div>
			</div>
	</div>
</section>
<section class="panel">
	<header class="panel-heading"
		style="padding-left: 35px; padding-right: 35px;">
		RDC收货信息录入<span> </span>
		<div style="display:none">
	<c:import url="/common/setNum.jsp"></c:import> </div>
	</header>
	<div class="panel-body">
		<div class="col-md-12">
			<table class="table table-hover table-striped table-condensed"
				id="inrdcreceivetb">

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
	getInRDCReceiveInit();
	if(nowUser=="")
	{
	getInRDCReceiveData();
	}
	else{
	getInRDCReceiveDataByName(nowUser);
	}
});
/**相关事件代码begin by cxcat*/
$('#inrdcreceivetb_length').remove();
$('#name').keydown(function(e){
	if (e.which == 13) {
		if($('#cat-error').val()!=""){
			$('#cat-error').val("");
			$('#cat-error').css('border-color',"none");
		}
		$('#cat-btn').click();
		if($('#0-part_no').text()){
			$('.odd').remove();
		}
		if($('#1-part_no').text()){
			$('#cat-error').css('border-color',"#ccc");
		}
		$('#inrdcreceivetb_length').remove();
		}
});
$('#confirm').click(function(){
	var batch_no = $('#cat-submit').val();
	var bill_no = $('#name').val();
	var username = $('#cat-username').text();
	//alert(batch_no+""+bill_no+""+username);
	if(batch_no){
		$.ajax({
			url:'inrdcreceive.sp?save&batch_no='+batch_no+'&bill_no='+bill_no+'&username='+username,
		   type:'GET'
		}).done(function(){
			$('.odd').remove();
			
		});
	}
});
/**相关事件代码begin by cxcat end*/
</script>

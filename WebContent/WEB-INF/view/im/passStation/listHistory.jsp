<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%><%@ include
	file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/passstationhistory.js"></script>
<script src="js/pickers-init.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="workCalander" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
					<label class="col-sm-1 control-label col-lg-1" style="width: 90px; ">VIN:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control " name="vin"
							value="${vo.vin}"></input>
					</div>
					<label class="col-sm-1 control-label col-lg-1" style="width: 90px; ">订单号:</label>
					<div class="col-sm-1">
						<input type="text" class="form-control " name="order_number"
							value="${vo.order_number}"></input>
					</div>
					<%-- <label class="col-sm-1 control-label col-lg-1" style="width: 90px; ">CSN:</label>
					<div class="col-sm-1">
						<input type="text" class="form-control " name="csn"
							value="${vo.csn}"></input>
					</div> --%>
					
					<label class="col-sm-2 control-label col-lg-2" style="width: 90px; ">开始站点:</label>
					<div class="col-sm-2">
						<select class="form-control" name="station" id="station">
						<option ></option>
							<c:forEach items="${stationlist}" var="o">
								<option >${o}</option>
							</c:forEach>
						</select>	
					</div>
					
					<label class="col-sm-2 control-label col-lg-2" style="width: 90px; ">结束站点:</label>
					<div class="col-sm-2">
						<select class="form-control" name="station_end" id="station_end">
						<option ></option>
							<c:forEach items="${stationlist}" var="o">
								<option >${o}</option>
							</c:forEach>
						</select>	
					</div>
				</div>
				<div class="row">
					<label class="col-sm-2 control-label col-lg-2" style="width: 90px; ">车型:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control " name="car_type"
							value="${vo.car_type}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 90px; ">颜色:</label>
					<div class="col-sm-1">
						<input type="text" class="form-control " name="color"
							value="${vo.color}"></input>
					</div>
					<%-- <label class="col-sm-2 control-label col-lg-2" style="width: 90px; ">用户名:</label>
					<div class="col-sm-1">
						<input type="text" class="form-control " name="username"
							value="${vo.username}"></input>
					</div> --%>
					<label class="col-sm-2 control-label col-lg-2" style="width: 90px; ">开始时间:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control  form_datetime" name="entry_time"
							value="${vo.entry_time}"></input>
					</div>
					<label class="col-sm-2 control-label col-lg-2" style="width: 90px; ">结束时间:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control form_datetime" name="entry_time_end"
							value="${vo.entry_time_end}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success" type="button" onclick="queryWorkCalander()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">

    	<span class="tools pull-right" onclick="adjust(workCalandertb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
   		<c:import url="/common/setNum.jsp"></c:import>
    </header>
	<div class="panel-body">
		<div class="col-md-12">
			<table id="workCalandertb" class="table table-hover table-striped table-condensed">
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

<!-- ADD EQUIPMENTS -->
<div aria-hidden="true" aria-labelledby="addEquipmentModal" role="dialog"
	tabindex="-1" id="addEquipmentModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="addEquipmentContent">
		</div>
	</div>
</div>

<script type="text/javascript">
//解决重复加载时不更新数据问题
$("#modifyModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  

//解决重复加载时不更新数据问题
$("#addEquipmentModal").on("hidden.bs.modal", function() {  
    $(this).removeData("bs.modal");  
});  


deleteWorkCenter = function(key)
{
	$.post("workcalander.sp?del",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
    		getWorkCalanderData();
		}
		operationConfirm(result);
  	});
}
</script>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%><%@ include
	file="/common/includeJS.inc.jsp"%>
<script type="text/javascript" src="dataTables/media/im/certconfigfuel.js"></script>
<!-- MAIN PAGE -->
<section class="panel">
	<div class="panel-body">
		<form id="certificate" class="form-horizontal adminex-form">
			<div class="col-md-12">
				<div class="row">
<%-- 					<label class="col-sm-2 control-label col-sm-2" style="width: 120px; ">VIN:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="clzl" id="clzl"
							value="${vo.clzl}"></input>
					</div> --%>
					<label class="col-sm-4 control-label col-sm-4" style="width: 120px; ">车辆型号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="vin" id = "vin"
							value="${vo.vin}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success" type="button" onclick="queryPrintInfo()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading" style="padding-left: 35px; padding-right: 35px; ">

    	<span class="tools pull-right" onclick="adjust(certificatetb)">
        	<a href="javascript:;" class="fa fa-chevron-down"></a>
       	</span>
       	<%-- <c:import url="/common/setNum.jsp"></c:import> --%>
    </header>
	<div class="panel-body">
		<div class="col-md-12">
			<form role="form" action="CertConfigFuelController.sp?save" id ="parameterForm" 
			onsubmit="return checkedValue(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="info1">
					<div class="form-group">
						<div class="col-sm-offset-9 col-sm-3">
							<button type="button" class="btn btn-primary" onclick="printInfo()">打印</button>
							<button type="button" data-dismiss="modal" class="btn btn-primary">上传</button>
						</div>
					</div>
				
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >生产企业</label>
							<div class="col-sm-3">
							<input type="text" class="form-control" name="producecompany" id="producecompany" value="${vo.producecompany }" placeholder="">
							<input type="hidden" name="key" id="key"	value="${vo.key }">
							
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >车辆型号</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="cartype" id="cartype" value="${vo.cartype }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >发动机型号</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="driveengine" id="driveengine" value="${vo.driveengine }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >燃料类型</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="fueltype" id="fueltype" value="${vo.fueltype }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >排量</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="displacement" id="displacement" value="${vo.displacement }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >额定功率</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="powerrate" id="powerrate" value="${vo.powerrate }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >变速器类型</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="transtype" id="transtype" value="${vo.transtype }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >驱动形式</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="drivetype" id="drivetype" value="${vo.drivetype }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >整车整备质量</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="completecarmass" id="completecarmass" value="${vo.completecarmass }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >最大设计总质量</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="maxdesignmass" id="maxdesignmass" value="${vo.maxdesignmass }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >市区工况</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="cityoil" id="cityoil" value="${vo.cityoil }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >综合工况</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="alloil" id="alloil" value="${vo.alloil }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >市郊工况</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="suburboil" id="suburboil" value="${vo.suburboil }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >标准1</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="countrystandard" id="countrystandard" value="${vo.countrystandard }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >阶段1</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="stage1" id="stage1" value="${vo.stage1 }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >日期1</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="stagestarttimeyear1" id="stagestarttimeyear1" value="${vo.stagestarttimeyear1 }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >限制1</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="limiteoil1" id="limiteoil1" value="${vo.limiteoil1 }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >阶段2</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="stage" id="stage" value="${vo.stage }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >日期2</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="stagestarttimeyear" id="stagestarttimeyear" value="${vo.stagestarttimeyear }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >限制2</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="limiteoil" id="limiteoil" value="${vo.limiteoil }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >制造日期</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="allstarttimeyear" id="allstarttimeyear" value="${vo.allstarttimeyear }" placeholder="">
						</div>
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >颜色</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="color" id="color" value="${vo.color }" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-sm-2 col-sm-3 control-label" >车型编码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="carmodelcode" id="carmodelcode" value="${vo.carmodelcode }" placeholder="">
						</div>
					</div>
				</div>
					
			</div>
		</form>
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


deleteCertificate = function(key)
{
	$.post("CertConfigFuelController.sp?del",{key:key},function(result){
		if(eval(result)=="操作成功")
		{
			getCertificateData();
		}
		operationConfirm(result);
  	});
}

queryPrintInfo = function()
{
	vin_s = $("#vin").val();
	alert(vin_s);
	$.post("CertPrintFuelController.sp?toFuelList",{vin:vin_s},function(result){
		//alert(result);
		var datas = eval(eval(result));
		datas = eval(datas);
/* 		alert(datas[0].carmodelcode);
		alert(datas); */
		$("#producecompany").val(datas[0].producecompany);
		$("#cartype").val(datas[0].cartype);
		$("#driveengine").val(datas[0].driveengine);
		$("#fueltype").val(datas[0].fueltype);
		$("#displacement").val(datas[0].displacement);
		$("#powerrate").val(datas[0].powerrate);
		$("#transtype").val(datas[0].transtype);
		$("#drivetype").val(datas[0].drivetype);
		$("#completecarmass").val(datas[0].completecarmass);
		$("#maxdesignmass").val(datas[0].maxdesignmass);
		$("#cityoil").val(datas[0].cityoil);
		$("#alloil").val(datas[0].alloil);
		$("#suburboil").val(datas[0].suburboil);
		$("#countrystandard").val(datas[0].countrystandard);
		$("#stage1").val(datas[0].stage1);
		$("#stagestarttimeyear1").val(datas[0].stagestarttimeyear1);
		$("#limiteoil1").val(datas[0].limiteoil1);
		$("#stage").val(datas[0].stage);
		$("#stagestarttimeyear").val(datas[0].stagestarttimeyear);
		$("#limiteoil").val(datas[0].limiteoil);
		$("#recordnum").val(datas[0].recordnum);
		$("#allstarttimeyear").val(datas[0].allstarttimeyear);
		$("#color").val(datas[0].color);
		$("#carmodelcode").val(datas[0].carmodelcode);
/* 		if(eval(result)=="操作成功")
		{
			getCertificateData();
		} */
		//operationConfirm(result);
  	});
	
	/* $.ajax({
		url : "CertPrintFuelController.sp?toFuelList&vin=1232141" + vin + "&t="+Math.random(),
		 type: 'POST',  
         data: form,  
         dataType:'json',
         async: false,  
         cache: false,  
         processData: false,  
         contentType: false,
		 success : function(data) {
			var datas = eval(data);
			alert(data);
			if(datas == null)
			{
				$.each(
						datas,
						function(i, item) {
							$("#producecompany").val(item.producecompany);

				
								});
				return false;
			}
		}
	}); */
	return false;
}

printInfo = function()
{
	 vin_s = $("#vin").val();
	alert(vin_s);
	$.post("CertPrintFuelController.sp?print",{vin:vin_s,printer:"printer"},function(result){
		alert(result);
/* 		if(eval(result)=="操作成功")
		{
			getCertificateData();
		}
		operationConfirm(result); */
  	}); 


	var vin = getVin();
    if(vin==""){
      return false;
    }
    //$("#vin_para").val(vin);
    $.ajax({
      url: "CertPrintFuelController.sp?print",
      type: "POST",
      dataType: "json",
      data: $('#parameterForm').serialize(),
      async: false,
      cache: false,
      success: function(data){
          alert(data);
        var data = eval(data)[0];
        var result = data.RESULT;
        var message = data.MESSAGE;
        if(result!=null && result=="FAIL"){
          $("#message").val(message).parent().addClass("has-error");
          $("#vin").select();
          return false;
        }
        else{
          $("#message").val("VIN【"+vin+"】保存成功").parent().addClass("has-success");
          getEPI(vin);
          return false;
        }
      }
    });
    
	return false;
}
</script>

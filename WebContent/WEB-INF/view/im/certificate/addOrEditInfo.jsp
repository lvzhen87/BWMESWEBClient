<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	<script src="js/pickers-init.js"></script>
	<script type="text/javascript" src="dataTables/media/im/certificate.js"></script>
	
<script type="text/javascript">

	deleteProductionLine = function(key)
	{
		$("#"+key).remove();
	}
	
	toSubmit = function(form) {
			//提交表单
		var $form = $(form);
		$.post($form.attr("action"), $form.serializeArray(), function(result) {
			$("#modifyModal").modal("hide");
			getCertificateData();
			operationConfirm(result);
			
		});
		return false;
	}
	checkedValue = function(from)
	{
		if($("#enterpriseCode").val()=="")
		{
			bootbox.alert("企业代码不能为空!");
		} 
		else
		{
			toSubmit(from);
		}
		return false;
	}
</script>
	
<section class="panel">
	<div class="panel-body">
		<form id="certificate" class="form-horizontal" onsubmit="return false">
			<div class="col-md-12">
				<div class="row">
					 
					<label class="col-sm-2 control-label col-lg-2" style="width: 120px; ">VIN号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="vin" id="vin"
							value="${vo.vin}"></input>
					</div>
					<div class="col-sm-2" style=" width: 130px; padding-left: 0px; ">
						<button class="btn btn-success" type="button" onclick="queryCertificateInfo()" style=" padding-left: 25px; padding-right: 25px; ">查询</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li ><a href="#info4" data-toggle="tab">车辆状态信息A1</a></li>		
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="certificate.sp?save"
			onsubmit="return checkedValue(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="info4">
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">生产单位名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="vehicleProUnitName"
								value="${vo.vehicleProUnitName }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">合格证编号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="hgzbh"
								value="${vo.hgzbh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆品牌/车辆名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="trademark"
								value="${vo.trademark }">
								<input type="text" class="form-control" name="carName"
								value="${vo.carName }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆颜色</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="biwColour"
								value="${vo.biwColour }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">底盘合格证编号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="dphgzbh"
								value="${vo.dphgzbh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">燃料种类</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fuelKind"
								value="${vo.fuelKind }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">油耗</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fuelConsumption"
								value="${vo.fuelConsumption }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">钢板弹簧片数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="leafSpring"
								value="${vo.leafSpring }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">轮距前后</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="trackFront"
								value="${vo.trackFront }">
								<input type="text" class="form-control" name="trackLater"
								value="${vo.trackLater }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">轴数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="axleNum"
								value="${vo.axleNum }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">整备质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="equipmentMass"
								value="${vo.equipmentMass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">准牵引总质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zqyzzl"
								value="${vo.zqyzzl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">额定载客</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="ratedPassenger"
								value="${vo.ratedPassenger }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">产品公告号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="announcementNo"
								value="${vo.announcementNo }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">整车物料号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zcwlh"
								value="${vo.zcwlh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">发证日期</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fzrq"
								value="${vo.fzrq }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆型号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="carModel"
								value="${vo.carModel }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">底盘型号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="dpxh"
								value="${vo.dpxh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">发动机型号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="engineNum"
								value="${vo.engineNum }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">排量和功率</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="displacement"
								value="${vo.displacement }">
								<input type="text" class="form-control" name="power"
								value="${vo.power }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">外廓尺寸</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="gabariteLength"
								value="${vo.gabariteLength }">
								<input type="text" class="form-control" name="gabariteBreadth"
								value="${vo.gabariteBreadth }">
								<input type="text" class="form-control" name="gabariteHeight"
								value="${vo.gabariteHeight }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">轮胎数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="tyreNumber"
								value="${vo.tyreNumber }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">轴距</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="wheelbase1"
								value="${vo.wheelbase1 }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">转向形式</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="turnModality"
								value="${vo.turnModality }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">额定载质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="constantLoadMass"
								value="${vo.constantLoadMass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">半挂车最大允许总质量（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="bgczdyxzzl"
								value="${vo.bgczdyxzzl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">最高时速</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="maximumSpeed"
								value="${vo.maximumSpeed }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">公告批次</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="announcementBatch"
								value="${vo.announcementBatch }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆分类</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="category"
								value="${vo.category }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">制造企业名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="enterpriseName"
								value="${vo.enterpriseName }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆识别代号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="clsbdh"
								value="${vo.clsbdh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">底盘id</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="dpId"
								value="${vo.dpId }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">发动机号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="engineNum"
								value="${vo.engineNum }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">排放标准</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="effluentStandard"
								value="${vo.effluentStandard }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">货箱内部尺寸</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="hxnbccLeng"
								value="${vo.hxnbccLeng }">
								<input type="text" class="form-control" name="hxnbccBreadth"
								value="${vo.hxnbccBreadth }">
								<input type="text" class="form-control" name="hxnbccHeight"
								value="${vo.hxnbccHeight }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">轮胎规格</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="tyreStandard1"
								value="${vo.tyreStandard1 }">
								<input type="text" class="form-control" name="tyreStandard2"
								value="${vo.tyreStandard2 }">
								<input type="text" class="form-control" name="tyreStandard3"
								value="${vo.tyreStandard3 }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">轴荷</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="axleLoad"
								value="${vo.axleLoad }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">总质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="totalMass"
								value="${vo.totalMass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">载质量利用系数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zzllyxs"
								value="${vo.zzllyxs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">驾驶室准乘人数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="passengers"
								value="${vo.passengers }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆制造日期</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="clzzdate"
								value="${vo.clzzdate }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">备注</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="remark"
								value="${vo.remark }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">企业标准</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="nspectionStandard"
								value="${vo.nspectionStandard}">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">其他信息</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="otherInformation"
								value="${vo.otherInformation }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">生产地址</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="productProducer"
								value="${vo.productProducer }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">返回结果</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="returnResult"
								value="${vo.returnResult }">
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


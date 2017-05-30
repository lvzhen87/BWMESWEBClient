<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
	<script src="js/pickers-init.js"></script>
<script type="text/javascript">
	deleteProductionLine = function(key)
	{
		$("#"+key).remove();
	}
	
	toSubmit = function(form) {
			//提交表单category
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
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#info1" data-toggle="tab">车辆状态信息A</a></li>
			<li class=""><a href="#info2" data-toggle="tab">车辆状态信息B</a></li>
			<li class=""><a href="#info3" data-toggle="tab">车辆状态信息C</a></li>
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="certificate.sp?save"
			onsubmit="return checkedValue(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="info1">
					<div class="form-group">
						<label for="areaNumber"
							class="col-lg-3 col-sm-3 control-label" >企业代码</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="enterpriseCode" id="enterpriseCode"
								value="${vo.enterpriseCode }" placeholder="必填">
							<input type="hidden" name="key"	value="${vo.key }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaName"
							class="col-lg-3 col-sm-3 control-label">企业ID </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="enterpriseID"
								value="${vo.enterpriseID }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆类型 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="vehiclelx"
								value="${vo.vehiclelx }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆品牌 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="trademark"
								value="${vo.trademark}">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆名称 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="carName"
								value="${vo.carName }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆系列名称/代号 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="modelSeriesName"
								value="${vo.modelSeriesName }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆型号 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="carModel"
								value="${vo.carModel }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">产品公告号 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="announcementNo"
								value="${vo.announcementNo }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">公告批次 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="announcementBatch"
								value="${vo.announcementBatch }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">一致性证书编号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="cocNum"
								value="${vo.cocNum }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">CCC证书号 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="cccNum"
								value="${vo.cccNum }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">一致性证书主管职位 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="certificateCompPosition"
								value="${vo.certificateCompPosition}">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">企业名称 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="enterpriseName"
								value="${vo.enterpriseName }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">分厂代码 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="factoryCode"
								value="${vo.factoryCode }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车型编码 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="vehicleModel"
								value="${vo.vehicleModel }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆类别 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="category"
								value="${vo.category }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆中文 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="chineseBrand"
								value="${vo.chineseBrand }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">英文品牌 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="englishBrand"
								value="${vo.englishBrand }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">单元型号 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="dyxh"
								value="${vo.dyxh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车身颜色 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="biwColour"
								value="${vo.biwColour }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">公告生效日期 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="announcementEffDate"
								value="${vo.announcementEffDate }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">油耗标志启用日期 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fuelIdentification"
								value="${vo.fuelIdentification }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">使用车辆制造日期 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="syclzzdate"
								value="${vo.syclzzdate }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">ccc证书号签发日期 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="cccCertificateDate"
								value="${vo.cccCertificateDate }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">一致性证书主管签名 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="certificatezgqz"
								value="${vo.certificatezgqz }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">一致性证书备注 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zsremark"
								value="${vo.zsremark }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆生产单位名称</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="vehicleProUnitName"
								value="${vo.vehicleProUnitName }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">产品生产地址 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="productProducer"
								value="${vo.productProducer }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">最终制造阶段制造商地址</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zzzzjdzzsaddress"
								value="${vo.zzzzjdzzsaddress }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">发动机制造商 </label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fzjzzs"
								value="${vo.fzjzzs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆信息备注</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zlinforemark"
								value="${vo.zlinforemark }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车辆制造企业其他信息</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="otherInformation"
								value="${vo.otherInformation }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">合格证打印</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="hgzdy"
								value="${vo.hgzdy }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">配置序列号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="pzxlh"
								value="${vo.pzxlh }">
						</div>
					</div>
				</div>
				<div class="tab-pane" id="info2">
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
							class="col-lg-3 col-sm-3 control-label">燃料种类</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fuelKind"
								value="${vo.fuelKind }">
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
							class="col-lg-3 col-sm-3 control-label">外廓尺寸（mm）</label>
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
							class="col-lg-3 col-sm-3 control-label">轴荷</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="axleLoad"
								value="${vo.axleLoad }">
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
							class="col-lg-3 col-sm-3 control-label">整备质量（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="equipmentMass"
								value="${vo.equipmentMass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">额定载客（人）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="ratedPassenger"
								value="${vo.ratedPassenger }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">转向轴个数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zxznum"
								value="${vo.zxznum }">
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
							class="col-lg-3 col-sm-3 control-label">变速器类型</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="transmissionType"
								value="${vo.transmissionType }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">法定铭牌位置</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fdmpwz"
								value="${vo.fdmpwz }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">汽缸数和排列</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="qgspl"
								value="${vo.qgspl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">主传动比</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zcdb"
								value="${vo.zcdb }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">最大净功率</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zdjgl1"
								value="${vo.zdjgl1 }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车门数量和结构</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="cmsljg"
								value="${vo.cmsljg }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">座椅数量及位置</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zyslwz"
								value="${vo.zyslwz }">
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
							class="col-lg-3 col-sm-3 control-label">排量和功率（ml/kw）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="displacement"
								value="${vo.displacement }">
							<input type="text" class="form-control" name="power"
								value="${vo.power }">
						</div>
					</div>
					 
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">货箱内部尺寸（mm）</label>
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
							class="col-lg-3 col-sm-3 control-label">轮胎规格（车轴1/2/3）</label>
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
							class="col-lg-3 col-sm-3 control-label">载质量利用系数</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zzllyxs"
								value="${vo.zzllyxs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">半挂车鞍座允许总质量（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="bgzazzdyxMass"
								value="${vo.bgzazzdyxMass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">驾驶室准乘人数（人）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="passengers"
								value="${vo.passengers  }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">发动机编号位置</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="enginebhwz"
								value="${vo.enginebhwz }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">前悬/后悬(mm)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="frontOverhang"
								value="${vo.frontOverhang }">
							<input type="text" class="form-control" name="rearOverhang"
								value="${vo.rearOverhang }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">运行状态带车身质量（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="runMass"
								value="${vo.runMass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">识别号在地盘上的位置</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="sbhwz"
								value="${vo.sbhwz }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">接近角/离去角(度)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="approachAngle"
								value="${vo.approachAngle }">
							<input type="text" class="form-control" name="departureAngle"
								value="${vo.departureAngle }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车轴间质量分配</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zzzlfp"
								value="${vo.zzzlfp }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">各车轴的最大质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="gczyxzdzl"
								value="${vo.gczyxzdzl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">发动机工作原理</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fzjgzyl"
								value="${vo.fzjgzyl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">制动装置简要说明</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zdzzjm"
								value="${vo.zdzzjm }">
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
							class="col-lg-3 col-sm-3 control-label">轮距（前/后 mm）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="trackFront"
								value="${vo.trackFront }">
							<input type="text" class="form-control" name="trackLater"
								value="${vo.trackLater }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">轴距(mm)（1/2/3)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="wheelbase1"
								value="${vo.wheelbase1 }">
							<input type="text" class="form-control" name="wheelbase2"
								value="${vo.wheelbase2 }">
							<input type="text" class="form-control" name="wheelbase3"
								value="${vo.wheelbase3 }">
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
							class="col-lg-3 col-sm-3 control-label">总质量（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="totalMass"
								value="${vo.totalMass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">额定载质量（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="constantLoadMass"
								value="${vo.constantLoadMass }">
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
							class="col-lg-3 col-sm-3 control-label">最高车速（km/h）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="maximumSpeed"
								value="${vo.maximumSpeed }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">统电动标志</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="isEV"
								value="${vo.isEV }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">驱动形式</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="qdxs"
								value="${vo.qdxs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">驱动轴</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="driveShaft"
								value="${vo.driveShaft }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">最大载质量（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zdzzzl"
								value="${vo.zdzzzl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">直接喷射</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zjps"
								value="${vo.zjps }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">离合器形式</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="lhqxs"
								value="${vo.lhqxs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">辅助转向方式</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fzzxfs"
								value="${vo.fzzxfs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车身形式</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="csxs"
								value="${vo.csxs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">速比</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="speedRatio"
								value="${vo.speedRatio }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">最大净功率时转速（min-1)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="zdjglszs"
								value="${vo.zdjglszs }">
						</div>
					</div>
				</div>
				<div class="tab-pane" id="info3">
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">油耗适应的标准号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fuelConsumptionStandard"
								value="${vo.fuelConsumptionStandard }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">油耗限值（L/100km）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="yhxz"
								value="${vo.yhxz }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">油耗工况（市区/市郊/综合 L/100km）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="yhsqgk"
								value="${vo.yhsqgk }">
								<input type="text" class="form-control" name="yhsjgk"
								value="${vo.yhsjgk }">
								<input type="text" class="form-control" name="yhzhgk"
								value="${vo.yhzhgk }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">产品标准号及实施阶段</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="sjbzh"
								value="${vo.sjbzh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">声级定制噪声（DB（A））</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="sjdzzs"
								value="${vo.sjdzzs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">加速行驶车外噪声（dB（A））</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="jsxscwzs"
								value="${vo.jsxscwzs }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">排气排放物标准号\实施阶段</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="pqpfwbzh"
								value="${vo.pqpfwbzh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">排气排放物实验用燃料(液体)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="pqpfwsywl"
								value="${vo.pqpfwsywl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">CO排放量(液体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="co"
								value="${vo.co }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">HC排放量(液体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="hc"
								value="${vo.hc }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">NOX排放量(液体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="nox"
								value="${vo.nox }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">HC+NOX排放量(液体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="hcnox"
								value="${vo.hcnox }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">烟度排放量(液体)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="yd"
								value="${vo.yd }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">微粒物排放量(液体)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="w1w"
								value="${vo.w1w }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">排气排放物实验用燃料(气体)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="pqpfwsyyl"
								value="${vo.pqpfwsyyl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">CO排放量(气体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="co"
								value="${vo.co }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">NOX排放量(气体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="nox"
								value="${vo.nox }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">NMHC排放量(气体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="nmhcqt"
								value="${vo.nmhcqt }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">THC排气量(气体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="thcqt"
								value="${vo.thcqt }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">CH4排气量(气体 g/km)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="ch4qt"
								value="${vo.ch4qt }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">微粒物排气量(气体)</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="wlwqt"
								value="${vo.wlwqt }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">二氧化碳排放量标准号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="o2cpflbzh"
								value="${vo.o2cpflbzh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">二氧化碳排放量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="o2cpflsq"
								value="${vo.o2cpflsq }">
								<input type="text" class="form-control" name="o2cpflsj"
								value="${vo.o2cpflsj }">
								<input type="text" class="form-control" name="o2cpflzh"
								value="${vo.o2cpflzh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">车顶最大允许载荷</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="cdzdyxzh"
								value="${vo.cdzdyxzh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">挂车的最大质量（制动下）（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="gczdzl"
								value="${vo.gczdzl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">牵引车与挂车的最大组合质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="qycgcmaxmass"
								value="${vo.qycgcmaxmass }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">催化剂类型</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="chjlx"
								value="${vo.chjlx }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">挂车的最大质量（非制动下）（kg）</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="gczdzl"
								value="${vo.gczdzl }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">牵引车与挂车连接点处的最大垂直负荷</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="qycgcljdczdczfh"
								value="${vo.qycgcljdczdczfh }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaCategory"
							class="col-lg-3 col-sm-3 control-label">催化剂编号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="chjbh"
								value="${vo.chjbh }">
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


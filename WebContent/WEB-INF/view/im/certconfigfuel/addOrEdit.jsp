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
		
		toSubmit(from);
		return false;
	}
</script>
	
<section class="panel">
	<header class="panel-heading custom-tab dark-tab">
		<button aria-hidden="true" data-dismiss="modal" class="close"
			type="button">×</button>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#info1" data-toggle="tab">燃油基础信息</a></li>
		</ul>
	</header>
	<div class="panel-body">
		<form role="form" action="CertConfigFuelController.sp?save"
			onsubmit="return checkedValue(this)" class="form-horizontal">
			<div class="tab-content">
				<div class="tab-pane active" id="info1">
					<div class="form-group">
					<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >生产企业</label>
							<div class="col-lg-9">
							<input type="text" class="form-control" name="producecompany" id="producecompany" value="${vo.producecompany }" placeholder="必填">
							<input type="hidden" name="key"	value="${vo.key }">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >车辆型号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="cartype" id="cartype" value="${vo.cartype }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >发动机型号</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="driveengine" id="driveengine" value="${vo.driveengine }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >燃料类型</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="fueltype" id="fueltype" value="${vo.fueltype }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >排量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="displacement" id="displacement" value="${vo.displacement }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >额定功率</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="powerrate" id="powerrate" value="${vo.powerrate }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >变速器类型</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="transtype" id="transtype" value="${vo.transtype }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >驱动形式</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="drivetype" id="drivetype" value="${vo.drivetype }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >整车整备质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="completecarmass" id="completecarmass" value="${vo.completecarmass }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >最大设计总质量</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="maxdesignmass" id="maxdesignmass" value="${vo.maxdesignmass }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >市区工况</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="cityoil" id="cityoil" value="${vo.cityoil }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >综合工况</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="alloil" id="alloil" value="${vo.alloil }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >市郊工况</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="suburboil" id="suburboil" value="${vo.suburboil }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >标准1</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="countrystandard" id="countrystandard" value="${vo.countrystandard }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >阶段1</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="stage1" id="stage1" value="${vo.stage1 }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >日期1</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="stagestarttimeyear1" id="stagestarttimeyear1" value="${vo.stagestarttimeyear1 }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >限制1</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="limiteoil1" id="limiteoil1" value="${vo.limiteoil1 }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >阶段2</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="stage" id="stage" value="${vo.stage }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >日期2</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="stagestarttimeyear" id="stagestarttimeyear" value="${vo.stagestarttimeyear }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >限制2</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="limiteoil" id="limiteoil" value="${vo.limiteoil }" placeholder="必填">
						</div>
					</div>
					<div class="form-group">
						<label for="areaNumber" class="col-lg-3 col-sm-3 control-label" >车型编码</label>
						<div class="col-lg-9">
							<input type="text" class="form-control" name="carmodelcode" id="carmodelcode" value="${vo.carmodelcode }" placeholder="必填">
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


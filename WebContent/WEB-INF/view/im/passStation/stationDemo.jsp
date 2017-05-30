<div class="container" style="margin-top: 50px;">
	<div class="container-fulid clearfix">
		<div class="col-md-12 column">
			<h2>二次开发过站Demo</h2>
		</div>
		<div class="col-md-12 column">
			<label class="col-lg-1 col-sm-4 control-label" for="workCenterName">工作中心:</label>
			<div class="col-lg-3 col-sm-8">
				<input type="text" class="form-control" id="workCenterName"/>
			</div>
			<label class="col-lg-1 col-sm-4 control-label" for="stepName">工序:</label>
			<div class="col-lg-3 col-sm-8">
				<input type="text" class="form-control" id="stepName"/>
			</div>
			<label class="col-lg-1 col-sm-4 control-label" for="serialnumber">产品序列号:</label>
			<div class="col-lg-3 col-sm-8">
				<input type="text" class="form-control" id="serialNumber"/>
				<input type="hidden" id="unitKey"/>
			</div>
		</div>
		<div class="col-md-12 column">
			<button type="button" class="btn btn-block btn-success" id="getUnit">查找</button>
		</div>
		<div class="col-md-12 column">
			<h4>产品信息</h4>
		</div>
		<div class="col-lg-10 col-sm-12">
			<div class="row">
				<label class="col-lg-1 col-sm-4 control-label" for="lotName">批次号:</label>
				<div class="col-lg-3 col-sm-8">
					<input type="text" class="form-control" id="lotName" readonly/>
				</div>
				<label class="col-lg-1 col-sm-4 control-label" for="partNumber">物料编码:</label>
				<div class="col-lg-3 col-sm-8">
					<input type="text" class="form-control" id="partNumber" readonly/>
				</div>
				<label class="col-lg-1 col-sm-4 control-label" for="partRevision">物料版本:</label>
				<div class="col-lg-3 col-sm-8">
					<input type="text" class="form-control" id="partRevision" readonly/>
				</div>

			</div>
			<div class="row">
				<label class="col-lg-1 col-sm-4 control-label" for="routeName">工艺路径名称:</label>
				<div class="col-lg-3 col-sm-8">
					<input type="text" class="form-control" id="routeName" readonly/>
				</div>
				<label class="col-lg-1 col-sm-4 control-label" for="productionLine">产线:</label>
				<div class="col-lg-3 col-sm-8">
					<input type="text" class="form-control" id="productionLine" readonly/>
				</div>
				<label class="col-lg-1 col-sm-4 control-label" for="trxState">产品情况:</label>
				<div class="col-lg-3 col-sm-8">
					<input type="text" class="form-control" id="trxState" readonly/>
				</div>

			</div>
			<div class="row">
				<label class="col-lg-1 col-sm-4 control-label" for="currentStatus">产品状态:</label>
				<div class="col-lg-3 col-sm-8">
					<input type="text" class="form-control" id="currentStatus" readonly/>
				</div>
			</div>
		</div>
		<div class="col-lg-2 col-sm-12 column">
			<div class="col-md-12 column">
				<button type="button" class="btn btn-lg btn-block btn-warning" id="overStation" disabled="disabled">过站</button>
			</div>
			<br>
			<div class="col-md-12 column">
				<div class="panel purple">
					<div class="btn-block btn-info active" style="margin-top: 10%">
						<div class="title" style="text-align: center">当前过站数量</div>
						<div class="value" id="count" style="text-align: center">0</div>
					</div>
				</div>
			</div>

		</div>
		<div class="col-md-12 column">
			<h4>当前过站历史记录</h4>
		</div>
		<div class="col-md-12 column" style="height: 450px; overflow-y: auto">
			<table class="table table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th>
							批次号
						</th>
						<th>
							产品序列号
						</th>
						<th>
							工序类型
						</th>
						<th>
							工序名称
						</th>
						<th>
							工作中心
						</th>
						<th>
							工序完成时间
						</th>
						<th>
							工序完成人
						</th>
					</tr>
				</thead>
				<tbody id="overStationHistoriesData">

				</tbody>
			</table>
		</div>
	</div>
</div>


<script type="text/javascript">

	var thisWorkCenterName="";//当前工作中心，为了判断工作中心是否变更
	var count = 0;//过站数量默认为0

	$(document).ready(function() {

		//根据产品序列号获得产品
		function getUnitBySerialNumber()
		{
			var serialNumber = $.trim($("#serialNumber").val());//产品序列号
			if(serialNumber == "")//判断是否已经输入产品序列号，如果没有，弹出提示
			{
				bootbox.alert("请输入产品序列号。")
				return;
			}
			else
			{
				$.ajax({//通过ajax发送请求
					type: 'GET',//请求类型为get
					async : true,//是否为异步请求
					data : {'serialNumber':serialNumber},//发送请求的数据：产品序列号
					contentType : "application/json;charset=UTF-8",//请求的格式
					dataType : "json",//数据类型
					url : "api-unit.sp?GetUnitBySerialNumber",//请求的地址，使用的API是GetUnitBySerialNumber
					success : function(data) {//成功，返回数据
						if(data.code == 1)//成功获得产品信息
						{
							var result = data.result;
							$('#unitKey').val(result.key);//将产品的key赋值给id为“unitKey”的输入框（隐藏的输入框）
							$('#lotName').val(result.lotName);//在产品信息中显示产品的批次
							$('#partNumber').val(result.partNumber);//在产品信息中显示物料编码
							$('#partRevision').val(result.partRevision);//在产品信息中显示物料版本
							$('#routeName').val(result.routeName);//在产品信息中显示该产品所用的工艺路径
							$('#productionLine').val(result.productionLine);//在产品信息中显示该产品所在的产线
							$('#trxState').val(result.trxState);//在产品信息中显示产品情况
							$('#currentStatus').val(result.currentStatus);//在产品信息中显示产品状态

							$("#overStation").removeAttr("disabled","disabled");//如果成功查询到产品信息，过站按钮变为可用
						}
					},
					error : function(error, a) {//失败返回错误信息
					}
				});

				var workCenterName = $.trim($("#workCenterName").val());//获得工作中心输入框中的值
				if(workCenterName != thisWorkCenterName)//判断工作中心是否改变
				{
					thisWorkCenterName = workCenterName;//修改当前工作中心为输入框内输入的工作中心
					count=0;//过站数量清零
					$("#count").html(count);//显示因为工作汇总新变更而清零后的过站数量
					$("#overStationHistoriesData").html("");//工作中心变更，战士的过站历史记录重置

				}

			}

		}

		//产品过站
		function overStation()
		{
			var stationVO = {};//用于存放发送请求的数据
			var workCenterName = $.trim($("#workCenterName").val());//工作中心
			var serialNumber = $.trim($("#serialNumber").val());//产品序列号
			var stepName = $.trim($("#stepName").val());//工序

			if(workCenterName == "")//判断是否已经输入工作中心，如果没有，弹出提示
			{
				bootbox.alert("请选择工作中心。")
				return;
			}
			if(stepName == "")//判断是否已经输入工序，如果没有，弹出提示
			{
				bootbox.alert("请选择工序。")
				return;
			}
			else
			{
				//将产品序列号、工作中心、工序添加到发送请求数据中
				stationVO.serialNumber = encodeURI(serialNumber);
				stationVO.workCenterName = encodeURI(workCenterName);
				stationVO.stepName = encodeURI(stepName);

				$.ajax({//通过ajax发送请求
					type: 'GET',//请求类型为get
					async : true,//是否为异步请求
					data : stationVO,//发送请求的数据：产品序列号、工作中心、工序
					contentType : "application/json;charset=UTF-8",//请求的格式
					dataType : "json",//数据类型
					url : "api-unit.sp?CompleteRouteStepAtWorkCenter",//请求的地址，使用的API是CompleteRouteStepAtWorkCenter
					success : function(data) {//成功，返回数据
						if(data.code == 1) {//成功过站
							count++;//过站数量+1
							$("#count").html(count);//显示更新后的过站数量
							getOverStationHistories();//调用获取过站记录方法
							$("#overStation").attr("disabled","disabled");//成功过站，过站按钮变为不可用，避免重复过站
						}
						else if(data.code ==0) {//过站失败
							bootbox.alert(data.errMsg);//输出错误信息
						}

					},
					error : function(error, a) {//失败，返回错误信息
					}
				});
			}


		}

		//根据产品的key获取过站记录
		function getOverStationHistories()
		{
			var serialNumber = $.trim($("#serialNumber").val());//产品序列号
			var lotName = $.trim($("#lotName").val());//产品所在批次
			var unitKey = $.trim($("#unitKey").val());//产品key
			if(unitKey == "")
			{
				return;
			}
			else
			{
				$.ajax({//通过ajax发送请求
					type: 'GET',//请求类型为get
					async : true,//是否为异步请求
					data : {'key':unitKey},//发送请求的数据：产品Key
					contentType : "application/json;charset=UTF-8",//请求的格式
					dataType : "json",//数据类型
					url : "api-unit.sp?GetUnitHistoryByKey",//请求的地址，使用的API是GetUnitHistoryByKey
					success : function(data) {//成功，返回数据
						if(data.code == 1) {//成功获得产品历史记录
							var results = data.result;
							var res = results[results.length - 1];//获取记录中最新的一条
							var unitHistoryKey = res.key;//获取最新记录的key
							var unitHistoryOperationName = res.operationName;//获取最新记录的工序类型
							var unitHistoryRouteStepName = res.routeStepName;//获取最新记录的所用的工序
							var unitHistoryWorkCenterName = res.workCenterName;//获取最新记录的工作中心
							var unitHistoryCompletedTime = res.completedTime;//获取最新记录的完成时间
							var unitHistoryCompletedUserName = res.completedUserName;//获取最新纪录完成操作人


							$("#overStationHistoriesData").prepend("<tr class='unitrow' id='tr-" + unitHistoryKey + "'>");//在当前过站历史记录的最上面插入一行
							$("#tr-" + unitHistoryKey).append("<td id='lotName-" + unitHistoryKey + "'>" + lotName + "</td>");//插入批次
							$("#tr-" + unitHistoryKey).append("<td id='serialNumber-" + unitHistoryKey + "'>" + serialNumber + "</td>");//插入产品序列号
							$("#tr-" + unitHistoryKey).append("<td id='operationName-" + unitHistoryKey + "'>" + unitHistoryOperationName + "</td>");//插入工序类型
							$("#tr-" + unitHistoryKey).append("<td id='routeStepName-" + unitHistoryKey + "'>" + unitHistoryRouteStepName + "</td>");//插入工序
							$("#tr-" + unitHistoryKey).append("<td id='workCenterName-" + unitHistoryKey + "'>" + unitHistoryWorkCenterName + "</td>");//插入工作中心
							$("#tr-" + unitHistoryKey).append("<td id='completedTime-" + unitHistoryKey + "'>" + unitHistoryCompletedTime + "</td>");//插入完成时间
							$("#tr-" + unitHistoryKey).append("<td id='completedUserName-" + unitHistoryKey + "'>" + unitHistoryCompletedUserName + "</td>");//插入完成操作人
						}
					},
					error : function(error, a) {//失败，返回错误信息
					}
				});
			}
		}

		$("#getUnit").click(function(){//查找按钮点击事件，调用getUnitBySerialNumber方法
			getUnitBySerialNumber();

		});

		$("#overStation").click(function(){//过站按钮点击事件，调用overStation方法
			overStation();
		});
	});


</script>
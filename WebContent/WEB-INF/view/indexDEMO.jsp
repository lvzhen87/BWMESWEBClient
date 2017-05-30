<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="keywords"
	content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<link rel="shortcut icon" href="#" type="image/png">

<title>C-MES SYSTEM</title>

<!--common-->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet">


<!--pickers css-->
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datepicker/css/datepicker-custom.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-timepicker/css/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datetimepicker/css/datetimepicker-custom.css" />
<link rel="stylesheet" type="text/css"
	href="dataTables/media/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="js/bootstrap-toggle/css/bootstrap-toggle.min.css">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header">

	<section>
		<!-- left side start-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="javascript:loadpage('main.sp?toMainPage','制造执行系统')"><img src="images/logo.png" alt="" width="144px">
				</a>
			</div>

			<div class="logo-icon text-center">
				<a href="javascript:loadpage('main.sp?toMainPage','制造执行系统')"><img src="images/logo_icon.png" alt="" width="40px">
				</a>
			</div>
			<!--logo and iconic logo end-->

			<div class="left-side-inner">

				<!--sidebar nav start-->
				<ul class="nav nav-pills nav-stacked custom-nav" style=" margin-top: 75px; ">
					<li><a href="javascript:loadpage('main.sp?toMainPage','制造执行系统')"><i
							class="fa fa-home"></i> <span>首页</span> </a>
					</li>
					<!--	<li class="menu-list"><a href="#"><i class="fa fa-gears"></i>
							<span>工厂建模</span> </a>
						<ul class="sub-menu-list">
						 <li><a href="javascript:loadpage('site.sp?toMainPage','工厂配置')">工厂</a>
							</li>
							<li><a href="javascript:loadpage('area.sp?toMainPage','车间配置')">车间</a>
							</li>
							<li><a
								href="javascript:loadpage('productionline.sp?toMainPage','生产线配置')">生产线</a>
							</li>
							<li><a
								href="javascript:loadpage('workcenter.sp?toMainPage','工作中心配置')">工作中心</a>
							</li>
							<li><a href="javascript:loadpage('equipment.sp?toMainPage','设备配置')">设备</a>
							</li>
							<li><a href="javascript:loadpage('carrier.sp?toMainPage','输送工具配置')">输送工具</a>
							</li> 
							<li><a
								href="javascript:loadpage('station.sp?toMainPage','工作站配置')">工作站</a>
							</li>
						</ul></li>
						-->
						
					<!-- 	<li class="menu-list"><a href="#"><i class="fa fa-book"></i> <span>基础数据
						</span>
					</a>

						<ul class="sub-menu-list">
						<li><a href="javascript:loadpage('part.sp?toMainPage','物料主数据')">物料主数据</a>
							</li>
							<li><a href="javascript:loadpage('bom.sp?toMainPage','BOM数据')">BOM</a>
							</li>

							<li><a
								href="javascript:loadpage('testdefinition.sp?toMainPage','检查项目')">检查项目</a>
							</li>

							<li><a href="javascript:loadpage('operation.sp?toMainPage','工序类型配置')">工序类型</a>
							</li>
							<li><a href="javascript:loadpage('route.sp?toMainPage','工艺路径配置')">工艺路径</a>
							</li> 
							
							
							<li><a
								href="javascript:loadpage('dslist.sp?toMainPage','数据列表管理')">数据列表管理</a>
							</li>
							 <li><a
								href="javascript:loadpage('image.sp?toMainPage','图片管理')">图片管理</a>
							</li>
							<li><a
								href="javascript:loadpage('reportDataDefinitionController.sp?toMainPage','报表数据表管理')">报表数据表管理</a>
							</li>
							<li><a
								href="javascript:loadpage('reportDesignController.sp?toMainPage','报表模版管理')">报表模版管理</a>
							</li>
							 
							
							
						</ul></li>-->
					<!-- <li class="menu-list"><a href="#"><i class="fa fa-laptop"></i> <span>自定义表单</span>
					</a>

						<ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('form.sp?toMainPage','创建表单')">创建表单</a>
							</li>
							<li><a
								href="javascript:loadpage('customizedform.sp?toCustomizedPage','表单列表')">表单列表</a>
							</li>
						</ul></li> -->
						
						
					<li class="menu-list"><a href="#"><i class="fa fa-asterisk"></i> <span>系统管理</span>
					</a>

						<ul class="sub-menu-list">
							<li><a href="javascript:loadpage('user.sp?toMainPage','用户配置')">用户</a>
							</li>
							<li><a href="javascript:loadpage('menu.sp?toMainPage','菜单配置')">菜单</a>
							</li>
							
							<li><a href="javascript:loadpage('usergroup.sp?toMainPage','用户组配置')">用户组</a>
							</li>
							
							<li><a
								href="javascript:loadpage('authority.sp?toMainPage','菜单权限配置')">菜单权限</a>
							</li>
							<!-- 
							<li><a href="javascript:loadpage('department.sp?toMainPage','部门配置')">部门</a>
							</li>
							
							 <li><a
								href="javascript:loadpage('accessprivilege.sp?toMainPage','权限配置')">权限</a>
							</li> 
							 <li><a
								href="javascript:loadpage('authority.sp?toMainPage','菜单权限配置')">菜单权限</a>
							</li>
							
							<li><a
								href="javascript:loadpage('userCustomer.sp?toMainPage','客户人员配置')">客户人员</a>
							</li>
							 -->
							
							
							
							<!-- 
							<li><a
								href="javascript:loadpage('atDefinition.sp?toMainPage','自定义表')">自定义表</a>
							</li>
							<li><a
								href="javascript:loadpage('jarImportController.sp?tomain','自定义模块')">自定义模块</a>
							</li>
							<li><a
								href="javascript:loadpage('eventsheet.sp?toMainPage','集成器配置')">集成器</a>
							</li> -->
						<!-- 	<li><a
								href="javascript:loadpage('import-export.sp?toMainPage','导入导出')">导入导出</a>
							</li> 
							<li><a
								href="javascript:loadpage('udaDefinition.sp?toMainPage','自定义属性配置')">自定义属性配置</a>
							</li>
							 -->
			
						</ul></li>
					<!-- <li class="menu-list" ><a href="#"><i class="fa fa-wrench"></i> <span>生产管理</span>
					</a>
						<ul class="sub-menu-list">
						
							<li><a 
								href="javascript:loadpage('order.sp?toMainPage','计划管理')">计划管理</a>
							</li>
							<li><a 
								href="javascript:loadpage('lot.sp?toMainPage','批次管理')">批次管理</a>
							</li>
							<li><a 
								href="javascript:loadpage('unit.sp?toMainPage','产品管理')">产品管理</a>
							</li>
							<li><a
								href="javascript:loadpage('consumption.sp?toMainPage','物料消耗管理')">物料消耗管理</a>
							</li>	
							<li><a
								href="javascript:loadpage('stationControl.sp?toMainPage','生产过站管理')">生产过站管理</a>
							</li>		
							<li><a
								href="javascript:loadpage('testInstance.sp?toMainPage','生产检验管理')">生产检验管理</a>
							</li>		
							<li><a
								href="javascript:loadpage('package.sp?toMainPage','包装下线管理')">包装下线管理</a>
							</li>	
							<li><a 
								href="javascript:loadpage('unittracking.sp?toMainPage','成品追溯')">成品追溯</a>
							</li>
							
						</ul></li> -->
					<li class="menu-list" >
						<a href="#"><i class="fa fa-wrench"></i> <span>计划管理</span></a>
						<ul class="sub-menu-list">
							<!-- <li>
								<a href="javascript:loadpage('qjpart.sp?toMainPage','物料主数据接收')">物料主数据接收</a>
							</li>
							<li>
								<a href="javascript:loadpage('qjbom.sp?toMainPage','BOM接收')">BOM接收</a>
							</li> -->
							<li>
								<a href="javascript:loadpage('plan.sp?toMainPage','计划导入与分解')">计划导入与分解</a>
							</li>
							<li>
								<a href="javascript:loadpage('plan.sp?toOrderList','计划调整与发布和查询')">计划调整与发布和查询</a>
							</li>
							<li>
								<a href="javascript:loadpage('plan.sp?toBomList','BOM导入和查询')">BOM导入和查询</a>
							</li>
							
								<li>
								<a href="javascript:loadpage('qjbom.sp?toEleBomMainList','电池BOM导入和查询')">电池BOM导入和查询</a>
							</li>
							
							<!-- <li>
								<a href="javascript:loadpage('plan.sp?toFinishPage','计划报工')">计划报工</a>
							</li> -->
						</ul>
					</li>
					
					<li class="menu-list" >
						<a href="#"><i class="fa fa-wrench"></i> <span>物料需求管理</span></a>
						 <ul class="sub-menu-list">
							<li><a 
								href="javascript:loadpage('materialCaculate.sp?toMainPage','日物料需求计算')">日物料需求计算</a>
							</li>
						</ul> 
					</li>
					
					
					<li class="menu-list" >
						<a href="#"><i class="fa fa-wrench"></i> <span>制造过程管理</span></a>
						 <ul class="sub-menu-list">
							<!-- <li>
								<a href="javascript:loadpage('pass.sp?toScanPage','过站扫描')">过站扫描</a>
							</li> -->
							<li><a 
								href="javascript:loadpage('passStation.sp?toUploadScanPage','焊接上线')">焊接上线</a>
							</li>
							
							<li><a 
								href="javascript:loadpage('passStationHistory.sp?toMainPage','过站历史查询')">过站历史查询</a>
							</li>
							
							
						</ul> 
						
						
						
						
						
						
						
					</li>
				<!-- 	<li class="menu-list" >
						<a href="#"><i class="fa fa-wrench"></i> <span>配置管理</span></a>
						<ul class="sub-menu-list">
							<li>
								<a href="javascript:loadpage('opcmonitor.sp?toMainPage','OPC点位设置')">OPC点位设置</a>
							</li>
						</ul>
					</li> -->
					
					
					
			<li class="menu-list"><a href="#"><i class="fa fa-laptop"></i> <span>质量管理</span></a>
								 
								 		<ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('materialTrack.sp?toConfigPage','关键件配置')">关键件配置</a>
							</li>

						</ul>
						<ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('materialTrack.sp?toScanPage','关键件扫描')">关键件扫描</a>
							</li>

						</ul>
						<ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('materialTrack.sp?toQueryPage','车辆关键件查询')">车辆关键件查询</a>
							</li>

						</ul>
								 
								  <!--  <ul class="sub-menu-list">
										<li><a href="javascript:loadpage('defect.sp?toMainPage','缺陷录入')">缺陷录入</a>
										</li>
								   </ul>
									<ul class="sub-menu-list">
										<li>
											<a href="javascript:loadpage('DefectCodeController.sp?toMainPage','缺陷配置')">缺陷配置</a>
										</li>
										 
									</ul> 


									<ul class="sub-menu-list">
										<li>
											<a href="javascript:loadpage('FactoryBrandController.sp?toMainPage','工厂品牌配置')">工厂品牌配置</a>
										</li>
										 
									</ul> 
									

								    <ul class="sub-menu-list">
										<li>
											<a href="javascript:loadpage('QualityGateController.sp?toMainPage','质量门配置')">质量门配置</a>
										</li>
										 
									</ul> 
									
									<ul class="sub-menu-list">
										<li>
											<a href="javascript:loadpage('QualityPartController.sp?toMainPage','基础质量零件配置')">基础质量零件配置</a>
										</li>
										 
									</ul> 
									

									<ul class="sub-menu-list">
										<li>
											<a href="javascript:loadpage('OperationQualityPartController.sp?toMainPage','工厂质量零件配置')">工厂质量零件配置</a>
										</li>
										 
									</ul> 
									
									
									<ul class="sub-menu-list">
										<li>
											<a href="javascript:loadpage('DefectRepairController.sp?toMainPage','制造质量门问题返工')">缺陷返工</a>
										</li>
										 
									</ul>
									
								     <ul class="sub-menu-list">
										<li>
											<a href="javascript:loadpage('DefectQueryController.sp?toMainPage','车辆问题查询')">车辆问题查询</a>
										</li>
										 
									</ul>
						
						
					 -->	
						
						
						</li>
						
						 
					
					
					
					
					
					
					
					
					
					
					
					
					
					
						<li class="menu-list" >
							<a href="#"><i class="fa fa-wrench"></i> <span>证书管理</span></a>
							 <ul class="sub-menu-list">
				 			  <!-- <li><a
								href="javascript:loadpage('printer.sp?toMainPage','打印机信息配置')">打印机信息配置</a>
							</li>
							</ul>
							 <ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('printerBusiness.sp?toMainPage','打印机业务配置')">打印机业务配置</a>
							</li> -->
							</ul>
							 <ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('certificate.sp?toMainPage','合格证配置')">合格证配置</a>
							</li>
							</ul>
							 <ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('certificate.sp?toMainPageInfo','合格证查询')">合格证查询</a>
							</li>
				</ul>
						</li>
					
					
					
				</ul>
				
				
				
				
				
				
			
				<!--sidebar nav end-->

			</div>
		</div>
		<!-- left side end-->

		<!-- main content start-->
		<div class="main-content" style="position: relative;">

			<!-- header section start-->
			<div class="header-section">

				<!--toggle button start-->
				<a class="toggle-btn"><i class="fa fa-bars"></i>
				</a>
				<!--toggle button end-->
				<span id="caption" style="font-size: 20pt;position: absolute;left: 45%;top:50%;transform:translate(-80%,-50%);display: block;">制造执行系统</span>
				<!--notification menu start -->
				<div class="menu-right">
					<ul class="notification-menu">
						<li><a href="#" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown"> 当前登录用户为：<%=request.getParameter("userName") %><span
								class="caret"></span> </a>
							<ul class="dropdown-menu dropdown-menu-usermenu pull-right">
								<li><a href="javascript:loadpage('user.sp?toMainPage&userName=<%=request.getParameter("userName")%>')"><i class="fa fa-cog"></i>配置</a>
								</li>
								<li><a href="login.sp?toMainPage"><i class="fa fa-sign-out"></i>登出</a>
								</li>
							</ul></li>
					</ul>
				</div>
				<!--notification menu end -->

			</div>
			<!-- header section end-->

			<!--body wrapper start-->
			<div class="wrapper" id="appContent"><img alt="" width="100%"  src="images/noback.png"></div>
			<!--body wrapper end-->
		</div>
		<!-- main content end-->
	</section>

	<!-- Placed js at the end of the document so the pages load faster -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/modernizr.min.js"></script>
	<script src="js/jquery.nicescroll.js"></script>

	<!--common scripts for all pages-->
	
	<script src="js/common.js"></script>

	<!--pickers plugins-->
	<script src="js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script
		src="js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script src="js/bootstrap-daterangepicker/moment.min.js"></script>
	<script src="js/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="js/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script src="js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>

	<script src="js/pickers-init.js"></script>
	<script src="js/bootbox.min.js"></script>
	<script src="dataTables/media/js/jquery.dataTables.min.js"></script>
	<script src="dataTables/media/js/dataTables.bootstrap.min.js"></script>
	<script src="js/bootstrap-toggle/js/bootstrap-toggle.min.js"></script>
	<script type="text/javascript" src="js/d3/d3.v3.min.js"></script>
	<script src="js/scripts.js"></script>

</body>
</html>



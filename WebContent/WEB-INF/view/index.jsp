<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page import="java.util.*"%>
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
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-toggle/css/bootstrap-toggle.min.css">
<script src="js/jquery-2.1.1.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header">
	<%-- <p>${Menulist}</p> --%>
	<section>
		<!-- left side start-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="javascript:loadpage('main.sp?toMainPage','制造执行系统')"><img
					src="images/lg_logo.png" alt="" width="144px"> </a>
			</div>

			<div class="logo-icon text-center">
				<a href="javascript:loadpage('main.sp?toMainPage','制造执行系统')"><img
					src="images/logo_icon.png" alt="" width="40px"> </a>
			</div>
			<!--logo and iconic logo end-->

			<div class="left-side-inner">

				<!--sidebar nav start-->
				<ul class="nav nav-pills nav-stacked custom-nav"
					style="margin-top: 75px;">
					<li><a
						href="javascript:loadpage('main.sp?toMainPage','制造执行系统')"><i
							class="fa fa-home"></i> <span>首页</span> </a></li>

					<li class="menu-list"><a href="#"><i class="fa fa-wrench"></i>
							<span>系统管理</span> </a>

						<ul class="sub-menu-list">
							<li><a
								href="javascript:loadpage('user.sp?toMainPage','用户配置')">用户</a></li>
<!-- 							<li><a
								href="javascript:loadpage('department.sp?toMainPage','部门配置')">部门</a>
							</li> -->
							<li><a
								href="javascript:loadpage('menu.sp?toMainPage','菜单配置')">菜单</a></li>

							<li><a
								href="javascript:loadpage('authority.sp?toMainPage','菜单权限配置')">菜单权限</a>
							</li>
<!-- 							<li><a
								href="javascript:loadpage('workCalendar.sp?toMainPage','工作日历配置')">工作日历</a>
							</li> 
							<li><a
								href="javascript:loadpage('printer.sp?toMainPage','打印机信息配置')">打印机信息配置</a>
							</li>
							<li><a
								href="javascript:loadpage('printerBusiness.sp?toMainPage','打印机业务配置')">打印机业务配置</a>
							</li>

							<li><a
								href="javascript:loadpage('userCustomer.sp?toMainPage','客户人员配置')">客户人员</a>
							</li>-->
							<!-- <li><a href="javascript:loadpage('usergroup.sp?toMainPage','用户组配置')">用户组</a>
							</li>
							<li><a href="javascript:loadpage('department.sp?toMainPage','部门配置')">部门</a>
							</li>
							<li><a href="javascript:loadpage('menu.sp?toMainPage','菜单配置')">菜单</a>
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
				
<li class="menu-list"><a href="#"><i class="fa fa-file"></i>
							<span>证书管理</span></a>
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
						</ul></li>

						
						<li class="menu-list"><a href="#"><i class="fa fa-truck"></i>
								<span>证书打印上传</span></a>
							<ul class="sub-menu-list">
								<li><a
									href="javascript:loadpage('CertPrintFuelController.sp?toMainPage','燃油打印上传')">燃油打印上传</a>
								</li>					
								<li><a
									href="javascript:loadpage('instorestatics.sp?toMainPage','一致性打印上传')">一致性打印上传</a>
								</li>	
								<li><a
									href="javascript:loadpage('instorestatics.sp?toMainPage','合格证打印上传')">合格证打印上传</a>
								</li>	
								<li><a
									href="javascript:loadpage('instorestatics.sp?toMainPage','环保打印上传')">环保打印上传</a>
								</li>	
								<li><a
									href="javascript:loadpage('instorestatics.sp?toMainPage','合格证配置')">合格证配置</a>
								</li>		
								<li><a
									href="javascript:loadpage('instorestatics.sp?toMainPage','一致性配置')">一致性配置</a>
								</li>
								<li><a
									href="javascript:loadpage('CertConfigFuelController.sp?toMainPage','燃油配置')">燃油配置</a>
								</li>
								<li><a
									href="javascript:loadpage('instorestatics.sp?toMainPage','环保配置')">环保配置</a>
								</li>
							</ul></li>

<%-- <c:if test="${isAdmin == false }">
					<c:forEach items="${pList}" var="cxcat">
						<li class="menu-list"><a href="${cxcat.menuSrc}"><i
								class="${cxcat.menuIcon}"></i> <span>${cxcat.menuName}</span></a>
							<ul class="sub-menu-list">
								<c:forEach items="${allMaps}" var="val">
									<c:if test="${val.key==cxcat.selfId}">
										<c:forEach items="${val.value}" var="item">
											<li><a href="${item.menuSrc}">${item.menuName}</a></li>
										</c:forEach>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</c:if> --%>
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
				<a class="toggle-btn"><i class="fa fa-bars"
					style="padding: 5px;"></i> </a>
				<!--toggle button end-->
				<span id="caption"
					style="font-size: 20pt; position: absolute; left: 45%; top: 50%; transform: translate(-80%, -50%); display: block;">制造执行系统</span>
				<!--notification menu start -->
				<div class="menu-right">
					<ul class="notification-menu">
						<li><a href="#" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown"> <img src="images/avatar.png"
								style="width: 38px;"> <!-- 当前登录用户为： --><span id="cat-username"><%=request.getParameter("userName")%></span><span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu dropdown-menu-usermenu pull-right">
								<li><a
									href="javascript:loadpage('user.sp?toMainPage&userName=<%=request.getParameter("userName")%>')"><i
										class="fa fa-cog"></i>配置</a></li>
								<li><a href="login.sp?toMainPage"><i
										class="fa fa-sign-out"></i>登出</a></li>
							</ul></li>
					</ul>
				</div>
				<!--notification menu end -->

			</div>
			<!-- header section end-->

			<!--body wrapper start-->
			<div class="wrapper" id="appContent">
				<img alt="" width="100%" src="images/noback.png">
			</div>
			<!--body wrapper end-->
		</div>
		<!-- main content end-->
	</section>

	<!-- Placed js at the end of the document so the pages load faster -->
	<script src="js/jquery-2.1.1.min.js"></script>
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



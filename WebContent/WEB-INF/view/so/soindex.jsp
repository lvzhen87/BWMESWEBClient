<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="st" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<!-- 
  <link rel="shortcut icon" href="#" type="image/png">
   -->

<title>工厂操作</title>



<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet">



<!--pickers css-->
<link rel="stylesheet" type="text/css"
	href="css/gbtags.css" />
<link rel="stylesheet" type="text/css"
	href="css/ladda-themeless.css" />
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
<style>
	.container {
		width:90%;
	}
</style>
<script src="js/jquery-2.1.1.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header" style="background:white;">

	<section>
		<div class="left-side sticky-left-side" style="height: auto;">
			 MES现场客户端
			 <!-- <div class="logo" style="height: 45px;">
		
			</div> -->
		</div>
		<div class="header-section">
			<!-- <a class="toggle-btn"><i class="fa fa-bars"></i> </a> -->
			<span id="formCaption"
				style="font-size: 20pt; position: absolute; left: 40%; top: 50%; transform: translate(-80%, -50%); display: block;"></span>
			<div class="menu-right">
				<ul class="notification-menu">
					<li><a href="#" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown">  当前登录用户为：<span id="curUserName"><%=request.getParameter("userName")%></span><span
							class="caret"></span> 
					</a>
						<ul class="dropdown-menu dropdown-menu-usermenu pull-right">
							<li><a href="loginso.sp?logout"><i
									class="fa fa-sign-out"></i>登出</a></li>
						</ul></li>

				</ul>
			</div>
		</div>

	</section>
	<section id="userFormData">
		<script>
			var htmlData = "";
			var formName = "";
			function getFormContentByUserName(name) {
				
				$.ajax({
					type : 'GET',
					async : true,
					data : {"name":name},
					contentType : "application/json;charset=GBK",
					dataType : "json",
					url : "loginso.sp?getFormByUserName",
					success : function(data) {
						var jsondata = eval(data);
						if (jsondata.code == 1) {
							var formlist = jsondata.formlist;
							var form = formlist[0];
							htmlData = form.content;
							formName = form.text;
						} else if (jsondata.code == 0) {
							htmlData = '<div class="container-fulid" style="margin-top:80px;margin-left:10px;"><h4>温馨提示：该用户没有定义的表单,如果需要请联系系统管理员。</h4></div>';
							formName = "";
						}
						$("#formCaption").html(jsondata.station);
						$("#userFormData").html(htmlData);
						
					},
					error : function(error, a) {
					}
				});
			}
			$(document).ready(function() {
				$("#userFormData").html("");
				var name = $("#curUserName").html();
				getFormContentByUserName(name);
			});
		</script>
	</section>

</body>

<script src="js/jquery.nicescroll.js"></script>
<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>

<script src="js/bootbox.min.js"></script>
<script src="js/scripts.js"></script>
<script src="js/common.js"></script>
<script src="common/common.js"></script>
<!--pickers plugins-->
<script type="text/javascript" src="js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="js/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="js/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="js/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<!--pickers initialization-->
<script src="js/pickers-init.js"></script>
<script src="js/loading/jquery.jribbble.min.js"></script>
<script src="js/loading/spin.js"></script>
<script src="js/loading/ladda.js"></script>
<script src="js/loading/respond.min.js"></script>
<script src="dataTables/media/js/jquery.dataTables.min.js"></script>
<script src="dataTables/media/js/dataTables.bootstrap.min.js"></script>
<script src="js/bootstrap-toggle/js/bootstrap-toggle.min.js"></script>
<script type="text/javascript" src="js/d3/d3.v3.min.js"></script>

</html>
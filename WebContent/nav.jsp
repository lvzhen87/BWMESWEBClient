<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.io.*"%> 
	<%@ taglib prefix="st" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="ico/favicon.ico" type="image/x-icon" />


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
    
<title>生产制造执行系统</title>
<script type="text/javascript">


</script>
<style>
.nav>li>a {
	font-size: 26px;
	text-decoration: none;
}
</style>
</head>

<body class="login-body" >

<div class="container">

    <form class="form-signin" action="#" method="post">
        <div class="form-signin-heading text-center">
            <img src="images/lg_logo.png" alt="" width="300px" height="113px"/>
        </div>
        <div class="login-wrap row">
            <ul class='nav nav-stacked' style="">
            <%
        	Properties prop = new Properties();
    		String path=System.getProperty("catalina.base")+"/conf/SYSTEM-CONFIG.properties";
    		InputStream in=new FileInputStream(path);
 			prop.load(in);
 			String temp= prop.getProperty("HTTP_URL");
            %>
				<li><a href='<%=temp%>/MES-EA' target="_blank"><i class='fa fa-th-list'></i> 数据源配置</a></li>
				<li><a href='login.sp?toLoginMainPage' target="_blank"><i class='fa fa-gears'></i> 工厂工艺参数设计</a></li>
				<li><a href='loginso.sp?toMainPage' target="_blank"><i class='fa fa-wrench'></i> 工厂客户端操作</a></li>
			</ul>
        </div>
    </form>

</div>
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
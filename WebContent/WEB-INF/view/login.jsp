<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>登录工厂工艺参数设计页面</title>

    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="loginDesign-body">

<div class="container">

    <form class="form-signin" action="login.sp?toLogin" method="post">
        <div class="form-signin-heading text-center">
            <img src="images/lg_logo.png" alt="" width="300px" height="113px" />
        </div>
        <div class="login-wrap row" style="position: relative;">
        	<span style="font-size: 24pt;color:#00bcb8;position: absolute;left: 15%;top:20%;transform:translate(0%,-50%);display: block;" >工厂工艺参数设计</span>
        </div>
        <div class="login-wrap row">
            <div class="col-xs-12"><input  style="color:white;font-size:14px;background:black;padding-left: 41px;background-image:url('images/user.png');background-repeat:no-repeat;background-position:10px;"  type="text" class="form-control" placeholder="User ID" name="userName" autofocus required></div>
            <div class="col-xs-12"><input style="color:white;font-size:14px;background:black;padding-left: 41px;background-image:url('images/password.png');background-repeat:no-repeat;background-position:10px" type="password" name="password" class="form-control" placeholder="Password" required></div>
            <div class="col-xs-2"></div>
            <div class="col-xs-8"><input id="zdy" type="image" src="images/login_button.png" /></div>
            <div class="col-xs-2"></div>
       
			<div class="registration col-xs-12">${errorMsg }</div>
        </div>
    </form>

</div>



<!-- Placed js at the end of the document so the pages load faster -->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>

</body>
</html>

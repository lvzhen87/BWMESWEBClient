<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="st" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="ico/favicon.ico" type="image/x-icon" />
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet">

<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
    
<title>登录工厂客户端操作页面</title>
<script type="text/javascript">
$(function(){
	
		$("#user").blur(function(){
			$("#error").empty();
		});
		$("#pwd").focus(function(){
			$(this).val("");
			$("#error").empty();
		});
});

</script>
</head>

<body class="loginShop-body" >

<div class="container">

    <form class="form-signin" action="loginso.sp?ShopOperation" method="post">
        <div class="form-signin-heading text-center">
            <img src="images/lg_logo.png" alt="" width="300px" height="113px"/>
        </div>
        <div class="login-wrap row" style="position: relative;">
        	<span style="font-size: 24pt;color:#00bcb8;position: absolute;left: 18%;top:20%;transform:translate(0%,-50%);display: block;" >工厂客户端操作</span>
        </div>
        <div class="login-wrap row">
            <div class="col-xs-12"><input id="user" style="color:white;font-size:14px;background:black;padding-left: 41px;background-image:url('images/user.png');background-repeat:no-repeat;background-position:10px;"  type="text" class="form-control" placeholder="User ID" name="userName" autofocus required></div>
            <div class="col-xs-12"><input id="pwd" style="color:white;font-size:14px;background:black;padding-left: 41px;background-image:url('images/password.png');background-repeat:no-repeat;background-position:10px" type="password" name="password" class="form-control" placeholder="Password" required></div>
            <div class="col-xs-2"></div>
            <div class="col-xs-8"><input id="zdy" type="image" src="images/login_button.png" /></div>
            <div class="col-xs-2"></div>
			<div class="registration col-xs-12" id="error">${errorMsg }</div>
        </div>
    </form>

</div>
</body>
</html>
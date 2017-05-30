<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<script type="text/javascript">
bootbox.alert("${message}",function
		(){
	window.location.href="login.sp?toLoginMainPage"; 
});
</script>
</html>
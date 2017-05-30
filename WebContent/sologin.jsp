<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登陆工厂客户端操作页面</title>
</head>
<body>

<%	Object o = request.getSession().getAttribute("souser");
 	if(o!=null){
 	
 %>

	 <jsp:forward page="WEB-INF/view/so/soindex.jsp"></jsp:forward>

 <%} 
 	else {
 %>
 <jsp:forward page="WEB-INF/view/so/loginso.jsp"></jsp:forward>
 <%}%>


</body>
</html>
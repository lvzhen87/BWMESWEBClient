<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>EA登录</title>
</head>
<body>

<%	Object o = request.getSession().getAttribute("eauser");
 	if(o!=null){
 	
 %>

	 <jsp:forward page="WEB-INF/view/ea/initdbindex.jsp"></jsp:forward>

 <%} 
 	else {
 %>
 <jsp:forward page="WEB-INF/view/ea/loginea.jsp"></jsp:forward>
 <%}%>


</body>
</html>
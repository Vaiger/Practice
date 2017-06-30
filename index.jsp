<%@page import="controllere.JpaController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<center>
	<h3>Робота з базою даних ComponentShop</h3>
	<iframe name="forMenu" src="menuTable.html" width="200" height="230">
	</iframe>
	<iframe name="forTable" width="420" height="230"> </iframe>
	<br>
	<iframe name="forOperation" src="menuOperation.html" width="630"
		height="80"> </iframe>
	<br>
	<iframe name="forDialog" width="630" height="170"> </iframe>
</center>
		<%
				session.setAttribute("controller", new JpaController());
		%>
	
</body>
</html>
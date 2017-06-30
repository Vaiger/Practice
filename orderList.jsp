<%@page import="webview.SelectTable"%>
<%@page import="test.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div align="center">
		Заказы<select size="4" name="idZakaza">
			<%
				Class<Order> c = Order.class;
				for (Object x : SelectTable.getController().getObjectList(c)) {
					Order obj = (Order) x;
			%>
			<option><%=obj.toString()%></option>
			<%
				}
			%>
		</select>
	</div>
</body>
</html>

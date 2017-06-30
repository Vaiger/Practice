<%@page import="webview.SelectTable"%>
<%@page import="test.Buyer"%>
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
		Клієнти<select size="4" name="clientId">
			<%
				Class<Buyer> c = Buyer.class;
				for (Object x : SelectTable.getController().getObjectList(c)) {
					Buyer obj = (Buyer) x;
			%>
			<option><%=obj.toString()%></option>
			<%
				}
			%>
		</select>
	</div>
</body>
</html>

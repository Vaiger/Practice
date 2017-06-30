<%@page import="javax.swing.table.TableModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>showTable</title>
</head>
<body>
	<%
		String tableName = (String) (session.getAttribute("tableName"));
		TableModel tableModel = (TableModel) (session.getAttribute("tableModel"));
	%>
	<center>
		<b> Таблиця <%=tableName%></b>
		<table border="1">
			<tr>
				<%
					int nCol = tableModel.getColumnCount();
					for (int i = 0; i < nCol; i++) {
						String h = tableModel.getColumnName(i);
				%>
				<th width="100"><%=h%></th>
				<%
					}
				%>
			</tr>
			<%
	int nRow = tableModel.getRowCount();
				for (int r = 0; r < nRow; r++) {
			%>
			<tr>
				<%
					for (int j = 0; j < nCol; j++) {
							Object obj = tableModel.getValueAt(r, j);
							String str = "null";
							if (obj != null)
								str = tableModel.getValueAt(r, j).toString();
				%>
				<td width="100" align="center"><%=str%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
</body>
</html>
			
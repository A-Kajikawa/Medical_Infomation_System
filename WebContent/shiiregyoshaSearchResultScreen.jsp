<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.ShiiregyoshaArrayBean" import="bean.ShiiregyoshaBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>仕入れ業者一覧表示</title>
</head>
<body>
<h2>仕入れ業者一覧</h2>
<table border="1">
<tr>
<th>仕入れ先ID</th><th>仕入れ先名</th><th>仕入れ先住所</th><th>仕入れ先電話番号</th><th>資本金</th><th>発注して届く日数</th>
</tr>
<%
ShiiregyoshaArrayBean list = (ShiiregyoshaArrayBean) session.getAttribute("shiiregyoshaArray");
for(ShiiregyoshaBean rcd : list.getShiiregyoshaArray()){
%>
<tr>
<td><%=rcd.getShireid() %></td>
<td><%=rcd.getShiiremei() %></td>
<td><%=rcd.getShiireaddress() %></td>
<td><%=rcd.getShiiretel() %></td>
<td><%=rcd.getShihonkin() %></td>
<td><%=rcd.getNouki() %></td>
<%
	int id = (int)session.getAttribute("id");
	if(id != 1){
%>
<td><form action="DetailServlet" method="get">
<input type="hidden" name="shiireid" value="<%= rcd.getShireid() %>">
<input type="submit" value="電話番号変更"/>
</form></td>
<%
	}else{
%>
<td></td>
<%
	}
%>
<%
}
%>
</tr>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.TouyakuBean" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投薬指示の確認用JSPです</title>
</head>
<body>
<h2>投薬指示確認画面</h2>

<table border="1">
<tr>
<th>薬剤名</th><th>容量</th><th>削除</th>
</tr>
<jsp:useBean id="patientBean" class="bean.PatientBean" scope="session"/>
<jsp:useBean id="touyakuArray" class="bean.TouyakuArrayBean" scope="session"/>
<%
ArrayList<TouyakuBean> array = touyakuArray.getTouyakuArray();
for(TouyakuBean tb : array){
%>
<tr>
<td><%=tb.getMedicine() %></td>
<td><%=tb.getQuantity() %></td>
<td>
<form action="MedicineDeleteServlet" method="get">
<input type="hidden" name="medicine" value=<%=tb.getQuantity() %>>
<input type="submit" value="取消"/>
</form>
</td>
</tr>
<%
}
%>
</table>
<br>
<input type="button" value="処置確定"
			onclick="location.href='TreatmentServlet'" />

<br>
<input type="button" value="薬剤選択画面へ戻る"
			onclick="location.href='treatmentScreen.jsp'" />
</body>
</html>
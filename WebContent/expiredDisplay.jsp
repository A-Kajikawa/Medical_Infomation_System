<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" import="bean.PatientBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>保険証期限切れ表示画面</title>
</head>
<body>
<h1>期限切れ患者表示</h1>
<table border="1">
<tr>
<th>患者ID</th><th>患者名</th><th>患者姓</th><th>保険証名記号番号</th><th>有効期限</th>
</tr>
<jsp:useBean id="patientArrayBean" class="bean.PatientArrayBean" scope="session"/>
<%
ArrayList<PatientBean> patientarrayBean = patientArrayBean.getPatientArray();
for(PatientBean rcd : patientarrayBean){
%>
<tr>
<td><%=rcd.getPatid() %></td>
<td><%=rcd.getPatfname() %></td>
<td><%=rcd.getPatlname() %></td>
<td><%=rcd.getHokenmei() %></td>
<td><%=rcd.getHokenexp() %></td>
</tr>
<%
}
%>
</table>
<br>
<button type="button" onclick="location.href='reception.jsp'">受付画面へ戻る</button>
</body>
</html>
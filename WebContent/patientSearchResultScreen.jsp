<%@page import="bean.PatientArrayBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" import="bean.PatientBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>患者検索結果画面</title>
</head>
<body>
<jsp:useBean id="patientarraybean" class="bean.PatientArrayBean" scope="session"/>
<h2>患者検索結果画面</h2>
<table border="1">
<tr>
<th>患者ID</th><th>患者名</th><th>患者姓</th><th>保険証記号番号</th><th>有効期限</th><th>保険証変更</th>
</tr>
<%
ArrayList<PatientBean> list = patientarraybean.getPatientArray();
String flag = null;
flag = (String) session.getAttribute("flag");
System.out.println(flag);
for(PatientBean rcd : list){
%>
<tr>
<td><%=rcd.getPatid() %></td>
<td><%=rcd.getPatfname() %></td>
<td><%=rcd.getPatlname() %></td>
<td><%=rcd.getHokenmei() %></td>
<td><%=rcd.getHokenexp() %></td>
<td>
<%
//recept(flag0)doctor(flag1)
	if(flag.equals("0")){
%>
	<form action="DetailPatientServlet" method="get">
	<input type="hidden" name="patid" value="<%= rcd.getPatid() %>">
	<input type="submit" value="保険証変更"/>
	</form>
<%
	}else{
%>
	<form action="DetailPatientServlet" method="get">
	<input type="hidden" name="patid" value="<%=rcd.getPatid()%>">
	<input type="submit" value="投薬指示"/>
	</form>
<%
	}
%>
</td>
</tr>
<%
}
%>
</table>
</body>
</html>
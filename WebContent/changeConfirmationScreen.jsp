<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更確認画面</title>
</head>
<body>
<h2>保険証変更確認画面</h2>
<jsp:useBean id="patientBean" class="bean.PatientBean" scope="session"/>
<form action="ChangeInsuranceCardServlet" method="get">
<table>
<tr><td>患者ID:<%= patientBean.getPatid()%></td></tr>
<tr><td>患者姓:<%= patientBean.getPatfname()%></td></tr>
<tr><td>患者名:<%= patientBean.getPatlname()%></td></tr>
<tr><td>現在の保険証記号番号:<%= patientBean.getHokenmei()%></td></tr>
<tr><td>現在の有効期限:<%= patientBean.getHokenexp()%></td></tr>
</table>
<input type="submit" value="変更">
</form>
</body>
</html>
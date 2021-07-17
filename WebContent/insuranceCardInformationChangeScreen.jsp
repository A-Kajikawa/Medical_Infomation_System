<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>保険証情報変更画面</title>
</head>
<body>
<h2>保健証情報変更画面</h2>
<h3>保険証情報</h3>
<jsp:useBean id="patientBean" class="bean.PatientBean" scope="session"/>
<form action="KamaseServlet" method="get">
<table>

<tr><td>患者ID:<%= patientBean.getPatid()%></td></tr>
<tr><td>患者姓:<%= patientBean.getPatfname()%></td></tr>
<tr><td>患者名:<%= patientBean.getPatlname()%></td></tr>
<tr><td>現在の保険証記号番号:<%= patientBean.getHokenmei()%></td></tr>
<tr><td>新しい保険証記号番号:<input type="text" name="hokenmei" size="20"></td></tr>
<!-- この値のみセットアトリビュートする -->
<tr><td>現在の有効期限:<%= patientBean.getHokenexp()%></td></tr>
<tr><td>有効期限:<input type="text" name="hokenexp" size="20"></td></tr>
<!-- この値のみセットアトリビュートする -->
</table>
<input type="submit" value="確認">
</form>
</body>
</html>
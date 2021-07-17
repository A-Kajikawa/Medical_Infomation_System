<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>患者登録画面</title>
</head>
<body>
<h2>患者登録画面</h2>
<%
System.out.println("ここは患者登録JSPです。");
%>
<p>患者登録を行います。入力項目へ入力の上確認ボタンを押してください。
<form action="InputCheackServlet" method="get">
<input type="hidden" name="flag" value="patient">
<p>患者ID<br>
<input type="text" name = "patid" size = 20 required="required">
<p>患者名<br>
<input type="text" name = "patfname" size = 20 required="required">
<p>患者姓<br>
<input type="text" name="patlname" size="20" required="required">
<p>保険証名記号番号<br>
<input type="text" name="hokenmei" size="20" required="required">
<p>有効期限<br>
<input type="date" name="hokenexp" size="20" required="required"><br>
<input type="submit" value="確認">
</form>
</body>
</html>
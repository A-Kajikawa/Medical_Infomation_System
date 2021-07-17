<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>患者検索画面</title>
</head>
<body>
<h2>患者検索画面</h2>
<form action="PatientSerchServlet" method="get">
<p>患者名を入力してください。<br>
<input type="text" name="patfname" size=20 required="required">
<input type="submit" value="検索">
</form>
</body>
</html>
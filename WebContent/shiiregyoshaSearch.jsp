<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>仕入れ業者検索画面</title>
</head>
<body>
<h2>仕入れ業者検索</h2>
<p>電話番号を変更する仕入れ業者の仕入れIDを入力して下さい。
<form action="ShiiregyoshaSearchServlet" method="get">
<input type="text" name="shiireid" size=20>
<input type="submit" value="検索">
</form>
</body>
</html>
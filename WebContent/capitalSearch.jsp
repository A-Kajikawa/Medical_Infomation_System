<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資本金検索画面</title>
</head>
<body>
<h2>資本金検索画面</h2>
<p>資本金検索をします。下のテキストボックスに検索したい資本金を入力してください。入力された値以上の仕入れ業者を表示します。
<form action="CapitalSearchServlet" method="get">
<input type="text" name="shihonkin" size="20">
<input type="submit" value="検索">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者用従業員検索画面</title>
</head>
<body>
<h2>従業員検索画面</h2>
<p>パスワードを変更する従業員のユーザーIDを入力してください。
<form action="EmployeeSearchServlet" method="get">
<input type="text" name="empid" size="20">
<input type="submit" value="検索">
</form>
</body>
</html>
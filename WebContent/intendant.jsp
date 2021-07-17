<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員管理</title>
</head>
<body>
<jsp:useBean id="loginBean" class="bean.LoginBean"></jsp:useBean>
<h1>従業員登録機能</h1>
<form action="IntendantServlet" method="get">
<p>従業員ID
<input type="text" name = "id" size = 20>
<p>従業員名
<input type="text" name = "lname" size = 20>
<p>従業員姓
<input type="text" name="fname" size="20">
<p>社員パスワード
<input type="text" name="passwd" size="20">
<p>受付は0を医師は1を入力
<input type="text" name="emp" size="20">
<input type="submit" value="登録">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更画面</title>
</head>
<body>
<jsp:useBean id="loginBean" class="bean.LoginBean" scope="session"/>
<h2>パスワード変更画面</h2>
<form action="ChangePasswordServlet" method="get">
<p>新しいパスワード<br>
<input type="text" name="newPasswd" size="20"><br>
<p>もう一度入力してください<br>
<input type="text" name="newPasswd2" size="20"><br>
<input type="submit" value="登録">
</form>
</body>
</html>
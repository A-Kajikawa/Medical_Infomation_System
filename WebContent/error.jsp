<%@page import="exception.LoginException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面</title>
</head>
<body>
<h1>エラー</h1>
<%LoginException e = (LoginException)session.getAttribute("Except");
e.getMessage();%>
<h3>入力に誤りがあります。ID・PWを確認してください</h3>
<input type="button" value="戻る"
			onclick="location.href='login_func.html'" />
</body>
</html>
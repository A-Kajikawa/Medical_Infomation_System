<%@page import="exception.LoginException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面</title>
</head>
<body>
<h1>エラー</h1>
<%
HttpSession session2 = request.getSession();
String flag = (String)session2.getAttribute("flag");
System.out.println("ここはエラー画面3です");
System.out.println(flag);
%>
<%LoginException e = (LoginException)session.getAttribute("Except");
e.getMessage();%>
<h3>変更できませんでした。正しい値を入力してください。</h3>

<input type="button" value="戻る"
			onclick="location.href='changePhoneNumber.jsp'" />
</body>
</html>
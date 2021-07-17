<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>電話番号変更画面</title>
</head>
<body>
<jsp:useBean id="shiiregyoshaBean" class="bean.ShiiregyoshaBean" scope="session"/>
<h2>電話番号変更画面</h2>
<form action="ChangeNumberServlet" method="get">
<%
String shiireid = shiiregyoshaBean.getShireid();
session.setAttribute("shiireid", shiireid);
%>
<p>新しい電話番号<br>
<input type="text" name="newPhoneNumber" size=20 required="required">
<p>もう一度入力してください<br>
<input type="text" name="newPhoneNumber2" size=20 required="required">
<input type="submit" value="変更">
</form>
</body>
</html>
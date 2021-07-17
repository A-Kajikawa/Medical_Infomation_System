<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員登録画面</title>
</head>
<body>
<h1>従業員登録画面</h1>
<%
System.out.println("登録画面JSP実行中");
%>
<p>ここでは従業員の登録を行います。入力項目に入力の上、確認ボタンを押してください。
<form action="InputCheackServlet" method="get">
<input type="hidden" name="flag" value="employee">
<p>従業員ID
<input type="text" name = "empid" size = 20 required="required">
<p>従業員名
<input type="text" name = "empfname" size = 20 required="required">
<p>従業員姓
<input type="text" name="emplname" size="20" required="required">
<p>社員パスワード
<input type="password" name="emppasswd" size="20" required="required">
<p>社員パスワード(確認)
<input type="password" name="emppasswd2" size="20" required="required">
<p>職種を選択してください
<input type="radio" value="reception" name="radio" checked="checked">受付
<input type="radio" value="doctor" name="radio">医師
<input type="submit" value="確認">
</form>
</body>
</html>
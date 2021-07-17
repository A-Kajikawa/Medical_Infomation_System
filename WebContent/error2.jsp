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
System.out.println("ここはエラー画面2です");
System.out.println(flag);
%>
<%LoginException e = (LoginException)session.getAttribute("Except");
e.getMessage();%>
<h3>変更できませんでした。正しい値を入力してください。</h3>
<%
if(flag.equals("employee")){
%>
<input type="button" value="戻る"
			onclick="location.href='changepasswd.jsp'" />
<%
}else if(flag.equals("recept")){
%>
<input type="button" value="戻る"
			onclick="location.href='patientRegistration.jsp'" />
<%
}else{
%>
<input type="button" value="戻る"
			onclick="location.href='employeeRegistration.jsp'" />
<%
}
%>
</body>
</html>
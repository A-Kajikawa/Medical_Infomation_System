<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員登録確認画面</title>
</head>
<body>
<h2>登録する内容はこれでよろしいでしょうか？</h2>
<jsp:useBean id ="loginBean" class="bean.LoginBean" scope="session"/>
<form action="EmployeeRegistrationServlet" method="get">
<p>従業員ID
<%=loginBean.getEmpId() %>
<p>従業員姓
<%=loginBean.getEmpFname() %>
<p>従業員名
<%=loginBean.getEmpLname() %>
<p>社員パスワード
<%=loginBean.getPassWord() %>
<%
if(loginBean.getEmpRole() == 1){
%>
<p>受付
<%
}else{
%>
<p>医師
<%
}
session.setAttribute("loginBean", loginBean);
%>
<input type="submit" value="登録">
</form>
</body>
</html>
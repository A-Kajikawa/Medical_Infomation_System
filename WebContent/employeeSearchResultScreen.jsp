<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員検索結果画面</title>
</head>
<body>
<h2>従業員検索結果画面</h2>
<jsp:useBean id="loginBean" class="bean.LoginBean" scope="session"/>
<table border="1">
<tr>
<th>従業員ID</th><th>従業員名</th><th>従業員姓</th><th>従業員パスワード</th><th>職種No</th><th>パスワード変更</th>
</tr>
<tr>
<td><%=loginBean.getEmpId() %></td>
<td><%=loginBean.getEmpFname() %></td>
<td><%=loginBean.getEmpLname() %></td>
<td><%=loginBean.getPassWord() %></td>
<td><%=loginBean.getEmpRole() %></td>
<td><form action="changepasswd.jsp" method="get">
<input type="hidden">
<input type="submit" value="パスワード変更"/>
</form></td>
</tr>
</table>
</body>
</html>
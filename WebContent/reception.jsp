<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受付画面</title>
<style type="text/css">
.btn {
  border-radius: 5px;
  background-color: lightblue;
  padding: 10px;
  text-decoration: none;
  color: white;
}
</style>
</head>
<body>
<jsp:useBean id="loginBean" class="bean.LoginBean" scope="session"/>
<h2><%=loginBean.getEmpFname()%>さん。こんにちは</h2>
<h2>受付画面</h2>
<a href="changepasswd.jsp" class="btn">パスワード変更</a><br><br><br>
<a href="patientRegistration.jsp" class="btn">患者登録</a><br><br><br>
<a href="patientSerch.jsp" class="btn">患者検索・保険証情報変更</a><br><br><br>
<a href="ExpirationDateServlet" class="btn">保険証期限確認機能</a><br><br><br>
<input type="button" value="ログイン画面へ戻る"
			onclick="location.href='login_func.html'" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者用メニュー画面</title>
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
<h2>管理者メニュー画面</h2>
<a href="employeeRegistration.jsp" class="btn">従業員登録</a><br><br><br>
<a href="ShiiregyoshaListDisplayServlet" class="btn">仕入れ業者一覧表示</a><br><br><br>
<a href="shiiregyoshaSearch.jsp" class="btn">仕入れ業者検索・電話番号変更</a><br><br><br>
<a href="employeeSearch.jsp" class="btn">従業員検索・従業員パスワード変更</a><br><br><br>
<a href="capitalSearch.jsp" class="btn">資本金検索</a><br><br><br>
<input type="button" value="ログイン画面へ戻る"
			onclick="location.href='login_func.html'" />
</body>
</html>
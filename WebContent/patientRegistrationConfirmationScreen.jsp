<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>患者登録確認画面</title>
</head>
<body>
<h2>登録する内容はこれでよろしいでしょうか？</h2>
<jsp:useBean id ="patientBean" class="bean.PatientBean" scope="session"/>
<form action="PatientRegistrationServlet" method="get">
<%
System.out.println("患者登録確認JSPフェーズ開始");
%>
<p>患者ID
<%=patientBean.getPatid() %>
<p>患者姓
<%=patientBean.getPatfname() %>
<p>患者名
<%=patientBean.getPatlname() %>
<p>保険証記号番号
<%=patientBean.getHokenmei() %>
<p>有効期限
<%=patientBean.getHokenexp() %>
<input type="submit" value="登録">
</form>
</body>
</html>
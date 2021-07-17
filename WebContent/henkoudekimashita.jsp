<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.LoginBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>変更できました。</h2>
<jsp:useBean id="loginBean" class="bean.LoginBean"/>
<%
LoginBean loginbean = (LoginBean)session.getAttribute("loginBean");
System.out.println("ここは変更できました画面でした");
System.out.println(loginbean.getEmpId());
System.out.println(loginbean.getEmpFname());
System.out.println(loginbean.getEmpLname());
System.out.println(loginbean.getPassWord());
System.out.println(loginbean.getEmpRole());

	if(loginbean.getEmpRole() == 0){
%>
	<input type="button" value="戻る"
			onclick="location.href='manager.jsp'" />
<%
	}else if(loginbean.getEmpRole() == 1){
%>
	<input type="button" value="戻る"
			onclick="location.href='reception.jsp'" />
<%
	}else{
%>
	<input type="button" value="戻る"
			onclick="location.href='doctor.jsp'" />
<%
	}
%>
</body>
</html>
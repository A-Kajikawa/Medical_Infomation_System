<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>処置画面</title>
<style type="text/css">
table { width: 600px; }
th { padding: 3px; height: 50px; }
td { padding: 3px; height: 50px; }
th.cell_title { font-size: 150%; }
td.cell_image { width: 70%; }
td.cell_info { width: 30%; }
td.cell_comment { font-size: 120%;}
</style>
</head>
<body>
<h2>処置画面</h2>
<jsp:useBean id="patientBean" class="bean.PatientBean" scope="session"/>
<jsp:useBean id="touyakuArray" class="bean.TouyakuArrayBean" scope="session"/>

<form action="MedicineCheackServlet" method="get">
<table border="1">
<tr>
<td colspan="3"><%=patientBean.getPatfname() + patientBean.getPatlname() + "さん"%></td>
</tr>
<tr>
<td>薬名</td>
<td>投薬可否</td>
<td>数量</td>
</tr>
<tr>
<td>
<label>エスタックイブファインEX</label>
</td>
<td>
<input type="radio" name="medicine1" value="1">する
<input type="radio" name="medicine1" value="0" checked="checked">しない
</td>
<td>
<select name="medicine1_quantity1">
	<option value="1mg" selected>1mg</option>
	<option value="2mg">2mg</option>
	<option value="3mg">3mg</option>
	<option value="4mg">4mg</option>
	<option value="5mg">5mg</option>
	<option value="6mg">6mg</option>
	<option value="7mg">7mg</option>
	<option value="8mg">8mg</option>
	<option value="9mg">9mg</option>
</select>
</td>
</tr>
<tr>
<td>
<label>パブロンエースPro錠</label>
</td>
<td>
<input type="radio" name="medicine2" value="1">する
<input type="radio" name="medicine2" value="0" checked="checked">しない
</td>

<td>
<select name="medicine2_quantity2">
	<option value="1mg" selected>1mg</option>
	<option value="2mg">2mg</option>
	<option value="3mg">3mg</option>
	<option value="4mg">4mg</option>
	<option value="5mg">5mg</option>
	<option value="6mg">6mg</option>
	<option value="7mg">7mg</option>
	<option value="8mg">8mg</option>
	<option value="9mg">9mg</option>
</select>
</td>
</tr>
<tr>
<td>
<label>パブロンエースPro微粒</label>
</td>
<td>
<input type="radio" name="medicine3" value="1">する
<input type="radio" name="medicine3" value="0" checked="checked">しない
</td>

<td>
<select name="medicine3_quantity3">
	<option value="1mg" selected>1mg</option>
	<option value="2mg">2mg</option>
	<option value="3mg">3mg</option>
	<option value="4mg">4mg</option>
	<option value="5mg">5mg</option>
	<option value="6mg">6mg</option>
	<option value="7mg">7mg</option>
	<option value="8mg">8mg</option>
	<option value="9mg">9mg</option>
</select>
</td>
</tr>
<tr>
<td><input type="text" name="otherInput" size="20"></td>
<td>
<input type="radio" name="other" value="1">する
<input type="radio" name="other" value="0" checked="checked">しない
</td>
<td><input type="text" name="otherQuantity" size="20"></td>
</tr>
</table>
<input type="submit" value="確認">
</form>
<input type="button" value="戻る" onclick="location.href='patientSearchResultScreen.jsp'"/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body >
<table border="2">
	<tr><td>
	<form action="ConverterServlet" method="post" enctype="multipart/form-data">
		<table>
			<tr><td>xls:</td><td><input type="file" name="xls"></td></tr>
			<tr><td colspan="2"><input type="reset" value="RESET"><input type="submit" value="SUBMIT"/></td></tr>
		</table>
	</form>
	</td></tr>
	<tr><td>
	<form action="ConverterServlet" method="post" enctype="multipart/form-data">
		<table>
			<tr><td>word:</td><td><input type="file" name="doc"></td></tr>
			<tr><td colspan="2"><input type="reset" value="RESET"><input type="submit" value="SUBMIT"/></td></tr>
		</table>
	</form>
	</td></tr>
</table>
</body>
</html>
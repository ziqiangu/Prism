<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%=request.getSession().getAttribute("msg")%>
	LOGIN
	<form id = "myForm" name = "prism_form"  method= "GET" action = "login">
		Name:  <input type ="text" name = "n" > <br>
		Password:  <input type ="text" name = "p" > <br>
		<input type = "submit" name = "submit" value = "login">
	</form>
	ADD USER
	<form id = "myForm2" name = "prism_form2"  method= "POST" action = "login">
		Name:  <input type ="text" name = "n" > <br>
		Password:  <input type ="text" name = "p" > <br>
		<input type = "submit" name = "submit" value = "login">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World</title>
</head>
<body>
	<%@ page language="java" %>
	<%! int count = 0; %>
	<% for (int i = 0; i < 10; i++){
		count = count + 1;
	%>
	<br/>
	The counter value is: <%= count %>
	<%} %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Info_First</title>
<style>
body
{
	background-color:#CBE4C9;
}
h1
{
	color:#131E12;
	//text-align:center;
}
p
{
	font-family:"Times New Roman";
	font-size:20px;
}
</style>
</head>
<body>

<jsp:useBean id="hi" class="ve450.ruix.sql_connection" scope="page" />

<%String[] op = hi.gogogo();%>

<h1>Sample</h1>
<h2>Main Info</h2>
<p>Name:&nbsp <%= op[1]%></p>
<p>ID:&nbsp <%= op[0]%></p>
<p>date_of_birth:&nbsp <%= op[2]%></p>
<p>Father:&nbsp <%= op[4]%></p>
<a href="http://www.baidu.com/">Visit Father</a> 	

<h2>Maintenance Info</h2>
<p>Last fixing time:&nbsp <%= op[3]%></p>
</body>
</html>
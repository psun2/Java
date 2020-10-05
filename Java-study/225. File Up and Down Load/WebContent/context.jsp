<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="file.testFileDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>context Test</title>
</head>
<body>
	<%
		new testFileDAO().testContext();
	%>
</body>
</html>
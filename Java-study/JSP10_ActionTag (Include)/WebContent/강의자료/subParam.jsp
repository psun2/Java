<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.net.URLDecoder"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>subParam</title>
</head>
<body>
	<%!String id, pw;%>

	<%
		id = request.getParameter("id");
	pw = request.getParameter("pw");
	%>

	아이디:
	<%=URLDecoder.decode(id, "utf-8")%><br /> 비밀번호:
	<%=pw%><br />
</body>
</html>
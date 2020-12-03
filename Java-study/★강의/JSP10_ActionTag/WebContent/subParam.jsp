<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>subParam</title>
</head>
<body>
<%! String id, pw; %>
<%
	//id = request.getParameter("id");
	id = URLDecoder.decode(request.getParameter("id"), "utf-8");
	pw = request.getParameter("pw");
%>
<p> 현재 페이지는 subParam 입니다.</p>
아이디: <%= id %><br>
비밀번호: <%= pw %><br>
</body>
</html>












<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.net.URLEncoder"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>param2</title>
</head>
<body>

	<p>현재 페이지는 param2 페이지 입니다.</p>

	<%
		int num = 788;
	String id = "홍길동";
	%>
	<jsp:include page="subParam.jsp">
		<jsp:param value="<%=URLEncoder.encode(id, \"utf-8\")%>" name="id" />
		<jsp:param value="<%=num%>" name="pw" />
	</jsp:include>

	<p>위 내용은 sunParam 의 내용입니다.</p>

</body>
</html>
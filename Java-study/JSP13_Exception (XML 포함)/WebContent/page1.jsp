<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 페이지에 직접 지시 명시한 에러 페이지는 xml 에서 
	정의한 내용보다 우선 순위가 높아 해당 바인딩된 에러 페이지로 이동
 -->
<%@ page errorPage="page1_error.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>예외 발생1</title>
</head>
<body>
<%
	request.getParameter("id").toUpperCase();
%>
</body>
</html>
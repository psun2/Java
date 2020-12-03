<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- page 지시자 태그를 사용한 예외 처리 --%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>예외발생1</title>
</head>
<body>
<%= request.getParameter("id").toUpperCase() %>
</body>
</html>












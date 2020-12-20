<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> <!-- security tag lib -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Denied Page</title>
</head>
<body>
	<h1>Access Denied Page 페이지</h1>
	<!-- Access is denied -->
	<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage() }" /></h2>
	<!-- 접근 권한 거부 -->
	<h2><c:out value="${msg }" /></h2>
</body>
</html>
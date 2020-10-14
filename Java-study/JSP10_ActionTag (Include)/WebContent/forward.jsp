<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>forward</title>
</head>
<body>
	<h3>현재 페이는 forward page 입니다.</h3>
	
	<jsp:forward page="forward_sub.jsp"/>
	
	<h3>forward page 의 마지막 입니다.</h3>
</body>
</html>
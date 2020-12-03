<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>   
<% response.setStatus(200); %> 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>에러안내</title>
</head>
<body>
에러가 발생했습니다...<br>
예외 타입은 : <%= exception.getClass().getName() %><br>
예외 메세지는 <%= exception.getMessage() %><br>
</body>
</html>












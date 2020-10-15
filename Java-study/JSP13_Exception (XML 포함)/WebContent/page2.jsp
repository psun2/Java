<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>예외 발생2</title>
</head>
<body>

<%--
페이지 1 예외 처리 방법과는 다르게, 
<%@ page errorPage="" %>(지시자) 작성을 안해 줘도 됨
 --%>

<% int a = 555 / 0; %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%response.setStatus(HttpServletResponse.SC_OK); %>
<!-- 
	status 200 : 정상 설정 의 이유
	에러 페이지 이므로 에러에 대한 메시지를 담고 있는 현재 페이지를
	보여 주기 위함
 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>404error</title>
</head>
<body>
	요청한 페이지는 존재하지 않습니다.
</body>
</html>
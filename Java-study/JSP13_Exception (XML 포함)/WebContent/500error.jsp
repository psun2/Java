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
<title>500error</title>
</head>
<body>
	개발자의 개발 미스로 인한 500번대 error 입니다.<br />
	서비스 이용에 불편을 끼쳐드려 죄송합니다.<br />
	빠른 시일내에 문제를 해결하겠습니다.<br />
</body>
</html>
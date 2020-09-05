<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		session.invalidate(); // 세션을 빼앗기도록 만들어 줍니다. => 로그 아웃
	%>
	<script>
		location.href = 'main.jsp';
	</script>
</body>
</html>
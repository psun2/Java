<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div>
		${id } <br />
		<%= request.getParameter("name") %> <br />
	</div>
	<button onclick="history.back()">뒤로가기</button>
</body>
</html>
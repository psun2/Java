<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>forward</title>
</head>
<body>

	<p>현재 페이지는 forward 페이지 입니다 ...</p>
	
	<!-- Action Tag -->
	<jsp:forward page="sub.jsp" />
	<!-- 다른 페이지가 request 되었는데 url 이 바뀌지 않았습니다.
	최초 request url 은 그대로 인데 내용은 sub.jsp 의 내용으로 reponse합니다.
	forward 와 sendRedirect 의 큰 차이점은 url 의 변화 차이 입니다.
	 -->

	<p>위 라인의 내용은 sub 페이지의 내용 입니다</p>
</body>
</html>
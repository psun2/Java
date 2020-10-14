<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>param</title>
</head>
<body>

	<p>현재 페이지는 param 페이지 입니다.</p>

	<%
		int num = 788;
	%>

	<!-- jsp:forward 는 url 만 그대로 이고 안의 내용은 attribute page 의 내용 입니다. -->

	<jsp:forward page="subParam.jsp">
		<jsp:param value="test123" name="id" />
		<jsp:param value="<%=num%>" name="pw" />
	</jsp:forward>

	<p>위 내용은 sunParam 의 내용입니다.</p>

</body>
</html>
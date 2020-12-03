<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
<%
	int sum = 0;
	for(int cnt = 1; cnt <= 100; cnt++){
		sum += cnt;
	}
%>
1부터 100까지의 합은: <%= sum %>
 --%>

	<h3>오늘의 식단</h3>
	- 치킨<br>
	- 피자<br>
	- 라면<br>
	<%@ include file = "test.jsp" %>

</body>
</html>












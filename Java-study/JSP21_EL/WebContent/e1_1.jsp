<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>EL</title>
</head>
<body>
	<h3>Expression Language</h3>
	${10}<br />
	${99.9}<br />
	${"ABC"}<br /> 
	${true}<br />
	
	<hr />
	<h3>EL 의 연산자</h3>
	${1 + 2}<br />
	${1 - 2}<br />
	${1 * 2}<br /> 
	${1 / 2}<br /> <%-- EL 은 Java 언어가 아니다! --%>
	${1 > 2}<br /> 
	${1 < 2}<br /> 
	${(1 < 2) ? 1 : 2}<br /> 
	${(1 < 2) || (1 > 2)}<br /> 
	
	<hr />
	<h3>EL 은 자바변수 사용 불가</h3>
	<%
		int num = 10;
	%>
	num: ${num}<br /> <!-- 동작 안합니다. EL 은 자바 변수 사용 불가 -->
	num: <%=num %>
	
</body>
</html>
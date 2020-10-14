<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
JSP 태그 <%@  ~~ %> 는
디렉티브 (direcrive, ‘지시자’) 라고 합니다   
--%>

<%
	System.out.println("페이지 로딩 순서 알아 보기");
	System.out.println("tagEx.jsp 페이지 로딩\n");
%>
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
		for (int cnt = 1; cnt <= 100; cnt++) {
			sum += cnt;
		}
	%>
	1부터 100까지의 합은:
	<%=sum%>
 	--%>
 	
 	<h3>오늘의 식단</h3>
	-치킨<br /> 
	-피자<br /> 
	-라면<br />
	<b style="color: red">
		<%@ include file="test.jsp" %>
	</b>
	
</body>
</html>
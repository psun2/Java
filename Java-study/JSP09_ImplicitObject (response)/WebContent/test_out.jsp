<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>out</title>
</head>
<body>
	<%
		int i = 0;
		while(true) {
			i++;
			out.println("5 * " + i + " = " + 5 * i + "<br />");
	
	%>
	---<br />
	<%
			if(i == 9) break;
		}
	%>
	
	<%! // 선언부태그: 변수선언, 메소드 정의
		int j = 100;
		String str = "test";
		
		public int sum(int a, int b) {
			return a * b;
		}
	%>
	
	<%--에러
	<% // 스크립트릿 에서의 메소드 정의
		int i = 100;
		String str = "test";
		
		public int sum2(int a, int b) {
			return a * b;
		}
	%>
	--%>
	
	<hr />
	i = <%=i %><br />
	str = <%=str %><br />
	sum = <%=sum(6, 5) %>
</body>
</html>
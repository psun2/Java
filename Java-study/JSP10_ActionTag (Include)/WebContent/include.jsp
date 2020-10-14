<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>include</title>
</head>
<body>
	<h3>현재 페이는 include page 입니다.</h3>
	
	<!-- @지시자의 include -->
	<%! 
		// 선언문으로 선언하지 않고
		// 스크립트릿 으로 선언한 변수는 변수가 아닙니다.
		String message1 = "여기는 include 페이지 입니다.";
	%>
	
	<%@ include file="include_sub.jsp" %>
	넘겨준 변수 message1: <%=message1 %><br />
	넘겨받은 변수 message2: <%=message2 %><br />
	
	<h3>include page 의 마지막 입니다.</h3>
	
	<hr />
	
	<h3>액션태그 include 와 @지시자 clude의 차이점</h3>
	<p>
	지시자를 활용한 @include는 java로 변환 될때 java 코드로 삽입된후 함께 컴파일 되고,<br />
	또한 변수의 공유가 가능합니다.<br />
	</p>
	
	<p>
	액션 태그를 이용한 jsp:include 는 파라미터를 넘겨 줄 수 있고, 컴파일이 끝나고,<br />
	HTML로 보여주기 직전에 삽입 됩니다.<br />
	변수 공유가 안되는 대신 변수를 request로 넘겨 줄수 있습니다.<br />
	</p>
	
</body>
</html>
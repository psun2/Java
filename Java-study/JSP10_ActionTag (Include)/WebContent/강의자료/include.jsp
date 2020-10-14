<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Include</title>
</head>
<body>

	<p>현재 페이지는 include 페이지 입니다 ...</p>
	<!-- Action Tag -->
	<jsp:include page="sub.jsp" />
	<p>위 라인의 내용은 sub 페이지의 내용 입니다</p>

	<hr />

	<p>현재 페이지는 include 페이지 입니다 ...</p>
	<%@ include file="sub.jsp"%>
	<p>위 라인의 내용은 sub 페이지의 내용 입니다</p>

	<!-- 크롬에서 열어 페이지 소스보기를 통해 비교해본 결과 
	똑같은 결과가 나옵니다.
	 -->

	<!-- 둘의 차이점 은 삽입 시점입니다.
	 지시자를 활용한 @include는 java로 변환 될때 java 코드로 삽입된후 함께 컴파일 되고,
	 액션 태그를 이용한 jsp:include 는 파라미터를 넘겨 줄 수 있고, 컴파일이 끈나고,
	 HTML로 보여주기 직전에 삽입 됩니다.
	  -->

	<hr />
	<%!// 변수선언
	String name = "홍길동";
	int age = 33;%>

	<!-- 변수를 서로 공유 할 수 있습니다. -->
	<%@ include file="sub2.jsp"%>
	성별:
	<%=gender%>

	<!-- 변수 공유가 되지 않습니다 Error -->
	<!-- 대신 parameter을 넘겨 줄 수 있습니다. -->
	<%--
	<jsp:include page="sub2.jsp" />
	성별:
	<%=gender%>
	 --%>



</body>
</html>
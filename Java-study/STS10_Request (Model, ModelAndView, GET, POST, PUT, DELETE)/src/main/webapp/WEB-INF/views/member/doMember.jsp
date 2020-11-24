<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<jsp:include page="/common" />
<!-- 물리적 파일이 없어서 나는 에러 -->
<!-- 즉 include 할때 /common의 controller 로 요청을 보냅니다. -->

<!-- 에러가 싫다면 에러 제거 방법 2가지 -->

<!-- 1. 에러를 끈다 -->
<!-- Project => 우클릭 => Properties => Validation => Jsp Syntax => Enable project specific settings => Included fragment file not found => Ignore -->

<!-- 2. 물리적 파일에 연결합니다. -->
<%--  <jsp:include page="./comn.jsp" /> --%>
<!-- 
	jsp 주석 <%--  --%> 이 아닐시 include 문이 적용이 됩니다.
 -->
<!-- HTML 주석 사용 불가 --> 
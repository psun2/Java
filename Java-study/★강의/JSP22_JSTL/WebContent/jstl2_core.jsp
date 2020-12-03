<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSTL Core2</title>
</head>
<body>

<h2>set, remove, out</h2>
이름: <c:out value='jang'/><br>  
<c:set var="name" value="홍길동"/>  <%-- 변수 생성 --%>
이름: <c:out value='${name }'/><br>  <%-- JSTL 의 변수는 -> EL 에서 사용 가능 --%>
${name }<br>

<c:remove var="name"/>  <%-- 변수 삭제 --%>
이름: <c:out value="${name }"/><br>

<hr>
<%
	int age = 10;
%>
나이: ${age }<br>  <%-- Java 변수는 EL 에서 사용(출력) 불가. Java -> EL (x) --%>
<c:set var="age" value="<%= age %>"/> <%-- Java -> JSTL -> EL (o) --%>
나이: ${age }<br>

<hr>
<h2>catch</h2>

<c:catch var="error">
<!-- 이 사이에서 예외 발생하면 예외 객체를 error 변수에 담는다 -->
	<%= 2 / 0 %> 		
</c:catch>
<br>
<c:out value='${error }'/>
<br>

<c:catch var="ex">
name parameter 값 = <%= request.getParameter("name") %><br>
name parameter 값 = ${param.name }<br>

<% if(request.getParameter("name").equals("test")){ // 예외발생 %>
		${param.name }은  test 입니다.<br>
<% }%>

</c:catch>

<c:if test="${ex != null }">
	예외발생<br>
	${ex }<br>
</c:if>

</body>
</html>
































<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>JSTL CORE</title>
</head>
<body>
	<h2>set, remove, out</h2>
	이름: <c:out value="jang" /><br />
	<c:set var="name" value="홍길동" /> <!-- 컨테이너 쪽에서 만들어 지는 변수 생성 -->
	${name }<br />
	
	<c:remove var="name" /> <!-- 변수 삭제 -->
	이름: <c:out value="${name }" /> => 변수가 삭제되어 출력 안됨<br />
	이름: ${name } => 변수가 삭제되어 출력 안됨<br />
	
	<hr />
	
	<%
		int age = 10;
	%>
	나이: ${age } => 자바 변수는 EL 에서 사용 불가<br />
	
	<c:set var="age" value="<%=age %>" />
	나이: ${age }<br />
	나이 <c:out value="${name }" /><br />
	
	<hr />
	
	<h2>catch</h2>
	<%-- <%=2 / 0 %> exceptiion 페이지로 넘어갑니다. --%>
	<c:catch var="error">
		<!-- 이 사이에서의 예외 발생하면 예외 객체를 error 변수에 담는다 -->
		<%=2 / 0%>
	</c:catch> 
	<br />
	<c:out value="${error }" /><br />
	
	<c:catch var="ex">
		name parameter 값 = <%=request.getParameter("name") %><br />
		name parameter 값 = ${param.name }<br />
		
		<%
			if(request.getParameter("name").equals("test")) {
		%>
			${param.name } 은 test 입니다.<br />
		<%
			}
		%>
	</c:catch>
	
	<c:if test="${ex !=null }">
		예외발생<br />
		${ex }
	</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 본 페이지는 에러 페이지임을 표시 -->  
<%@ page isErrorPage="true" %>


<!-- 
	status 200 : 정상 설정 의 이유
	에러 페이지 이므로 에러에 대한 메시지를 담고 있는 현재 페이지를
	보여 주기 위함
 -->
<%--
<% response.setStatus(200); %>
 --%>
<% response.setStatus(HttpServletResponse.SC_OK); %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
에러가 발생했습니다...<br />
<!-- 
페이지가 바뀌지 않은채 error 페이지가 나타납니다.
이 결과로 이 페이지는 forward 된 페이지 입니다.
-->

<%--  

<jsp:forward page=""></jsp:forward> 와 같습니다.  

--%>

예외 타입은: <%=exception.getClass().getName() %><br />
예외 메시지는 <%=exception.getMessage() %><br />

<!-- 
그냥을 사용 불가
페이지 지사자를 통해 isErrorPage="true" 를 해줘야 사용 가능합니다.
-->
</body>
</html>
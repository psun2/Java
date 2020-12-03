<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lec.beans.*" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>EL request</title>
</head>
<body>
<%
	pageContext.setAttribute("myage", "묻지마");
	request.setAttribute("myage", 30);
	request.setAttribute("mydto", new WriteDTO(100, "제목", "내용", "작성자", 3));
%>

	<%-- scope 우선순위
		page > request > session > application
	 --%>
	${myage }<br>  <%-- pageContext.getAttribute("myage") --%>
	${requestScope.myage }<br><%-- 콕 찝어서 request 의 속성값 --%>
	${pageScope.myage} <br> <%-- 콕 찝어서 pageContext 의 속성값 --%>
	
	${mydto }<br>  <%-- toString() 값 --%>
	${mydto.uid }<br>
	<%= ((WriteDTO)request.getAttribute("mydto")).getUid() %><br>
	${mydto.subject }<br>
	${mydto.content }<br>

</body>
</html>












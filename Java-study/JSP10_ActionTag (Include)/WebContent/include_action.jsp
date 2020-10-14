<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- action 태그의 include -->
	<%--
	셀프 클로징 태그 사용 방법
	<jsp:include page="include_action_sub.jsp" />
	 --%>
	 
	 <jsp:include page="include_action_sub.jsp">
	 	<jsp:param value="<%=URLEncoder.encode(\"홍길동\", \"utf-8\") %>" name="message"/>
	 </jsp:include>
</body>
</html>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session 내부 객체 사용
	// session.getAttribute(name) <-- 특정 name 의 attr value.
	
	// 세션의 모~든  attr name 들 추출하기

	String sessionName, sessionValue;
	int i = 0;
	
	Enumeration<String> enumeration = session.getAttributeNames();
	while(enumeration.hasMoreElements()) {
		sessionName = enumeration.nextElement();
		sessionValue = session.getAttribute(sessionName).toString();
		out.println((i + 1) + "]" + sessionName + " : " + sessionValue + "<br />");
		i++;
	}
	if(i == 0) {
		out.println("세션 안에 attribute 가 없습니다.");
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="sessionCreate.jsp" method="get">
	<input type="submit" value="세션생성"/>
</form>

<br />

<form action="sessionDelete.jsp" method="get">
	<input type="submit" value="세션삭제"/>
</form>

<br />

<%
	String sessionId = session.getId();
	int sessionInterval = session.getMaxInactiveInterval();
	
	out.println("세션 ID: " + sessionId + "<br />");
	out.println("세션 유효시간: " + sessionInterval + "<br />");
	// 세션 시간은 서버에 설정 되어 있습니다.(tomcat)
	
	// Server 의 web.xml 에서 볼 수 있습니다.
	// <session-config>
        // <session-timeout>30</session-timeout>
    // </session-config>

%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Session List</title>
</head>
<body>
<%
	// session 내부 객체 사용
	// session.getAttribute(name)  <-- 세션의 특정 name  의 attr value. (Object 리턴)
	
	// 세션의 모~든 attr name 들 추출하기
	
	String sessionName, sessionValue;
	int i = 0;
	
	Enumeration<String> enumeration = session.getAttributeNames();  // Enumeration<String> 리턴  
	while(enumeration.hasMoreElements()){
		sessionName = enumeration.nextElement();
		sessionValue = session.getAttribute(sessionName).toString(); 
		out.println((i + 1) + "] " + sessionName + " : " + sessionValue + "<br>");
		i++;
	}
	if(i == 0){
		out.println("세션 안에 attribute 가 없습니다<br>");
	}
%>
<br>
<form action="sessionCreate.jsp" method="get">
<input type="submit" value="세션생성">
</form>

<br>
<form action="sessionDelete.jsp" method="get">
<input type="submit" value="세션삭제">
</form>

<br>
<%
	String sessionId = session.getId();
	int sessionInterval = session.getMaxInactiveInterval();
	
	out.println("세션 ID: " + sessionId + "<br>");
	out.println("세션 유효시간: " + sessionInterval + "<br>");
%>

<hr>
<%
	out.println("--- session.invalidate() ----<br>");
	
	//session.invalidate();   // 세션 무효화
%>


</body>
</html>












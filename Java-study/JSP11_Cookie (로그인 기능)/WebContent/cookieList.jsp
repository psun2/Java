<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 쿠기 검사 -->    
<%
	// 쿠키는 사용자(클라이언트)에 저장 되므로 클라이언트에서 담아 올 수 있도록 합니다.
	Cookie[] cookies = request.getCookies();

	if(cookies != null) {
		for(int i = 0; i < cookies.length; i++) {
			String cookieName = cookies[i].getName(); // 쿠키 '이름'
			String cookieValue = cookies[i].getValue(); // 쿠키 '값'
			out.println((i + 1) + "] " + cookieName + " : " + cookieValue + "<br />");
		}
	} else {
		out.println("쿠키가 없습니다.<br />");
	}
	 
	// 최초 request 할때는 쿠키가 없었으나, 
	// refresh 할때는 tomcat에서 발행한 쿠키가 하나 생깁니다.
%>
   
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>cookie 리스트</title>
</head>
<body>

<form action="cookieCreate.jsp" method="get">
	<input type="submit" value="쿠키생성&갱신" />
</form>

<br />

<form action="cookieDelete.jsp" method="get">
	<input type="submit" value="쿠키삭제" />
</form>

</body>
</html>
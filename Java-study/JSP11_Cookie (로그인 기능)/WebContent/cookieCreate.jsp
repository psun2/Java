<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
 	String cookieName = "num";
	String cookieValue = "" + Math.floor(Math.random() * 100);

	// 쿠키 생성
	Cookie cookie = new Cookie(cookieName, cookieValue);
	
	// 쿠키 파기(expiry) 시간 설정 (30초 후)
	cookie.setMaxAge(30); // reponse 되고 30 초 후 파기 됩니다.
	
	response.addCookie(cookie);
	
%>

<script>
	alert('<%=cookieName%>  쿠키생성');
	location.href="cookieList.jsp"
</script>
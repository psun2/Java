<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cookieName = "num";
	String cookieValue = "" + Math.floor(Math.random() * 100);
	Cookie cookie = new Cookie(cookieName, cookieValue); // 이름-값  쌍으로 쿠키 생성
	cookie.setMaxAge(30); // 쿠키 파기 (expiry) 시간 설정 (30초 후)
	response.addCookie(cookie);
%>

<script>
alert("<%= cookieName %> 쿠키 생성");
location.href = "cookieList.jsp";
</script>









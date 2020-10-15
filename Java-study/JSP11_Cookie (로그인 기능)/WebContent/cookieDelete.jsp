<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String cookieName = "num";
	
	// 삭제 시킬 쿠키와 똑같은 이름의 쿠키 생성
	Cookie cookie = new Cookie(cookieName, "");
	
	// 직접적으로 삭제 하는 루트가 없으므로, 
	// 파기 시간을 0 초 로 설정해 reponse 와 동시에 파기 하게끔 합니다.
	cookie.setMaxAge(0);
	
	response.addCookie(cookie);

%>

<script>
	alert('<%=cookieName%>  쿠키생성');
	location.href="cookieList.jsp"
</script>
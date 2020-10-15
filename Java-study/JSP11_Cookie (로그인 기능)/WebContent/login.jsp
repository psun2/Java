<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	
	Cookie cookie = null;

	if(cookies != null) {
		for(Cookie cok: cookies) {
			if(cok.getName().equals("id")) {
				cookie = cok;
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	if(cookie == null) {
%>
	<h2>로그인 상태가 아닙니다.</h2>
	<form action="loginAction.jsp" method="post">
		id: <input name="id" type="text" required /><br />
		pw: <input name="pw" type="password" required /><br />
		<input type="submit" value="로그인" /><br />
	</form>
<%
	} else {
%>
	<h2>로그인 상태입니다.</h2>
	<form action="logout.jsp" method="post">	
		<input type="submit" value="로그아웃" /><br />
	</form>
<%
	}
%>
</body>
</html>
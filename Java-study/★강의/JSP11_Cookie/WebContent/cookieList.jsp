<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>cookie 리스트</title>
</head>
<body>
<%
	// 클라이언트가 보낸 request  안에 cookie 들이 담겨 있다. (있다면!)
	Cookie [] cookies = request.getCookies();  // Cookie [] 배열 리턴
	
	if(cookies != null){  // 쿠키가 하나도 없다면 null 이 리턴된다.
		for(int i = 0; i < cookies.length; i++){
			String cookieName = cookies[i].getName();  // 쿠키 '이름'
			String cookieValue = cookies[i].getValue();  // 쿠키 '값'
			out.println((i + 1) + "] " + cookieName + " : " + cookieValue + "<br>");
		}
	} else {
		out.println("쿠키가 없습니다 <br>");
	}
%>
<br>
<form action="cookieCreate.jsp" method="get">
<input type="submit" value="쿠키생성&갱신">
</form>

<br>
<form action="cookieDelete.jsp" method="get">
<input type="submit" value="쿠키삭제">
</form>


</body>
</html>

















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// toString 을 했는데 넘어온 값이 null 이 였다면 null exception 발생
	// String userid = session.getAttribute("userid").toString();
	
	String userid = (String) session.getAttribute("userid");

	System.out.println(userid);
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(userid == null || userid.equals("")) {
	%>
	<h2>로그인 상태가 아닙니다.</h2>
	<form action="loginAction.jsp" method="post">
		id: <input name="userid" type="text" required /><br />
		pw: <input name="pw" type="password" required /><br />
		<input type="submit" value="로그인" /><br />
	</form>
	<%	
		} else {
	%>
	<h2>로그인 상태입니다.</h2>
	<form action="logout.jsp" method="post">	
		<input type="submit" value="로그아웃" required/><br />
	</form>
	<%		
		}
	%>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- import -->
<%@ page import="user.UserDAO"%>
<!-- 자바스크립트 작성을 위한 PrintWriter 패키지 import -->
<%@ page import="java.io.PrintWriter"%>
<!-- 응답받는 모든 데이터의 인코딩 설정 -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- 자바beans 사용 -->
<jsp:useBean id="user" class="user.UserDTO" scope="page" />
<!-- setProperty는 login.jsp에서 넘겨준 데이터를 그대로 받는 역할을 합니다. -->
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if (userID != null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 로그인이 되어있습니다.');");
		script.println("location.href = 'main.jsp';");
		script.println("</script>");
		script.close();
		return;
	}

	UserDAO userDAO = new UserDAO();
	int result = userDAO.login(user.getUserID(), user.getUserPassword());

	if (result == 1) { // 로그인 성공
		session.setAttribute("userID", user.getUserID()); // 로그인이 되어 있는 상태인  세션값 부여
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'main.jsp';");
		script.println("</script>");
		script.close();
		return;
	} else if (result == 0) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀렸습니다.');");
		// history.back() : 이전페이지로 사용자를 돌려 보냅니다.
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	} else if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('아이디가 존재하지 않습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	} else if (result == -2) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	%>
</body>
</html>
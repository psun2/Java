<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		System.out.println("진입");
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String userID = null;
	String userPassword = null;

	if (request.getParameter("userID") != null)
		userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");

	if (request.getParameter("userPassword") != null)
		userPassword = URLDecoder.decode(request.getParameter("userPassword"), "UTF-8");

	if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("")) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}

	UserDAO userDAO = new UserDAO();

	int result = userDAO.login(userID, userPassword);

	if (result == 1) { // 로그인 성공
		session.setAttribute("userID", userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	if (result == 0) { // 비밀번호 오류
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀렸습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	if (result == -1) { // 없는 아이디
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('없는 아이디 입니다.');");
		script.println("location.href='userJoin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	if (result == -2) { // 데이터 베이스 오류
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류 입니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	%>
</body>
</html>
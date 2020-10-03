<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="util.SHA256"%>
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
	if (session.getAttribute("userID") != null)
		userID = (String) session.getAttribute("userID");

	if (userID != null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('현재 로그인 상태입니다.');");
		script.println("location.href='userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}

	String userPassword = null;
	String userEmail = null;

	if (request.getParameter("userID") != null)
		userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");

	if (request.getParameter("userPassword") != null)
		userPassword = URLDecoder.decode(request.getParameter("userPassword"), "UTF-8");

	if (request.getParameter("userEmail") != null)
		userEmail = URLDecoder.decode(request.getParameter("userEmail"), "UTF-8");

	if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("") || userEmail == null
			|| userEmail.equals("")) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}

	UserDAO userDAO = new UserDAO();

	int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail), 0));
	System.out.println(result);

	if (result == 1) { // 회원 가입 성공
		session.setAttribute("userID", userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='emailSendAction.jsp'");
		script.println("</script>");
		script.close();
	} else { // 회원가입 실패
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 아이디 입니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	%>
</body>
</html>
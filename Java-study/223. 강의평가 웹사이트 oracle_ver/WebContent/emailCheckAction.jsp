<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="user.UserDAO"%>
<%@page import="util.SHA256"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String code = null;
	String userID = null;

	if (request.getParameter("code") != null)
		code = URLDecoder.decode(request.getParameter("code"), "UTF-8");

	if (session.getAttribute("userID") != null)
		userID = URLDecoder.decode((String) session.getAttribute("userID"), "UTF-8");

	if (userID == null || userID.equals("")) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.');");
		script.println("location.href='userLogin.jsp';");
		script.println("</script>");
		script.close();
		return;
	}

	UserDAO userDAO = new UserDAO();

	String userEmail = userDAO.getUserEmail(userID);
	
	System.out.println(userEmail);
	System.out.println(SHA256.getSHA256(userEmail));
	System.out.println(code);

	boolean isRight = SHA256.getSHA256(userEmail).equals(code);

	if (isRight) {
		userDAO.setUserEmailChecked(userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('인증에 성공했습니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 코드입니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	
	%>
</body>
</html>
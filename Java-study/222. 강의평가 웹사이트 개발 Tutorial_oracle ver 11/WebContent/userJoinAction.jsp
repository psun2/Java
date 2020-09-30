<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8"); // 없을시 한글이 깨집니다.
	response.setContentType("text/html; charset=UTF-8");
	// userID : íê¸
	// userPassword : íê¸

	String userID = null;
	String userPassword = null;

	if (request.getParameter("userID") != null)
		userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");

	if (request.getParameter("userPassword") != null)
		userPassword = URLDecoder.decode(request.getParameter("userPassword"), "UTF-8");

	System.out.println("userID : " + request.getParameter("userID"));
	System.out.println("userPassword : " + request.getParameter("userPassword"));
	System.out.println("userID : " + userID);
	System.out.println("userPassword : " + userPassword);

	if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("")) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}

	int result = new UserDAO().join(userID, userPassword);

	if (result == 1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입에 성공했습니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	} else if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입에 실패했습니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}

	// context 사용 (성공)
	int result2 = new UserDAO().join2(userID, userPassword);

	if (result2 == 1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입에 성공했습니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	} else if (result2 == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입에 실패했습니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	%>


</body>
</html>
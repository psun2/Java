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
<jsp:setProperty name="user" property="userName" />
<jsp:setProperty name="user" property="userGender" />
<jsp:setProperty name="user" property="userEmail" />
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

	if (user.getUserID() == null || user.getUserID().equals("") || user.getUserPassword() == null
			|| user.getUserPassword().equals("") || user.getUserName() == null || user.getUserName().equals("")
			|| user.getUserGender() == null || user.getUserGender().equals("") || user.getUserEmail() == null
			|| user.getUserEmail().equals("")) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
	} else {

		UserDAO userDAO = new UserDAO();
		int result = userDAO.join(user);
		// join 함수는 UserDTO를 받는데 12 ~ 18 Line 에서 이미 UserDTO를 셋팅을 했습니다.
		System.out.println("데이터베이스 반환 값 : " + result);

		if (result == -1) { // 회원가입 실패
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 존재하는 아이디 입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else { // 회원가입 성공
			session.setAttribute("userID", user.getUserID()); // 로그인이 되어 있는 상태인  세션값 부여
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'main.jsp';");
			script.println("</script>");
			script.close();
		}
	}
	%>
</body>
</html>
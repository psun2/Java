<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 회원가입을 처리하는 함수 -->
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>

<!-- java.io.PrintWriter : 특정한 스트립트 구문을 실행 할 때 사용 -->
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

String userID = null;
String userPassword = null;

// 유저아이디 라는 값을 사용자가 실제로 잘 전송 했다면...
if (request.getParameter("userID") != null) {
	userID = request.getParameter("userID");
}
if (request.getParameter("userPassword") != null) {
	userPassword = request.getParameter("userPassword");
}

// 사용자가 정상적으로 입력하지 않은 경우
if (userID == null || userPassword == null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력이 안 된 사항이 있습니다.');");
	// 입력이 되지 않아 다시 뒤쪽으로 돌려 보내는 history.back() 함수 사용
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

// 정상입력 한경우 => 회원가입
UserDAO userDAO = new UserDAO();
int result = userDAO.login(userID, userPassword);

if (result == 1) { // 정상적으로 로그인이 된경우
	session.setAttribute("userID", userID);
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("location.href = 'index.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

} else if (result == 0) { // 비밀번호가 틀렸을때

	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('비밀번호가 틀렸습니다.');");
	script.println("history.back()");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

} else if (result == -1) {

	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('존재하지 않는 아이디 입니다.');");
	script.println("history.back()");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

} else if (result == -2) {

	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('데이터베이스 오류가 발생했습니다.');");
	script.println("history.back()");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

}
%>
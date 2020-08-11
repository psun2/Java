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

// 로그인 상태라면
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
}

// 로그인 상태시 회원 가입을 하지 못함
if (userID != null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인이 된 상태입니다.');");
	script.println("location.href = 'index.jsp';");
	script.println("</script>");
	script.close();
	return;
}

String userPassword = null;
String userEmail = null;

// 유저아이디 라는 값을 사용자가 실제로 잘 전송 했다면...
if (request.getParameter("userID") != null) {
	userID = request.getParameter("userID");
}
if (request.getParameter("userPassword") != null) {
	userPassword = request.getParameter("userPassword");
}
if (request.getParameter("userEmail") != null) {
	userEmail = request.getParameter("userEmail");
}

// 사용자가 정상적으로 입력하지 않은 경우
if (userID == null || userPassword == null || userEmail == null) {
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
int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail), false));

if (result == -1) { // DAO 의 join함수에서 회원가입 실패시 -1을 반환

	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('이미 존재하는 아이디 입니다.');");
	// 입력이 되지 않아 다시 뒤쪽으로 돌려 보내는 history.back() 함수 사용
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

} else { // 성공시 1을 반환

	// 회원가입 성공시 session값을 주어 바로 로그인이 될수 있도록 서버에서 관리
	session.setAttribute("userID", userID);
	PrintWriter script = response.getWriter();
	script.println("<script>");
	// 로그인 성공시 특정페이지로 이동
	// 즉 사용자가 회원가입이 성공되고 mail 인증을 받을 수 있도록 바로 인증 페이지로 넘어 갑니다.
	script.println("location.href='emailSendAction.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

}
%>
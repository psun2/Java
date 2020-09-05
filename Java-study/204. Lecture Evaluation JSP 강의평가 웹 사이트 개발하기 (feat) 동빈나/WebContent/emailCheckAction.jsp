<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>

<!-- java.io.PrintWriter : 특정한 스트립트 구문을 실행 할 때 사용 -->
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

String hashCode = null;

// emailSend 에서 보낸 url 을 클릭시 그 주소의 hash 코드를 가지고 옴....
// 그걸 request 받음
if (request.getParameter("hashCode") != null) {
	hashCode = request.getParameter("hashCode");
}

UserDAO userDAO = new UserDAO();

String userID = null;

// 로그인 한경우
if (session.getAttribute("userID") != null) { // 사용자가 현재 로그인 중인지를 판단	
	userID = (String) session.getAttribute("userID");
	// session 값 같은 경우 기본적으로 Object 를 반환 합니다.
}

// 로그인 하지 않은 경우
if (userID == null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인을 해주세요.');");
	script.println("location.href = 'userLogin.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

String userEmail = userDAO.getUserEmail(userID);

// 사용자의 이메일이 정확히 해쉬값과 일치 하는지 확인 하는 작업 입니다.
boolean isRight = (new SHA256().getSHA256(userEmail).equals(hashCode)) ? true : false;

// 사용자가 보낸 값이 정상적이라면
if (isRight) {
	userDAO.setUserEmailChecked(userID); // 이메일 인증 성공시 db에 성공 여부를 남김
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('인증에 성공했습니다.');");
	script.println("location.href = 'index.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

} else { // 정상값이 아니여서 이메일 인증에 실패 한 경우
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('유효하지 않는 코드입니다.');");
	script.println("location.href = 'index.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}
%>
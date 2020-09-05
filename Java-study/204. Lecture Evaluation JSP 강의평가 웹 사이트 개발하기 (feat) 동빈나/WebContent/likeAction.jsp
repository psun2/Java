<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="user.UserDAO"%>
<%@ page import="evaluation.EvaluationDAO"%>
<%@ page import="likey.LikeyDAO"%>

<!-- java.io.PrintWriter : 특정한 스트립트 구문을 실행 할 때 사용 -->
<%@ page import="java.io.PrintWriter"%>

<%!// 사용자의 IP를 얻음
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		System.out.println("likeAction ip : " + ip);
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("likeAction ip : " + ip);
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("likeAction ip : " + ip);
		}
		// 그래도 null값이면 기본적인 주소를 받아옴
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
			System.out.println("likeAction ip : " + ip);
		}
		System.out.println("likeAction ip : " + ip);
		return ip;
	}%>
<!--  사용자의 IP를 확인 한뒤, 로그인정보를 확인 합니다. -->
<%
	// 기본적으로 강의추천 삭제는 사용자가 로그인을 한 상태 이여야 합니다.
String userID = null; // 초기에 기본적으로 사용자의 아이디는 null 값을 가지고 있음
if (session.getAttribute("userID") != null) {
	// 현재 사용자가 로그인 한 상태 
	// 즉, 세션값이 유효한 상태 일때
	userID = (String) session.getAttribute("userID");
}

if (userID == null) { // 사용자가 로그인 하지 않은 경우
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인을 해주세요.');");
	// 경고 메세지 후 로그인 페이지로 이동
	script.println("location.href = 'userLogin.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

// 인코딩 셋팅
request.setCharacterEncoding("UTF-8");
String evaluationID = null;

if (request.getParameter("evaluationID") != null) {
	evaluationID = request.getParameter("evaluationID");
}

System.out.println();
System.out.println("getParameter : " + request.getParameter("evaluationID"));
// 세션을 등록 한 적이 없어 null
System.out.println("getAttribute : " + session.getAttribute("evaluationID"));
System.out.println();

EvaluationDAO evaluationDAO = new EvaluationDAO();
LikeyDAO likeyDAO = new LikeyDAO();
int result = likeyDAO.like(userID, evaluationID, getClientIP(request));

// 해당 게시글을 삭제할때 그 게시글을 작성한 작성자가 맞는가를 판단합니다.

if (result == 1) { // 추천 성공
	result = evaluationDAO.like(evaluationID); // 강의 평가 글의 추천 갯수를 늘려줍니다.
	if (result == 1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('추천이 완료되었습니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();
		return;
	} else { // 추천 실패
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터 베이스 오류가 발생 했습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
} else { // 이미 추천을 누른 상태라면
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('이미 추천을 누른 글입니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return;
}
%>
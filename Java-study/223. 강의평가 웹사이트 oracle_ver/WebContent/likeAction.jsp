<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<%@page import="evaluation.EvaluationDAO"%>
<%@page import="likey.LikeyDAO"%>
<%@page import="likey.LikeyDTO"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.PrintWriter"%>

<%!public static String getClientIP(HttpServletRequest request) {

		String ip = request.getHeader("X-FORWARDED-FOR");

		System.out.println(ip);

		if (ip == null || ip.length() == 0)
			ip = request.getHeader("Proxy-Client-IP");

		if (ip == null || ip.length() == 0)
			ip = request.getHeader("WL-Proxy-Client-IP");

		if (ip == null || ip.length() == 0)
			ip = request.getRemoteAddr();

		return ip;

	}%>


<%
	UserDAO userDAO = new UserDAO();
String userID = null;
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

request.setCharacterEncoding("UTF-8");

String evaluationID = null;

if (request.getParameter("evaluationID") != null)
	evaluationID = URLDecoder.decode(request.getParameter("evaluationID"), "UTF-8");

LikeyDAO likeyDAO = new LikeyDAO();
EvaluationDAO evaluationDAO = new EvaluationDAO();

if (userID.equals(evaluationDAO.getUserID(evaluationID))) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('본인인 작성한 게시글은 추천 할 수 없습니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return;
}

int result = likeyDAO.like(userID, evaluationID, getClientIP(request));

if (result == 1) { // 추천 성공
	result = evaluationDAO.like(evaluationID);

	if (result == 1) {

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('추천이 완료되었습니다.');");
		script.println("location.href='index.jsp';");
		script.println("</script>");
		script.close();

	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
	}

} else {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('이미 추천을 누른 글 입니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
}
%>

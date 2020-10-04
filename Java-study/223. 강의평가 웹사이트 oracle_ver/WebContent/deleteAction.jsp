<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<%@page import="evaluation.EvaluationDAO"%>
<%@page import="likey.LikeyDAO"%>
<%@page import="likey.LikeyDTO"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.PrintWriter"%>

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

EvaluationDAO evaluationDAO = new EvaluationDAO();
if (userID.equals(evaluationDAO.getUserID(evaluationID))) {

	int result = evaluationDAO.delete(evaluationID);

	if (result == 1) { // 삭제 성공
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('삭제가 완료되었습니다.');");
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
	script.println("alert('자신이 쓴 글만 삭제 할 수 있습니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
}
%>

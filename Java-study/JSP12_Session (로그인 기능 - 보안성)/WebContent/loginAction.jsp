<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	private static final String USERID = "admin";
	private static final String USERPW = "1234";
%>

<%
	if(session.getAttribute("userid") != null) {
		out.println("<script>");
		out.println("alert('이미 로그인된 사용자 입니다.');");
		out.println("history.back();");
		out.println("</script>");
		return;
	}

	String userId = null;
	String pw = null;
	
	if(request.getParameter("userid") != null)
		userId = request.getParameter("userid");

	if(request.getParameter("pw") != null)
		pw = request.getParameter("pw");
	
	if(userId == null || userId.equals("") ||
			pw == null || pw.equals("")) {
		out.println("<script>");
		out.println("alert('모든 항목을 입력해 주세요.');");
		out.println("history.back();");
		out.println("</script>");
		return;
	}
	
	if(!userId.equals(USERID)) {
		out.println("<script>");
		out.println("alert('아이디가 일치하지 않습니다.');");
		out.println("history.back();");
		out.println("</script>");
		return;
	}
	
	if(!pw.equals(USERPW)) {
		out.println("<script>");
		out.println("alert('비밀번호가 일치하지 않습니다.');");
		out.println("history.back();");
		out.println("</script>");
		return;
	}
	
	session.setAttribute("userid", userId);

%>

<script>
	alert('로그인에 성공했습니다.');
	location.href='login.jsp';
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	private static final String USERID = "admin";
	private static final String USERPW = "1234";
%>


<%
	Cookie[] cookies = request.getCookies();
	
	Cookie cookie = null;

	if(cookies != null) {
		for(Cookie cok: cookies) {
			if(cok.getName().equals("id")) {
				cookie = cok;
				break;
			}
		}
	}
	
	// 로그인 상태 라면 ....
	if(cookie != null) {
		out.println("<script>");
		out.println("alert('이미 로그인 상태입니다.');");
		out.println("history.back();");		
		out.println("</script>");
		return;
	}
	
	String userId = null;
	String userPw = null;
	
	if(request.getParameter("id") != null)
		userId = request.getParameter("id");

	if(request.getParameter("pw") != null)
		userPw = request.getParameter("pw");
	
	if(userId == null || userId.equals("") || 
			userPw == null || userPw.equals("")) {
		out.println("<script>");
		out.println("alert('모든 항목을 입력해 주세요.');");
		out.println("history.back();");		
		out.println("</script>");
		return;
	}
	
	if(!userId.equals(USERID)) {
		out.println("<script>");
		out.println("alert('아이디가 일치 하지 않습니다.');");
		out.println("history.back();");		
		out.println("</script>");
		return;
	}

	if(!userPw.equals(USERPW)) {
		out.println("<script>");
		out.println("alert('비밀번호가 일치 하지 않습니다.');");
		out.println("history.back();");		
		out.println("</script>");
		return;
	}

	cookie = new Cookie("id", userId);
	
	cookie.setMaxAge(30);
	
	response.addCookie(cookie);
	
	out.println("<script>");
	out.println("alert('로그인에 성공했습니다.');");
	out.println("location.href='login.jsp';");		
	out.println("</script>");

%>
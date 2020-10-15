<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cookie = new Cookie("id", "");

	cookie.setMaxAge(0);
	
	response.addCookie(cookie);
	
	out.println("<script>");
	out.println("alert('로그아웃에 성공했습니다.');");
	out.println("location.href='login.jsp';");		
	out.println("</script>");
%>
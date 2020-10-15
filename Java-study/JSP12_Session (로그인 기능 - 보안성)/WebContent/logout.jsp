<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.removeAttribute("userid"); // 로그아웃

%>

<script>
	alert('로그아웃');
	location.href="login.jsp";
</script>
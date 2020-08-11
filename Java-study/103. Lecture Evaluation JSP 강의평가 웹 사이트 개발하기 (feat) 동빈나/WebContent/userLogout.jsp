<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// session.invalidate() : 현재 사용자가 클라이언트의 세션정보를 파기 합니다.
session.invalidate();
%>

<script>
// 로그아웃시 메인페이지로 이동
location.href = 'index.jsp';
</script>
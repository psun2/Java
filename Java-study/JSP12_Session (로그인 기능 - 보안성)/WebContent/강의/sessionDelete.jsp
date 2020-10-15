<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sessionName = "num";

	// 세션 삭제 (name)
	session.removeAttribute(sessionName);
%>

<script>
alert("<%= sessionName%> 세션삭제");
location.href = "sessionList.jsp";
</script>











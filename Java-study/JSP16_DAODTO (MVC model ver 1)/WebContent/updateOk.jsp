<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lec.beans.*"%>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO" />

	
	<%	// parameter 받아 오기
		request.setCharacterEncoding("UTF-8");
	
		int uid = Integer.parseInt(request.getParameter("uid"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// 사실 이단계에서 parameter 검증 필요
	%>
	
	<% // dao 사용한 트랜잭션
		int cnt = dao.update(uid, subject, content);
	
	%>
	
	<%
		if(cnt == 0) {
	%>
		<script>
			alert('수정 실패');
			history.back();
		</script>
	<%	
		} else {
	%>
		<script>
			alert('수정 성공');
			location.href='list.jsp?uid=<%=uid%>';
		</script>
	<%		
		}
	%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%	// parameter 받아 오기
		request.setCharacterEncoding("UTF-8");
	
		int uid = Integer.parseInt(request.getParameter("uid"));
	%>
	
	<% // dao 사용한 트랜잭션
		int cnt = (Integer) request.getAttribute("result");
	
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
			location.href='list.park?uid=<%=uid%>';
		</script>
	<%		
		}
	%>
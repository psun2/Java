<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<% // dao 사용한 트랜잭션
		int cnt = (Integer) request.getAttribute("result");
	%>
	
	<%
		if(cnt == 0) {
	%>
		<script>
			alert('삭제 실패');
			history.back();
		</script>
	<%	
		} else {
	%>
		<script>
			alert('삭제 성공');
			location.href='list.park';
		</script>
	<%		
		}
	%>
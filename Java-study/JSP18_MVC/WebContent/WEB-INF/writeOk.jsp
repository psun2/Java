<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/> <%-- DAO bean 생성 --%>    
<%
request.setCharacterEncoding("UTF-8");
%>
	
	<% // dao 사용한 트랜잭션
		int cnt = (Integer) request.getAttribute("result");
	%>
	
	<%
		if(cnt == 0) {
	%>
		<script>
			alert('등록 실패');
			location.back();
		</script>
	<%		
		} else {
	%>
		<script>
			alert('등록성공, 리스트 출력합니다.');
			location.href="list.park";
		</script>
	<%	
		}
	%>
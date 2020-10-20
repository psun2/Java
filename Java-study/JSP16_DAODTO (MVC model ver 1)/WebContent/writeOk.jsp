<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO"/> <%-- DAO bean 생성 --%>    
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dto" class="com.lec.beans.WriteDTO"/>
<jsp:setProperty property="*" name="dto"/>

<%--	
	<%
		request.setCharacterEncoding("UTF-8"); // POST, 한글인코딩 꼭!
		
		// parameter 받기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// 유효성체크
		// name, subject 가 null 이거나 빈 문자열이면 이전화면으로 돌아가기
		if(name == null || name.trim().equals("") || subject == null || subject.trim().equals("")) {			
	%>
			<script>
				alert('작성자 이름, 글제목을 입력하세요!!');
				history.back();
			</script>
	<%
			return; // 더이상 JSP 프로세싱 하지 않도록 여기서 종료
		}
	%>
 --%>	
	
	<% // dao 사용한 트랜잭션
		// int cnt = dao.insert(subject, content, name);
		int cnt = dao.insert(dto);
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
			location.href="list.jsp";
		</script>
	<%	
		}
	%>
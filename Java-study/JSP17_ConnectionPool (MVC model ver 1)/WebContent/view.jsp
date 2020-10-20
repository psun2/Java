<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="com.lec.beans.*"%> 
<jsp:useBean id="dao" class="com.lec.beans.WriteDAO" /><%-- DAO bean 생성 --%>

	
	<% // parameter 받아오기
		int uid = Integer.parseInt(request.getParameter("uid"));
		// 이단계에서  parameter 검증 필요			
	%>
	
	<% //dao 사용한 트랜잭션
		WriteDTO[] arr = dao.readByUid(uid);
	%>
	
	<%
		if(arr == null || arr.length == 0) {
	%>
		<script>
			alert('해당 정보가 삭제되거나 없습니다.');
			history.back();
		</script>
	<%		
			return;
		}
	%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>읽기 <%=arr[0].getSubject() %></title>
</head>
	<script>
		function chkDelete(uid) {
			// 삭제 여부, 다시 확인하고 진행하기
			var r = confirm('정말로 삭제 하시겠습니까?');
			
			if(r) location.href='deleteOk.jsp?uid=<%=uid%>';
		}
	</script>
<body>	
	<h2>읽기 <%=arr[0].getSubject() %></h2>
	<br />
	UID: <%=arr[0].getUid() %><br />
	작성자: <%=arr[0].getName() %><br />
	제목: <%=arr[0].getSubject() %><br />
	날짜: <%=arr[0].getRegDate() %><br />
	조회수: <%=arr[0].getViewcnt() %><br />
	내용 <br />
	<hr />
	<div>
	<%=arr[0].getContent() %>
	</div>
	<hr />
	<br />
	<button onclick="location.href='update.jsp?uid=<%=arr[0].getUid() %>'">수정하기</button>
	<button onclick="location.href='list.jsp'">목록보기</button>
	<button onclick="chkDelete(<%=arr[0].getUid() %>);">삭제하기</button>
	<button onclick="location.href='write.jsp'">신규등록</button>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>

	<%
		request.setCharacterEncoding("UTF-8");
		String strPage = request.getParameter("page");
		
		int pagingCnt = 10;
		int pageNum = 1;
		
		try {
			pageNum = Integer.parseInt(strPage);
		} catch(Exception e) {
			e.printStackTrace();
	%>
		<script>
			alert('접근 할 수 없습니다.');
			histroy.back();
		</script>
	<%
			return;
		}
		
		// 1 ~ 9 까지는 몫이 0 근데 10도 몫을 0으로 만들기 위하여 -1 을 해줌
		int startPagingNum = ((pageNum - 1) / pagingCnt) * pagingCnt  + 1;
		int endPagingNum = startPagingNum + pagingCnt - 1;
	%>
	
	<%!
		//JDBC 관련 기본 객체 변수들 선언
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement psmt= null;
		ResultSet rs = null; // excuteQuery() SELET 결과
		int cnt = 0;
		
		// Connection 에 필요한 값 셋팅
		final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
		final String USERID = "scott_b";
		final String USERPW = "tiger_b";

	%>
	
	<%!
		// 쿼리문 준비
		// ex) String sql_xxx = "INSERT INTO .....";
		
		// 1. endPaging 의 갯수를 알아보기 위한 query
		String endSql = "SELECT COUNT(*) FROM test_write";
		
		int maxPagingNum = 0;

	%>
	
	<%
	
		try {
			Class.forName(DRIVER);
			out.println("드라이버 로딩 성공<br />"); // 테스트 출력
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			out.println("conn 성공<br />"); // 테스트 출력
			
			// 트랜잭션 실행
			psmt = conn.prepareStatement(endSql);
			rs = psmt.executeQuery();
			if(rs.next())
				cnt = rs.getInt(1); // 총데이터 갯수를 알아옴
			
			maxPagingNum = (int) Math.ceil(cnt / pagingCnt); // ceil 반올림 메서드
			// reminder(나머지) 나머지가 있다는 것은 데이터가 있다는 증거이고, 
			// 그럼 올림을 하여  잔바리 데이터들을 한 페이지에 올 수 있도록 합니다.
			
			if(endPagingNum > maxPagingNum)
				endPagingNum = maxPagingNum;
			// endPagingNum => 무조건 + 9씩을 해주어서 
			// 페이지 처리가 안될 부분도 무조건 숫자를 맞추려 함으로... 맨 마지막 페이지의 번호를 받아서.
			// 그 번호가 넘으려 할때 그 번호로 바꾸어 줍니다.
			
			
		} catch(Exception e) {
			e.printStackTrace();
			// 예외 처리를 하든지, 예외 페이지를 설정 해주어야 한다.
		} finally {
			// DB 리소스 해제
			try{if(rs !=null && !rs.isClosed()) rs.close();}catch(Exception e2){e2.printStackTrace();}
			try{if(stmt !=null && !stmt.isClosed()) stmt.close();}catch(Exception e2){e2.printStackTrace();}
			try{if(psmt !=null && !psmt.isClosed()) psmt.close();}catch(Exception e2){e2.printStackTrace();}
			try{if(conn !=null && !conn.isClosed()) conn.close();}catch(Exception e2){e2.printStackTrace();}
		}
	%>
	
	<%
		System.out.println("startPagingNum: " + startPagingNum);
		System.out.println("endPagingNum: " + endPagingNum);
	%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<ul>
		<%
			if(pageNum > 1) {
		%>
			<li><a href="list.jsp?page=1" class='tooltip-top' title='처음'><i class='fa fa-angle-double-left'></i></a></li>
		<%		
			}
		%>
		<%
			if(startPagingNum > 1) {
		%>
			<li><a href="list.jsp?page=<%=(pageNum / 10 - 1) * 10 + 1  %>" class='tooltip-top' title='이전'><i class='fa fa-angle-left'></i></a></li>
		<%		
			}
		%>
		<%
			for(int i = startPagingNum; i <= endPagingNum; i++) {
		%>
			<li><a href="list.jsp?page=<%=i %>"><%=i %></a></li>
		<%		
			}
		%>
		<%
			if(maxPagingNum > endPagingNum) {
		%>
			<li><a href='list.jsp?page=<%=((pageNum / 10) + 1) * 10 + 1 %>' class='tooltip-top' title='다음'><i class='fa fa-angle-right'></i></a></li>
		<%
			}
		%>
		<%
			if(pageNum < maxPagingNum) {
		%>
			<li><a href='list.jsp?page=<%=maxPagingNum %>' class='tooltip-top' title='맨끝'><i class='fa fa-angle-double-right'></i></a></li>
		<%
			}
		%>
	</ul>
</body>
</html>
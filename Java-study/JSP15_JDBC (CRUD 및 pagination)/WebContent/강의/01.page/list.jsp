<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	int curPage = 1; // 현재 페이지 (디폴트 1 page);
	
	// 현재 몇 페이지 인지 parameter 가져오기 + 검증
	String pageParam = request.getParameter("page");
	
	if(pageParam != null && !pageParam.trim().equals("")) {
		try {
			int p = Integer.parseInt(pageParam);
			if(p > 0) curPage = p;
		} catch(NumberFormatException e) {
			// page parameter 관련 오류는 별도의 예외 처리 안함
			// 안하면 default 가 1 인 상태로 있음... curPage
			e.printStackTrace();
		}
	}
 %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>

	
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
		// final String SQL_WRITE_SELECT = "SELECT * FROM test_write ORDER BY wr_uid DESC";
		
		// 페이징 
		// 1. 글 목록 전체 개수 가져오기 
		final String SQL_WRITE_COUNT_ALL = "SELECT COUNT(*) FROM test_write"; 
		
		// 2. fromRow 부터 pageRows 만큰 SELECT
		final String SQL_WRITE_SELECT_FROM_ROW 
			= "SELECT * FROM " +
				"(SELECT ROWNUM AS RNUM, T.* FROM " +
				 "(SELECT * FROM test_write ORDER BY wr_uid DESC) T) " + 
				"WHERE RNUM >=? AND RNUM < ?";
	
		// cnt = 0; // 글 목록 전체의 개수
		
		// 페이징 관련 셋팅 값들
		int writePages = 10; // 한 [페이징] 에 몇개의 '페이지' 를 표현 할 것인가?
		int pageRows = 8; // 한 '페이지' 에 몇개의 글을 리스트 할 것인가?
		int totalPage = 0; // 총 몇 '페이지' 분량인가 ?
	%>
	<%
	
		try {
			Class.forName(DRIVER);
			out.println("드라이버 로딩 성공<br />"); // 테스트 출력
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			out.println("conn 성공<br />"); // 테스트 출력
			
			// 트랜잭션 실행
			psmt = conn.prepareStatement(SQL_WRITE_COUNT_ALL); // 전체 글의 갯수 가져 오기
			rs = psmt.executeQuery();
			
			if(rs.next()) cnt = rs.getInt(1); // 전체 개수 구하기
			
			rs.close();
			psmt.close();
			
			totalPage = (int) Math.ceil((cnt / (double)pageRows));
			out.println("쿼리성공<br />");
			
			int fromRow = (curPage - 1) * pageRows + 1; // 몇번째 row 부터 SELECT ?
			psmt = conn.prepareStatement(SQL_WRITE_SELECT_FROM_ROW);
			psmt.setInt(1, fromRow); // 몇번째 row 부터
			psmt.setInt(2, fromRow + pageRows); // 몇개(pageRows)의 row 만큼
			rs = psmt.executeQuery();
	%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<!-- 페이징 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="CSS/common.css"/>
	<script src="https://kit.fontawesome.com/bb29575d31.js"></script>

	<title>글 목록 <%=curPage %>페이지</title><%-- 현재 페이지 --%>
	<style>
		table {
			width: 100%;
		}
		table, th, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
	</style>
</head>
<body>

	<hr />
	<h2>리스트 <%=curPage %>페이지</h2><%-- 현재 페이지 표시 --%>
	<h4>총 <%=cnt %>개</h4> <%-- 전체 글 개수 --%>
	<table>
		<thead>
			<tr>
				<th>row</th> <%-- 줄번호 --%>
				<th>UID</th> <%-- wr_uid 값 --%>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
		
	<% 
		while(rs.next()) {
		int rnum = rs.getInt("RNUM"); // rownum 을 받아 온다.
		int uid = rs.getInt("wr_uid");
		String subject = rs.getString("wr_subject").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		String name = rs.getString("wr_name").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		int viewcnt = rs.getInt("wr_viewcnt");
		
		Date d = rs.getDate("wr_regdate");
		Time t = rs.getTime("wr_regdate");
		String regdate = "";
		
		if(d != null) {
			regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " 
		+ new SimpleDateFormat("hh:mm:ss").format(t);
		}
				
		out.println("<tr>");
		out.println("<td>"+( cnt - rnum + 1 )+"</td>");
		out.println("<td>"+uid+"</td>");
		out.println("<td><a href='view.jsp?uid=" + uid + "&page="+curPage+"'>"+subject+"</a></td>");
		out.println("<td>"+name+"</td>");
		out.println("<td>"+viewcnt+"</td>");
		out.println("<td>"+regdate+"</td>");
		out.println("</tr>");
		}
	%>
	
	


		</tbody>
	</table>
	<br />
	<button onclick="location.href='write.jsp'">신규등록</button>
	<%
						
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
	<%-- 페이징 --%>
	<jsp:include page="pagination.jsp">
		<jsp:param value="<%=writePages %>" name="writePages" />
		<jsp:param value="<%=totalPage %>" name="totalPage" />
		<jsp:param value="<%=curPage %>" name="curPage" />
	</jsp:include>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>    
<%@ page import = "java.sql.*" %>  <%-- JDBC 관련 클래스 import --%>    

<%
	int curPage = 1;  // 현재 페이지 (디폴트 1 page)
	
	// 현재 몇 페이지인지 parameter 가져오기 + 검증
	String pageParam = request.getParameter("page");
	
	if(pageParam != null && !pageParam.trim().equals("")){
		
		// 1 이상의 자연수 이어야 한다.
		try{
			int p = Integer.parseInt(pageParam);
			if(p > 0) curPage = p;
		} catch(NumberFormatException e){
			// page parameter 관련 오류는 별도의 예외 처리 안함
		}
		
	} // end if

%>

<%!
	// JDBC 관련 기본 객체 변수들 선언
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;   //  executeQuery(),  SELECT 결과
	int cnt = 0;   			//  executeUpdate(), DML 결과
	
	// Connection 에 필요한 값 세팅
	final String DRIVER = "oracle.jdbc.driver.OracleDriver";  // JDBC 오라클 드라이버 클래스
	final String URL = "jdbc:oracle:thin:@localhost:1521:XE";  // DB 접속 URL
	final String USERID = "scott_b";  // DB 접속 계정 정보
	final String USERPW = "tiger_b";
%>
<%!
	// 쿼리문 준비
//	final String SQL_WRITE_SELECT = 
//		"SELECT * FROM test_write ORDER BY wr_uid DESC";

	// 페이징
	// 1. 글 목록 전체 개수 가져오기
	final String SQL_WRITE_COUNT_ALL = "SELECT COUNT(*) FROM test_write"; 
	
	// 2. fromRow 부터 pageRows 만큼 SELECT
	final String SQL_WRITE_SELECT_FROM_ROW = "SELECT * FROM " + 
			"(SELECT ROWNUM AS RNUM, T.* FROM " +
			"(SELECT * FROM test_write ORDER BY wr_uid DESC) T ) " +
			"WHERE RNUM >= ? AND RNUM < ?" 
		;

	//cnt  // 글 목록 전체의 개수
	
	// 페이징 관련 세팅 값들
	int writePages = 10;  // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
	int pageRows = 8;  // 한 '페이지' 에 몇개의 글을 리스트 할 것인가?
	int totalPage = 0;  // 총 몇 '페이지' 분량인가?

%>
<%
	try{
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공<br>");  // 테스트 출력
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공<br>");  // 테스트 출력
		
		// 트랜잭션 실행
		pstmt = conn.prepareStatement(SQL_WRITE_COUNT_ALL);
		rs = pstmt.executeQuery();
		
		if(rs.next())
			cnt = rs.getInt(1);  //  전체 개수 구하기
		
		rs.close();
		pstmt.close();
		
		totalPage = (int)Math.ceil(cnt / (double)pageRows);  // 총 몇 페이지 분량
		
		int fromRow = (curPage - 1) * pageRows + 1;  // 몇번째 row 부터 SELECT?
		pstmt = conn.prepareStatement(SQL_WRITE_SELECT_FROM_ROW);
		pstmt.setInt(1, fromRow);   // 몇번째 row 부터
		pstmt.setInt(2, fromRow + pageRows);  // 몇개(pageRows) 의 row  만큼
		rs = pstmt.executeQuery();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 목록 <%= curPage %>페이지</title> <%-- 현재 페이지 --%>
<style>
table {width: 100%;}
table, th, td {
	border : 1px solid black;
	border-collapse: collapse;
}
</style>

<!-- 페이징 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="CSS/common.css"/>
<script src="https://kit.fontawesome.com/bb29575d31.js"></script>

</head>
<body>
		<hr>
		<h2>리스트 <%= curPage %>페이지</h2>  <%-- 현재 페이지 표시 --%>
		<h4>총 <%= cnt %>개</h4>  <%-- 전체 글 개수 --%>
		<table>
			<tr>
				<th>row</th>  <%-- 줄번호 --%>
				<th>UID</th>  <%-- wr_uid 값 --%>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
<%
	while(rs.next()){
		int rnum = rs.getInt("rnum");  // rownum 을 받아온다
		
		int uid = rs.getInt("wr_uid");
		String subject = rs.getString("wr_subject");
		String name = rs.getString("wr_name");
		int viewcnt = rs.getInt("wr_viewcnt");
		Date d = rs.getDate("wr_regdate");
		Time t = rs.getTime("wr_regdate");
		String regdate = "";
		if(d != null){
			regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
					+ new SimpleDateFormat("hh:mm:ss").format(t);
		}
		
		out.println("<tr>");
		out.println("<td>" + (cnt - rnum + 1) + "</td>");
		out.println("<td>" + uid + "</td>");
		out.println("<td><a href='view.jsp?uid=" + uid + "&page=" + curPage + "'>" + subject + "</a></td>");
		out.println("<td>" + name + "</td>");
		out.println("<td>" + viewcnt + "</td>");
		out.println("<td>" + regdate + "</td>");		
		out.println("</tr>");
	} // end while
%>		
		</table>
		<br>
		<button onclick="location.href = 'write.jsp'">신규등록</button>
<%		
	} catch(Exception e){
		e.printStackTrace();
		// ※ 예외 처리를 하든지, 예외 페이지를 설정해주어야 한다.
	} finally {
		// DB 리소스 해제
		try{			
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	} // end try
%>

<%-- 페이징 --%>
<jsp:include page="pagination.jsp">
	<jsp:param value="<%= writePages %>" name="writePages"/>
	<jsp:param value="<%= totalPage %>" name="totalPage"/>
	<jsp:param value="<%= curPage %>" name="curPage"/>
</jsp:include>



</body>
</html>












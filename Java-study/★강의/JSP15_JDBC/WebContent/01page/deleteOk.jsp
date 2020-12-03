<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>  <%-- JDBC 관련 클래스 import --%>    
<% 	// parameter 받아오기
	int uid = Integer.parseInt(request.getParameter("uid"));
	//※ 이단계에서 parameter 검증 필요
	
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
	final String SQL_WRITE_DELETE_BY_UID = 
		"DELETE FROM test_write WHERE wr_uid = ?"
		;
%>
<%
	try{
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공<br>");  // 테스트 출력
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공<br>");  // 테스트 출력
		
		// 트랜잭션 실행
		pstmt = conn.prepareStatement(SQL_WRITE_DELETE_BY_UID);
		
		pstmt.setInt(1, uid);
		
		cnt = pstmt.executeUpdate();
		
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

<% if(cnt == 0){ %>
	<script>
		alert("삭제 실패");
		history.back();
	</script>
<% } else { %>
	<script>
		alert("삭제 성공");
		location.href = "list.jsp?page=" + <%= curPage%>;
	</script>
<% } %>












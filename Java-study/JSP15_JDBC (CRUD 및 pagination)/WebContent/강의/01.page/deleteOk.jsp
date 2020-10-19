<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>
	
	<% //parameter 받아오기
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		int curPage = 1;
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
		// 이단계에서 parameter 검증 필요
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
		final String SQL_WRITE_DELETE_BY_UID = "DELETE FROM test_write WHERE wr_uid = ?";
	%>
	
	<%
	
		try {
			Class.forName(DRIVER);
			out.println("드라이버 로딩 성공<br />"); // 테스트 출력
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			out.println("conn 성공<br />"); // 테스트 출력
			
			// 트랜잭션 실행
			psmt = conn.prepareStatement(SQL_WRITE_DELETE_BY_UID);
			psmt.setInt(1, uid);
			cnt = psmt.executeUpdate();
			
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
			location.href='list.jsp?page=<%=curPage%>';
		</script>
	<%		
		}
	%>
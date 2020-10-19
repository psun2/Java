<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>

	<%
		request.setCharacterEncoding("UTF-8");
		String strUid = request.getParameter("uid");
		
		int uid = -1;
		try {
			 uid = Integer.parseInt(strUid);			
			} catch(Exception e) {
				e.printStackTrace();
		%>
				<script>
					alert('접근할 수 없습니다.');
					history.back();
				</script>
		<%	
				return;
			}
			
			if(uid == -1) {
		%>		
				<script>
					alert('접근할 수 없습니다.');
					history.back();
				</script>		
		<%
				return;
			}
			
			String strPage = request.getParameter("page");
			
			int pageCnt = 10;
			int pageNum = 1;
			
			if(strPage != null) {
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
			}
			
			int startPageNum = (pageNum - 1) * pageCnt  + 1;
			int endPageNum = startPageNum + pageCnt - 1;
			
			System.out.println("startPageNum: " + startPageNum);
			System.out.println("endPageNum: " + endPageNum);
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
		String sql = "DELETE FROM test_write WHERE wr_uid = ?";
	%>
	
	<%
	
		try {
			Class.forName(DRIVER);
			out.println("드라이버 로딩 성공<br />"); // 테스트 출력
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			out.println("conn 성공<br />"); // 테스트 출력
			
			// 트랜잭션 실행
			psmt = conn.prepareStatement(sql);
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
	<script>
		if(<%=cnt %> === 1) {
			alert('삭제 성공');
			location.href="list.jsp?page=<%=pageNum %>";
		} else {
			alert('삭제 성공');
			history.back();			
		}
	</script>
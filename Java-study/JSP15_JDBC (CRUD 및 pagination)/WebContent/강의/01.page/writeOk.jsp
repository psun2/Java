<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>
	
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
		final String SQL_WRITE_INSERT = "INSERT INTO TEST_WRITE (wr_uid, wr_subject, wr_content, wr_name) VALUES(test_write_seq.nextval, ?, ?, ?)";
	%>
	
	<%
	
		try {
			Class.forName(DRIVER);
			out.println("드라이버 로딩 성공<br />"); // 테스트 출력
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			out.println("conn 성공<br />"); // 테스트 출력
			
			// 트랜잭션 실행
			psmt = conn.prepareStatement(SQL_WRITE_INSERT);
			psmt.setString(1, subject);
			psmt.setString(2, content);
			psmt.setString(3, name);
			
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
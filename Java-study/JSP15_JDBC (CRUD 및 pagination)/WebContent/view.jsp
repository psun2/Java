<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>

	<%
		request.setCharacterEncoding("UTF-8");
		String strUid = null;
		if(request.getParameter("uid") != null) 
			strUid = request.getParameter("uid");
		
		if(strUid == null || strUid.equals("")) {
	%>
		<script>
			alert('접근 할 수 없습니다.');
			histroy.back();
		</script>		
	<%	
			return;
		}
		
		int uid = -1;
		try {
			uid = Integer.parseInt(strUid);
		} catch(Exception e) {
			e.printStackTrace();
	%>
		<script>
			alert('없는 게시물 번호 입니다.');
			histroy.back();
		</script>
	<%	
			return;
		}
		
		if(uid == -1) {
	%>
		<script>
			alert('없는 게시물 번호 입니다.');
			histroy.back();
		</script>
	<%				
			return;
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
		String sql = "SELECT * FROM test_write WHERE wr_uid = ?";
		
		String subject = null;
		String content = null;
		String name = null;
		int viewcnt = -1;
		String regdate = null;
		
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
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				subject = rs.getString("wr_subject");
				content = rs.getString("wr_content");
				if(content == null) content = ""; // 초기 조건에서 null 일 수 도 있다는 전제를 했음.
				name = rs.getString("wr_name");
				viewcnt = rs.getInt("wr_viewcnt");
				
				Date date = rs.getDate("wr_regdate");
				Time time = rs.getTime("wr_regdate");
				
				String strDate = new SimpleDateFormat("yyyy-mm-dd").format(date);
				String strTime = new SimpleDateFormat("HH:ss:mm").format(time);
				
				String day = "오전";
				String houre = strTime.substring(0, 2);
				if(Integer.parseInt(houre) > 12) {
					day = "오후";
				}
				
				regdate = strDate + " ("+ day +")" + strTime;
				
				if(viewcnt == -1) {
		%>
				<script>
					alert('조회수 데이터 베이스 오류');
					histroy.back();
				</script>
		<%
					return;
				}
				
			} else {
		%>
				<script>
					alert('해당 게시물이 존재 하지 않습니다.');
					histroy.back();
				</script>
		<%
				return;
			}
			
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>읽기 <%=subject %></h2>
	<br />
	UID: <%=uid %><br />
	작성자: <%=name %><br />
	제목: <%=subject %><br />
	날짜: <%=regdate %><br />
	조회수: <%=viewcnt %><br />
	내용 <br />
	<hr />
	<div>
		<%=content %>
	</div>
	<hr />
	<br />
	<button><a href="update.jsp?uid=<%=uid%>">수정하기</a></button>
	<button><a href="list.jsp">목록보기</a></button>
	<button onclick="chkDelete();">삭제하기</button>
	<button><a href="write.jsp">신규등록</a></button>
	<script>
		function chkDelete() {
			const userDeleteConfirm = confirm('정말로 삭제 하시겠습니까 ?');
			
			if(userDeleteConfirm) {
				location.href='deleteOk.jsp?uid=<%=uid%>';
				return;
			}
			
			return;
		}
	</script>
</body>
</html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>

	<%
		request.setCharacterEncoding("UTF-8");
		String strUid = request.getParameter("uid");
		
		if(strUid == null || strUid.equals("")) {
	%>
		<script>
			alert('접근할 수 없습니다.');
			history.back();
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
	<form id="form" action="updateOk.jsp" method="post" onsubmit="return chkSubmit();">
		글번호: <input type="text" value="<%=uid %>" disabled /><br />
		<input type="hidden" value="<%=uid %>" name="uid" /><br />
		작성자: <input type="text" value="<%=name %>" disabled/><br />
		<input type="hidden" value="<%=name %>" name="name" /><br />
		제목: <input type="text" value="<%=subject %>" name="subject" /><br />
		내용: <input type="text" value="<%=content %>" name="content" /><br />
		
		<input type="hidden" value="<%=pageNum %>" name="page" /><br />
		<br /><br />
		<input type="submit" value="수정" />
	</form>
	
	<button onclick="history.back();">이전으로</button>
	<button onclick="location.href='list.jsp';">목록보기</button>
	<script>
		function chkSubmit() {
			const form = document.forms['form'];
			console.log(form);
			
			if(form['subject'].value.trim() === '') {
				alert('제목을 입력해주세요.');
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>
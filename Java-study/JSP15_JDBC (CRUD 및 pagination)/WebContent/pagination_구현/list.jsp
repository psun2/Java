<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@	page import="java.text.SimpleDateFormat" %>    
<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>
	
	<%
		request.setCharacterEncoding("UTF-8");
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

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<!-- 페이징 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="CSS/common.css"/>
	<script src="https://kit.fontawesome.com/bb29575d31.js"></script>
	<title>Insert title here</title>
</head>
<style>
	table {
		width: 100%;
	}
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
	}
</style>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
<%!
	// 쿼리문 준비
	// ex) String sql_xxx = "INSERT INTO .....";
	String sql = "SELECT * FROM (SELECT ROWNUM AS NO, T.* FROM (SELECT * FROM test_write ORDER BY wr_uid DESC) T) WHERE NO >= ? AND NO <= ?";
%>
		
<%

	try {
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공<br />"); // 테스트 출력
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공<br />"); // 테스트 출력
		
		// 트랜잭션 실행
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, startPageNum);
		psmt.setInt(2, endPageNum);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			int uid = rs.getInt("wr_uid");
%>			
			<tr>
				<td><%=uid %></td>
				<td><a href="view.jsp?uid=<%=uid%>&page=<%=pageNum%>"><%=rs.getString("wr_subject").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br />") %></a></td>
				<td><%=rs.getString("wr_name").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br />") %></td>
				<td><%=rs.getInt("wr_viewcnt") %></td>
<%
			Date date = rs.getDate("wr_regdate");
			Time time = rs.getTime("wr_regdate");
			
			String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String strTime = new SimpleDateFormat("HH:mm:ss").format(time);
			
			String day = "오전";
			String houre = strTime.substring(0, 2);
			if(Integer.parseInt(houre) > 12) {
				day = "오후";
			}
			String resultTime = new SimpleDateFormat("hh:mm:ss").format(time);
%>
				<td><%=strDate + " (" + day + ")" + strTime%></td>
			</tr>
<%		
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

		</tbody>
	</table>
	<br />
	<button onclick="location.href='write.jsp'">신규등록</button>
	<jsp:include page="pagination.jsp">
		<jsp:param value="<%=pageNum %>" name="page"/>
	</jsp:include>
</body>
</html>

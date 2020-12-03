<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>  <%-- JDBC 관련 클래스 import --%>    
<%@ page import = "java.text.SimpleDateFormat" %>

<% // parameter 받아오기
	int uid = Integer.parseInt(request.getParameter("uid"));
	//※ 이단계에서 parameter 검증 필요
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
	final String SQL_WRITE_INC_VIEWCNT = 
		"UPDATE test_write SET wr_viewcnt = wr_viewcnt + 1 WHERE wr_uid= ?";
	final String SQL_WRITE_SELECT_BY_UID = 
		"SELECT * FROM test_write WHERE wr_uid = ?";
%>
<%
	String name = "";
	String subject = "";
	String content = "";
	String regdate = "";
	int viewcnt = 0;
%>
<%
	try{
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공<br>");  // 테스트 출력
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공<br>");  // 테스트 출력
		
		// 여러 쿼리문이 하나의 트랜잭션으로 실행할때
		conn.setAutoCommit(false);  //  일단 auto-commit 을 false 로 한다.
		
		// 쿼리(들) 실행
		// 1. 조회수 증가
		pstmt = conn.prepareStatement(SQL_WRITE_INC_VIEWCNT);
		pstmt.setInt(1, uid);
		cnt = pstmt.executeUpdate();  // UPDATE
		
		pstmt.close();
		
		// 2. 글 읽어오기
		pstmt = conn.prepareStatement(SQL_WRITE_SELECT_BY_UID);
		pstmt.setInt(1, uid);
		rs = pstmt.executeQuery();  // SELECT
		
		// 한개의 레코드만 select 될것이다.
		if(rs.next()){
			subject = rs.getString("wr_subject");
			content = rs.getString("wr_content");
			if(content == null) content = "";  // null 인  경우
			name = rs.getString("wr_name");
			viewcnt = rs.getInt("wr_viewcnt");
			Date d = rs.getDate("wr_regdate");
			Time t = rs.getTime("wr_regdate");
			regdate = "";
			if(d != null){
				regdate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
					
			
		} else {
			// 그렇지 않다면 wr_uid 값의 게시글 이 없는 것이다.
%>
			<script>
				alert("해당 정보가 삭제되거나 없습니다");
				 history.back();
			</script>
<%			
			return;   // 더이상 JSP 프로세싱 하지 않고 종료.
		} //  end if
		
		conn.commit(); // 트랜잭션이 마무리 되면 commit 한다.
	} catch(Exception e){
		e.printStackTrace();
		// ※ 예외 처리를 하든지, 예외 페이지를 설정해주어야 한다.
		
		conn.rollback();  // 트랜잭션 중간에 실패하면  rollback 한다!
		
		throw e;
		
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
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>읽기 <%= subject %></title>
</head>
<script>
function chkDelete(uid){
	// 삭제 여부, 다시 확인하고 진행하기
	var r = confirm("삭제하시겠습니까?");
	
	if(r){
		location.href = 'deleteOk.jsp?uid=' + uid;
	}
}
</script>
<body>
<h2>읽기 <%= subject %></h2>
<br>
UID : <%= uid %><br>
작성자 : <%= name %><br>
제목: <%= subject %><br>
등록일: <%= regdate %><br>
조회수: <%= viewcnt %><br>
내용: <br>
<hr>
<div>
<%= content %>
</div>
<hr>
<br>
<button onclick="location.href='update.jsp?uid=<%= uid%>'">수정하기</button>
<button onclick="location.href='list.jsp'">목록보기</button>
<button onclick="chkDelete(<%= uid%>)">삭제하기</button>
<button onclick="location.href='write.jsp'">신규등록</button>



</body>
</html>















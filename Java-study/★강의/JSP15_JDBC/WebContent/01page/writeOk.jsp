<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>  <%-- JDBC 관련 클래스 import --%>    

<%
	request.setCharacterEncoding("utf-8");  // POST, 한글인코딩 꼭!
	// parameter 받기
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	
	// 유효성 체크
	// name, subjecr 가 null 이거나 빈 문자열이면 이전화면으로 돌아가기
	if(name == null || subject == null ||
		name.trim().equals("") || subject.trim().equals("")){
%>
		<script>
			alert("작성자 이름, 글제목을 입력하세요!!")
			history.back();
		</script>
<%
		return;  // ★더이상 JSP 프로세싱 하지 않도록 여기서 종료★
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
	final String SQL_WRITE_INSERT = "INSERT INTO test_write" +
		"(wr_uid, wr_subject, wr_content, wr_name) " + 
		"VALUES(test_write_seq.nextval, ?, ?, ?)"	
		;
%>
<%
	try{
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공<br>");  // 테스트 출력
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공<br>");  // 테스트 출력
		
		// 트랜잭션 실행
		pstmt = conn.prepareStatement(SQL_WRITE_INSERT);
		pstmt.setString(1, subject);
		pstmt.setString(2, content);
		pstmt.setString(3, name);
		
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
		alert("등록 실패!!!!!");
		history.back();  // 브라우저가 직전에 기억하는 이전 페이지로..
	</script>
<% } else { %>
	<script>
		alert("등록성공, 리스트 출력합니다");
		location.href = "list.jsp";
	</script>
<% } %>






















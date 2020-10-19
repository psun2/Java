<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	// response.setContentType("text/html; charset=UTF-8");

	String name = null;
	String subject = null;
	String content = null;
	
	if(request.getParameter("name") != null)
		name = request.getParameter("name");
	if(request.getParameter("subject") != null)
		subject = request.getParameter("subject");
	if(request.getParameter("content") != null)
		content = request.getParameter("content");
	
	if(name == null || name.equals("") ||
			subject == null || subject.equals("")) {
		out.println("<script>");
		out.println("alert('내용을 제외한 모든 항목을 기입해 주세요.');");
		out.println("history.back();");		
		out.println("</script>");
	}	

%>

<%@ page import="java.sql.*"%> <%-- JDBC 관련 클래스 import --%>

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
	String sql = "INSERT INTO TEST_WRITE (wr_uid, wr_subject, wr_content, wr_name) VALUES(test_write_seq.nextval, ?, ?, ?)";
%>

<%
	int result = -1;	

	try {
		Class.forName(DRIVER);
		out.println("드라이버 로딩 성공<br />"); // 테스트 출력
		conn = DriverManager.getConnection(URL, USERID, USERPW);
		out.println("conn 성공<br />"); // 테스트 출력
		
		// 트랜잭션 실행
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, subject);
		psmt.setString(2, content);
		psmt.setString(3, name);
		result = psmt.executeUpdate();
		
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
	if(result == -1) {
		out.println("<script>");
		out.println("alert('등록실패 => 데이터 오류 발생');");
		out.println("history.back();");		
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('등록성공');");
		out.println("location.href='list.jsp';");		
		out.println("</script>");
	}
%>

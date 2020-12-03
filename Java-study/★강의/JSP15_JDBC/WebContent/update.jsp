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
		
		// 트랜잭션 실행
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글수정 <%= subject %></title>
</head>
<script>
function chkSubmit(){
	frm = document.forms['frm'];
	var subject = frm['subject'].value.trim();
	
	if(subject == ""){
		alert("제목은 반드시 작성해야 합니다");
		frm["subject"].focus();
		return false;
	}
	
	return true;
}
</script>
<body>
<h2>수정</h2>
<form name="frm" action="updateOk.jsp" method="post" onsubmit="return chkSubmit()">
<input type="hidden" name="uid" value="<%= uid %>">
작성자: <%= name %><br>  <%-- 작성자 이름은 변경 불가 --%>
제목:
<input type="text" name="subject" value="<%= subject%>"><br>
내용:<br>
<textarea name="content"><%= content %></textarea>
<br><br>
<input type="submit" value="수정"/>
</form>

<button onclick="history.back()">이전으로</button>
<button onclick="location.href='list.jsp'">목록보기</button>
<br>

</body>
</html>












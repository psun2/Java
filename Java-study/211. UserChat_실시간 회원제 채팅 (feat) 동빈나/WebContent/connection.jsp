<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  프로젝트 명이 한글인데 위의 인코딩 셋팅 부분을 날리고 하니 계속 DataSource 부분의 프로젝트명의 한글이 깨져서 오류가 났습니다. -->
<html>
<head>
<%@ page
	import="java.sql.*, javax.sql.*, java.io.*, javax.naming.InitialContext, javax.naming.Context"%>
</head>
<body>
	<%
		InitialContext initCtx = new InitialContext();
	Context envContext = (Context) initCtx.lookup("java:/comp/env");
	DataSource ds = (DataSource) envContext.lookup("jdbc/110. UserChat_실시간 회원제 채팅 (feat) 동빈나");
	Connection con = ds.getConnection();
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT VERSION()");
	while (rs.next())
		out.println("MySQL version : " + rs.getString("version()"));
	rs.close();
	stmt.close();
	con.close();
	initCtx.close();
	%>
</body>
</html>
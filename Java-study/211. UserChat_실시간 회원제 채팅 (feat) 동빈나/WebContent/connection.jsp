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
	System.out.println("InitialContext : " + initCtx);

	Context envContext = (Context) initCtx.lookup("java:/comp/env");
	System.out.println("Context : " + envContext);
	
	DataSource ds = (DataSource) envContext.lookup("jdbc/211. UserChat_실시간 회원제 채팅 (feat) 동빈나");
	System.out.println("DataSource : " + ds);
	
	Connection conn = ds.getConnection();
	System.out.println("Connection : " + conn);
	
	Statement stmt = conn.createStatement();
	System.out.println("Statement : " + stmt);
	
	ResultSet rs = stmt.executeQuery("SELECT VERSION()");
	System.out.println("ResultSet : " + rs);
	while (rs.next())
		out.println("MySQL version : " + rs.getString("version()"));
	rs.close();
	stmt.close();
	conn.close();
	initCtx.close();
	%>
</body>
</html>
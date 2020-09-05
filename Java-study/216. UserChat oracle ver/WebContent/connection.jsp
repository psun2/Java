<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@page
	import="java.sql.*, javax.sql.*, java.io.*, javax.naming.InitialContext, javax.naming.Context"%>
<meta charset="UTF-8">
<title>Context Test</title>
</head>
<body>
	<%
		InitialContext initCtx = new InitialContext();
	System.out.println("InitialContext : " + initCtx);

	Context envContext = (Context) initCtx.lookup("java:/comp/env");
	System.out.println("Context : " + envContext);
	
	DataSource ds = (DataSource) envContext.lookup("jdbc/216. UserChat oracle ver");
	System.out.println("DataSource : " + ds);
	
	Connection conn = ds.getConnection();
	System.out.println("Connection : " + conn);
	
	Statement stmt = conn.createStatement();
	System.out.println("Statement : " + stmt);
	
	String sql = "SELECT * FROM v$version WHERE banner LIKE 'Oracle%'";
	
	ResultSet rs = stmt.executeQuery(sql);
	System.out.println("ResultSet : " + rs);
	
	while (rs.next()) {
		out.println("Oracle Version : " + rs.getString("BANNER"));
	}

	rs.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>
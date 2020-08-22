<html>
<head>
<%@ page
	import="java.sql.*, javax.sql.*, java.io.*, javax.naming.InitialContext, javax.naming.Context"%>
</head>
<body>
	<%
		InitialContext initCtx = new InitialContext();
	System.out.println("1");
	Context envContext = (Context) initCtx.lookup("java:/comp/env");
	System.out.println("2");
	DataSource ds = (DataSource) envContext.lookup("jdbc/110. UserChat_실시간 회원제 채팅 (feat) 동빈나");
	System.out.println("3");
	Connection con = ds.getConnection();
	System.out.println("4");
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT VERSION();");
	while (rs.next())
		out.println("MySQL version : " + rs.getString("version()"));
	rs.close();
	stmt.close();
	con.close();
	initCtx.close();
	%>
</body>
</html>
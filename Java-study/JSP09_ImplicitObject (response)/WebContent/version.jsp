<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>version</title>
</head>
<body>
	<h3>버전 확인</h3>
	<p>
		<b>- 서버: </b>
		<span><%=application.getServerInfo() %></span>
	</p>
	<p>
		<b>- 서블릿: </b>
		<span><%=application.getMajorVersion() %></span>
		<span>.</span>
		<span><%=application.getMinorVersion() %></span>
	</p>
	<p>
		<b>- JSP: </b>
		<span><%=JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %></span>
	</p>
</body>
</html>
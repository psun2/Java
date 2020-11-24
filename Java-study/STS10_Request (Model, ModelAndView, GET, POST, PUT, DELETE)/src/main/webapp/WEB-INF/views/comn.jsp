<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%
	// forward받은 jsp 측에서 원래 request uri 내용
	String servlet_request_uri = (String) request.getAttribute("javax.servlet.forward.request_uri");

	String contextPath = request.getContextPath();
	String servlet_requestPath = servlet_request_uri.substring(contextPath.length());
	
	System.out.println("servlet_request_uri: " + servlet_request_uri);
	System.out.println("contextPath: " + contextPath);
	System.out.println("servlet_requestPath: " + servlet_requestPath);
	
	String uri = request.getRequestURI();
	String requestPath = uri.substring(contextPath.length());
	String url = request.getRequestURL().toString();

	System.out.println("uri: " + uri);
	System.out.println("requestPath: " + requestPath);
	System.out.println("url: " + url);
	
	// jsp 파일명
	String jspFile = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
	String jspName = uri.substring(uri.lastIndexOf("/") + 1 , uri.length() - 4 );

	System.out.println("jspFile: " + jspFile);
	System.out.println("jspName: " + jspName);
	
	request.setAttribute("jspName", jspName);
	
%>
<!DOCTYPE>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title><%= jspName %></title>
	<!-- 
	<link rel="stylesheet" type="text/css" href="./CSS/sts_common.css" />
	 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/sts_common.css" />
</head>
<body>
	<div class="sts">
	<b>Servlet</b><br>
	request URI : <span><%=servlet_request_uri %></span> <br />
	context path : <span><%=contextPath %></span> <br />
	request path : <span><%=servlet_requestPath %></span> <br />
	VIEW file : <span><%= jspFile %></span> <br />
	<hr>
	<b>VIEW(JSP)</b> <br />
	url : <span><%= url %></span> <br />
	VIEW Path : <span><%= requestPath %></span> <br />
	uri : <span><%= uri %></span> <br />
	</div>
</body>
</html>

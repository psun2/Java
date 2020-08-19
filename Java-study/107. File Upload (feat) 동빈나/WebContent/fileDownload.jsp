<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP 파일 업로드</title>
</head>
<body>
	<%
		// 실제 물리적인 서버의 경로에 있는 업로드 폴더를 가져옵니다.(접근) 
	String directory = application.getRealPath("/upload/");
	String[] files = new File(directory).list();

	for (String file : files) {
		out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file="
		+ java.net.URLEncoder.encode(file, "UTF-8") + "\">" + file + "</a><br>");
	}
	%>
</body>
</html>
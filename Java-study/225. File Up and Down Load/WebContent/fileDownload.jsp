<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="file.FileDAO"%>
<%@page import="file.FileDTO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>225. File Up and Down Load</title>
</head>
<body>
	<%
		String savePath = request.getServletContext().getRealPath("/upload/");
	String savePath2 = application.getRealPath("/upload/");

	System.out.println("savePath : " + savePath);
	System.out.println("savePath2 : " + savePath2);

	String[] files = new File(savePath).list();
	System.out.println(Arrays.toString(files));

	for (String file : files) {
		out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file="
		+ java.net.URLEncoder.encode(file, "UTF-8") + "\">" + file + "</a><br/>");

		// 출력
		System.out.println("<a href=\"" + request.getContextPath() + "/downloadAction?file="
		+ java.net.URLEncoder.encode(file, "UTF-8") + "\">" + file + "</a>");
	}
	%>
</body>
</html>
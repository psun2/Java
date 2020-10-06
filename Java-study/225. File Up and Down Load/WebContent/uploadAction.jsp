<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="file.FileDAO"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>225. File Up and Down Load</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String directory = request.getServletContext().getRealPath("/upload");
	String directory2 = application.getRealPath("/upload");

	System.out.println("directory : " + directory);
	System.out.println("directory2 : " + directory2);

	int maxSize = 1024 * 1024 * 100; // 100MB 104,857,600

	// 업로드 끝
	MultipartRequest multi = new MultipartRequest(request, directory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	
	String fileName = multi.getOriginalFileName("file");
	String fileRealName = multi.getFilesystemName("file");
	
	new FileDAO().upload(fileName, fileRealName);
	out.write("파일명: " + fileName + "<br />");
	out.write("실제 파일명: " + fileRealName + "<br />");
	%>
</body>
</html>
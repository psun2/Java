<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="java.io.File"%>
<%@ page import="file.FileDAO"%>
<%@ page import="file.FileDTO"%>
<%@page import="java.util.ArrayList"%>
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
		// String directory = application.getRealPath("/upload/"); // 업로드와 같은 이유로 경로 변경
		// String directory = "D:/Study/Java/Java-study/107. File Upload (feat) 동빈나/WebContent/upload";
		// String[] files = new File(directory).list();

		// for (String file : files) {
		// 	out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file="
		// 	+ java.net.URLEncoder.encode(file, "UTF-8") + "\">" + file + "</a><br>");
		// }

		ArrayList<FileDAO> fileList = new FileDTO().getList();
		System.out.println(fileList);
		System.out.println(request.getContextPath());

		for (FileDAO file : fileList) {
			out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file="
			+ java.net.URLEncoder.encode(file.getFileRealName(), "UTF-8") + "\">" + file.getFileName() + "(다운로드 횟수: "
			+ file.getdownloadCount() + ")</a><br>");
		}
	%>
</body>
</html>
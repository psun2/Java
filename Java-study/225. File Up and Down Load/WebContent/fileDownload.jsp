<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="file.FileDAO"%>
<%@page import="file.FileDTO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>225. File Up and Down Load</title>
</head>
<body>
	<%
		ArrayList<FileDTO> fileList = new FileDAO().getList();

	for (FileDTO file : fileList) {
		out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file="
		+ java.net.URLEncoder.encode(file.getFileRealName(), "UTF-8") + "\">" + file.getFileName() + " (다운로드 횟수: "
		+ file.getDownloadCount() + ")" + "</a><br/>");
	}
	%>
</body>
</html>
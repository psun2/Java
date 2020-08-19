<%@ page import="file.FileDAO"%>
<%@ page import="java.io.File"%>
<!-- DefaultFileRenamePolicy : 사용자가 올리는 파일들이 이름이 같을 경우 자동으로 이름이 겹치지 않도록 바꾸어 줍니다. -->
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<!-- 실제로 파일업로드를 도와주는 클래스 -->
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
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
	// ▶ Application 내장 객체는 전체 프로젝트에 대한 자원을 관리하는 객체입니다.
	// ▶ 서버의 실제 프로젝트 경로에서 자원을 찾을 때 가장 많이 사용합니다.
	// 우리가 만들었던 upload폴더를 찾도록 합니다.
	String directory = application.getRealPath("/upload/");

	// 파일의 최대크기 (총 100mb 까지만 저장되게 만듬)
	int maxSize = 1204 * 1204 * 100;

	String encoding = "UTF-8";

	// 생정자를 이용하여 생성과 동시에 파일전송을 할 수 있습니다.
	MultipartRequest multipartRequest 
	= new MultipartRequest(request, directory, maxSize, encoding,
			new DefaultFileRenamePolicy());
	
	// index에서 오는 file이라는 이름을 가진 input 으로 전해져오는 파일이름을 fileName 이라는 변수에 담슴니다. 
	String fileName = multipartRequest.getOriginalFileName("file");
	
	// 실제로 사용자가 업로드한 파일 이름을 fileRealName 변수에 저장합니다.
	String fileRealName = multipartRequest.getFilesystemName("file");
	       	
	new FileDAO().upload(fileName, fileRealName);
	
	out.write("파일명: " + fileName + "<br>");
	out.write("실제 파일명: " + fileRealName + "<br>");
	%>
</body>
</html>
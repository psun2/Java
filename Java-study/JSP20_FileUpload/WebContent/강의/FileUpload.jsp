<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- cos 라이브러리 --%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy" %>    
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<%-- parameter 값들,  file name 값들 추출 --%>
<%@ page import="java.util.Enumeration" %>

<%-- File 객체 추출 --%>
<%@ page import="java.io.File" %>

<%-- 이미지 파일 다루기 --%>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>파일 업로드 결과</title>
</head>
<body>
<form action="FileCheck.jsp" method="post" name="fileCheck">
<%
	// MultipartRequest  객체 생성 준비
	
	// 파일 저장 경로
	//String saveDirectory = "C:\\tomcat_b\\upload";
	String saveDirectory = getServletContext().getRealPath("upload");

	System.out.println("업로드 경로: " + saveDirectory);
	
	int maxPostSize = 5 * 1024 * 1024;   // POST 파일 업로드, 최대 5M
		// 1byte = 8bit
		// 1Kbyte = 1024 byte
		// 1Mbyte = 1024 Kbyte
		// 1Gbyte = 1024 Mbyte
		// ...
	String encoding = "utf-8";
	FileRenamePolicy policy = new DefaultFileRenamePolicy();  // 업로딩 파일 이름 중복
	
	MultipartRequest multi = null;  
	
	// MultipartRequest 객체 생성단계에서 이미 파일은 저장완료됨!
	multi = new MultipartRequest(
				request,
				saveDirectory,
				maxPostSize,
				encoding,
				policy
			);
	
	Enumeration names = null;
	// 1. parameter  정보 추출
	names = multi.getParameterNames();  // 일반 form 의 name 들 추출
	while(names.hasMoreElements()){
		String name = (String)names.nextElement();  // name
		String value = multi.getParameter(name);  // value
		out.println(name + " : " + value + "<br>");
	} // end while
	out.println("<hr>");
	
	// 2. File  정보 추출
	names = multi.getFileNames();  // type='file' 요소 name 들 추출
	while(names.hasMoreElements()){
		String name = (String)names.nextElement();
		out.println("input name: " + name + "<br>");
		
		// 위 name 의 원래 파일(업로드 파일) 이름 가져온다
		String originalFileName = multi.getOriginalFileName(name);
		out.println("원본파일 이름: " + originalFileName + "<br>");
		out.println("<input type='hidden' value='" + originalFileName + "' name='originalFileName'/>");
		
		// 시스템에 실제 업로딩 된 파일 이름
		String fileSystemName = multi.getFilesystemName(name);
		out.println("파일시스템 이름: " + fileSystemName + "<br>");
		out.println("<input type='hidden' value='" + fileSystemName + "' name='fileSystemName'/>");
		
		// 업로딩된 파일의 타입 : MIME  타입 (ex: image/png ...)
		String fileType = multi.getContentType(name);
		out.println("파일타입: " + fileType + "<br>");
		
		// 실제 저장된 파일의  File 객체 
		File file = multi.getFile(name);
		if(file != null){
			long fileSize = file.length();  // 파일 크기 (byte)
			out.println("파일크기: " + fileSize + " bytes<br>");
			
			// 이미지 파일 다루기
			BufferedImage bi = ImageIO.read(file);
			if(bi != null){   // 이미지 판정 여부
				int width = bi.getWidth();
				int height = bi.getHeight();
				out.println("이미지파일 WxH: " + width + " x " + height + "<br>");
			} else {
				out.println("이미지 파일이 아닙니다<br>"); 	
			}
		} // end if
		out.println("<hr>");
	}

%>

<input type="submit" value="업로드 파일 확인"/><br>
</form>
</body>
</html>












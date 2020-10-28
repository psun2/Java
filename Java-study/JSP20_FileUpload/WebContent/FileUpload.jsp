<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--cos 라이브러리 --%>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="com.oreilly.servlet.multipart.FileRenamePolicy" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<%-- 이미지 파일 관련 객체 --%>
<%@page import="java.awt.image.BufferedImage" %>
<%@page import="javax.imageio.ImageIO" %>

<%-- File 객체 추출 --%>
<%@page import="java.io.File" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>파일 업로드 결과</title>
</head>
<body>
	<form action="FileCheck.jsp" method="post" name="fileCheck">
		<%
			// MultipartRequest 객체 생성 준비
			
			// 파일 저장경로 
			// String saveDirectory = "C:\\Users\\박성언\\Desktop\\upload";
			String savePath = request.getServletContext().getRealPath("upload");
			
			System.out.println("업로드 경로: " + savePath);
			
			int maxPostSize = 5 * 1024 * 1024; // POST 파일 업로드, 최대 5M
			// 1byte = 8bit
			// 1Kbyte = 1024 byte
			// 1Mbyte = 1024 Kbyte
			// 1Gbyte = 1024 Mbyte
			// ...
			
			String encoding = "UTF-8";
			
			FileRenamePolicy policy = new DefaultFileRenamePolicy(); // 파일 자동 보정 객체
			
			MultipartRequest multi = null;
			
			// MultipartRequest 객체 생성 단계에서 이미 저장 완료
			multi = new MultipartRequest(
					request,
					savePath,
					maxPostSize,
					encoding,
					policy
					);
			
			Enumeration names = null;
			
			// 1. parameter 정보 추출
			names = multi.getParameterNames(); // 일반 form의 name들 추출
			while(names.hasMoreElements()) {
				String name = (String) names.nextElement(); // name
				String value = multi.getParameter(name); // value
				out.println(name + " : " + value + "<br />");
			} // end while
				
			// 2. File 정보 추출
			names = multi.getFileNames();
			while(names.hasMoreElements()) {
				String name = (String) names.nextElement();
				out.println("input name: " + name + "<br />");
				
				// 위 name 의 원래 파일(업로드 파일) 정보 가져온다
				String originalFileName = multi.getOriginalFileName(name);
				out.println("원본파일 이름: " + originalFileName + "<br />");
				out.println("<input type='hidden' value='"+ originalFileName +"' name='originalFileName'/>");
			
				// 시스템에 실제 업로딩 된 파일 정보
				String fileSystemName = multi.getFilesystemName(name);
				out.println("파일시스템 이름: " + fileSystemName + "<br />");
				out.println("<input type='hidden' value='"+ fileSystemName +"' name='fileSystemName'/>");
				
				// 업로딩된 파일의 타입: MIME 타입(ex: image/png...)
				String fileType = multi.getContentType(name);
				out.println("파일타입: " + fileType + "<br />");
				
				// 실제 저장된 File 객체
				File file = multi.getFile(name);
				if(file != null) {
					long fileSize = file.length(); // 파일크기(byte)
					out.println("파일크기: " + fileSize + "btyes<br />");
					
					// 이미지 파일 다루기
					BufferedImage bi = ImageIO.read(file);
					if(bi != null) { // 이미지 판정 여부
						int width = bi.getWidth();
						int height = bi.getHeight();
						out.println("이미지파일 WxH: " + width + " x " + height + "<br />");
					} else {
						out.println("이미지 파일이 아닙니다<br />");
					} 
				} // end if
				
				out.println("<hr />");
			
			} // end while
			
		%>
		<input type="submit" value="업로드 파일 확인" /><br />
	</form>
</body>
</html>
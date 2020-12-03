<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.net.*" %>    
<%@ page import="java.io.*" %>    
<% request.setCharacterEncoding("utf-8"); %>
<%
	String fileSystemName = request.getParameter("fileSystemName");
	if(fileSystemName == null) { return; }
	String originalFileName = request.getParameter("originalFileName");
	if(originalFileName == null) {originalFileName = fileSystemName; }
%>

<%
	// 1) 다운로드 할 파일의 경로 지정
	//String realPath = "C:\\tomcat_b\\upload";
	String realPath = getServletContext().getRealPath("upload");

	
	String downloadFilePath = realPath + File.separator + fileSystemName;
	
	System.out.println("downloadFilePath: " + downloadFilePath);
	
	// 2) 유형 (MIME) 확인
	//   -> response 받을 브라우저에서 위 유형정보로 판단함
	String fileType = getServletContext().getMimeType(downloadFilePath);
	//  유형이 지정되지 않은 경우
	if(fileType == null){
		fileType = "application/octet-stream";  // 8bit 스트림 방식
	}						// 유형이 알려지지 않은 파일에 대해 형식 지정
	System.out.println("파일유형(MIME): " + fileType);
	
	
	// 3) reponse 세팅
	response.setContentType(fileType);
	
	// 최초 업로드한 파일이름을 인코딩하여 준비
	String encFileName = URLEncoder.encode(originalFileName, "utf-8");
	
	// 원본 파일 이름으로 다운받을수 있도록
	// response 헤더에 세팅 --> 브라우저에서 이 이름으로 저장하려 동작한다.
	response.setHeader("Content-Disposition", "attachment; filename=" + encFileName);
	
	// 4) 클라이언트에 파일 전송하기 전에 내부 객체 clear()
	// JSP 에서 OutputStream 을 이용해서 바이너리나 이미지 등을 스트림으로 전송할 때 밑의 response.getOutputStream() 을 해주기 전에
	// 기본적으로 생성된 내부객체 out 과의 충돌 이 생실수 있으니, 미리 clear() 한다.
	// 충돌 발생시  IllegalStateException 발생
	out.clear();   // out 객체내 출력할 내용들 제거
	out = pageContext.pushBody(); // <-- 커스텀 태그등도  clear,  out 에 새로운 body 생성

	// 5) 파일 전송
	File srcFile = new File(downloadFilePath);
	FileInputStream in = new FileInputStream(srcFile);
	ServletOutputStream sout = response.getOutputStream();
	
	byte [] buff = new byte[5 * 1024 * 1024];
	int numRead = 0;
	int totalRead = 0;
	
	try {
	
		// 파일로부터 읽어와서 전송
		while((numRead = in.read(buff, 0, buff.length)) != -1){
			totalRead += numRead;
			sout.write(buff, 0, numRead);  // 전송!
		}
		
	} catch(Exception e){
		// 파일 다운로드중 취소하면  IOException 발생
		e.printStackTrace();
	} finally {
		sout.flush();  // 마지막 byte 까지 완전히 전송
		sout.close();
		in.close();
	}

	System.out.println("전송 byte: " + totalRead + " bytes");
%>













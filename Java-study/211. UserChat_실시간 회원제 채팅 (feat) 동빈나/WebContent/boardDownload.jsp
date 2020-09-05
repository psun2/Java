<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.BoardDAO"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<%@ page import="javax.servlet.ServletOutputStream"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP Ajax 실시간 회원제 채팅 서비스</title>
</head>
<body>
	<%
		System.out.println();
	System.out.println("------------------------------------");
	System.out.println();

	request.setCharacterEncoding("UTF-8");

	String boardID = request.getParameter("boardID");

	if (boardID == null || boardID.equals("")) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "접근 할 수 없습니다.");
		System.out.println("이전페이지? : " + request.getHeader("Referer"));
		response.sendRedirect("boardView.jsp");
		return;
	}

	String root = request.getSession().getServletContext().getRealPath("/");

	System.out.println("root : " + root);
	System.out.println("request.getSession().getServletContext().getRealPath(\"\") : "
			+ request.getSession().getServletContext().getRealPath(""));

	System.out.println("request.getSession() : " + request.getSession());
	System.out.println("request.getSession().getServletContext() : " + request.getSession().getServletContext());
	System.out.println("request.getSession().getServletContext().getRealPath(\"/\") : "
			+ request.getSession().getServletContext().getRealPath("/"));

	String savePath = root + "upload";
	System.out.println("업로드 파일의 저장 경로 : " + savePath);

	String fileName = "";
	String realFile = "";

	BoardDAO boardDAO = new BoardDAO();
	fileName = boardDAO.getFile(boardID);
	realFile = boardDAO.getRealFile(boardID);

	// 파일 업로드를 안한 경우 즉 디비에서 가져올 파일의 명이 없습니다.
	if (fileName == null || fileName.equals("") || realFile == null || realFile.equals("")) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "접근 할 수 없습니다.");
		response.sendRedirect("boardView.jsp");
		return;
	}

	InputStream is = null;
	OutputStream os = null;
	// ServletOutputStream os = null;
	File file = null;
	boolean skip = false;
	String client = "";

	try {
		try {

			System.out.println("savePath : " + savePath);
			System.out.println("realFile : " + realFile);
			file = new File(savePath, realFile);

			System.out.println("file : " + file);

			// 파일을 인풋 스트림으로 읽습니다.
			is = new FileInputStream(file);
			os = response.getOutputStream();

			System.out.println("is : " + is);
			System.out.println("is : " + is.toString());

		} catch (Exception e2) {
			e2.printStackTrace();
			skip = true;
		}

		client = request.getHeader("User-Agent");
		System.out.println("client = request.getHeader(\"User-Agent\") : " + request.getHeader("User-Agent"));

		response.reset();

		// 출력 불가
		// System.out.println("response.reset(); : "  + response.reset());

		// 다운로드 받 을 수 있게 file input 창을 띄워 줍니다. ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
		response.setContentType("application/octet-stream");
		System.out.println("response.setContentType(\"application/octet-stream\") : " + response.getContentType());
		// ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

		response.setHeader("Content-Description", "JSP Generated Data");
		System.out.println("response.setHeader(\"Content-Description\", \"JSP Generated Data\") : "
		+ response.getHeader("Content-Description"));

		if (!skip) { // 정상적으로 데이터를 전송 할 수 있다면 ....

			System.out.println("client.indexOf(\"MSIE\") : " + client.indexOf("MSIE"));
			if (client.indexOf("MSIE") != -1) {
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(fileName.getBytes("KSC5601"), "ISO8859_1"));

		System.out.println(response.getHeader("Content-Disposition"));
			} else {
		System.out.println("여길로오면 익스플로러가 아님");

		System.out.println("fileName : " + fileName);
		// fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
		fileName = new String(fileName.getBytes("UTF-8"), "ISO8859_1");
		System.out.println("fileName : " + fileName);

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		System.out.println("Content-Disposition : " + response.getHeader("Content-Disposition"));

		response.setHeader("Content-Type", "application/octet-stream; chatset=UTF-8");
		System.out.println("Content-Type : " + response.getHeader("Content-Type"));

			}

			// 서버가 클라이언트로 전송할 데이터의 길이 정의
			System.out.println("file.length() : " + file.length());

			response.setHeader("Content-Length", "" + file.length());
			System.out.println("Content-Length : " + response.getHeader("Content-Length"));

			System.out.println("현재 서버 상태 : " + response.getStatus());
			System.out.println(response.toString());

			System.out.println("os : " + os);
			// os = response.getOutputStream(); // 여기서 초기화시 원인 모를 Error 발생 output 스트림을 이미 불러왔다고함....
			System.out.println("os : " + os);
			System.out.println("file : " + file);

			System.out.println("현재 서버 상태 : " + response.getStatus());

			// int length = (int) file.length();
			byte buffer[] = new byte[1024];
			System.out.println(Arrays.toString(buffer));

			int leng = 0;
			while ((leng = is.read(buffer)) > 0) {
		os.write(buffer, 0, leng);
		os.flush();
			}

			System.out.println(Arrays.toString(buffer));

			is.close();
			os.close();
		} else { // 보낼 파일이 없는 경우
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('파일을 찾을 수 없습니다.');history.back()</script>");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
</body>
</html>
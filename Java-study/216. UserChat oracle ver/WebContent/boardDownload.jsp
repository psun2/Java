<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="board.BoardDAO"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<%@page import="java.lang.*"%>
<%@page import="java.util.*"%>
<%@page import="java.net.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 메타 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	String boardID = request.getParameter("boardID");

	if (boardID == null || boardID.equals("")) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "접근할 수 없습니다.");
		response.sendRedirect("boardView.jsp");
		return;
	}

	String root = session.getServletContext().getRealPath("/");
	System.out.println("root : " + root);

	String root2 = session.getServletContext().getRealPath("");
	System.out.println("root2 : " + root2);

	String savePath = root + "upload";

	String boardFile = "";
	String boardRealFile = "";

	BoardDAO boardDAO = new BoardDAO();
	boardFile = boardDAO.getFile(boardID);
	boardRealFile = boardDAO.getRealFile(boardID);

	if (boardFile.equals("") || boardRealFile.equals("")) { // 파일이 없는 경우
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "접근할 수 없습니다.");
		response.sendRedirect("boardShow.jsp?boardID=" + boardID);
		return;
	}

	InputStream is = null;
	OutputStream os = null;
	File file = null;
	boolean skip = false;
	String client = "";

	try {
		try {
			file = new File(savePath, boardRealFile);
			is = new FileInputStream(file);
		} catch (Exception e2) {
			e2.printStackTrace();
			skip = true; // 파일이 없는 경우
		}
		client = request.getHeader("User-Agent");
		System.out.println("User-Agent : " + request.getHeader("User-Agent"));
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Description", "JSP Generated Data");

		if (!skip) { // 다운로드 받을 파일이 존재 한다면...
			if (client.indexOf("MSIE") != -1) { // 크롬인 경우
		System.out.println("filename1 = " + new String(boardFile.getBytes()));
		System.out.println("filename2 = " + new String(boardFile.getBytes("KSC5601"), "ISO8859_1"));
		response.setHeader("Content-Disposition",
				"attachment; filename=" + new String(boardFile.getBytes("KSC5601"), "ISO8859_1"));
			} else { // 익스프롤러 인 경우
		System.out.println("filename3 = " + new String(boardFile.getBytes("UTF-8"), "iso-8859-1"));
		boardFile = new String(boardFile.getBytes("UTF-8"), "iso-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + boardFile + "\"");
		response.setHeader("Content-Type", "apllication/octet-stream; charset=UTF-8");
			}

			// 서버가 클라이언트로 전송할 데이터 길이 정의
			response.setHeader("Content-Length", file.length() + "");

			os = response.getOutputStream();
			byte[] buffer = new byte[(int) file.length()];
			int leng = 0;
			while ((leng = is.read(buffer)) > 0) {
		os.write(buffer, 0, leng); // 다운로드 진행
			}
		} else { // 다운로드 파일이 존재 하지 않는 경우
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('파일을 찾을 수 없습니다.'); history.back();</script>");
		}

		// 객체 클로즈
		is.close();
		os.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
</body>
</html>
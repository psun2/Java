<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- import -->
<%@ page import="bbs.BbsDAO"%>
<%@ page import="bbs.BbsDTO"%>
<!-- 자바스크립트 작성을 위한 PrintWriter 패키지 import -->
<%@ page import="java.io.PrintWriter"%>
<!-- 응답받는 모든 데이터의 인코딩 설정 -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}

	// 글쓰기는 로그인이 되어이을 경우만 가능하게 합니다.

	if (userID == null) { // 로그인이 안되어 있을때
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요.');");
		script.println("location.href = 'login.jsp';");
		script.println("</script>");
		script.close();
		return;
	}

	int bbsID = 0;

	if (request.getParameter("bbsID") != null)
		bbsID = Integer.parseInt(request.getParameter("bbsID"));

	if (bbsID == 0) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글 입니다.');");
		script.println("location.href = 'bbs.jsp';");
		script.println("</script>");
		script.close();
		return;
	}

	System.out.println("bbsID : " + bbsID);

	// 수정은 본인 만 할 수 있습니다.
	// 현재 게시글의 번호로 유저의 ID를 가져올 수 있도록 합니다.
	BbsDTO bbs = new BbsDAO().getBbs(bbsID);

	System.out.println("bbs.getUserID() : " + bbs.getUserID());

	// 현재의 세션 즉 로그인 되어 있는 사용자와 글의 글쓴이를 비교 하여 본인인지를 확인 합니다.
	if (!userID.equals(bbs.getUserID())) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.');");
		script.println("location.href = 'bbs.jsp';");
		script.println("</script>");
		script.close();
		return;
	} else { // 로그인이 되어 있을때

		BbsDAO bbsDAO = new BbsDAO();
		int result = bbsDAO.delete(bbsID);
		// join 함수는 UserDTO를 받는데 12 ~ 18 Line 에서 이미 UserDTO를 셋팅을 했습니다.
		System.out.println("데이터베이스 반환 값 : " + result);

		if (result == -1) { // 글쓰기 실패
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('글 삭제에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else { // 글쓰기 성공
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'bbs.jsp';");
			script.println("</script>");
			script.close();
		}

	}
	%>
</body>
</html>
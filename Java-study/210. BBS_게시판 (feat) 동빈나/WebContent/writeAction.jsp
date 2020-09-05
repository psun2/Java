<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- import -->
<%@ page import="bbs.BbsDAO"%>
<!-- 자바스크립트 작성을 위한 PrintWriter 패키지 import -->
<%@ page import="java.io.PrintWriter"%>
<!-- 응답받는 모든 데이터의 인코딩 설정 -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- 자바beans 사용 -->
<jsp:useBean id="bbs" class="bbs.BbsDTO" scope="page" />
<!-- setProperty는 login.jsp에서 넘겨준 데이터를 그대로 받는 역할을 합니다. -->
<jsp:setProperty name="bbs" property="bbsTitle" />
<jsp:setProperty name="bbs" property="bbsContent" />
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
		} else { // 로그인이 되어 있을때
			if (bbs.getBbsTitle() == null || bbs.getBbsTitle().equals("") || bbs.getBbsContent() == null
			|| bbs.getBbsContent().equals("")) { // 글쓰기 콘텐츠가 비어 있을 경우
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
			} else {

		BbsDAO bbsDAO = new BbsDAO();
		int result = bbsDAO.write(bbs.getBbsTitle(), userID, bbs.getBbsContent());
		// join 함수는 UserDTO를 받는데 12 ~ 18 Line 에서 이미 UserDTO를 셋팅을 했습니다.
		System.out.println("데이터베이스 반환 값 : " + result);

		if (result == -1) { // 글쓰기 실패
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('글쓰기에 실패했습니다.');");
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
		}
	%>
</body>
</html>
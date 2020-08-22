<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.BbsDTO"%>
<%@ page import="bbs.BbsDAO"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- 반응형 웹을 위한 메타태그 -->
<meta name="viewport" content="width=device-width" initial-scale="1">
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="./css/bootstrap.css">
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 js -->
<script defer src="./js/bootstrap.js"></script>
<!-- custom scc -->
<link rel="stylesheet" href="./css/custom.css">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
	if (session.getAttribute("userID") != null)
		userID = (String) session.getAttribute("userID");

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

	BbsDTO bbs = new BbsDAO().getBbs(bbsID);
	%>
	<!-- 네이게이션 -->
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<!-- 21번line 이 29번 line reference -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">게시판</a></li>
			</ul>

			<%
				if (userID == null) { // 로그인이 되어있지 않은 상황
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdwon-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- 로그인이 파란색으로 불이 들어 와 있는 이유 : 현재 페이지가 로그인 페이지이고 class 에서 active 를 해주었기 때문 입니다. -->
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul></li>
			</ul>
			<%
				} else { // 로그인이 되어 있는 사람들이 볼 화면
			%>
			<ul class="nav navbar navbar-right">
				<li class="dropdown"><a href="#" class="dropdwon-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- 로그인이 파란색으로 불이 들어 와 있는 이유 : 현재 페이지가 로그인 페이지이고 class 에서 active 를 해주었기 때문 입니다. -->
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>
			<%
				}
			%>

		</div>
	</nav>
	<!-- 네비게이션 끝 -->

	<!-- 게시판 -->
	<div class="container">
		<div class="row">
			<!-- table-striped : 게시판의 글 목록들이 홀수와 짝수를 번갈아가며 background-color이 다릅니다. -->
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">게시판 글
							보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%; border: 1px solid #dddddd;">글 제목</td>
						<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
		.replaceAll("(\n|\r)", "<br>")%></td>
					</tr>
					<tr>
						<td style="border: 1px solid #dddddd;">작성자</td>
						<td colspan="2"><%=bbs.getUserID()%></td>
					</tr>
					<tr>
						<td style="border: 1px solid #dddddd;">작성일자</td>
						<td colspan="2"><%=bbs.getBbsDate().substring(0, 11) + bbs.getBbsDate().substring(11, 13) + "시"
		+ bbs.getBbsDate().substring(14, 16) + "분"%></td>
					</tr>
					<tr>
						<td style="border: 1px solid #dddddd;">내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left"><%=bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
		.replaceAll("[\n]", "<br>")%></td>
					</tr>
				</tbody>
			</table>
			<a href="bbs.jsp" class="btn btn-primary">목록</a>
			<%
				// 글의 작성자가 본인이라면 글의 수정을 가능하게 해 줍니다.
			if (userID != null && userID.equals(bbs.getUserID())) {
			%>
			<a href="update.jsp?bbsID=<%=bbs.getBbsID()%>"
				class="btn btn-primary">수정</a> <a
				href="deleteAction.jsp?bbsID=<%=bbs.getBbsID()%>"
				class="btn btn-primary" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>
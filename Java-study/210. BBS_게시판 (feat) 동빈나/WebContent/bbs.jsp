<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.BbsDTO"%>
<%@ page import="bbs.BbsDAO"%>
<%@ page import="java.util.ArrayList"%>
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

<style type="text/css">
a, a:hover {
	color: inherit;
	text-decoration: none;
}
</style>
</head>
<body>
	<%
		String userID = null;
	if (session.getAttribute("userID") != null)
		userID = (String) session.getAttribute("userID");

	// 기본 default 값
	int pageNumber = 1;
	if (request.getParameter("pageNumber") != null)
		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
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
			<ul class="nav navbar-nav navbar-right">
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
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
						BbsDAO bbsDAO = new BbsDAO();
					ArrayList<BbsDTO> list = bbsDAO.getList(pageNumber);
					for (int i = 0; i < list.size(); i++) {
					%>
					<tr>
						<td><%=list.get(i).getBbsID()%></td>
						<td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
		.replaceAll("\n", "<br>").replaceAll("\r", "<br>")%></a></td>
						<td><%=list.get(i).getUserID()%></td>
						<td><%=list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11, 13) + "시"
		+ list.get(i).getBbsDate().substring(14, 16) + "분"%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>

			<!-- 다음페이지를 보여 줍니다. -->
			<%
				if (pageNumber != 1) {
			%>

			<a href="bbs.jsp?pageNumber=<%=pageNumber - 1%>"
				class="btn btn-success btn-arraw-left">이전</a>

			<%
				}
			// 다음페이지의 존재 여부를 알기 위하여 pageNumber + 1 을 해줍니다.
			if (bbsDAO.nextPage(pageNumber + 1)) {
			%>

			<a href="bbs.jsp?pageNumber=<%=pageNumber + 1%>"
				class="btn btn-success btn-arraw-left">다음</a>

			<%
				}
			%>

			<!-- 오른쪽에 고정된 글쓰기 버튼 -->
			<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
</body>
</html>
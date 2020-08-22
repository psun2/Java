<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.BbsDAO"%>
<%@ page import="bbs.BbsDTO"%>
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

	System.out.println("userID : " + userID);

	if (userID == null) {
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
	}
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
			<ul class="nav navbar navbar-right">
				<li class="dropdown"><a href="#" class="dropdwon-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- 로그인이 파란색으로 불이 들어 와 있는 이유 : 현재 페이지가 로그인 페이지이고 class 에서 active 를 해주었기 때문 입니다. -->
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<!-- 네비게이션 끝 -->

	<!-- 업데이트 게시판 -->
	<div class="container">
		<div class="row">
			<form method="post"
				action="updateAction.jsp?bbsID=<%=bbs.getBbsID()%>">
				<!-- table-striped : 게시판의 글 목록들이 홀수와 짝수를 번갈아가며 background-color이 다릅니다. -->
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2"
								style="background-color: #eeeeee; text-align: center;">게시판
								글 수정 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글 제목" name="bbsTitle" maxlength="50"
								value="<%=bbs.getBbsTitle()%>" /></td>
							<!-- value="< % = bbs.getBbsTitle() %> : 자신이 글을 수정하기 전의 내용을 볼 수 있도록 합니다.-->
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용"
									name="bbsContent" maxlength="2048" style="height: 350px;"><%=bbs.getBbsContent()%></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="글수정" />
			</form>
		</div>
	</div>
	<!-- 업데이트 게시판 끝 -->
</body>
</html>
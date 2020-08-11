<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>강의평가 웹 사이트</title>
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<!-- 커스텀 css -->
<link rel="stylesheet" href="./css/custom.css" />
<!-- 제이쿼리 -->
<script defer src="./js/jquery-3.5.1.min.js"></script>
<!-- popper -->
<script defer src="./js/popper.min.js"></script>
<!-- 부트스트랩 -->
<script defer src="./js/bootstrap.min.js"></script>
</head>
<body>
	<%
		String userID = null;

	// 로그인 상태라면
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}

	// 로그인 상태시 회원 가입을 하지 못함
	if (userID != null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 된 상태입니다.');");
		script.println("location.href = 'index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}

	%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="index.jsp">강의평가 웹 사이트</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- collapse 이 오타 났을시 dropdown 이 이상한 곳에서 펼쳐지는 현상을 경험함 -->
		<div id="navbar" class="collaps navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="navbar-item active">
					<!-- 현재 index.jsp 에 있어서 active 라는 poroperty 가 들어 갑니다. --> <a
					class="nav-link" href="index.jsp">메인</a>
				</li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="dropdown"
					data-toggle="dropdown"> 회원관리 </a>
					<div class="dropdown-menu" aria-labelledby="dropdown">
						<%
							// 로그인이 되지 않은 상태라면
						if (userID == null) {
						%>
						<a class="dropdown-item" href="userLogin.jsp"> 로그인 </a> <a
							class="dropdown-item" href="userJoin.jsp"> 회원가입 </a> <a
							<%} else { // 로그인이 된 상태 라면%> class="dropdown-item"
							href="userLogout.jsp"> 로그아웃 </a>
						<%
							}
						%>
					</div></li>
			</ul>

			<!-- form을 이용한 검색창 생성 -->
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="검색 내용을 입력하세요." aria-label="Search">
				<!-- 실제로 검색이 되게 하는 전송 버튼 -->
				<button class="btn btn-outline-success my-2 my-sm-0" type="Submit">검색</button>
			</form>
		</div>
	</nav>

	<!-- 본문 시작 -->
	<section class="container mt-3" style="maX-width: 560px;">
		<form method="post" action="./userRegisterAction.jsp">

			<!-- 아이디 -->
			<div class="form-group">
				<label>아이디</label> <input type="text" name="userID"
					class="form-control" />
			</div>

			<!-- 패스워드 -->
			<div class="form-group">
				<label>비밀번호</label> <input type="password" name="userPassword"
					class="form-control" />
			</div>

			<!-- 이메일 -->
			<div class="form-group">
				<label>이메일</label> <input type="email" name="userEmail"
					class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">회원가입</button>

		</form>
	</section>





	<!-- footer : 페이지의 젤 마지막 부분 일반적으로 회사정보 등이 담기는 곳 -->
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #ffffff;">
		Copyright &copy; 2020 Sung-Un Park All Rights Reserved. </footer>
</body>
</html>
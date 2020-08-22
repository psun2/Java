<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdwon-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- 로그인이 파란색으로 불이 들어 와 있는 이유 : 현재 페이지가 로그인 페이지이고 class 에서 active 를 해주었기 때문 입니다. -->
						<li class="active"><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<!-- 네비게이션 끝 -->

	<!-- 로그인 양식 -->
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<!-- post : 회원가입이나 로그인 같이 어떠한 정보를 숨길때 사용 합니다. -->
				<!-- get : url 의 queryString 으로 정보가 나타납니다. -->
				<form method="post" action="loginAction.jsp">
					<!-- loginAction 이란 페이지로 정보를 보내주겠다는 의밍 입니다. -->
					<h3 style="text-align: center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디"
							name="userID" maxlength="20" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							name="userPassword" maxlength="20" />
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="로그인" />
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<!-- 로그인 양식 끝 -->

</body>
</html>
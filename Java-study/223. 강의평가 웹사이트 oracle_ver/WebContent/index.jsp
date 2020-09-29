<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width= device-width, initial-scale=1">
<title>강의평가 웹 사이트</title>
<!-- 부트스트랩 CSS -->
<link rel="stylesheet" href="./Bootstrap/css/bootstrap.min.css" />
<!-- custom css -->
<link rel="stylesheet" href="./css/custom.css" />
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">강의평가 웹 사이트</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active"><a class="nav-link" href="index.jsp">메인</a></li>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown"> 회원관리
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdown">
            <a class="dropdown-item" href="#">로그인</a> <a class="dropdown-item" href="#">회원가입</a> <a
              class="dropdown-item" href="#">로그아웃</a>
          </div>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" placeholder="내용을 입력하세요." aria-label="Search" />
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
        <!-- my : 상하 마진을 줄 수 있음 -->
      </form>
    </div>
  </nav>
	<!-- 제이쿼리 -->
	<script src="./jquery/jquery-3.5.1.min.js"></script>
	<!-- 부트스트랩 js -->
	<script src="./Bootstrap/js/bootstrap.min.js"></script>
	<!-- poper  -->
	<script src="./poper/poper.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %>
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
<%
String userID = null;
if(session.getAttribute("userID") != null) 
	userID = (String) session.getAttribute("userID");

if(userID == null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('현재 로그인 상태가 아닙니다.');");
	script.println("location.href='userLogin.jsp'");
	script.println("</script>");
	script.close();
	return;
}
%>
<body>
<!-- 네비게이션 -->
   <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">강의평가 웹 사이트</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
        	<a class="nav-link" href="index.jsp">메인</a>
        </li>
        <li class="nav-item dropdown">
        	<a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown">회원관리</a>
          	<div class="dropdown-menu" aria-labelledby="dropdown">
            <%
            	if(userID == null) {
            %>
            	<a class="dropdown-item active" href="userLogin.jsp">로그인</a> 
            	<a class="dropdown-item" href="userJoin.jsp">회원가입</a> 
            <%
            	} else {
            %>
            	<a class="dropdown-item" href="userLogout.jsp">로그아웃</a>
            <%
            	}
            %>
          	</div>
        </li>
      </ul>
      <form action="./index.jsp" method="get" class="form-inline my-2 my-lg-0">
        <input type="search" name="search" class="form-control mr-sm-2"  placeholder="내용을 입력하세요." aria-label="Search" />
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
        <!-- my : 상하 마진을 줄 수 있음 -->
      </form>
    </div>
  </nav>
<!-- // 네비게이션 -->

<!-- 본문 -->
	<section class="container mt-3" style="max-width:560px;">
		<div class="alert alert-warning mt-4" role="alert">
			이메일 주소 인증을 하셔야 이용 가능합니다. <br />
			인증메일을 받지 못하셨나요?
		</div>
		<a href="emailSendAction.jsp" class="form-control btn btn-primary btn-lg">인증 메일 다시 받기</a>
	</section>
<!-- // 본문 -->


<!-- footer -->
	<footer class="bg-dark mt-4 p-5 text-center" style="color:#ffffff">
		Copyright &copy; 2020 Park All Rights Reserved.
	</footer>
<!-- // footer -->

	<!-- 제이쿼리 -->
	<script src="./jquery/jquery-3.5.1.min.js"></script>
	<!-- 부트스트랩 js -->
	<script src="./Bootstrap/js/bootstrap.min.js"></script>
	<!-- poper  -->
	<script src="./poper/poper.js"></script>
</body>
</html>
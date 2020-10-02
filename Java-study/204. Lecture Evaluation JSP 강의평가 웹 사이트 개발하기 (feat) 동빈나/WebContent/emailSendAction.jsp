<%@page import="javax.mail.internet.InternetAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 메일을 전송하기 위하여 기본적으로 javax.mail.* 라이브러리를 임포트 -->
<%@ page import="javax.mail.Transport"%>
<%@ page import="javax.mail.Message"%>
<%@ page import="javax.mail.Address"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="javax.mail.Session"%>
<%@ page import="javax.mail.Authenticator"%>

<!-- 만든 Gmail 라이브러리 사용 -->
<%@ page import="util.Gmail"%>

<!-- java.util.Properties : 속성 정의 라이브러리 -->
<%@ page import="java.util.Properties"%>

<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>

<!-- java.io.PrintWriter : 특정한 스트립트 구문을 실행 할 때 사용 -->
<%@ page import="java.io.PrintWriter"%>

<%
	UserDAO userDAO = new UserDAO();
String userID = null; // 초기에 기본적으로 사용자의 아이디는 null 값을 가지고 있음
if (session.getAttribute("userID") != null) {
	// 현재 사용자가 로그인 한 상태 
	// 즉, 세션값이 유효한 상태 일때
	userID = (String) session.getAttribute("userID");
}

if (userID == null) { // 사용자가 로그인 하지 않은 경우
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인을 해주세요.');");
	// 경고 메세지 후 로그인 페이지로 이동
	script.println("location.href = 'userLogin.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

boolean emailChecked = userDAO.getUserEmailChecked(userID);
if (emailChecked) { // 사용자의 이메일이 인증이 되어 있다면...
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('이미 인증 된 회원 입니다.');");
	// 입력이 되지 않아 다시 뒤쪽으로 돌려 보내는 history.back() 함수 사용
	script.println("location.href = 'index.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

// host는 웹페이지의 주소를 그대로 넣어 주시면 됩니다.
String host = "http://localhost:8080/103._Lecture_Evaluation/";

// 보내는 사람
// 자신의 이메일 아이디를 넣어 주시면 됩니다.
// String from = "자신의 구글 이메일 계정";
String from = "tjddjs90test@gmail.com";

// 받는 사람
String to = userDAO.getUserEmail(userID);

// 제목
String subject = "(Javax.mail 라이브러리 TEST)강의평가를 위한 이메일 인증 메일입니다.";

// 내용
String content = userID+"님 다음 링크에 접속하여 이메일 인증을 진행하세요." + "<a href='" + host + "emailCheckAction.jsp?hashCode="
		+ new SHA256().getSHA256(to) + "'>이메일 인증하기</a>";

// 실제로 smtp에 접속하기위한 정보를 넣어 줍니다.
Properties p = new Properties();

// 구글 smtp 서버를 이용하기 위해서
p.put("mail.smtp.host", "smtp.googlemail.com"); // 서버 호스트
p.put("mail.smtp.port", "465"); // port
p.put("mail.smtp.starttls.enable", "true"); // 사용가능여부
p.put("mail.smtp.auth", "true"); // 인증
p.put("mail.smtp.debug", "true"); // 디버그
p.put("mail.smtp.socketFactory.port", "465");
p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSlSocketFactory");
p.put("mail.smtp.socketFactory.fallback", "false");
p.put("mail.smtp.user", from);

try { // 실제로 메일을 보내는 과정
	Authenticator auth = new Gmail();
	Session ses = Session.getInstance(p, auth);
	ses.setDebug(true);
	MimeMessage msg = new MimeMessage(ses);
	msg.setSubject(subject);
	Address fromAddr = new InternetAddress(from);
	msg.setFrom(fromAddr);
	Address toAddr = new InternetAddress(to);
	msg.addRecipient(Message.RecipientType.TO, toAddr);
	msg.setContent(content, "text/html;charset=UTF-8");
	Transport.send(msg);
} catch (Exception e) { // 메일 전송시 오류가 발생한 경우 
	e.printStackTrace();
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('오류가 발생했습니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}
%>

<!-- 이메일을 성공적으로 보냈다면 수행할 작업 -->

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
							class="dropdown-item" href="userJoin.jsp"> 회원가입 </a>
						<%
							} else { // 로그인이 된 상태 라면
						%>
						<a class="dropdown-item" href="userLogout.jsp"> 로그아웃 </a>
						<%
							}
						%>
					</div></li>
			</ul>

				<!-- form을 이용한 검색창 생성 -->
			<form action="./index.jsp" method="get"
				class="form-inline my-2 my-lg-0">
				<input type="text" name="search" class="form-control mr-sm-2" type="search"
					placeholder="검색 내용을 입력하세요." aria-label="Search">
					
					<!-- 
					get 방식
					검색전 : http://localhost:8080/103._Lecture_Evaluation/index.jsp
					검색후 : http://localhost:8080/103._Lecture_Evaluation/index.jsp?search=asdasdad
					? 이하의 문자열을 queryString 이라 합니다.
					
					
					post 방식
					검색 전 / 후 의 queryString 의 변화가 없습니다.
					현재까지 잘 모르겠지만, post 방식은 name attribute만 넘어가는 것 같습니다.
					해당 action 페이지에서
					request.getParameter로 받아 오는 걸로 생각 됩니다.
					 -->
					
				<!-- 실제로 검색이 되게 하는 전송 버튼 -->
				<button class="btn btn-outline-success my-2 my-sm-0" type="Submit">검색</button>
			</form>
		</div>
	</nav>
	
	<!-- 본문 시작 -->
	<section class="container mt-3" style="maX-width: 560px;">
		<div class="alert alert-success mt-4" role="alert">이메일 주소 인증 메일이
			전송되었습니다. 회원가입시 입력했던 이메일에 들어가셔서 인증해주세요.</div>
	</section>





	<!-- footer : 페이지의 젤 마지막 부분 일반적으로 회사정보 등이 담기는 곳 -->
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #ffffff;">
		Copyright &copy; 2020 Sung-Un Park All Rights Reserved. </footer>
</body>
</html>
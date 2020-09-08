<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 반응형 메타 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 -->
<link rel="stylesheet" href="./bootstrap-3.3.7-dist/css/bootstrap.css">

<!-- custom 을 위한 css -->
<link type="text/css" rel="stylesheet" href="./css/styles.css" />

<title>JSP Ajax 실시간 회원제 채팅 서비스</title>

<!-- 제이쿼리 (Ajax 사용을 위한...)-->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<!-- 부트스트랩 동작을 위한 js -->
<script src="./bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<!-- join.js -->
<script type="text/javascript" defer src="./js/join.js"></script>
</head>
<body>

	<!-- 세션작업(로그인) -->
	<%
		String userID = null;

	if (session.getAttribute("userID") != null)
		userID = (String) session.getAttribute("userID");

	// 현재 로그인 된상태로 회원가입을 하지 못 합니다.
	if (userID != null) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "현재 로그인이 되어 있는 상태입니다.");
		response.sendRedirect("index.jsp");
		return;
	}
	%>

	<!-- 네비게이션 -->
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">실시간 회원제 채팅 서비스</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">메인</a></li>
				<li><a href="find.jsp">친구찾기</a></li>
			</ul>
			<%
				// 로그인이 된상태면 위에서 index 페이지로 돌려보내는데 굳이 필요가 있을까 .... ?
			// if (userID == null) { // 로그인을 하지 않은 상태 라면...
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> 접속하기 <!-- <span class="caret"></span> : 이미지 입니다. (▼) -->
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li class="active"><a href="join.jsp">회원가입</a></li>
					</ul></li>
			</ul>
			<%
				// }
			%>
		</div>
	</nav>
	<!-- // 네비게이션 -->

	<!-- 회원가입 view -->
	<div class="container">
		<form method="post" action="./memberRegister">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd;">
				<thead>
					<tr>
						<th colspan="3"><h4>회원 등록 양식</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input class="form-control" type="text" id="userID"
							name="userID" maxlength="20" placeholder="아이디를 입력하세요." /></td>
						<td><button class="btn btn-primary"
								onclick="registerCheckFunction();" type="button">중복확인</button></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();"
							class="form-control" type="password" id="userPassword1"
							name="userPassword1" maxlength="20" placeholder="비밀번호를 입력하세요." /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();"
							class="form-control" type="password" id="userPassword2"
							name="userPassword2" maxlength="20" placeholder="비밀번호 확인을 입력하세요." /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="userName" name="userName" maxlength="20"
							placeholder="이름을 입력하세요." /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><input class="form-control" type="number"
							id="userAge" name="userAge" maxlength="20"
							placeholder="나이를 입력하세요." /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>성별</h5></td>
						<td colspan="2">
							<div class="form-group"
								style="text-align: center; margin: 0 auto;">
								<div class="btn-group" data-toggle="buttons">
									<label class="btn btn-primary active"> <input
										type="radio" autocomplete="off" name="userGender" value="남자"
										checked /> 남자
									</label> <label class="btn btn-primary"> <input type="radio"
										autocomplete="off" name="userGender" value="여자" /> 여자
									</label>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td colspan="2"><input class="form-control" type="email"
							id="userEmail" name="userEmail" maxlength="20"
							placeholder="이메일을 입력하세요." /></td>
					</tr>
					<tr>
						<td style="text-align: left" colspan="3">
							<h5 style="color: red;" id="passwordCheckMessage"></h5>
							<button class="btn btn-primary pull-right" type="submit">등록</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<!-- // 회원가입 view -->

	<!-- servlet session 값으로 확인하는 모달 -->
	<%
		String messageType = null;
	if (session.getAttribute("messageType") != null)
		messageType = (String) session.getAttribute("messageType");

	String messageContent = null;
	if (session.getAttribute("messageContent") != null)
		messageContent = (String) session.getAttribute("messageContent");

	if (messageType != null && messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div
					class="modal-content <%if (messageType.equals("오류 메시지"))
	out.println("panel-warning");
else
	out.println("panel-success");%>">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType%>
						</h4>
					</div>
					<div class="modal-body">
						<%=messageContent%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		// join.jsp의 모달 창을 사용자에게 보여주는 역할을 합니다.
		$('#messageModal').modal('show');
	</script>
	<%
		session.removeAttribute("messageType");
	session.removeAttribute("messageContent");
	}
	%>
	<!-- // servlet session 값으로 확인하는 모달 -->

	<!-- check Modal(비밀번호 일치, 아이디 중복여부) -->
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div id="checkType" class="modal-content panel-info">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">확인 메시지</h4>
					</div>
					<div id="checkMessage" class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- // check Modal(비밀번호 일치, 아이디 중복여부) -->
</body>
</html>
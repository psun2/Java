<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@page import="java.net.URLDecoder"%>
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

<!-- 메시지함 box.js -->
<script defer src="./js/box.js"></script>

<!-- 비밀번호 일치 여부를 위한 join.js -->
<script type="text/javascript" defer src="./js/join.js"></script>
</head>
<body>

	<!-- 세션작업(로그인) -->
	<%
		String userID = null;

	if (session.getAttribute("userID") != null)
		userID = URLDecoder.decode((String) session.getAttribute("userID"), "UTF-8");

	if (userID == null) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
		response.sendRedirect("iogin.jsp");
		return;
	}

	MemberDAO memberDAO = new MemberDAO();
	MemberDTO member = memberDAO.getUser(userID);

	if (member == null) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
		response.sendRedirect("update.jsp");
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
				<li><a href="box.jsp">메시지함 <span id="unread"
						class="label label-info"></span></a></li>
						<li><a href="boardView.jsp">자유 게시판</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expended="false"> 회원관리<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li class="active"><a href="update.jsp">회원정보수정</a></li>
						<li><a href="profileUpdate.jsp">프로필 수정</a></li>
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>

		</div>
	</nav>
	<!-- //네비게이션 -->

	<!-- 회원정보수정 view -->
	<div class="container">
		<form method="post" action="./memberUpdate">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd;">
				<thead>
					<tr>
						<th colspan="2"><h4>회원정보수정 양식</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td>
							<h5><%=member.getUserID()%>
							</h5> <input type="hidden" name="userID"
							value="<%=member.getUserID()%>" />
						</td>
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
							id="userName" name="userName" value="<%=member.getUserName()%>"
							maxlength="20" placeholder="이름을 입력하세요." /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><input class="form-control" type="number"
							id="userAge" name="userAge" value="<%=member.getUerAge()%>"
							maxlength="20" placeholder="나이를 입력하세요." /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>성별</h5></td>
						<td colspan="2">
							<div class="form-group"
								style="text-align: center; margin: 0 auto;">
								<div class="btn-group" data-toggle="buttons">
									<label
										class="btn btn-primary <%if (member.getUserGender().equals("남자"))
	out.print("active");%>">
										<input type="radio" autocomplete="off" name="userGender"
										value="남자"
										<%if (member.getUserGender().equals("남자"))
	out.print("checked");%> />
										남자
									</label> <label
										class="btn btn-primary <%if (member.getUserGender().equals("여자"))
	out.print("active");%>">
										<input type="radio" autocomplete="off" name="userGender"
										value="여자"
										<%if (member.getUserGender().equals("여자"))
	out.print("checked");%> />
										여자
									</label>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td colspan="2"><input class="form-control" type="email"
							id="userEmail" name="userEmail"
							value="<%=member.getUserEmail()%>" maxlength="20"
							placeholder="이메일을 입력하세요." /></td>
					</tr>
					<tr>
						<td style="text-align: left" colspan="3">
							<h5 style="color: red;" id="passwordCheckMessage"></h5>
							<button class="btn btn-primary pull-right" type="submit">수정</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<!-- // 회원정보수정 view -->

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


	<%
		session.removeAttribute("messageType");
	session.removeAttribute("messageContent");
	}
	%>

	<script>
		// join.jsp의 모달 창을 사용자에게 보여주는 역할을 합니다.
		$('#messageModal').modal('show');
	</script>

	<%
		if (userID != null) {
	%>
	<script type="text/javascript">
		$(document).ready(() => {
			setUserID('<%=userID%>');
			getUnread();
			getInfiniteUnread();
		});
	</script>
	<%
		}
	%>

</body>
</html>
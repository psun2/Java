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

<!-- 메시지함 box.js -->
<script type="text/javascript" defer src="./js/box.js"></script>
</head>
<body>

	<!-- 세션작업(로그인) -->
	<%
		String userID = null;

	if (session.getAttribute("userID") != null)
		userID = (String) session.getAttribute("userID");

	if (userID == null) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
		response.sendRedirect("login.jsp");
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
				<li class="active"><a href="box.jsp">메시지함 <span id="unread"
						class="label label-info"></span></a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expended="false"> 회원관리<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
					<li><a href="update.jsp">회원정보수정</a></li>
					<li><a href="profileUpdate.jsp">프로필 수정</a></li>
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>

		</div>
	</nav>
	<!-- //네비게이션 -->

	<!-- 메시지함 view (디자인) -->
	<div class="container">
		<table class="table" style="margin: 0 auto;">
			<thead>
				<tr>
					<th><h4>주고 받은 메시지 목록</h4></th>
				</tr>
			</thead>
			<div style="overflow-y: auto; width:100%; max-height:450px;">
			<table class="table table-bordered table-hover" style="text-align:center; border: 1px solid #dddddd; margin: 0 auto;" >
			<tbody id="boxTable">
			</tbody>
			</table>
			</div>
			<tbody></tbody>
		</table>
	</div>

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
			// chatBoxFunction();
			getInfiniteBox();
		});
	</script>
	<%
		}
	%>

</body>
</html>
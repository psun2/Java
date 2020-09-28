<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="board.BoardDAO"%>
<%@page import="board.BoardDTO"%>
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
</head>
<body>

	<!-- 세션작업(로그인) -->
	<%
		String userID = null;

	if (session.getAttribute("userID") != null)
		userID = (String) session.getAttribute("userID");

	// 로그인이 안되어 있다면 게시글 접근 불가능
	if (userID == null) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다");
		response.sendRedirect("login.jsp");
		return;
	}

	String boardID = null;
	if (request.getParameter("boardID") != null)
		boardID = request.getParameter("boardID");

	if (boardID == null || boardID.equals("")) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "게시물을 선택해 주세요.");
		response.sendRedirect("boardView.jsp");
		return;
	}

	BoardDAO boardDAO = new BoardDAO();
	BoardDTO board = boardDAO.getBoard(boardID);
	if (board.getBoardAvailable() == 0) {
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "삭제된 게시물 입니다.");
		response.sendRedirect("boardView.jsp");
		return;
	}

	// 이부분을 board 를 가져오는 부분보다 위에 있다면 굳이 아래서 +1 값을 해줘야 하나 .... ?
	boardDAO.hit(boardID); // show 페이지로 진입하는 순간 조회수를 1 증가합니다.
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
				<li class="active"><a href="boardView.jsp">자유 게시판</a></li>
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

	<!-- 게시판 -->
	<div class="container">
		<table class="table table-bordered table-hover"
			style="text-align: center;">
			<thead>
				<tr>
					<th colspan="4"><h4>게시물 보기</h4></th>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
					<td colspan="3"><%=board.getBoardTitle()%></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
					<td colspan="3"><%=board.getUserID()%></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성날짜</h5></td>
					<td><%=board.getBoardDate()%></td>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
					<!-- board.getBoardHit() + 1 이유 : 새로고침을하면 조회수가 오르지만 클릭하여 show 로 들어 왔을땐 현재 조회수가 올라 있지 
					않은 상태,,,, 그러므로 일단 출력시 +1 깂을하여 출력 합니다. -->
					<td><%=board.getBoardHit() + 1%></td>
				</tr>
				<tr>
					<td
						style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>글
							내용</h5></td>
					<td colspan="3" style="text-align: left;"><%=board.getBoardContent()%></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>첨부파일</h5></td>
					<td colspan="3"><a
						href="boardDownload.jsp?boardID=<%=board.getBoardID()%>"><%=board.getBoardFile()%></a></td>
				</tr>
			</thead>
			<tbody>


				<tr>
					<td colspan="5" style="text-align: right;"><a
						href="boardView.jsp" class="btn btn-primary =">목록</a> <a
						href="boardReply.jsp?boardID=<%=board.getBoardID()%>"
						class="btn btn-primary =">답변</a> <%
 	if (userID.equals(board.getUserID())) { // 게시글을 보고있는 사용자가 작성자 본인이라면...
 %> <a href="boardUpdate.jsp?boardID=<%=board.getBoardID()%>"
						class="btn btn-primary">수정</a> <a
						href="boardDelete?boardID=<%=board.getBoardID()%>"
						class="btn btn-primary" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
						<%
							}
						%></td>
				</tr>
			</tbody>
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
			getUnread();
			getInfiniteUnread();
		});
	</script>
	<%
		}
	%>

</body>
</html>
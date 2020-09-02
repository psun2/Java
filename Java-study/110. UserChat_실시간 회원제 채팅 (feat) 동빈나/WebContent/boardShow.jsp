<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.BoardDAO"%>
<%@ page import="board.BoardDTO"%>
<!DOCTYPE html>
<html lang="ko">
<%
	// 메인페이지로 삼기 위한 세션 작업 (로그인 처리)
String userID = null;
if (session.getAttribute("userID") != null)
	userID = (String) session.getAttribute("userID");

// 로그인이 되어 있지 않을때 게시판 접근을 불허 합니다.
if (userID == null) {
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
	response.sendRedirect("login.jsp");
	return;
}

String boardID = null;

if (request.getParameter("boardID") != null)
	boardID = (String) request.getParameter("boardID");

if (boardID == null || boardID.equals("")) {
	session.setAttribute("messageType", "오류메시지");
	session.setAttribute("messageContent", "게시물이 존재하지 않습니다.");
	response.sendRedirect("boardView.jsp");
	return;
}

System.out.println("boardID : " + boardID);

BoardDAO boardDAO = new BoardDAO();
BoardDTO board = boardDAO.getBoard(boardID);

// 해당 사용자가 클릭과 동시에 조회수가 1증가 합니다.
boardDAO.hit(boardID);

System.out.println("boardShow.jsp => board : " + board);
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="./css/bootstrap.css" />
<!-- 커스텀 css -->
<!-- ?after 추가로 인하여 캐쉬를 삭제해야 보였던 css가 매번 보이게 됩니다. -->
<!-- reason: 브라우저는 css를 캐쉬에 저장하므로, 캐쉬에 저장된 기억의 css 파일을 불러와서 바뀐 css가 적용되지 않았던 문제 해결 -->
<link rel="stylesheet" href="./css/custom.css?after" />
<title>JSP Ajax 실시간 회원제 채팅 서비스</title>
<!-- Ajax를 위한 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="./js/bootstrap.js"></script>

<script type="text/javascript">
	function getUnread() {
		$.ajax({
		type:"POST",
		url:"./chatUnread",
		data:{
			userID: encodeURIComponent('<%=userID%>')
			},
			success : function(result) {
				if (result >= 1)
					showUnread(result);
				else
					showUnread('');
			}
		});
	}

	// 반복적으로 현재 자신이 읽지 않은 메시지를 서버로 부터 받아서 보여 줍니다.
	function getInfiniteUnread() {
		setInterval(function() {
			getUnread();
		}, 1000);
	}

	function showUnread(result) {
		$('#unread').html(result);
	}
</script>
</head>
<body>

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
				<li><a href="box.jsp">메세지함<span id="unread"
						class="label label-info"></span></a></li>
				<li class="active"><a href="boardView.jsp">자유게시판</a></li>
			</ul>
			<%
				if (userID == null) { // 로그인 상태가 아니라면
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true">접속하기
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true">회원관리
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="update.jsp">회원정보수정</a></li>
						<li><a href="profileUpdate.jsp">프로필 수정</a></li>
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>

			<%
				}
			%>
		</div>
	</nav>

	<!-- 게시판 Mark Up -->
	<div class="container">
		<table class="table table-bordered table-hover"
			style="text-align: center; border: 1px solid #dddddd;">
			<thead>
				<tr>
					<th colspan="4">
						<h4>게시물 보기</h4>
					</th>
				</tr>

			</thead>
			<tbody>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
					<td colspan="3"><h5><%=board.getBoardTitle()%>
						</h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
					<td colspan="3"><h5><%=board.getUserID()%>
						</h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성날짜</h5></td>
					<td><h5><%=board.getBoardDate()%>
						</h5></td>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
					<td><h5><%=board.getBoardHit()%>
						</h5></td>
				</tr>
				<tr>
					<td
						style="vertical-align: midde; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>내용</h5></td>
					<td colspan="3" style="text-align: left;"><h5><%=board.getBoardContent()%>
						</h5></td>
				</tr>
				<tr>
					<td
						style="vertical-align: midde; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>첨부파일</h5></td>
					<td colspan="3"><h5>
							<a href="boardDownload.jsp?boardID=<%=board.getBoardID()%>"><%=board.getBoardFile()%></a>
						</h5></td>
				</tr>
				<tr>

					<td colspan="5" style="text-align: right;"><a
						href="boardReply.jsp?boardID=<%=board.getBoardID()%>"
						class="btn btn-primary">답변</a> <%
 	// 게시글 작성자 본인 이라면 ....
 if (userID.equals(board.getUserID())) {
 %> <a href="boardUpdate.jsp?boardID=<%=board.getBoardID()%>"
						class="btn btn-primary">수정</a> <a
						href="boardDelete?boardID=<%=board.getBoardID()%>"
						class="btn btn-primary" onclick="confirm('정말로 삭제하시겠습니까?');">삭제</a>

						<%
							}
						%> <a href="boardView.jsp" class="btn btn-primary">목록</a></td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- 알림창 구현 -->
	<div class="alert alert-success" id="successMessage"
		style="display: none;">
		<strong>메시지 전송에 성공했습니다.</strong>
	</div>
	<div class="alert alert-danger" id="dangerMessage"
		style="display: none;">
		<strong>이름과 내용을 모두 입력해주세요.</strong>
	</div>
	<div class="alert alert-warning" id="warningMessage"
		style="display: none;">
		<strong>데이터베이스 오류가 발생했습니다.</strong>
	</div>

	<!-- 회원 가입에 대한 모달 즉, submit에 대한 모달 -->
	<%
		// UserRegisterServlet 을 reference
	String messageType = null;
	if (session.getAttribute("messageType") != null)
		messageType = (String) session.getAttribute("messageType");

	String messageContent = null;
	if (session.getAttribute("messageContent") != null)
		messageContent = (String) session.getAttribute("messageContent");

	if (messageType != null && messageContent != null) {
	%>
	<!-- 자바스크립트의 registerCheckFunction() 함수 참조 -->
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
						<button type="button" class="close" data-dismiss="madal">
							<!-- &times; : X 모양의 이미지 (?) 입니다. -->
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
		// 실제보 보여주게 함.
		$('#messageModal').modal("show");
	</script>

	<%
		// 모달을 띄운뒤 세션을 파괴합니다.
	session.removeAttribute("messageType");
	session.removeAttribute("messageContent");
	}
	%>
	<%
		if (userID != null) {
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			getUnread();
			getInfiniteUnread();
		});
	</script>
	<%
		}
	%>
</body>
</html>
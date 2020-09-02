<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.UserDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="board.BoardDAO"%>
<%@page import="board.BoardDTO"%>
<!DOCTYPE html>
<html lang="ko">
<%
	System.out.println("boardUpdate.jsp 진입");

// 메인페이지로 삼기 위한 세션 작업 (로그인 처리)
String userID = null;
if (session.getAttribute("userID") != null)
	userID = (String) session.getAttribute("userID");

// 회원정보 수정은 로그인이 되어있는 사용자만 수정 할 수 있습니다.
if (userID == null) {
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "현재 로그인이 되어 있지 않는 상태입니다.");
	response.sendRedirect("index.jsp");
	return;
}

UserDTO user = new UserDAO().getUser(userID);

String boardID = request.getParameter("boardID");
System.out.println("boardID : " + boardID);
String boardID2 = (String) session.getAttribute("boardID");
System.out.println("boardID2 : " + boardID2);
if (boardID == null && boardID.equals("")) {
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "접근할 수 없습니다.");
	response.sendRedirect("boardShow.jsp");
	return;
}

BoardDAO boardDAO = new BoardDAO();
BoardDTO board = boardDAO.getBoard(boardID);

System.out.println("board : " + board);

// 글의 작성자가 아닐때 글을 수정 하려 할때....
if (!userID.equals(board.getUserID())) {
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "글의 작성자가 아닙니다.");
	response.sendRedirect("boardShow.jsp?boardID=" + boardID);
	return;
}
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="./css/bootstrap.css" />
<!-- 커스텀 css -->
<!-- ?after 추가로 인하여 캐쉬를 삭제해야 보였던 css가 매번 보이게 됩니다. -->
<!-- reason: 브라우저는 css를 캐쉬에 저장하므로, 캐쉬에 저장된 기억의 css 파일을 불러와서 바뀐 css가 적용되지 않았던 문제 해결 -->
<link type="text/css" rel="stylesheet" href="./css/custom.css" />
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

	// 비밀번호 일치 체크
	function passwordCheckFunction() {
		var userPassword1 = $('#userPassword1').val();
		var userPassword2 = $('#userPassword2').val();

		if (userPassword1 != userPassword2) {
			$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
		} else {
			$('#passwordCheckMessage').html('');
		}
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
				<li><a href="boardView.jsp">자유게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true">회원관리
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="update.jsp">회원정보수정</a></li>
						<li class="active"><a href="profileUpdate.jsp">프로필 수정</a></li>
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>

	<!-- 글 쓰기 페이지 -->
	<div class="container">
		<!-- enctype="multipart/form-data" : 파일 전송시 form 태그의 Attribute로 사용 합니다. -->
		<!-- 아직까진 자세한 기능을 모르겠습니다. -->
		<form method="post" action="./boardUpdate"
			enctype="multipart/form-data">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px soild #dddddd;">
				<thead>
					<tr>
						<th colspan="2"><h4>게시물 수정 양식</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td>
							<h5><%=user.getUserID()%>
							</h5> 
							<%
							System.out.println("1 : " + user.getUserID());
							System.out.println("2 : " + board.getBoardID());
							%>
							<input type="hidden" name="userID" value="<%=user.getUserID()%>">
							<input type="hidden" name="boardID" value="<%=board.getBoardID()%>">
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>글 제목</h5></td>
						<td><input class="form-control" type="text" maxlength="50"
							name="boardTitle" placeholder="글 제목을 입력하세요."
							value="<%=board.getBoardTitle()%>"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>글 내용</h5></td>
						<td><textarea class="form-control" rows="10"
								name="boardContent" maxlength="2048" placeholder="글 내용을 입력하세요."><%=board.getBoardContent()%></textarea>
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>파일 업로드</h5></td>
						<td colspan="2">
							<!-- span : 파일등록 양식 --> <input type="file" name="boardFile"
							class="file" />
							<div class="input-group col-xs-12">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-picture"></i></span>

								<!--
								
								파일이 변경되지 않는 이상 재업로드를 할 필요가 없기 때문에 
								value 가 아닌 placeholder 부분에 파일명을 나타나게 합니다.
								
									<input type="text"
									class="form-control input-lg" disabled
									placeholder="파일을 업로드 하세요." value="'<%= board.getBoardFile() %>'" /> 
									-->

								<input type="text" class="form-control input-lg" disabled
									placeholder="<%=board.getBoardFile()%>" /> <span
									class="input-group-btn">
									<button class="browse btn btn-primary input-lg" type="button">
										<i class="glyphicon glyphicon-search"></i> 파일찾기
									</button>
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: left" colspan="3"><h5
								style="color: red;"></h5> <input
							class="btn btn-primary pull-right" type="submit" value="수정" /> <!-- <button type="submit" class="btn btn-primary pull-right">등록</button>  -->
						</td>
					</tr>
				</tbody>
			</table>
		</form>
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

	<script type="text/javascript">
		// 파일 찾기 버튼 기능 구현

		$(document).on('click', '.browse', function() {
			console.log($(this));
			console.log($(this).parent());
			console.log($(this).parent().parent());
			console.log($(this).parent().parent().parent());
			console.log($(this).parent().parent().parent().find('.file'));
			var file = $(this).parent().parent().parent().find('.file');

			// console.log(file.trigger('click'));
			file.trigger('click');
		});

		// 파일 교체를 위한 change 이벤트
		$(document).on(
				'change',
				'.file',
				function() {
					console.log($(this));
					console.log($(this).parent());
					console.log($(this).parent().find('.form-control'));
					console.log($(this).parent().find('.form-control').val(
							$(this).val().replace(/c:\\fakepath\\/i, '')));

					$(this).parent().find('.form-control').val(
							$(this).val().replace(/c:\\fakepath\\/i, ''));
				});
	</script>
</body>
</html>
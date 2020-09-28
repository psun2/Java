<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="board.BoardDAO"%>
<%@page import="board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
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
		session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
		response.sendRedirect("login.jsp");
		return;
	}

	String pageNumber = "1"; // 맨처음에 시작할때는 당연히 1페이지 부터 시작 합니다.
	if (request.getParameter("pageNumber") != null)
		pageNumber = request.getParameter("pageNumber");
	
	System.out.println(pageNumber);

	// 넘어온 페이지Number 값이 숫자 형태 일때만 처리해줍니다.
	// 악의 적으로 이상한 글씨를 보낼 수도 있으므로.,...
	try {
		Integer.parseInt(pageNumber);
	} catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "페이지 번호가 잘못되었습니다.");
		response.sendRedirect("boardView.jsp");
		return;
	}

	ArrayList<BoardDTO> boardList = new BoardDAO().getList(pageNumber);

	System.out.println("현재 데이터 0개 인데??;;" + boardList.size());
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
					<th colspan="5"><h4>자유 게시판</h4></th>
				</tr>
				<tr>
					<th style="background-color: #fafafa; color: #000000; width: 70px;"><h5>번호</h5></th>
					<th style="background-color: #fafafa; color: #000000;"><h5>제목</h5></th>
					<th style="background-color: #fafafa; color: #000000;"><h5>작성자</h5></th>
					<th
						style="background-color: #fafafa; color: #000000; width: 200px;"><h5>작성
							날짜</h5></th>
					<th style="background-color: #fafafa; color: #000000; width: 70px;"><h5>조회수</h5></th>
				</tr>
			</thead>
			<tbody>
				<%
					for (BoardDTO board : boardList) {
				%>

				<tr>
					<td><%=board.getBoardID()%></td>

					<td style="text-align: left;"><a
						href="boardShow.jsp?boardID=<%=board.getBoardID()%>"> <%
 	for (int j = 0; j < board.getBoardLevel(); j++) { // 답변 들여 쓰기
 %> <span class="glyphicon glyphicon-arrow-right" aria-hieend="true"></span>
							<%
								}
							%> <%
 	if (board.getBoardAvailable() == 0) {
 %> (삭제된 게시물 입니다.) <%
 	} else {
 %> <%=board.getBoardTitle()%> <%
 	}
 %>
					</a></td>

					<td><%=board.getUserID()%></td>
					<td><%=board.getBoardDate()%></td>
					<td><%=board.getBoardHit()%></td>
				</tr>
				<!--
			
				<tr>
					<td>1</td>
					<td>예시 데이터 입니다.</td>
					<td>홍길동</td>
					<td>2020-09-24</td>
					<td>0</td>
				</tr>
				
				-->
				<%
					}
				%>

				<tr>
					<td colspan="5"><a href="boardWrite.jsp"
						class="btn btn-primary pull-right">글쓰기</a>
						<ul class="pagination" style="margin: 0 auto;">
						<%
						
						System.out.println();
						
						int startPage = (Integer.parseInt(pageNumber) / 10) * 10 + 1;
						if(Integer.parseInt(pageNumber) % 10 == 0) startPage -= 10;
						int targetPage = new BoardDAO().targetPage(pageNumber);
						System.out.println("targetPage : " + targetPage);
						if(startPage != 1) {
						%>
						 <li><a	href="boardView.jsp?pageNumber=<%=startPage - 1%>"><span class="glyphicon glyphicon-chevron-left"></span></a></li>						
						<%
						} else {
						%>
						<li><span class="glyphicon glyphicon-chevron-left" style="color:gray;"></span></li>
						<%
						}
						
						System.out.println(startPage);
						System.out.println(Integer.parseInt(pageNumber));
						
						for(int i = startPage; i < Integer.parseInt(pageNumber); i++) {
						%>
						<li><a	href="boardView.jsp?pageNumber=<%=i%>"><%=i %></a></li>
						<%
						}
						%>
						<li class="active"><a href="boardView.jsp?pageNumber=<%=pageNumber%>"><%=pageNumber %></a></li>
						<%
						System.out.println(Integer.parseInt(pageNumber) + 1);
						System.out.println(targetPage + Integer.parseInt(pageNumber));
						for(int i = Integer.parseInt(pageNumber) + 1; i <=targetPage + Integer.parseInt(pageNumber); i++) {
							if(i < startPage + 10) {
						%>
						<li><a	href="boardView.jsp?pageNumber=<%=i%>"><%=i %></a></li>
						<%
							}
						}
						if(targetPage + Integer.parseInt(pageNumber) > startPage + 9) {
						%>
						<li><a	href="boardView.jsp?pageNumber=<%=startPage + 10%>"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
						<%
						} else {
						%>
						<li><span class="glyphicon glyphicon-chevron-right" style="color: gray"></span></li>
						<%
						}
						%>
						</ul> 
						
						
						
						<!-- 
						<%
 						// if (!pageNumber.equals("1")) {
 						%> 
						 <a	href="boardView.jsp?pageNumber=<%=Integer.parseInt(pageNumber) - 1%>" class="btn btn-success">이전</a> 
						<%
 						// }
						%>
						<% 
 						// if (new BoardDAO().nextPage(pageNumber)) {
						%>
 						<a	href="boardView.jsp?pageNumber=<%=Integer.parseInt(pageNumber) + 1%>" class="btn btn-success">다음</a>
						<%
						 // }
						%>
 						 -->
 						 </td>
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
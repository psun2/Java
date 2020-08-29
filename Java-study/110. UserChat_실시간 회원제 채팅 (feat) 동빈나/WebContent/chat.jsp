<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="user.UserDAO"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- 바디 태그의 맨 윗부분에 있었는데, submit 함수로 인하여 userID의 선언이 더 아래있으므로 submitFunction에서 참조를 못하여,
	head태그로 이동
	결론 : 헤드 태그에서도 자바 코드 사용 가능!
	 -->
<%
	// 메인페이지로 삼기 위한 세션 작업 (로그인 처리)
String userID = null;
if (session.getAttribute("userID") != null)
	userID = (String) session.getAttribute("userID");

System.out.println("userID : " + userID);

String toID = null;
if (request.getParameter("toID") != null)
	toID = (String) request.getParameter("toID");

// userID 가 null 이라면 로그인이 되어있지 않은 상태
if (userID == null) {
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "현재 로그인이 되어 있지 않는 상태입니다.");
	response.sendRedirect("index.jsp");
	return;
}
if (toID == null) {
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "대화 상대가 지정 되어 있지 않는 상태입니다.");
	response.sendRedirect("index.jsp");
	return;
}
if (userID.equals(URLDecoder.decode(toID, "UTF-8"))) { // 자기 자신에게 쪽지 보낼때 생성할 오류 메시지
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "자기 자신에게는 쪽지를 보낼수 없습니다.");
	response.sendRedirect("find.jsp");
	return;
}

String checkUser = new UserDAO().registerCheck(toID) +"";

if(checkUser.equals("1")) {
	session.setAttribute("messageType", "오류 메시지");
	session.setAttribute("messageContent", "존재하지 않는 사용자에게는 쪽지를 보낼수 없습니다.");
	response.sendRedirect("find.jsp");
	return;
}
%>
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

<!-- 실제로 전송을 하기 위한 자바스크립트 -->
<script type="text/javascript">
	function autoClosingAlert(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() {alert.hide()}, delay);
	}
	function submitFunction() {
		console.log('진입');
		var fromID = '<%=userID%>';
		var toID = '<%=toID%>';
		var chatContent = $('#chatContent').val();
		$.ajax({
			type: "POST",
			url: "./chatSubmitServlet",
			data: {
				fromID: encodeURIComponent(fromID),
				toID: encodeURIComponent(toID),
				chatContent: encodeURIComponent(chatContent)				
			},
			success:function (result) {
				if(result == 1) {
					autoClosingAlert('#successMessage', 2000);
				} else if(result == 0) {
					autoClosingAlert('#dangerMessage', 2000);					
				} else {					
					autoClosingAlert('#warningMessage', 2000);
				}
			} 
		});
			// 메시지를 보낸후 textarea 의 값을 초기화
			$('#chatContent').val('');
	}
	
	// 채팅목록 을 가져오는 로직
	var lastID  = 0;
	function chatListFunction(type) {
		var fromID ='<%=userID%>';
		var toID ='<%=toID%>';
		$.ajax({
			type:"POST",
			url:"./chatListServlet",
			data:{
				fromID: encodeURIComponent(fromID),
				toID: encodeURIComponent(toID),
				listType: type
				},
				success: function(data) {
					if(data == "") return;
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for(var i = 0; i < result.length; i++) {
						if(result[i][0].value == fromID ) {
							// 메시지를 보낸 사람이 나의 아이디 와 같다면...
							result[i][0].value = '나';
						}
						addChat(result[i][0].value, result[i][2].value,result[i][3].value);
					}
					lastID = Number(parsed.last);
				}
		});
	}

	function addChat (chatName, chatContent, chatTime) {
		let src = './images/yellow-48.png';
		if(chatName != '나')
			src = 'https://placeimg.com/64/64/any';	
		
		$('#chatList').append(
		'<div class="row">' + 
		'<div class="col-lg-12">'+
		'<div class="media">' + 
		'<a class="pull-left" href="#">'+
		'<img class="media-object img-circle" style="width:30px; height:30px;"'+
		' src='+src+
		' alt="프로필사진">' +  
		'</a>'+
		'<div class="media-body">' + 
		'<h4 class="media-heading">' + 
		chatName + 
		'<span class="small pull-right">' + 
		chatTime + 
		'</span>' + 
		'</h4>' + 
		'<p>' + 
		chatContent + 
		'</p>' + 
		'</div>' + 
		'</div>' + 
		'</div>' + 
		'</div>' +
		'<hr>'
		);
	$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	}
	
	function getInfiniteChat ()  {
		setInterval(function () {
			chatListFunction(lastID);
		}, 1000); 
	}
</script>
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
			</ul>
			<%
				if (userID != null) { // 로그인 상태라면
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

	<!-- https://www.bootdey.com/ -->
	<!-- 채팅창 구현 -->
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-xs-12">
				<div class="portlet portlet-default">
					<div class="portlet-heading">
						<div class="portlet-title">
							<h4>
								<i class="fa fa-circle text-green"></i> 실시간 채팅창
							</h4>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id="chat" class="panel-collapse collapse in">
						<div id="chatList" class="portlet-body chat-widget"
							style="overflow-y: auto; width: auto; height: 600px;"></div>
						<div class="portlet-footer">
							<!-- 이름은 자신의 아이디 이므로 
							<div class="row">
								<div class="form-group col-xs-4">
									<input style="height: 40px;" type="text" id="chatName"
										class="form-control" placeholder="이름" maxlength="8" />
								</div>
							</div>	
							-->
							<div class="row" style="height: 90px;">
								<div class="form-group col-xs-10">
									<textarea style="height: 80px;" id="chatContent"
										class="form-control" placeholder="메시지를 입력하세요." maxlength="100"></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type="button" class="btn btn-dafault pull-right"
										onclick="submitFunction();">전송</button>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 알림창 구현 -->
	<div class="alert alert-success" id="successMessage"
		style="display: none;">
		<strong>메시지 전송에 성공했습니다.</strong>
	</div>
	<div class="alert alert-danger" id="dangerMessage"
		style="display: none;">
		<strong>내용이 입력되지 않았습니다.</strong>
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

	System.out.println(messageType);

	String messageContent = null;
	if (session.getAttribute("messageContent") != null)
		messageContent = (String) session.getAttribute("messageContent");

	System.out.println(messageContent);

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

	<script type="text/javascript">
	// (document).ready : 성공적으로 웹문서가 다 불러 와졌을때를 의미합니다.
	$(document).ready(() => {
		getUnread();
		// 맨 초기 에는 ten 즉 10개 만큼 불러와 사용자의 화면에 뿌려 줍니다.
		chatListFunction('0');
		getInfiniteChat();
		getInfiniteUnread();
	});
	</script>
</body>
</html>
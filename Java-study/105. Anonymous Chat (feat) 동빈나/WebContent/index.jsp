<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JSO AJAX 실시간 익명 채팅 사이트</title>
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
<!-- 상대방이 메시지를 받았나 알 수 있는 자바스크립트 import -->
<script defer type="text/javascript">
	function submitFunction() {
		// chatName 의 input의 값을 가져 옵니다.
		var chatName = $('#chatName').val();
		var chatContent = $('#chatContent').val();

		// ajax 를 이용한 서버와의 통신
		$.ajax({
			type : "POST",
			url : "./chatSubmitServlet",
			data : {
				// 파라미터이름: 실제로 보내는 값 
				chatName : chatName,
				chatContent : chatContent
			},
			success : function(result) {
				if (result == 1) {
					// alert('전송에 성공했습니다.');
					autoClosingAlert('#successMessage', 2000);
				} else if (result == 0) {
					// alert('이름과 내용을 정확히 입력하세요.');
					autoClosingAlert('#dangerMessage', 2000);
				} else {
					// alert('데이터베이스 오류가 발생했습니다.');
					autoClosingAlert('#warningMessage', 2000);
				}
			}
		});
		// 전송한뒤 input창의 value를 비우기
		$('#chatContent').val('');
	}
	function autoClosingAlert(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		// window.setTimeout(() => {alert.hide()}, delay);
		window.setTimeout(function() {
			alert.hide()
		}, delay);
	}

	function chatListFunction(type) {

		// ajax 를 이용한 서버와의 통신
		$.ajax({
			type : "POST",
			url : "./chatListServlet",
			data : {
				// 파라미터이름: 실제로 보내는 값 
				listType : type
			},
			
			success : function(data) {

				console.log(data);

				// 제이슨 형태로 파싱 과정
				var parsed = JSON.parse(data);
				console.log(parsed);

				var result = parsed.result;
				console.log(result);

				for (var i = 0; i < result.length; i++) {
					addChat(result[i][0].value, result[i][1].value,
							result[i][2].value);
				}

			}
		});

	}

	function addChat(chatName, chatContent, chatTime) {
		$('#chatList')
				.append(
						'<div class="row">'
								+ '<div class="col-lg-12">'
								+ '<div class="media">'
								+ '<a class="pull-left" href="#">'
								+ '<img class="media-object img-circle" src="./images/yellow-48.png" alt="프로필사진" />'
								+ '</a>' + '<div class="media-body">'
								+ '<h4 class="media-heading">' + chatName
								+ '<span class="small pull-right">' + chatTime
								+ '</span>' + '</h4>' + '<p>' + chatContent
								+ '</p>' + '</div>' + '</div>' + '</div>'
								+ '</div>' + '<hr/>'

				);
	}
	//<!-- 한명의 사용자가 채팅을 작성하는 부분 -->
	//<div class="row">
	//<div class="col-lg-12">
	//<!-- media 컴포넌트는 다양한 사진이나 글이 함께 작성할때 사용합니다. -->
	//<div class="media">
	//<a class="pull-left" href="#"> <img
					//class="media-object img-circle" src="./images/yellow-48.png"
					//alt="프로필사진" />
	//</a>
	//<div class="media-body">
	//<h4 class="media-heading">
	//이름 <span class="small pull-right">오후 05:16</span>
	//</h4>
	//</div>
	//<p>안녕하세요 이쪽은 메세지 구간 입니다.</p>
	//</div>
	//</div>
	//</div>
	//<hr/>
//<!-- 한명의 사용자가 채팅을 작성하는 부분 끝 -->
</script>

</head>
<body>
	<div class="container">
		<div class="container bootstrap snippet">
			<div class="row">
				<div class="col-xs-12">
					<div class="portlet portlet-default">
						<div class="portlet-heading">
							<div class="portlet-title">
								<h4>
									<i class="fa fa-circle text-green"></i>실시간 채팅방
								</h4>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="chat" class="panel-collapse collapse in">
							<!-- overflow-y : 글이 늘어날때마다 y축으로 스크롤바가 생기면서 늘어 납니다. -->
							<div id="chatList" class="portlet-body chat-widget"
								style="overflow-y: auto; width: auto; height: 300px">
								<div class="row">
									<div class="col-lg-12">
										<p class="text-center text-muted small"><%=new SimpleDateFormat("yyyy-MM-dd-E요일", Locale.KOREAN).format(new Date())%></p>
									</div>
								</div>
							</div>
							<div class="portlet-footer">
								<div class="row">
									<div class="form-group col-xs-4">
										<input type="text" id="chatName" class="form-control"
											placeholder="이름" maxlength="8" style="height: 40px;" />
									</div>
								</div>
								<div class="row" style="height: 90px;">
									<div class="form-group col-xs-10">
										<textarea id="chatContent" class="form-control"
											placeholder="메시지를 입력하세요." maxlength="100"
											style="height: 80px;"></textarea>
									</div>
									<!-- 메시지 전송 버튼 -->
									<div class="form-group col-xs-2">
										<button type="button" class="btn btn-default-pull-right"
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
		<div class="alert alert-success" id="successMessage"
			style="display: none;">
			<strong>메시지 전송에 성공했습니다.</strong>
		</div>
		<div class="alert alert-danger" id="dangerMessage"
			style="display: none;">
			<strong>이름과 내용을 모두 입력해주세요.</strong>
		</div>
		<div class="alert alert-waening" id="warningMessage"
			style="display: none;">
			<strong>데이터베이스 오류가 발생했습니다.</strong>
		</div>
	</div>
	<button type="button" class="btn btn-default pull-right"
		onclick="chatListFunction('today');">추가(새로고침)</button>
</body>
</html>
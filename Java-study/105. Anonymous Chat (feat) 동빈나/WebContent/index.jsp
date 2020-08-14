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
							<div class="portlet-body chat-widget"
								style="overflow-y: auto; width: auto; height: 300px">
								<div class="row">
									<div class="col-lg-12">
										<p class="text-center text-muted small">2020년 8월 14일</p>
									</div>
								</div>
								<!-- 한명의 사용자가 채팅을 작성하는 부분 -->
								<div class="row">
									<div class="col-lg-12">
										<!-- media 컴포넌트는 다양한 사진이나 글이 함께 작성할때 사용합니다. -->
										<div class="media">
											<a class="pull-left" href="#"> <img
												class="media-object img-circle" src="./images/yellow-48.png"
												alt="프로필사진" />
											</a>
											<div class="media-body">
												<h4 class="media-heading">
													이름 <span class="small pull-right">오후 05:16</span>
												</h4>
											</div>
											<p>안녕하세요 이쪽은 메세지 구간 입니다.</p>
										</div>
									</div>
								</div>
								<!-- 한명의 사용자가 채팅을 작성하는 부분 끝 -->
								<hr />
								<!-- 한명의 사용자가 채팅을 작성하는 부분 -->
								<div class="row">
									<div class="col-lg-12">
										<!-- media 컴포넌트는 다양한 사진이나 글이 함께 작성할때 사용합니다. -->
										<div class="media">
											<a class="pull-left" href="#"> <img
												class="media-object img-circle" src="./images/yellow-48.png"
												alt="프로필사진" />
											</a>
											<div class="media-body">
												<h4 class="media-heading">
													다른놈 <span class="small pull-right">오후 05:18</span>
												</h4>
											</div>
											<p>안녕하세요 이쪽은 다른놈 메세지 구간 입니다.</p>
										</div>
									</div>
								</div>
								<!-- 한명의 사용자가 채팅을 작성하는 부분 끝 -->
							</div>
							<div class="portlet-footer">
								<div class="row">
									<div class="form-group col-xs-4">
										<input type="text" id="chatName" class="form-control"
											placeholder="이름" maxlength="20" style="height: 40px;" />
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
</body>
</html>
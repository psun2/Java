<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<!-- 반응형 웹 meta 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="./css/bootstrap.css">
<!-- 커스텀 css -->
<link rel="stylesheet" href="./css/custom.css">
<title>JSP AJAX 회원가입</title>
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 부트스트랩 js -->
<script src="./js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<!-- servlet 즉 web.xml에 있는 url-pattenr 과  action 이 동일해야 합니다. -->
		<form method="post" action="./userRegister">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3">
							<h4>회원등록 양식</h4>
						</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input class="form-control" type="text" id="userID"
							name="userID" maxlength="20" /></td>
						<td style="width: 110px;"><button class="btn btn-primary"
								onclick="registerCheckFuction();" type="button">중복체크</button></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호</h5></td>
						<td colspan="2"><input class="form-control" type="password"
							id="userPassword1" name="userPassword1" maxlength="20" /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
						<td colspan="2"><input class="form-control" type="password"
							id="userPassword2" name="userPassword2" maxlength="20" /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="userName" name="userName" maxlength="20" /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="userAge" name="userAge" maxlength="20" /></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>성별</h5></td>
						<td colspan="2">
							<div class="form-group"
								style="text-align: center; margin: 0 auto;">
								<div class="btn btn-group" data-toggle="buttons">

									<!-- 남자와 여자의 active 와 checked 에 주목 -->
									<label class="btn btn-primary active"> <input
										type="radio" name="userGender" autocomplete="off" value="남자"
										checked>남자
									</label> <label class="btn btn-primary"> <input type="radio"
										name="userGender" autocomplete="off" value="여자">여자
									</label>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td colspan="2"><input class="form-control" type="email"
							id="userEmail" name="userEmail" maxlength="20" /></td>
					</tr>
					<tr>
						<!-- pull-right : 가장 오른쪽으로 고정 -->
						<td style="text-align: left;" colspan="3"><input
							class="btn btn-primary pull-right" type="submit" value="회원가입" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<%
		String messageType = null;
	if (session.getAttribute("messageType") != null) {
		messageType = (String) session.getAttribute("messageType");
	}

	String messageContent = null;
	if (session.getAttribute("messageContent") != null) {
		messageContent = (String) session.getAttribute("messageContent");
	}

	System.out.println(messageType);
	System.out.println(messageContent);

	if (messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div
					class="modal-content
					<%if (messageType.equals("오류 메시지"))
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
		$('#messageModal').modal("show");
	</script>
	<%
		session.removeAttribute("messageType");
	session.removeAttribute("messageContent");
	}
	%>
</body>
</html>
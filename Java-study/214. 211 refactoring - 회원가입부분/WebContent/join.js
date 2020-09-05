console.log('join.js 진입');

document
		.write("<script src='https://code.jquery.com/jquery-3.1.1.min.js'></script>");

// 아이디 중복체크
function registerCheckFunction() {
	console.log('registerCheckFunction 실행');
	var userID = $("#userID").val(); // input 의 value 를 가져옵니다.

	// ajax 를 이용한 비동기 통신
	$.ajax({
		type : 'POST',
		url : './UserRegisterCheckServlet',
		// 속성이름 : 값(var userID)
		data : {
			userID : userID
		},
		// 성공시 success 실행
		success : function(result) {
			if (result == 1) { // 중복 확인결과 사용 할 수 있을때
				$('#checkMessage').html('사용할 수 있는 아이디입니다.');
				// .attr() : 속성 부여
				$('#checkType').attr('class', 'modal-content panel-success');
			} else { // 중복 된 아이디 인 경우
				$('#checkMessage').html('사용할 수 없는 아이디입니다.');
				// .attr() : 속성 부여
				$('#checkType').attr('class', 'modal-content panel-warning');
			}
			$('#checkModal').modal("show"); // 모달이 보여지는 역할을 합니다.
		}
	});
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

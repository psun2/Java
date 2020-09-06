/**
 * 
 */

// 아이디 중복체크
const registerCheckFunction = () => {
	console.log('registerCheckFunction 진입');
	const userID = $('#userID').val();
	$.ajax({
		type:'POST',
		url:'./memberRegisterCheck',
		data:{userID: encodeURIComponent(userID)},
		success: (result) => {
			console.log("result : ", result);
			if(parseInt(result) === 1) {
				$('#checkMessage').html('사용할 수 있는 아이디 입니다.');
				$('#checkType').attr('class', 'modal-content panel-success');
			}  else if (parseInt(result) === 0) {
				$('#checkMessage').html('이미 존재하는 회원 입니다.');
				$('#checkType').attr('class', 'modal-content panel-warning');
			} else {
				$('#checkMessage').html('데이터베이스 오류 입니다.');
				$('#checkType').attr('class', 'modal-content panel-warning');
			}
			$('#checkModal').modal('show');
		}
	});
}

// 비밀번호 일치 확인 함수
const passwordCheckFunction = () => {
	console.log('passwordCheckFunction 진입');
	const userPassword1 = $('#userPassword1').val();
	const userPassword2 = $('#userPassword2').val();
	
	if(userPassword1 !== userPassword2)
		$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
	 else 
		$('#passwordCheckMessage').html('');
	
}


/**
 * 
 */

// 친구 찾기 기능 구현
const findFunction = () => {
	const userID = $('#findID').val();
	console.log(userID);
	
	$.ajax({
		url:'./MemberRegisterCheckServlet',
		type:'POST',
		data:{userID : userID},
		success: (result) => {
			console.log(result);
			if(Number(result) === 0) {
				$('#checkType').attr('class', 'modal-content panel-success');
				$('#checkMessage').html('친구 찾기에 성공했습니다.');
				getFriend(userID);
			} else {				
				$('#checkType').attr('class', 'modal-content panel-warning');
				$('#checkMessage').html('친구 찾기에 실패했습니다.');
				failFriend();
			}
		}
	});
	$('#checkModal').modal('show');
}

const getFriend = (findID) => {
	$('#friendResult').html(
	'<thead>' +
	'<tr>' +
	'<th><h4>검색 결과</h4></th>' +
	'</tr>' +
	'</thead>' +		
	'<tbody>' +
	'<tr>' +
	'<td style="text-align:center;"><h3>'+ 
	findID +
	'<h3>' +
	'<a href="chat.jsp?toID=' + 
	encodeURIComponent(findID) + 
	'" class="btn btn-primary pull-right">' +
	'메시지 보내기</a>' + 
	'</td>' +
	'</tr>' +
	'</tbody>'		
	);
}

const failFriend = () => {
	$('#friendResult').html('');
}
/**
 * 
 */

// 친구 찾기 기능 구현
const findFunction = () => {
	const userID = $('#findID').val();
	console.log(userID);
	
	$.ajax({
		url:'./MemberFindServlet',
		type:'POST',
		data:{userID : userID},
		success: (result) => {
			if(parseInt(result) === -1) {
				$('#checkType').attr('class', 'modal-content panel-warning');
				$('#checkMessage').html('친구 찾기에 실패했습니다.');
				failFriend();
			} else {
				console.log(result);
				
				let data = JSON.parse(result);
				let profile = data.userProfile;
				
				$('#checkType').attr('class', 'modal-content panel-success');
				$('#checkMessage').html('친구 찾기에 성공했습니다.');
				getFriend(userID, profile);				
			}
		}
		
	});
	$('#checkModal').modal('show');
}

const getFriend = (findID, profile) => {
	$('#friendResult').html(
	'<thead>' +
	'<tr>' +
	'<th><h4>검색 결과</h4></th>' +
	'</tr>' +
	'</thead>' +		
	'<tbody>' +
	'<tr>' +
	'<td style="text-align:center;">' +
	'<img class="media-object img-circle" style="max-width: 300px; height:300px; margin: 0 auto;" alt="프로필사진" src="'+decodeURIComponent(profile) +'"/>' + 
	'<h3>'+ 
	findID +
	'<h3>' +
	'<a href="chat.jsp?toID=' + 
	decodeURIComponent(findID) + 
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
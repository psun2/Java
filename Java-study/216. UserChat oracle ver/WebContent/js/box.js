/**
 * 
 */

let userID = null;

// 읽지 않은 메시지가 있는지 서버로 부터 받은 반환 값을 가져오는 통신을 하는 함수
const getUnread = () => {
	
	$.ajax({
		url:'./chatUnread',
		type:'POST',
		data:{userID: userID},
		success: (result) => {
		if(Number(result) >= 1)
			showUnread(result);
		else			
			showUnread('');
		}	
	});
}

// getUnread 함수를 일정시간 동안 계속 반복하는 함수
const getInfiniteUnread = () => {
	setInterval(getUnread, 1000);
}

const showUnread = (result) => {
	$('#unread').html(result);
}

const setUserID = (ID) => {
	userID = ID;
}
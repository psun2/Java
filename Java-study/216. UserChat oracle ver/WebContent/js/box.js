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
	setInterval(getUnread, 4000);
}

const showUnread = (result) => {
	$('#unread').html(result);
}

// 읽지 않은 메시지중 최신의 메시지만 데이터베이스에서 가져옵니다.
 function chatBoxFunction() {

 $.ajax({
 url:'./chatBox',
 type:'POST',
 data:{userID: encodeURIComponent(userID)},
 success: function(data) {
 if(data === '') return;
 $('#boxTable').html('');
 var parsed = JSON.parse(data);
 var result = parsed.result
 for(var i =0; i < result.length; i++) {
 if(result[i][0].fromID === userID)
 result[i][0].fromID = result[i][1].toID;
 else
 result[i][1].toID = result[i][0].fromID;
				
 addBox(result[i][0].fromID, result[i][1].toID, result[i][2].chatContent,
 result[i][3].chatTime)
 }
 }
 });
 }

// function chatBoxFunction() {
// console.log(JSON.stringify({ userID: encodeURIComponent(userID) }));
// console.log({ userID: encodeURIComponent(userID) });
//	
// fetch('./chatBox', {
// method: "POST",
// headers: {
// // "Content-Type": "application/json; charset=utf-8",
// // "Content-Type": "application/x-www-form-urlencoded",
// // "X-Requested-With": "XMLHttpRequest"
// },
// credentials: "same-origin",
// body: JSON.stringify({
// userID: encodeURIComponent(userID) ,
// toID: 박성언,
// }),
// }).then(response => {
// console.log(response);
// return response.json();
// }).then(json => {
// console.log('json : ', json);
// });
// }

// 읽지 않은 데이터를 가져온뒤 document 에 뿌려주는 함수
function addBox(lastID, toID, chatContent, chatTime) {
	$('#boxTable').append(
			'<tr onclick="location.href=\'chat.jsp?toID=' + encodeURIComponent(toID) + '\'">' + 
			'<td style="width:150px;"><h5>'+lastID+'</h5></td>' +
			'<td>' + 
			'<h5>' + 
			chatContent +
			'</h5>' +
			'<div class="pull-right">' + chatTime + '</div>' +
			'</td>' +
			'</tr>'
	);
}


function getInfiniteBox() {
	setInterval(function(){
		chatBoxFunction();
	}, 3000);
}

const setUserID = (ID) => {
	userID = ID;
}
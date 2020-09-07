/**
 * 
 */

// 메시지 전송 chat.jsp

// 메시지 전송 여부에 따라 경고창을 띄워주는 함수
const autoClosingAlert = (selector, delay) => {
	const alert = $(selector).alert();
	alert.show();
	window.setTimeout(() => alert.hide(), delay);
}

// servlet 파일과 연동하여 서버로 요청과 응답을 받는 함수
const submitFunction = (fromID, toID) => {
	console.log("submitFunction 진입");
	console.log("fromID : ", fromID);
	console.log("toID : ", toID);
	const chatContent = $('#chatContent').val();
	
	$.ajax({
		url:'./chatSubmit',
		type:'POST',
		data:{
			fromID: encodeURIComponent(fromID),
			toID: encodeURIComponent(toID),
			chatContent: encodeURIComponent(chatContent)
		},
		success: (result) => {
			if(parseInt(result) === 1) {
				autoClosingAlert('#successMessage', 2000);
			} else if(parseInt(result) === 0) {
				autoClosingAlert('#dangerMessage', 2000);
			} else {
				autoClosingAlert('#warningMessage', 2000);
			}
		}
	});
	$('#chatContent').val('');
}

let lastID = 0;
let fromID = null;
let toID = null;


const chatListFunction = (type) => {
	
	console.log('chatListFunction 진입');
	
	console.log('fromID : ', fromID);
	console.log('toID : ', toID);
	
	$.ajax({
		url:'./chatList',
		type:'POST',
		data: {
			fromID: encodeURIComponent(fromID),
			toID: encodeURIComponent(toID),
			listType: encodeURIComponent(type)
		},
		success: (data) => {
			// data : JSON (servlet 에서 넘겨주는 JSON 데이터가 들어오게 됩니다.)
			console.log('data : ', data);
			if(data === '') return;
			const parsed = JSON.parse(data);
			const result = parsed.result;
			
			
			for(let i = 0; i < result.length; i++) {
				if(result[i][0].fromID === fromID)
					result[i][0].fromID = result[i][0].fromID + ' (나)';
				addChat(result[i][0].fromID, result[i][2].chatContent, result[i][3].time);
				}
			
			console.log("lastID : ", lastID);
			lastID = Number(parsed.last);
			console.log("lastID : ", lastID);
			
		}
	});
	
}

const addChat = (chatName, chatContent, chatTime) => {
	$('#chatList').append(
	'<div class="row">' + 
	'<div class="col-lg-12">' + 
	'<div class="media">' + 
	'<a class="pull-left" href="#">' + 
	'<img class="media-object img-circle" src="./images/yellow-48.png" alt="프로필사진" style="width:30px; height:30px;" />' +
	'</a>' + 
	
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
	'<hr />'
	);
	$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
}

const getInfiniteChat = () => {
	setInterval(() => {
		chatListFunction(lastID);
	}, 1000);
}

const init = (paramFromID, paramToID) => {

	fromID = paramFromID;
	toID = paramToID;

	console.log('fromID : ', fromID);
	console.log('toID : ', toID);
	
	$(document).ready(() => {
		chatListFunction('ten');
		getInfiniteChat();
	});
}
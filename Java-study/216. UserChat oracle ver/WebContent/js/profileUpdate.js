/**
 * 
 */

// 실제로 파일을 선택해서 업로드 할 수 있도록 합니다.
$(document).on('click', '.browse', function() {
	let file = $(this).parent().parent().parent().find('.file');
	file.trigger('click');
	// eventType JavaScript 이벤트 타입을 표현하는 문자열, click 또는 submit 같은.
	// extraParameters 이벤트 핸들러에 전달할 추가 파라미터.
	// .trigger( event )
	// 요소들이 숨김 상태이기 때문에 tigger 을 사용 하여 가짜 버튼을 눌렀지만,
	// 실제로는 해당 target 이 클릭 되도록 합니다.
});

// 파일을 바꾸어 줍니다.
$(document).on(
		'change',
		'.file',
		function() {
			// console.log($(this).val()); // C:\fakepath\2020-09-08.png

			$(this).parent().find('.form-control').val(
					$(this).val().replace(/c:\\fakepath\\/i, ''));
			// 현재 눈에 보이는 input은 disabled 상태이기 때문에
			// .file 클래스의 value 가 서버 쪽으로 가게 되는데,
			// 디자은을 위해 .file 클래스를 가진 input 은 visibility: hidden이 되어있는 상태 입니다.
			
			// 실제 파일 명만 추출 합니다.
			// console.log($(this).parent().find('.form-control').val()); // 2020-09-08.png

		});
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
	<h2>글작성</h2>
	<form id="frm" action="writeOk.jsp" method="post">
		작성자: 
		<input type="text" name="name" />
		제목: 
		<input type="text" name="subject" />
		내용: 
		<textarea name="content"></textarea>
		
		<br /><br />
		
		<input type="submit" value="등록" />
	</form>
	<input type="button" value="목록 으로"/>
	
	<script>
	const form = document.getElementById('frm');
	
	function chkSubmit(e) {
		
		e.preventDefault();
		
		var name = form.name.value.trim();
		var subject = form.subject.value.trim();
		
		if(name == '') {
			alert('작성자 란은 반드시 입력해야 합니다.');
			form.name.focus();
			return;
		}

		if(subject == '') {
			alert('제목 반드시 입력해야 합니다.');
			form.subject.focus();			
			return;
		}
		
		form.submit();
		
	}
	
	const init = () => {
		form.addEventListener('submit', chkSubmit);
	}
	init();
</script>
</body>
</html>
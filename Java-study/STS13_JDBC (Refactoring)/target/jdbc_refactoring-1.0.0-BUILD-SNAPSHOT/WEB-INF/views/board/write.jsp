<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<script>
	function chkSubmit() {
		frm = document.forms['frm'];
		
		var name = frm['name'].value.trim();
		var subject = frm['subject'].value.trim();
		
		if(name == '') {
			alert('작성자 란은 반드시 입력해야 합니다.');
			frm['name'].focus();
			return false;
		}

		if(subject == '') {
			alert('제목 반드시 입력해야 합니다.');
			frm['sbjecet'].focus();			
			return false;
		}
		
		return true;
		
	}
</script>
<body>
	<h2>글작성</h2>
	<form name="frm" action="writeOk" method="post" onsubmit="return chkSubmit()">
		작성자: 
		<input type="text" name="name" />
		제목: 
		<input type="text" name="subject" />
		내용: 
		<textarea name="content"></textarea>
		
		<br /><br />
		
		<input type="submit" value="등록" />
	</form>
	<input type="button" value="목록 으로" onclick="location.href='list'"/>
</body>
</html>
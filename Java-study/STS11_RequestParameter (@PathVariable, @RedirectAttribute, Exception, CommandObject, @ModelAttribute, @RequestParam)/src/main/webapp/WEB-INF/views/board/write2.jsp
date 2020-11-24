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
	<form name="frm" action="writeOk2" method="post" onsubmit="return chkSubmit()">
		uid:
		<input type="text" name="uid" value="234" /> <br />
		작성자: 
		<input type="text" name="name" value="김휘진" /> <br />
		제목: 
		<input type="text" name="subject" value="세븐일레븐" /> <br />
		내용: 
		<textarea name="content">마있쪙 ~</textarea>
		
		<br /><br />
		
		<input type="submit" value="등록" />
	</form>
	<input type="button" value="목록 으로"/>
</body>
</html>
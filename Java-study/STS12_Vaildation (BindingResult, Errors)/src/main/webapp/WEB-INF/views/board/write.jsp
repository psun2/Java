<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<style>
span { color: red;}
</style>
</head>
<body>
	<form action="writeOk">
		uid(<span>숫자</span>): 
		<input type="text" name="uid"> <br />
		작성자(<span>*</span>): 
		<input type="text" name="name"> <br />
		<input type="submit" value="등록"> <br />
	</form>
</body>
</html>






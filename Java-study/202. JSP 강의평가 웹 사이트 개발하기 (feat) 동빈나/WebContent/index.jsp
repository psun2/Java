<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>우리의 첫 번째 페이지</title>
</head>
<body>
	hello World!
	// post 방식은 외부에 데이타가 노출되지 않는 방식을 이야기 합니다. (보안성이 뛰어남)
	<form action = "./userJoinAction.jsp" method = "post">
	<input type = "text" name = "userID">
	<input type = "password" name = "userPassword">
	<input type = "submit" value = "회원가입">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>225. File Up and Down Load</title>
</head>
<body>
	<form action="uploadAction.jsp" method="post" enctype="multipart/form-data">
		파일 : <input type="file" name="file1"/>
		<br />
		파일 : <input type="file" name="file2"/>
		<br />
		파일 : <input type="file" name="file3"/>
		<br />
		<input type="submit" value="업로드"/>
	</form>
	<a href="./fileDownload.jsp">파일 다운로드 페이지</a>
	<br/>
	<a href="./fileDownload(my).jsp">My 파일 다운로드 페이지</a>
</body>
</html>
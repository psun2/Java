<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>welcome</title>
</head>
<body>
	<div>
		안녕 하세요 !!! ${name }
	</div>
	<!-- WEB-INF 는 안보이는 폴더로서 접근 자체가 불가능 합니다. -->
	<img src="./WEB-INF/views/img/헤카림.jpg" alt="profile"/><br />

	<img src="<%=request.getContextPath() %>/resources/img/헤카림.jpg" alt="profile"/><br />	
	
	<img src="${pageContext.request.contextPath }/resources/img/리그오브레전드.jpg" alt="profile"/><br />	

	<img src="./resources/img/zed.jpg" alt="profile"/><br />	

	<hr />

	<!-- myRes 폴더 나오지 않습니다. -->
	<!-- servlet-context.xml 에서  -->
	<!-- resources 태그를 통하여 등록하여 -->
	<!-- dispatcher servlet의 관여를 하지 않게 합니다. -->
	<img src="<%=request.getContextPath() %>/myRes/img/헤카림.jpg" alt="profile"/><br />	
	
	<img src="${pageContext.request.contextPath }/myRes/img/리그오브레전드.jpg" alt="profile"/><br />	

	<img src="./myRes/img/zed.jpg" alt="profile"/><br />	
</body>
</html>
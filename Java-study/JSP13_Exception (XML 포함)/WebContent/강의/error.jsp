<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%response.setStatus(200); %> 
<!-- 
200 을 설정해주는 이유는 브라우저마다 500번대를 받았을때 처리 방법이 다르기 때문입니다. 
그래서 일단 에러메시지를 확인하기 위하여, 200번대를 설정해준뒤 에러를 확인 합니다.
-->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>에러 안내</title>
</head>
<body>
에러가 발생했습니다...<br />
<!-- 
페이지가 바뀌지 않은채 error 페이지가 나타납니다.
이 결과로 이 페이지는 forward 된 페이지 입니다.
-->

<%--  

<jsp:forward page=""></jsp:forward> 와 같습니다.  

--%>

예외 타입은: <%=exception.getClass().getName() %><br />
예외 메시지는 <%=exception.getMessage() %><br />

<!-- 
그냥을 사용 불가
페이지 지사자를 통해 isErrorPage="true" 를 해줘야 사용 가능합니다.
-->
</body>
</html>
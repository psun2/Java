<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	int age = -1;
	
	try {		
		age = Integer.parseInt(request.getParameter("age"));
	} catch(Exception e) {
%>
	<script>
		alert('parsing error');
		history.back();
	</script>
<%
	}
	
	if(age == -1) {
%>
	<script>
		alert('parsing error');
		history.back();
	</script>
<%		
	}
%>

당신은 <%=age %>세 입니다.<br />
당신은 성인 이므로, 사이트 이용이 가능합니다.<br />
<a href="age_input.jsp">처음으로</a>

</body>
</html>
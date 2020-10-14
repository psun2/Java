<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		String data1 = null, data2 = null, name = null, 
				id = null, pw = null, gender = null, 
				local = null, memo = null;
		String[] hobbys = null;
		
		if(request.getParameter("data1") != null)
			data1 = request.getParameter("data1");
		if(request.getParameter("data2") != null)
			data2 = request.getParameter("data2");
		if(request.getParameter("name") != null)
			name = request.getParameter("name");
		if(request.getParameter("id") != null)
			id = request.getParameter("id");
		if(request.getParameter("pw") != null)
			pw = request.getParameter("pw");
		if(request.getParameter("gender") != null)
			gender = request.getParameter("gender");
		if(request.getParameter("local") != null)
			local = request.getParameter("local");
		if(request.getParameter("memo") != null)
			memo = request.getParameter("memo");
		if(request.getParameter("hobby") != null)
			hobbys = request.getParameterValues("hobby");
		
		if(data1 == null || data1.equals("") || 
				data2 == null || data2.equals("") ||
				name == null || name.equals("") ||
				id == null || id.equals("") ||
				pw == null || pw.equals("") || 
				gender == null || gender.equals("") ||
				local == null || local.equals("") ||
				memo == null || memo.equals("") || 
				hobbys == null) {
	%>
		<script>
			alert("모든 항목을 작성해주세요.");
			history.back();
		</script>
	<%
		}
	%>
	
	<div>
		Hiddeen: <%=data1 %>, <%=data2 %>
	</div>
	<div>
		이름: <%=name %>
	</div>
	<div>
		아이디: <%=id %>
	</div>
	<div>
		비밀번호: <%=pw %>
	</div>
	<div>
		성별: <%=gender %>
	</div>
	<div>
		지역: <%=local %>
	</div>
	<div>
		취미: <%=Arrays.toString(hobbys) %>
	</div>
	<div>
		메모: <%=memo %>
	</div>
</body>
</html>
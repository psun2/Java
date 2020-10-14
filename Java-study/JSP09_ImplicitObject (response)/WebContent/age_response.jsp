<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");

	int age = -1;

	if(request.getParameter("age") != null){
		try {
		age = Integer.parseInt(request.getParameter("age"));					
		} catch(Exception e) {
			e.printStackTrace();
%>
	<script>
		alert('나이가 올바르지 않습니다.');
		history.back();
	</script>
<%
		}
		
		if(age >= 20) response.sendRedirect("age_adult.jsp?age=" + age);
		else response.sendRedirect("age_underage.jsp?age=" + age);
	} else {
%>
	<script>
		alert('나이가 올바르지 않습니다.');
		history.back();
	</script>
<%
	}

%>
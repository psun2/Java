<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// params 받아오는 작업
	
	request.setCharacterEncoding("UTF-8");
	
	String name = null;
	String ageText = null;
	String idText = null;
	String gender = null;
	
	int age = -1;
	int id = -1;
	
	if(request.getParameter("name") != null)
		name = request.getParameter("name");
	if(request.getParameter("age") != null)
		ageText = request.getParameter("age");
	if(request.getParameter("id") != null)
		idText = request.getParameter("id");
	if(request.getParameter("gender") != null)
		gender = request.getParameter("gender");
	
	if(name == null || name.equals("") || 
			ageText == null || ageText.equals("") ||
					idText == null || idText.equals("") ||
							gender == null || gender.equals("")) {
		out.println("<script>");
		out.println("alert('입력이 안된 항목이 있습니다.');");
		out.println("history.back();");
		out.println("</script>");
		return;
	}
	
	try {age = Integer.parseInt(ageText);} catch(Exception e) {
		out.println("<script>");
		out.println("alert('나이가 유효하지 않은 값 입니다.');");
		out.println("history.back();");
		out.println("</script>");
		return;	
	}

	try {id = Integer.parseInt(idText);} catch(Exception e) {
		out.println("<script>");
		out.println("alert('아이디가 유효하지 않은 값 입니다.');");
		out.println("history.back();");
		out.println("</script>");
		return;	
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 넘오온 parameter 의 이름과  property 즉 setter 의 이름이 같다면
		// 자동으로 값이 넘어가 객체가 생성되는 구조 입니다.
	%>
	<jsp:useBean id="person" class="beans.Person" scope="page">
		<jsp:setProperty property="name" name="person" />
		<jsp:setProperty property="age" name="person" />
		<jsp:setProperty property="id" name="person" />
		<jsp:setProperty property="gender" name="person" />
	</jsp:useBean>
	
	
	<h3>person</h3>
	이름: <jsp:getProperty property="name" name="person" /><br />
	나이: <jsp:getProperty property="age" name="person" /><br />
	아이디: <jsp:getProperty property="id" name="person" /><br />
	성별: <jsp:getProperty property="gender" name="person" /><br />
	
</body>
</html>
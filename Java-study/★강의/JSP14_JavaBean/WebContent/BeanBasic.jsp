<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="person1" class="com.lec.beans.Person" scope="page"/>
<%-- 다음과 같은 일 수행
	com.lec.beans.Person person1 = new com.lec.beans.Person(); // 기본생성자로 객체 생성
	request.setAttribute("person1", person1);  // <-- 좀 더 정확히는 이것도 수행
 --%>

<!-- 기본생성자 생성후 setPropery 사용 -->
<jsp:useBean id="person2" class="com.lec.beans.Person" scope="page">
	<jsp:setProperty name="person2" property="name" value="명기범"/>
	<jsp:setProperty name="person2" property="age" value="26" />
	<jsp:setProperty name="person2" property="id" value="998877" />
	<jsp:setProperty name="person2" property="gender" value="남" />
</jsp:useBean>

<jsp:useBean id="person3" class="com.lec.beans.Person" scope="page"/>
<jsp:setProperty name="person3" property="name"/>
<jsp:setProperty name="person3" property="age"/>
<jsp:setProperty name="person3" property="id"/>
<jsp:setProperty name="person3" property="gender"/>
<%--
	person3.setGender(request.getParameter("gender"))
 --%>
    
<%-- parameter 를 일괄로 받아올수 있다! --%>
<jsp:useBean id="person4" class="com.lec.beans.Person" scope="page"/>
<jsp:setProperty name="person4" property="*"/>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>자바 빈(bean)</title>
</head>
<body>
<%= person1 %>
<%@ page import="com.lec.beans.Person" %>
<%
	//Person person1;
%>
<hr>
	<jsp:setProperty name="person1" property="name" value="고길동"/>
	<jsp:setProperty name="person1" property="age" value="24"/>
	<jsp:setProperty name="person1" property="id" value="1515"/>
	<jsp:setProperty name="person1" property="gender" value="남"/>
	<%-- 다음과 같은 일을 수행한다.
		person1.setName("고길동");
		person1.setAge(24);
		person1.setId(1515);
		person1.setGender("남");
	 --%>
	
	
	<h3>person1</h3>
	이름: <jsp:getProperty name="person1" property="name"/><br>
	나이: <jsp:getProperty name="person1" property="age"/> <br>
	아이디: <jsp:getProperty name="person1" property="id"/> <br>
	성별: <jsp:getProperty name="person1" property="gender"/> <br>
	<%-- 다음과 같은 코드를 수행한다
		<%= person1.getName() %>
		<%= person1.getAge() %>
		<%= person1.getId() %>
		<%= person1.getGender() %>
	 --%>
	
	
	<h3>person2</h3>
	이름: <jsp:getProperty name="person2" property="name"/><br>
	나이: <jsp:getProperty name="person2" property="age"/> <br>
	아이디: <jsp:getProperty name="person2" property="id"/> <br>
	성별: <jsp:getProperty name="person2" property="gender"/> <br>
	
	
	<h3>person3</h3>
	이름: <jsp:getProperty name="person3" property="name"/><br>
	나이: <jsp:getProperty name="person3" property="age"/> <br>
	아이디: <jsp:getProperty name="person3" property="id"/> <br>
	성별: <jsp:getProperty name="person3" property="gender"/> <br>

	<h3>person4</h3>
	이름: <jsp:getProperty name="person4" property="name"/><br>
	나이: <jsp:getProperty name="person4" property="age"/> <br>
	아이디: <jsp:getProperty name="person4" property="id"/> <br>
	성별: <jsp:getProperty name="person4" property="gender"/> <br>



</body>
</html>












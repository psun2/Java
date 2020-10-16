<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 객체생성 -->
<jsp:useBean 
	id="person1" 
	class="강의.com.lec.beans.Person" 
	scope="page"
	/>
<%-- 다음과 같은 일 수행

// 기본 생성자로 객체 생성
com.lec.beans.Person person1 
	= new com.lec.beans.Person();

request.setAttribute("person1", person1);  // <-- 좀 더 정확히는 이것도 수행

 --%>
<!-- 
jsp:useBean 액션 태그는 객체를 생성합니다.
new Person()
-->

<!-- 기본 생성자 생성후 setProperty 사용 -->
<jsp:useBean id="person2" class="강의.com.lec.beans.Person" scope="page">
	<jsp:setProperty name="person2" property="name" value="십팔" />
	<jsp:setProperty name="person2" property="age" value="777" />
	<jsp:setProperty name="person2" property="id" value="999" />
	<jsp:setProperty name="person2" property="gender" value="fa" />
</jsp:useBean>

<jsp:useBean id="person3" class="강의.com.lec.beans.Person" scope="page" />
<jsp:setProperty name="person3" property="name" />
<jsp:setProperty name="person3" property="age" />
<jsp:setProperty name="person3" property="id" />
<jsp:setProperty name="person3" property="gender" />
<%--
	person3.setName(request.getParameter("name"));
 --%>
 
 
 
<%--paramter 를 일괄로 받아올수 있다! --%>
<jsp:useBean id="person4" class="강의.com.lec.beans.Person" scope="page" />
<jsp:setProperty name="person4" property="*" />
<!-- form 의 name이 일치하는 setter 들을 알아서 초기화 해 줍니다 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>자바(bean)</title>
</head>
<body>

<%=person1 %>
<%@ page import="강의.com.lec.beans.Person" %>
<%
	// Person person1; // error 위에서 bean 으로생성
%>

<hr />

<!-- 객체 내부의 값 설정 -->
<jsp:setProperty name="person1" property="name" value="ㅗ" />
<jsp:setProperty name="person1" property="age" value="18" />
<jsp:setProperty name="person1" property="id" value="1818" />
<jsp:setProperty name="person1" property="gender" value="fuck" />

<%-- 다음과 같은 일을 수행한다.
	person1.setName("ㅗ");
	person1.setName("18");
	person1.setName("1818");
	person1.setName("fuck");
 --%>


<h3>Person1</h3>
<!-- 객체 내부의 값 얻어오기 -->
이름: <jsp:getProperty name="person1" property="name" /><br />
나이: <jsp:getProperty name="person1" property="age" /><br />
아이디: <jsp:getProperty name="person1" property="id" /><br />
성별: <jsp:getProperty name="person1" property="gender" /><br />

<%--다음과 같은 코드를 수행한다
	<%=person.getName() %>
	<%=person.getAge() %>
	<%=person.getId() %>
	<%=person.getGender() %>
 --%>
 
 <h3>Person2</h3>
<!-- 객체 내부의 값 얻어오기 -->
이름: <jsp:getProperty name="person2" property="name" /><br />
나이: <jsp:getProperty name="person2" property="age" /><br />
아이디: <jsp:getProperty name="person2" property="id" /><br />
성별: <jsp:getProperty name="person2" property="gender" /><br />

 <h3>Person3</h3>
<!-- 객체 내부의 값 얻어오기 -->
이름: <jsp:getProperty name="person3" property="name" /><br />
나이: <jsp:getProperty name="person3" property="age" /><br />
아이디: <jsp:getProperty name="person3" property="id" /><br />
성별: <jsp:getProperty name="person3" property="gender" /><br />
 
 <% System.out.println(person3.getAge()); // 47 %>

 <h3>Person4</h3>
<!-- 객체 내부의 값 얻어오기 -->
이름: <jsp:getProperty name="person4" property="name" /><br />
나이: <jsp:getProperty name="person4" property="age" /><br />
아이디: <jsp:getProperty name="person4" property="id" /><br />
성별: <jsp:getProperty name="person4" property="gender" /><br />
 
</body>
</html>
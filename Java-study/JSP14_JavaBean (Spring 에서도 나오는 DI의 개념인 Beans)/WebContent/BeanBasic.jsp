<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Bean Basic</title>
</head>
<body>
<%request.setCharacterEncoding("UTF-8"); %>

<%--
<%@ page import="beans.Person" %>
 --%>
<%
	// 객체 생성
	// new Person()

	// @ 지시자로 import 하여 클래스 명만 적어 주었지만 error
	// import 대신 풀네임을 적도록 합니다.
%>
<%--
<jsp:useBean id="person1" class="Person" scope="page"/>
 --%>
<%
	// Person person1 = new Person(); 객체 생성
%>
<jsp:useBean id="person1" class="beans.Person" scope="page" />


<%
	// setter 을 통해 초기화
	
	// person1.setName("홍길동");
	// peroperty : 해당 setter 의 이름
	// name : 객체이름 
	// value : 값
%>
<jsp:setProperty property="name" name="person1" value="홍길동" />
<jsp:setProperty property="age" name="person1" value="1004" />
<jsp:setProperty property="id" name="person1" value="1988" />
<jsp:setProperty property="gender" name="person1" value="남" />

<%
	// getter 을 통해 해당 값 가져오기
	// person1.getName();

	// person1.getName();
	// peroperty : 해당 getter 의 이름
	// name : 객체이름 
%>

<h3>person1</h3>
이름: <jsp:getProperty property="name" name="person1" /><br />
나이: <jsp:getProperty property="age" name="person1" /><br />
아이디: <jsp:getProperty property="id" name="person1" /><br />
성별: <jsp:getProperty property="gender" name="person1" /><br />
<hr />

--------------------------------------------------------------------------------------

<%
	// 객체의 생성과 초기화를  동시에 진행
%>

<jsp:useBean id="person2" class="beans.Person" scope="page">
	<jsp:setProperty property="name" name="person2" value="Mark" />
	<jsp:setProperty property="age" name="person2" value="789" />
	<jsp:setProperty property="id" name="person2" value="345" />
	<jsp:setProperty property="gender" name="person2" value="여" />
</jsp:useBean>

<h3>person2</h3>
이름: <jsp:getProperty property="name" name="person2" /><br />
나이: <jsp:getProperty property="age" name="person2" /><br />
아이디: <jsp:getProperty property="id" name="person2" /><br />
성별: <jsp:getProperty property="gender" name="person2" /><br />
<hr />

--------------------------------------------------------------------------------------

<%
	// paramter 를 일괄로 받아올수 있다!
	// 유효성 검사 먼저 진행 후 객체를 생성해 
	// 유효성이 통과 되었을때 모두다 초기화 할 수 있는 꿀 조합(?!!)
%>

<jsp:useBean id="person3" class="beans.Person" scope="page" />
<jsp:setProperty property="*" name="person3" />

<h3>person3</h3>
이름: <jsp:getProperty name="person3" property="name" /><br />
나이: <jsp:getProperty property="age" name="person3" /><br />
아이디: <jsp:getProperty property="id" name="person3" /><br />
성별: <jsp:getProperty property="gender" name="person3" /><br />

</body>
</html>
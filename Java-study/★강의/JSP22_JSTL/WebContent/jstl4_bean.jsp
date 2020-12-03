<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lec.beans.*" %>
<%@ page import="java.util.*" %>

<%-- JSTL core 라이브러리 포함 --%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSTL & Bean</title>
</head>
<body>
<%
	Person kim = new Person();
	kim.setName("제임스");
	kim.setAge(200);
%>

<c:set var="dto" value="<%= kim %>"/>

이름: ${dto.name }<br> <%--  dto.getName() 값 --%>
나이: ${dto.age }<br> <%-- dto.getAge() 값 --%>
dto: ${dto }<br>    <%-- dto.toString() 값 --%>

<hr>

<jsp:useBean id="p0" class="com.lec.beans.Person">
	<jsp:setProperty name="p0" property="name" value="고길동"/>
	<jsp:setProperty name="p0" property="age" value="44"/>
</jsp:useBean>

이름: ${p0.name }<br>
나이: ${p0.age }<br>
p0: ${p0 }<br>

<hr>

<%-- 빈(bean) 배열의 경우 --%>
<%
	Person p1 = new Person();
	p1.setName("고질라");
	p1.setAge(100);
	Person p2 = new Person();
	p2.setName("킹기도라");
	p2.setAge(200);
	Person p3 = new Person();
	p3.setName("모스라");
	p3.setAge(80);

	Person [] arr = {p1, p2, p3};
%>

<c:set var="dtoArr" value="<%= arr %>"/>
<c:forEach var="p" items="${dtoArr }">
	${p.name }<br>
	${p.age }<br>
	${p }<br>
</c:forEach>

<hr>
${dtoArr[0].name }<br>
${dtoArr[1].name }<br>
${dtoArr[2].name }<br>
${dtoArr[3].name }<br> <%-- 인덱스 범위 벗어나도 Exception 없이 넘어감 --%>
${dtoArr[4].name }<br>

<hr>

<%-- 특정 bean 이 있는지 체크 : empty --%>
<c:if test="${empty dto }">
	dto 는 없습니다<br>
</c:if>

<c:if test="${not empty dto }">
	dto 가 있습니다<br>
</c:if>

<c:if test="${dto == null }">
	dto 는 업습니다<br>
</c:if>


<c:if test="${dto != null }">
	dto 가 있습니다<br>
</c:if>

<c:choose>
	<c:when test="${empty ghost }">
		ghost 는 없습니다<br>
	</c:when>
	<c:when test="${not empty ghost }">
		ghost 는 있습니다<br>
	</c:when>

</c:choose>

<%-- empty : 없거나 , null --%>
<%
	Person park = null;
%>
<c:set var="v1" value="<%= park %>"/>
park: ${v1 }<br>

<c:if test="${empty v1 }">
	v1 은 empty 입니다<br>
</c:if>

<c:if test="${empty v2 }">
	v2 은 empty 입니다<br>
</c:if>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>








































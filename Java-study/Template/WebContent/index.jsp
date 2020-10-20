<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String title = "인덱스 페이지";
	String encoding = "UTF-8";
%>

<jsp:include page="components/template/templateTop.jsp">
	<jsp:param value="<%=URLEncoder.encode(title, encoding) %>" name="title"/>
</jsp:include>

	<h2>예외 처리 - exception 발생</h2>
	
	<button onclick="location.href='success'">성공</button>
	<br />
	<button onclick="location.href='exception'">에러</button>
	<br />
	
<jsp:include page="components/template/modal.jsp" />

<jsp:include page="components/static/templateBottom.html" />
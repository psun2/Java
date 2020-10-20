<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! // title 설정
	String title = "성공 페이지";
	String encoding = "UTF-8";
%>
<jsp:include page="components/template/templateTop.jsp">
	<jsp:param value="<%=URLEncoder.encode(title, encoding) %>" name="title"/>
</jsp:include>
<%-- css 및 js import
	<link rel="stylesheet" href="myStyle.css">
	<script async defer src="myScript.js"></script>
--%>
<jsp:include page="components/static/templateTop_buttom.html" />
<%-- 구현 부 include 시작 --%>

	<h2>예외 처리 - exception 발생</h2>
	
<%-- 구현 부 include 끝 --%>
	
<%-- 메시지 기능 가장 하단에 고정 --%>
<jsp:include page="components/template/modal.jsp" />

<jsp:include page="components/static/templateBottom.html" />
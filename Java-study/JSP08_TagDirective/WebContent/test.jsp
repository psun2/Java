<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="my.servlet.test.MyServletTest" %>
	
<%
	System.out.println("페이지 로딩 순서 알아 보기");
	System.out.println("tagEx.jsp 페이지 로딩\n");
%>

<%
	GregorianCalendar today = new GregorianCalendar();
%>

<%=String.format("%ty년 %tm월 %td일", today, today, today) + "\t  와 이게 되네?\t" + new MyServletTest().getNumber()%>
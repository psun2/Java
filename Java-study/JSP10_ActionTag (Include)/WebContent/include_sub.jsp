<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<hr />
<h3>현재 페이는 sub page 입니다.</h3>
<p>
	include 된 페이지는 본 페이지의 내용만 가져 갑니다.
</p>
<hr />

<%
	String message2 = "여기는 sub page 입니다.";
%>

<hr />
넘겨준  변수 message2: <%=message2 %><br />
넘겨받은 변수 message1: <%=message1 %><br />
<hr />

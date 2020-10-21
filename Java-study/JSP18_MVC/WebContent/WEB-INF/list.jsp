<%@page import="com.lec.beans.WriteDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% // dao 사용한 트랜잭션
	WriteDTO[] arr = (WriteDTO[]) request.getAttribute("result");
%>   
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>글 목록</title>
	<style>
		table {
			width: 100%;
		}
		table, th, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<hr />
	<h2>리스트</h2>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
<%
	if(arr != null) {
		for(WriteDTO dto : arr) {
%>
	<tr>
		<td><%=dto.getUid() %></td>
		<td><a href="view.park?uid=<%=dto.getUid() %>"><%=dto.getSubject() %></a></td>
		<td><%=dto.getName() %></td>
		<td><%=dto.getContent() %></td>
		<td><%=dto.getViewcnt() %></td>
		<td><%=dto.getRegDate() %></td>
	</tr>
<%	
		}	
	}
%>		
		</tbody>
	</table>
	<br />
	<button onclick="location.href='write.park'">신규등록</button>
</body>
</html>
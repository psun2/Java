<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<style>
table {width: 100%;}
table, th, td {
	border : 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
		<hr>
		<h2>리스트</h2>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		<c:choose>
			<c:when test="${empty result || fn:length(result) == 0 }">
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${result }">
				<tr>
					<td>${dto.uid }</td>
					<td><a href="view?uid=${dto.uid }">${dto.subject }</a></td>
					<td>${dto.name }</td>
					<td>${dto.viewcnt }</td>
					<td>${dto.regDate }</td>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</table>
		<br>
		<button onclick="location.href = 'write'">신규등록</button>
</body>
</html>









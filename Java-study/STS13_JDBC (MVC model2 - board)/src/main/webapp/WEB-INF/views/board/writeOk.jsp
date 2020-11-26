<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
console.log('진입');
</script>

<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert('등록 실패');
			location.back();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert('등록성공, 리스트 출력합니다.');
			location.href = "list.do";
		</script>
	</c:otherwise>
</c:choose>

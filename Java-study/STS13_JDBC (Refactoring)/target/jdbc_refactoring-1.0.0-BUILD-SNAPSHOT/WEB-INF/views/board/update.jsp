<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Function --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty result || fn.length(result) != 0 }">
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글수정 ${result[0].subject }</title>
</head>
<script>
	function chkSubmit() {
		frm = document.forms['frm'];
		
		var subject = frm['subject'].value.trim();
		
		if(subject === '') {
			alert('제목은 반드시 작성해야합니다');
			frm['subject'].focus();
			return false;
		}
		
		return true;
	}
</script>
<body>
	<h2>글 수정</h2>
	<form name="frm" action="updateOk.do" method="post" onsubmit="return chkSubmit();">
		<input type="hidden" name="uid" value="${result[0].uid }" />
		작성자: ${result[0].name }<br /> <%--작성자 이름은 변경 불가 --%>
		<input type="hidden" name="name" value="${result[0].name }" />
		제목: <input type="text" name="subject" value="${result[0].subject }" /><br />
		내용: <textarea name="content">${result[0].content }</textarea>
		<br /><br />
		<input type="submit" value="수정" />
	</form>
	
	<button onclick="history.back();">이전으로</button>
	<button onclick="location.href='list.do';">목록보기</button>
	<br />
</body>
</html>
</c:if>


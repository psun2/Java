<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lec.beans.*" %>
<% // Controller 로부터 결과 데이터 받음
	WriteDTO [] arr = (WriteDTO []) request.getAttribute("list");
	int uid = Integer.parseInt(request.getParameter("uid"));
%>

<%
	if(arr == null || arr.length == 0){
%>
	<script>
		alert("해당 정보가 삭제되거나 없습니다");
		history.back();
	</script>
<%
		return;  //  ★더이상 JSP 프로세싱 하지 않고 종료
	}
%>
<%
	String name = arr[0].getName();
	String subject = arr[0].getSubject();
	String content = arr[0].getContent();
	String regdate = arr[0].getRegDate();
	int viewcnt = arr[0].getViewCnt();
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글수정 <%= subject %></title>
</head>
<script>
function chkSubmit(){
	frm = document.forms['frm'];
	var subject = frm['subject'].value.trim();
	
	if(subject == ""){
		alert("제목은 반드시 작성해야 합니다");
		frm["subject"].focus();
		return false;
	}
	
	return true;
}
</script>
<body>
<h2>수정</h2>
<form name="frm" action="updateOk.do" method="post" onsubmit="return chkSubmit()">
<input type="hidden" name="uid" value="<%= uid %>">
작성자: <%= name %><br>  <%-- 작성자 이름은 변경 불가 --%>
제목:
<input type="text" name="subject" value="<%= subject%>"><br>
내용:<br>
<textarea name="content"><%= content %></textarea>
<br><br>
<input type="submit" value="수정"/>
</form>

<button onclick="history.back()">이전으로</button>
<button onclick="location.href='list.do'">목록보기</button>
<br>

</body>
</html>












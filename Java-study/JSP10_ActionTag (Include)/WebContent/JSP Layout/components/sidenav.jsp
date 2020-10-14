<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int pageNum = 1;

	if(request.getParameter("page") != null)  {
		try {
			pageNum = Integer.parseInt(request.getParameter("page"));				
		} catch(Exception e) {
			e.printStackTrace();
%>
	<script>
		alert('잘못된 접근 입니다.');
		history.back();
	</script>
<%
		}
	}
%>
<!-- left 사이드 메뉴 시작 -->
<div class="col-sm-4">
 	<h3>Side menu</h3>
	<p>Lorem ipsum dolor sit ame.</p>
	<ul class="nav nav-pills flex-column">
		<li class="nav-item"><a class="nav-link <%=pageNum == 1 ? "active" : "" %>" href="<%=pageNum == 1 ? "#" : "index.jsp?page=1"%>">page1</a></li>
		<li class="nav-item"><a class="nav-link <%=pageNum == 2 ? "active" : "" %>" href="<%=pageNum == 2 ? "#" : "index.jsp?page=2"%>">page2</a></li>
		<li class="nav-item"><a class="nav-link <%=pageNum == 3 ? "active" : "" %>" href="<%=pageNum == 3 ? "#" : "index.jsp?page=3"%>">page3</a></li>
		<li class="nav-item"><a class="nav-link <%=pageNum == 4 ? "active" : "" %>" href="<%=pageNum == 4 ? "#" : "index.jsp?page=4"%>">page4</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li>
	</ul>
	<hr class="d-sm-none" />
</div>
<!-- left 사이드 메뉴 끝 -->
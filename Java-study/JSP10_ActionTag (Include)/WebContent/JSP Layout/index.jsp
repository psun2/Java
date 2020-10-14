<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./components/top.jsp" />
<jsp:include page="./components/header.jsp" />
<jsp:include page="./components/nav.jsp" />
<!-- 반응형 본문 시작 -->
<main class="container mt-5">
	<div class="row">
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
		
		String url = "./components/article"+pageNum+".jsp";
	%>
		<jsp:include page="./components/sidenav.jsp">
			<jsp:param value="<%=pageNum %>" name="page"/>
		</jsp:include>
		<jsp:include page="<%=url %>"/>
	</div>
</main>
<!-- 반응형 본문 끝 -->
<jsp:include page="./components/footer.jsp" />
<jsp:include page="./components/bottom.jsp" />
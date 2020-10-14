<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 페이지 이동을 위한 parameter -->
<%
	int menu = 1; //menu parameter 가 없으면 1page가 default 동작
String menu_pram = request.getParameter("menu");

if (menu_pram != null) {
	try {
		menu = Integer.parseInt(menu_pram);
	} catch (Exception e) {
		e.printStackTrace();
%>
<script>
	alert('요청하신 페이지를 찾지 못했습니다.');
	history.back();
</script>
<%
	}
}

if (menu > 4)
menu = 4;
if (menu < 1)
menu = 1;
%>
<jsp:include page="top.jsp"></jsp:include>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
<!-- 반응형 본문 시작 -->
<div class="container" style="margin-top: 30px">
	<div class="row">
		<jsp:include page="left.jsp">
			<jsp:param value="<%=menu%>" name="menu" />
		</jsp:include>
		<%
			String article_page = "article" + menu + ".jsp";
		%>
		<jsp:include page="<%=article_page%>"></jsp:include>
	</div>
</div>
<!-- 반응형 본문 끝 -->
<jsp:include page="footer.jsp"></jsp:include>

<jsp:include page="bottom.jsp"></jsp:include>

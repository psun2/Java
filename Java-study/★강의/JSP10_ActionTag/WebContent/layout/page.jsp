<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int menu = 1;  // menu parameter  가 없으면 1page 가 디폴트 동작
	String menu_param = request.getParameter("menu");
	if(menu_param != null){
		menu = Integer.parseInt(menu_param);
	}
	// 1 <= menu <= 4
	if(menu > 4) menu = 4;
	if(menu < 1) menu = 1;
%>
<jsp:include page="top.jsp"/>
<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
<!-- 반응형 본문 시작 -->
<div class="container" style="margin-top:30px">
  <div class="row">  
<jsp:include page="left.jsp">
	<jsp:param value="<%= menu %>" name="menu"/>
</jsp:include>
<% String article_page = "article" + menu + ".jsp"; %>
<jsp:include page="<%= article_page %>"/>
  </div>
</div>
<!-- 반응형 본문 끝 -->
<jsp:include page="footer.jsp"/>
<jsp:include page="bottom.jsp"/>











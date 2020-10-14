<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 페이지 이동을 위한 parameter -->
<%
	int menu = 1;//menu parameter 가 없으면 1page가 default 동작
String menu_pram = request.getParameter("menu");

if (menu_pram != null)
	menu = Integer.parseInt(menu_pram);

if (menu > 4)
	menu = 4;
if (menu < 1)
	menu = 1;
%>
<!-- left 사이트 메뉴 시작 -->
<div class="col-sm-4">
	<h3>Side menu</h3>
	<p>Lorem ipsum dolor sit ame.</p>
	<ul class="nav nav-pills flex-column">
		<li class="nav-item"><a class="nav-link <%=(menu == 1) ? "active" : ""%>" href="<%=(menu == 1) ? "#" : "page.jsp?menu=1"%>">page1</a></li>
		<li class="nav-item"><a class="nav-link <%=(menu == 2) ? "active" : ""%>" href="<%=(menu == 2) ? "#" : "page.jsp?menu=2"%>">page2</a></li>
		<li class="nav-item"><a class="nav-link <%=(menu == 3) ? "active" : ""%>" href="<%=(menu == 3) ? "#" : "page.jsp?menu=3"%>">page3</a></li>
		<li class="nav-item"><a class="nav-link <%=(menu == 4) ? "active" : ""%>" href="<%=(menu == 4) ? "#" : "page.jsp?menu=4"%>">page4</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li>
	</ul>
	<hr class="d-sm-none">
</div>
<!-- left 사이트 메뉴 끝 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 해당 지역에선 변수 선언이 되징 않은 변수를 찾으므로 빨간 줄이 가지만,
@ 지시자로 include 를 한 지역에서 설정도어 있으므로, 빨간 줄이 그어 지더라도
Error 이 아닙니다.
또한 해당 지역에서 선언한 변수도 @지시자로 이 페이지를 부른 servlet도 
해당 지역내의 변수를 사용 사능 합니다.
 -->

이름:
<%=name%>
나이:
<%=age%>

<%!char gender = 'M';%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie [] cookies = request.getCookies();
	String cookieName = "num";
	
	if(cookies != null){
		for(int i = 0; i < cookies.length; i++){
			if(cookieName.equals(cookies[i].getName())){
				cookies[i].setMaxAge(0); // expiry 를 0 으로 하면!
				response.addCookie(cookies[i]);  // response 되자 마자 삭제.
			}
		}
	}
%>
<script>
alert("<%= cookieName%> 쿠키삭제");
location.href = "cookieList.jsp";
</script>











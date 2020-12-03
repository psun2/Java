<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- http://localhost:8080/sts10_request/board/list 의 현재 경로는  
     http://localhost:8080/sts10_request/board/  다!
          따라서 아래 둘다 동일하게 작동한다. 
--%>    
<a href="${pageContext.request.contextPath }/board/write"/>write</a> 
<a href="${pageContext.request.contextPath }/board/view"/>view</a>
<a href="write"/>write</a> 
<a href="view"/>view</a>
<jsp:include page="/common"/>

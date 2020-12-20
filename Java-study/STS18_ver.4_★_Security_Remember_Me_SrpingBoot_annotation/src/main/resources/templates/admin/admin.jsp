<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta id="_csrf_parameterName" name="_csrf_parameterName" content="${_csrf.parameterName}"/>
    <meta id="_csrf_token" name="_csrf_token" content="${_csrf.token}"/>
    <meta id="_csrf_headerName" name="_csrf_headerName" content="${_csrf.headerName}"/>
    <meta id="messageType" name="messageType" content="${messageType}"/>
    <meta id="messageContent" name="messageContent" content="${messageContent}"/>
    <meta id="user" name="user" content="${pageContext.request.userPrincipal.name}"/>
	<title>member</title>
</head>
<body>
<h1>
	Hello member!  
</h1>

<sec:authorize access="isAuthenticated()">
    <form action="${pageContext.request.contextPath }/logoutProcess" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">LOGOUT</button>
    </form>
</sec:authorize>

<br />

<a href="#" onclick="document.getElementById('logout-form').submit();">Sign out</a>
<form id="logout-form" action='<c:url value='/logoutProcess'/>' method="POST">
   <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
</form>


</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="_csrf_parameter" content="${_csrf.parameterName}">
    <meta name="_csrf_token" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <title>로그아웃</title>
</head>
<body>
<h1>Custom Logout Page</h1>

<form method='post' action="${pageContext.request.contextPath}/customLogout">
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
    <input type='submit' value='로그아웃'><br>
</form>
<script type="text/javascript">
    const parameter = document.querySelector('meta[name=_csrf_parameter]').getAttribute("content");
    const token = document.querySelector('meta[name=_csrf_token]').getAttribute("content");
    const header = document.querySelector('meta[name=_csrf_header]').getAttribute("content");

    console.log(`parameter: `, parameter);
    console.log(`token: `, token);
    console.log(`header: `, header);
</script>
</body>
</html>

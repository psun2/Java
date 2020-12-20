<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="_csrf_parameter" content="${_csrf.parameterName}"/>
    <meta name="_csrf_token" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>로그인페이지</title>
</head>
<body>
<h1>Custom Login Page</h1>
<h2><c:out value="${error}"/></h2>
<h2><c:out value="${logout}"/></h2>

<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="username" value="admin00"/><br/>
    <input type="password" name="password" value="1234"/><br/>
    <input type="checkbox" name="remember-me"/> Remember Me <br/>
    <input type="submit" value="로그인"/><br/>
    <input
            type="hidden"
            name="${_csrf.parameterName }"
            value="${_csrf.token }"
    />
</form>

<script>
    const parameter = document
        .querySelector('meta[name=_csrf_parameter]')
        .getAttribute('content');
    const token = document
        .querySelector('meta[name=_csrf_token]')
        .getAttribute('content');
    const header = document
        .querySelector('meta[name=_csrf_header]')
        .getAttribute('content');

    console.log(`parameter: `, parameter);
    console.log(`token: `, token);
    console.log(`header: `, header);
</script>
</body>
</html>

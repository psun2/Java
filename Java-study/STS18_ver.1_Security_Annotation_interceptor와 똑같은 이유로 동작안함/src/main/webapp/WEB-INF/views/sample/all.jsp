<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="_csrf_parameter" content="${_csrf.parameterName}">
    <meta name="_csrf_token" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <title>all</title>
</head>
<body>
<h1>/sample/all page</h1>
<script>
    const parameter = document.querySelector('meta[name=_csrf_parameter]').getAttribute("content");
    const token = document.querySelector('meta[name=_csrf_token]').getAttribute("content");
    const header = document.querySelector('meta[name=_csrf_header]').getAttribute("content");

    console.log(`parameter: `, parameter);
    console.log(`token: `, token);
    console.log(`header: `, header);
</script>
</body>
</html>
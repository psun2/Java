<%--
  Created by IntelliJ IDEA.
  User: 박성언
  Date: 2020-12-08
  Time: 오후 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta id="_csrf_parameterName" name="_csrf_parameterName" content="${_csrf.parameterName}"/>
    <meta id="_csrf_token" name="_csrf_token" content="${_csrf.token}"/>
    <meta id="_csrf_headerName" name="_csrf_headerName" content="${_csrf.headerName}"/>
    <meta id="messageType" name="messageType" content="${messageType}"/>
    <meta id="messageContent" name="messageContent" content="${messageContent}"/>
    <meta id="user" name="user" content="${pageContext.request.userPrincipal.name}"/>
    <meta id="sessionUser" name="sessionUser" content="${user}"/>
    <title>회원가입</title>
</head>
<body class="container">
<form method="post" action="${pageContext.request.contextPath}/loginProcess">
    <input type="text" id="username" name="username" value="admin"/>
    <input type="password" id="password" name="password" value="0000"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="전송"/>
</form>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: 박성언
  Date: 2020-12-12
  Time: 오후 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label>
        아이디:
        <input type="text" name="username" value="admin"/>
    </label>
    <br/>
    <label>
        비밀번호
        <input type="password" name="password" value="0000">
    </label>
    <br/>
    <label>
        <!-- name => 설정파일의 remember-me-parameter 의 이름과 동일하게 합니다. -->
        <input type="checkbox" name="rememberMe">
        로그인 기억하기
    </label>
    <br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="로그인">
</form>
</body>
</html>

<!--
로그인 양식은 양식 로그인에 사용한 것과 유사합니다 .

<html>
<head></head>

<body>
<h1>Login</h1>

<form name='f' action="login" method='POST'>
<table>
<tr>
<td>User:</td>
<td><input type='text' name='username' value=''></td>
</tr>
<tr>
<td>Password:</td>
<td><input type='password' name='password' /></td>
</tr>
<tr>
<td>Remember Me:</td>
<td><input type="checkbox" name="remember-me" /></td>
</tr>
<tr>
<td><input name="submit" type="submit" value="submit" /></td>
</tr>
</table>
</form>

</body>
</html>
-->

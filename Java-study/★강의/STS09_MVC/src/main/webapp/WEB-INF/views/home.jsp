<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<img src="/sts09_mvc/resources/img/face01.png"/><br>
<img src="<%= request.getContextPath() %>/resources/img/face01.png"/><br>
<img src="${pageContext.request.contextPath }/resources/img/face01.png"/><br>
<img src="resources/img/face01.png"/><br>
<hr>

<img src="${pageContext.request.contextPath }/myRes/img/face01.png"/><br>


</body>
</html>














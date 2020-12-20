<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<h1>${_csrf.parameterName}</h1>
<h1>${_csrf.token}</h1>
<h1>${_csrf.headerName}</h1>

<P>  The time on the server is ${serverTime}. </P>

<form id="form" name="form" method="get">
	<input type="text" name="username" value="park" />
	<input type="password" name="password" value="password" />
	<input type="submit" value="전송" />
</form>
${pageContext.request.contextPath}/testBody
<script type="text/javascript">
	const form = document.getElementById('form');
	
	const handleSubmit = (e) => {
		e.preventDefault();
		
		const formData = new FormData(form);
		
		const data = Object.fromEntries(formData);
		
		console.log('${pageContext.request.contextPath}/testBody');
		
		fetch('${pageContext.request.contextPath}/testBody', {
	        method: 'POST', // *GET, POST, PUT, DELETE, etc.
	        mode: 'cors', // no-cors, cors, *same-origin
	        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
	        credentials: 'same-origin', // include, *same-origin, omit
	        headers: {
	            'Content-Type': 'application/json',
	            // 'Content-Type': 'application/x-www-form-urlencoded',
	            '${_csrf.headerName}' : '${_csrf.token}'
	            
	            // spring Security 사용시 token 값이 없이 보낸다면 에러
	            // (index):37 POST http://localhost:8080/security/testBody 403
	            // handleSubmit @ (index):37
	            // (index):1 Uncaught (in promise) SyntaxError: Unexpected token < in JSON at position 0
	            // Promise.then (async)
	            // handleSubmit @ (index):52
	        },
	        redirect: 'follow', // manual, *follow, error
	        referrer: 'no-referrer', // no-referrer, *client
	        body: JSON.stringify(data), // body data type must match "Content-Type" header
	    })
	    .then(response => response.json()) // parses JSON response into native JavaScript objects
	    .then(data => console.log(data));
	}
	
	const init = () => {
		form.addEventListener('submit', handleSubmit);
	} 
	init();
	
</script>

</body>
</html>

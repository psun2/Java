<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	fetch("http://localhost:8080/JSP18_REST_API/restTest.test")
	.then(response => response.json())
	.then(console.log)
	//.then(data => console.log(typeof data))
	// .then(data => JSON.parse(data))
	// .then(result => console.log(result))
	
	

</script>
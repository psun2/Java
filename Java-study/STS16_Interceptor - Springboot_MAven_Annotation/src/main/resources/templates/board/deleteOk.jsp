

<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert("삭제 실패");
			history.back();
		</script>
	</c:when>
	<c:otherwise>	
		<script>
			alert("삭제 성공");
			location.href = "list";
		</script>
	</c:otherwise>
</c:choose>












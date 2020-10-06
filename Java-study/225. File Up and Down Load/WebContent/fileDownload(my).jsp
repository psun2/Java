<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="file.FileDAO"%>
<%@page import="file.FileDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>225. File Up and Down Load</title>
</head>
<%
	ArrayList<FileDTO> list = new FileDAO().getList();
%>
<body>
	<table>
		<thead>
			<tr>
				<th>파일명</th>
				<th>서버에 저장된 실제 파일명</th>
				<th>다운로드 경로</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = list.size() - 1; i >= 0; i--) {
			%>
			<tr>

				<td><%=list.get(i).getFileName()%></td>
				<td><%=list.get(i).getFileRealName()%></td>
				<td><a
					href="downloadAction_my?fileName=<%=URLEncoder.encode(list.get(i).getFileName(), "UTF-8")%> &fileRealName=<%=URLEncoder.encode(list.get(i).getFileRealName(), "UTF-8")%>">Go</a>
				</td>
			</tr>
			<%
				System.out.println("<a href=\"downloadAction_my?fileName=" + list.get(i).getFileName() + "&fileRealName="
					+ list.get(i).getFileRealName() + "\">Go</a>");
			}
			%>
		</tbody>
	</table>
</body>
</html>
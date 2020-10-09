<%@page import="java.util.Enumeration"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="file.FileDAO"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>225. File Up and Down Load</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	// String directory = request.getServletContext().getRealPath("/upload");
	// String directory2 = application.getRealPath("/upload");
	String directory = "D:\\Study\\Java\\Java-study\\225. File Up and Down Load\\upload(시큐어 코딩 웹셀 방어)";

	// System.out.println("directory : " + directory);
	// System.out.println("directory2 : " + directory2);

	int maxSize = 1024 * 1024 * 100; // 100MB 104,857,600

	// 업로드 끝
	MultipartRequest multi = new MultipartRequest(request, directory, maxSize, "UTF-8", new DefaultFileRenamePolicy());

	Enumeration fileNames = multi.getFileNames();
	while (fileNames.hasMoreElements()) {

		String parameter = (String) fileNames.nextElement();
		String fileName = multi.getOriginalFileName(parameter);
		String fileRealName = multi.getFilesystemName(parameter);

		if (fileName == null)
			continue;

		System.out.println(fileName);
		System.out.println(fileName.endsWith(".png"));

		// 파일 업로드 시큐어 코딩
		// 웹셀 공격 방어

		// boolean discrimination = false;
		// String[] ext = { ".png", ".jpg", ".gif" };
		// for (String str : ext) {
		// 	if (fileName.endsWith(str)) {
		// discrimination = true;
		// break;
		// 	}
		// }

		if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".gif")) {
			File file = new File(directory, fileRealName);
			file.delete();
			out.write("업로드 할 수 없는 확장자 입니다." + "<br />");
		} else {
			new FileDAO().upload(fileName, fileRealName);
			out.write("파일명: " + fileName + "<br />");
			out.write("실제 파일명: " + fileRealName + "<br />");
		}
	}
	%>
</body>
</html>
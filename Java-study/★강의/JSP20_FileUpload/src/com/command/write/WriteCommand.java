package com.command.write;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		WriteDAO dao = new WriteDAO();
		
		// 1. MultipartRequest  생성 -> 파일업로드
		ServletContext context = request.getServletContext();
		// 서블릿 상의 upload 폴더 경로를 알아온다
		String saveDirectory = context.getRealPath("upload");
		System.out.println("업로드 경로: " + saveDirectory);
		
		int maxPostSize = 5 * 1024 * 1024;  // POST 받기, 최대 5M byte :1Kbyte = 1024 byte, 1Mbyte = 1024 Kbyte
		String encoding = "utf-8";  // response 인코딩
		FileRenamePolicy policy = new DefaultFileRenamePolicy(); //업로딩 파일 이름 중복에 대한 정책
		MultipartRequest multi = null; // com.oreilly.servlet.MultipartRequest 임포트
		
		// MultipartRequest 생성 단계에서 이미 파일은 저장됨.
		try {
			multi = new MultipartRequest(
					request,                 // JSP 내부객체 request
					saveDirectory,
					maxPostSize,
					encoding,
					policy
					);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// 2. 업로드 된 파일의 '원본이름(들)' 과 '저장된 이름(들)' 받아오기
		List<String> originalFileNames = new ArrayList<String>();
		List<String> fileSystemNames = new ArrayList<String>();
		
		Enumeration names = multi.getFileNames();
		while(names.hasMoreElements()) {
			String name = (String)names.nextElement();
			String originalFileName = multi.getOriginalFileName(name);
			String fileSystemName = multi.getFilesystemName(name);
			
			System.out.println("첨부파일: " + originalFileName + "->" + fileSystemName);
			
			if(originalFileName != null && fileSystemName != null) {
				originalFileNames.add(originalFileName);
				fileSystemNames.add(fileSystemName);
			}
		} // end while
		
		
		
		// 3. 입력한 값을 받아오기
		String name = multi.getParameter("name");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		
		// 유효성 체크 : null 이거나, 빈 문자열이면
		if(name != null && subject != null &&
				name.trim().length() > 0 && subject.trim().length() > 0) {
			
			try {
				// 첨부파일 정보도 같이 INSERT
				cnt = dao.insert(subject, content, name, originalFileNames, fileSystemNames);
			} catch(SQLException e) {
				e.printStackTrace();
			} 		
			
		} // end if
		
		request.setAttribute("result", cnt);	

	}

}
















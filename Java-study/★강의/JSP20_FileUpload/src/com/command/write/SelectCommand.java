package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.FileDAO;
import com.lec.beans.FileDTO;
import com.lec.beans.WriteDAO;
import com.lec.beans.WriteDTO;

public class SelectCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();
		WriteDTO [] arr = null;
		
		int uid = Integer.parseInt(request.getParameter("uid"));  // 매개변수 검증 필요
		
		try {
			arr = dao.selectByUid(uid);  // 읽기 only
			request.setAttribute("list", arr);
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		
		// 첨부파일 정보 읽어 들이기
		// 첨부파일 정보도 update 화면에 보여주어야 한다
		// ★ 첨부 파일용 DAO 추가
		FileDAO fileDao = new FileDAO();
		FileDTO [] fileArr = null;  // ★ 첨부 파일
		
		// ★첨부파일 읽어 들이기
		try {
			if(arr != null && arr.length == 1) {
				fileArr = fileDao.selectFilesByWrUid(uid);
				// ※ 이미지 파일 여부 세팅 필요는 pass
				request.setAttribute("fileList", fileArr);
			}
		} catch (SQLException e) { // 만약 ConnectionPool 을 사용한다면 여기서 NamingException 도 catch 해야 한다  
			e.printStackTrace();
		}
		

	}

}












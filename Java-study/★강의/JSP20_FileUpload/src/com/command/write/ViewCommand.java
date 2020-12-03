package com.command.write;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.FileDAO;
import com.lec.beans.FileDTO;
import com.lec.beans.WriteDAO;
import com.lec.beans.WriteDTO;

public class ViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();
		WriteDTO [] arr = null;
		
		// 첨부파일
		FileDAO fileDao = new FileDAO();
		FileDTO [] fileArr = null;
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		try {
			arr = dao.readByUid(uid);  // 읽기 + 조회수 증가
			request.setAttribute("list", arr);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		// 첨부파일 읽기
		try {
			if(arr != null && arr.length == 1) {
				fileArr = fileDao.selectFilesByWrUid(uid);  // 첨부파일 정보 읽어오기
				
				// 이미지 파일 여부 세팅 필요
				String realPath = 
						request.getServletContext().getRealPath("upload");
				
				for(FileDTO fileDto : fileArr) {
					String downloadFilePath = 
							realPath + File.separator + fileDto.getFile();
					BufferedImage imgData = // 이미지 여부 확인
							ImageIO.read(new File(downloadFilePath));
					if(imgData != null) {
						fileDto.setImage(true); // 이미지 다!
					}
				}
				request.setAttribute("fileList", fileArr);  // VIEW 에 보여줄 첨부파일 목록
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}













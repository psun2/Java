package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.write.Command;
import com.lec.beans.WriteDAO;

public class WriteActionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int cnt = 0;
		WriteDAO dao = new WriteDAO();

		// 입력한 값을 받아오기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		// 유효성 체크 : null 이거나, 빈문자열이면
		if (name != null && subject != null && name.trim().length() > 0 && subject.trim().length() > 0) {
			try {
				cnt = dao.insert(subject, content, name);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} // end if
		
		request.setAttribute("result", cnt);
	}

	@Override
	public void executeByUid(HttpServletRequest request, HttpServletResponse response, int uid) {
		// TODO Auto-generated method stub

	}

}

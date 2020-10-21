package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.write.Command;
import com.lec.beans.WriteDAO;

public class UpdateActionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeByUid(HttpServletRequest request, HttpServletResponse response, int uid) {
		int cnt = 0;
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		try {
			cnt = new WriteDAO().update(uid, subject, content);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

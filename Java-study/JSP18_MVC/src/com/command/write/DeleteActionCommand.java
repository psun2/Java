package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.write.Command;
import com.lec.beans.WriteDAO;

public class DeleteActionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeByUid(HttpServletRequest request, HttpServletResponse response, int uid) {
		int cnt = 0;
		try {
			cnt = new WriteDAO().deletByUid(uid);
			request.setAttribute("result", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.write.Command;
import com.lec.beans.WriteDAO;
import com.lec.beans.WriteDTO;

public class UpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeByUid(HttpServletRequest request, HttpServletResponse response, int uid) {
		WriteDTO[] arr;
		try {
			arr = new WriteDAO().selectByUid(uid);
			request.setAttribute("result", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

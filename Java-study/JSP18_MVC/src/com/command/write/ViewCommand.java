package com.command.write;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;
import com.lec.beans.WriteDTO;

public class ViewCommand implements Command {

	@Override
	public void executeByUid(HttpServletRequest request, HttpServletResponse response, int uid) {

		WriteDTO[] result = null;

		try {
			result = new WriteDAO().readByUid(uid);
			request.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}

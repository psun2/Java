package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;
import com.lec.beans.WriteDTO;

public class ViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();
		WriteDTO [] arr = null;
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		try {
			arr = dao.readByUid(uid);  // 읽기 + 조회수 증가
			request.setAttribute("list", arr);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}

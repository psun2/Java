package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;
import com.lec.beans.WriteDTO;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		WriteDAO dao = new WriteDAO();
		WriteDTO[] arr = null;
		
		try {
			// 트랜잭션 수행
			arr = dao.select();
			
			// "list" 란 name 으로 request 에 arr 값 저장
			// 즉, request 에 담아서 컨트롤러에 전달되는 셈
			request.setAttribute("result", arr);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	} // end execute()

	@Override
	public void executeByUid(HttpServletRequest request, HttpServletResponse response, int uid) {
		// TODO Auto-generated method stub
		
	}

} // end Command
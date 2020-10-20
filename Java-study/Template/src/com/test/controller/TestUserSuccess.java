package com.test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.user.UserDAO;

@WebServlet("/success")
public class TestUserSuccess extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();

		try {
			int result = new UserDAO().write("a", "b", "c");
			
			if(result == 1) {
				session.setAttribute("messageType", "성공 메시지");
				session.setAttribute("messageContent", "디비등록 성공 ");
				response.sendRedirect("successExam.jsp");
				return;
			} else {
				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "디비등록 실패 ");
				response.sendRedirect("errorExam.jsp");
				return;		
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 처리 예제");
		}
	}
}

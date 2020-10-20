package com.test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/exception")
public class TestUserException extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			int a = 10 / 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외 처리 예제");
			HttpSession session = request.getSession();
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "여기는 그냥 내용 ~ ");
			response.sendRedirect("errorExam.jsp");
		}
	}
}

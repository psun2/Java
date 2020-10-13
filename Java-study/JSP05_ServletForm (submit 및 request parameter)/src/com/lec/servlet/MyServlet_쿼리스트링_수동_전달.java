package com.lec.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")
public class MyServlet_쿼리스트링_수동_전달 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyServlet_쿼리스트링_수동_전달() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() 호출");
		int result = 0;

		result = Integer.parseInt(request.getParameter("num"));

		response.getWriter().println("result x 2: " + (result * 2) + "<br />");

	}

}

package com.lec.context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletContextParam")
public class ServletContextParam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContextParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		String id = request.getServletContext().getInitParameter("id");
		String pw = request.getServletContext().getInitParameter("pw");
		String local = request.getServletContext().getInitParameter("local");

		// Servlet 의 initparam 과의 비교
		// String id = getInitParameter("id");
		// String pw = getInitParameter("pw");
		// String local = getInitParameter("local");

		out.println("id: " + id + "<br />");
		out.println("pw: " + pw + "<br />");
		out.println("local: " + local + "<br />");

		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

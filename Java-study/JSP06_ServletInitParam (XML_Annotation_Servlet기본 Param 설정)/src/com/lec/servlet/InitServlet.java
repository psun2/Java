package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// xml 에서 url 맵핑을 시켜준 경우 annotation 은 생략합니다.
//@WebServlet("/InitS")

// Annotation 에서의 initParam
@WebServlet(urlPatterns = { "/Inits" }, initParams = { @WebInitParam(name = "id", value = "ttt"),
		@WebInitParam(name = "pw", value = "www"), @WebInitParam(name = "local", value = "busan") })
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String id = getInitParameter("id");
		String pw = getInitParameter("pw");
		String local = getInitParameter("local");

		PrintWriter out = response.getWriter();

		out.println("id: " + id + "<br />");
		out.println("pw: " + pw + "<br />");
		out.println("local: " + local + "<br />");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

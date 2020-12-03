package com.test.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SCEx")
public class ServletContext_Ex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletContext_Ex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = getServletContext().getInitParameter("id");
		String pw = getServletContext().getInitParameter("password");
		String local = getServletContext().getInitParameter("local");
	
	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("id: " + id + "<br>");
		out.println("pw: " + pw + "<br>");
		out.println("local: " + local + "<br>");
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}



















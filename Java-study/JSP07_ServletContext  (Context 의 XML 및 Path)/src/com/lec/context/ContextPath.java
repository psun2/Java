package com.lec.context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContextPath")
public class ContextPath extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ContextPath() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		// URL: Uniform Resource Locator: 통합파일 식별자(?)
		StringBuffer url = request.getRequestURL();

		// URI: Uniform Resource Identifier: 통합자원 식별자(?)
		String uri = request.getRequestURI();

		// ContextPath
		String contextPath = request.getContextPath();

		// ServletContextPath
		String servletContextPath = request.getServletContext().getContextPath();

		// ServletContextRealPath
		String servletContextRealPath = request.getServletContext().getRealPath("/");

		// ServletPath
		String servletPath = request.getServletPath();

		// ServletContextName
		String servletContextName = request.getServletContext().getServletContextName();

		// domain 추출하기
		String url_domain = request.getScheme() // http
				+ "://" + request.getServerName() // localhost
				+ ":" + request.getServerPort(); // 8080

		PrintWriter out = response.getWriter();

		out.println("<b style=\"color:red\">URL</b>: " + url + "<br /><br />");
		out.println("<b style=\"color:red\">URI</b>: " + uri + "<br /><br />");
		out.println("<b style=\"color:red\">ContextPath (URL 상의 ContextPath)</b>: " + contextPath + "<br /><br />");
		out.println("<b style=\"color:red\">ServletContextPath (물리적인 ContextPath)</b>: " + servletContextPath + "<br /><br />");
		out.println("<b style=\"color:red\">ServletContextRealPath</b>: " + servletContextRealPath + "<br /><br />");
		out.println("<b style=\"color:red\">ServletPath</b>: " + servletPath + "<br /><br />");
		out.println("<b style=\"color:red\">ServletContextName</b>: " + servletContextName + "<br /><br />");
		out.println("<b style=\"color:red\">url_domain</b>: " + url_domain + "<br /><br />");

		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

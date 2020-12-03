package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConPath")
public class ContextPath extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContextPath() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// URL : Uniform Resource Locator : 통합파일 식별자(?)
		StringBuffer url = request.getRequestURL();
		
		// URI : Uniform Resource Identifier : 통합자원 식별자(?)
		String uri = request.getRequestURI();
		
		// Context Path
		String conPath = request.getContextPath();
		
		// domain 추출하기
		String url_domain = request.getScheme() + "://"
				+ request.getServerName() + ":" 
				+ request.getServerPort()
				;
		
		// 물리적인 servletContextName <- Context path 가 아니다!
		ServletContext context = request.getServletContext();
							// = getServletContext()  // 걍 이렇게 해도 된다!
		
		String servletContextName = context.getServletContextName();
		
		
		
		//------------------------------------------------
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		out.println("<hr>");

		out.println("URL: " + url + "<br>");
		out.println("URI: " + uri + "<br>");
		out.println("ContextPath: " + conPath + "<br>");
		out.println("URL_domain: " + url_domain + "<br>");
		out.println("Servlet ContextName: " + servletContextName + "<br>");
		
		out.println("<hr>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
























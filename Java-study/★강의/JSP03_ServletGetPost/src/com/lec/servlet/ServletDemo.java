package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Sev")
public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletDemo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		int sum = 0;
		for(int cnt = 1; cnt <= 100; cnt++) {
			sum += cnt;
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		
		out.printf("100 까지의 합 결과 : %d<br>", sum);
		
		out.println("</html>");		
		
		out.close();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출");
		doGet(request, response);
	}
	
	// 이클립스 override
	// ALT + SHIFT + S, V

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doDelete() 호출");
		doGet(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPut() 호출");
		doGet(req, resp);
	}
	
	
	

}





















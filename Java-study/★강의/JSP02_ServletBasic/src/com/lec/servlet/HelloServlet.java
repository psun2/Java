package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/He")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlet() {
        super();
    }

    
    // GET방식 request 올때마다 호출됨.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 서블릿으로  HTML response 해보기
		// 1. content type 설정
		// 2. PrintWriter 객체 생성 <-- response 객체로부터
		// 3. PrintWriter 객체로 HTML 스트림에 '쓰기'
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// ***Stream   : byte 단위 전송
		// ***Reader, ***Writer  : character 단위 전송
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang='ko'>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>서블릿 response</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>HTML 문서 response</h2>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}







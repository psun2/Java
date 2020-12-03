package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormOk")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletForm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		String [] hobbys = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		String local = request.getParameter("local");
		String memo = request.getParameter("memo");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("hidden: " + data1 + ", " + data2 + "<br>");
		out.println("이름: " + name + "<br>");
		out.println("아이디: " + id + "<br>");
		out.println("비밀번호: " + pw + "<br>");
		out.println("취미: " + Arrays.toString(hobbys) + "<br>");
		out.println("성별: " + gender + "<br>");
		out.println("지역: " + local + "<br>");
		out.println("메모: " + memo + "<br>");
		
		out.close();
		
		
		// request 에 담겨 있는 parameter name 들 추출하기
		System.out.println("1] getPramaterNames() 사용");
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String param_name = names.nextElement();
			String param_value = request.getParameter(param_name);  // hobby 는 동일 name 이 여러개.  getParameter 로 가져오면 '첫번째' 값만 가져옴.
			System.out.println(param_name + " : " + param_value);			
		}
		
		
		System.out.println();
		System.out.println("2] getParameterMap() 사용");
		Map<String, String[]> paramMap =
					request.getParameterMap();
		
		for(String key : paramMap.keySet()) {
			System.out.println(key + " : " + Arrays.toString(paramMap.get(key)));
		}
		
		
		
		
	}

}














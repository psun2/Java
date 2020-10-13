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
public class ServletForm_이름을모르는_parameter_받기 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		String[] hobbys = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		String local = request.getParameter("local");
		String memo = request.getParameter("memo");

		PrintWriter out = response.getWriter();

		out.println("hidden: " + data1 + ", " + data2 + "<br />");
		out.println("이름: " + name + "<br />");
		out.println("아이디: " + id + "<br />");
		out.println("패스워드: " + pw + "<br />");
		out.println("취미: " + Arrays.toString(hobbys) + "<br />");
		out.println("성별: " + gender + "<br />");
		out.println("지역: " + local + "<br />");
		out.println("메모: " + memo + "<br />");
		out.close();

		// request 에 담겨 있는 name 들 추출하기
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String paramName = names.nextElement();
			String paramValue = request.getParameter(paramName);

			System.out.println("paramName: " + paramName + ", paramValue: " + paramValue);
		}

		System.out.println();
		
		// request 에 담겨 있는 name 들 추출하기(Map)
		Map<String, String[]> paramMap = request.getParameterMap();

		for (String key : paramMap.keySet()) {
			System.out.println("key: " + key + ", value: " + Arrays.toString(paramMap.get(key)));
		}
	}

}

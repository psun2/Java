package com.lec.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cycle")
public class ServletCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCycle() {
		super();
		System.out.println("서블릿 생성");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출");
		// 서버가 시작시 한번 실행합니다.
		// useEffect 에 deps 를 빈 배열로 준 경우
	}

	@Override
	public void destroy() {
		System.out.println("destroy() 호출");
		// 서버가 종료시 한번 실행합니다.
		// useEffect 에 deps 를 빈 배열로 준 경우 에서의 return 반환 함수
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() 호출");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost() 호출");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() 호출");
	}

}

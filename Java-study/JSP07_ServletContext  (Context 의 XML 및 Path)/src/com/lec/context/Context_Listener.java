package com.lec.context;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Context_Listener")
public class Context_Listener extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Context_Listener() {
		System.out.println("servlet 생성자");
		System.out.println("context 와 servlet 의 cycel 관계");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init() => servlet 시작시 실행됩니다.");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("이건뭐야 ? : " + config);
	}

	@Override
	public void destroy() {
		System.out.println("destroy() => servlet이 다운 되었을때 호출 됩니다.");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet() => request(요청)시 실행됩니다.");
	}

}

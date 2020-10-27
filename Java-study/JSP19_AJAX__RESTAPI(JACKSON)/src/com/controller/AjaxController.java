package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.write.AjaxListCommand;
import com.command.write.ListCommand;

@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ajaxAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ajaxAction(request, response);
	}

	private void ajaxAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();

		String type = uri.substring(contextPath.length());

		
		switch (type) {
		case "/list.ajax": // 글목록 AJAX 요청
			// 일단 글 목록 읽어오기
			new ListCommand().execute(request, response);

			// 읽어온 데이터를 다음 커맨드에 넘겨줌
			new AjaxListCommand().execute(request, response);
			break;
		} // end switch
	} // end action

}

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.write.Command;
import com.command.write.DeleteActionCommand;
import com.command.write.ListCommand;
import com.command.write.UpdateActionCommand;
import com.command.write.UpdateCommand;
import com.command.write.ViewCommand;
import com.command.write.WriteActionCommand;

@WebServlet("*.park")
public class ParkContorller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		final String URI = request.getRequestURI();
		final String CONTEXT_PATH = request.getContextPath();
		final String TYPE = URI.substring(CONTEXT_PATH.length());

//		System.out.println(TYPE);

		Command command = null;
		String viewPage = null;
		int uid = -1;

		try {
			uid = Integer.parseInt(request.getParameter("uid"));
		} catch (Exception e) {
			// 예외처리
		}

		switch (TYPE) {
		case "/list.park":
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
			break;

		case "/view.park":
			command = new ViewCommand();
			command.executeByUid(request, response, uid);
			uidThrowException(uid, request, response);
			viewPage = "view.jsp?uid=" + uid;
			break;

		case "/write.park":
			viewPage = "write.jsp";
			break;

		case "/writeOk.park":
			command = new WriteActionCommand();
			command.execute(request, response);
			viewPage = "writeOk.jsp";
			break;

		case "/update.park":
			command = new UpdateCommand();
			command.executeByUid(request, response, uid);
			viewPage = "update.jsp?uid=" + uid;
			break;

		case "/updateOk.park":
			command = new UpdateActionCommand();
			command.executeByUid(request, response, uid);
			viewPage = "updateOk.jsp?uid=" + uid;
			break;

		case "/deleteOk.park":
			command = new DeleteActionCommand();
			command.executeByUid(request, response, uid);
			viewPage = "deleteOk.jsp?uid=" + uid;
			break;

		default:
			viewPage = "default.jsp";
		} // end switch

		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/" + viewPage);
			dispatcher.forward(request, response);
		}
	}

	void uidThrowException(int uid, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession();
			if (uid == -1) {
				// 예외처리
//			session.setAttribute("messageType", "오류 메시지");
//			session.setAttribute("messageContent", "없는 uid");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 uid');");
				out.println("history.back();");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}

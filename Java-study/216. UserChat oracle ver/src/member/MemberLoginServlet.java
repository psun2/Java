package member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");

		if (userID == null || userID.equals("") || userPassword == null || userPassword.equals("")) {

			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력해주세요.");
			response.sendRedirect("login.jsp");
			return;

		} else {

			userID = URLDecoder.decode(userID, "UTF-8");
			userPassword = URLDecoder.decode(userPassword, "UTF-8");

			int result = new MemberDAO().login(userID, userPassword);

			switch (result) {
			case 1:
				request.getSession().setAttribute("userID", userID);
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "로그인에 성공했습니다.");
				response.sendRedirect("index.jsp");
				return;
			case 2:
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
				response.sendRedirect("login.jsp");
				return;
			case 0:
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "존재하지 않는 회원 입니다.");
				response.sendRedirect("login.jsp");
				return;
			default:
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
				response.sendRedirect("login.jsp");
				return;
			}

//			if (result == 1) {
//				request.getSession().setAttribute("userID", userID);
//				request.getSession().setAttribute("messageType", "성공 메시지");
//				request.getSession().setAttribute("messageContent", "");
//				response.sendRedirect("index.jsp");
//			} else if (result == 2) { // 비밀번호 오류
//				request.getSession().setAttribute("messageType", "오류 메시지");
//				request.getSession().setAttribute("messageContent", "");
//				response.sendRedirect("login.jsp");
//				return;
//			} else if (result == 0) { // 존재하지 않는 회원
//				request.getSession().setAttribute("messageType", "오류 메시지");
//				request.getSession().setAttribute("messageContent", "");
//				response.sendRedirect("login.jsp");
//				return;
//			} else { // 데이터 베이스 오류
//				request.getSession().setAttribute("messageType", "오류 메시지");
//				request.getSession().setAttribute("messageContent", "");
//				response.sendRedirect("login.jsp");
//				return;
//			}

		}

	}

}

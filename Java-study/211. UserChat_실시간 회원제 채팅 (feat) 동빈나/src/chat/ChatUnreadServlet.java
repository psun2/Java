package chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChatUnreadServlet")
public class ChatUnreadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = request.getParameter("userID");
		if (userID == null || userID.equals(""))
			response.getWriter().write("0");
		else {
			userID = URLDecoder.decode(userID, "UTF-8");

			// 본인 말고 다른사람의 메시지 함은 볼수 없게 막습니다
			// 로그인한 사용자와 정보를 요청하는 사용자가 정확히 일치할때 정보를 보내 줄수 있도록 합니다.
			HttpSession session = request.getSession();
			if (!URLDecoder.decode(userID, "UTF-8").equals((String) session.getAttribute("userID"))) {
				// 로그인 되어있는 아이디와 현재 서블릿으로 넘어온 아이디가 같은지 검증
//				session.setAttribute("messageType", "오류 메시지");
//				session.setAttribute("messageContent", "접근할 수 없습니다.");
//				response.sendRedirect("index.jsp");

				// 서블릿은 위와 같은 문법을 사용 할 수 없다고 합니다.
				// 그러므로 공백을 출력 해서 요청을 받을때 공백을 출력 해서 아무 행동도 일어나지 않게 합니다.
				response.getWriter().write("");
				return;
			}

			response.getWriter().write(new ChatDAO().getAllUnreadChat(userID) + "");
		}
	}

}

package chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;

@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");

		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || chatContent == null
				|| chatContent.equals("")) {
			response.getWriter().write("0"); // 오류 발생시
		} else if (fromID.equals(toID)) { // 받는 사람이 자기 자신일때, 오류를 보냄으로써 자기 자신에게 메시지를 보낼수 없게 합니다.
			response.getWriter().write("-1"); // 이쪽 메시지 처리하는것은 구현 안함
		} else {
			fromID = URLDecoder.decode(fromID, "UTF-8");
			toID = URLDecoder.decode(toID, "UTF-8");

			// 본인 말고 다른사람의 메시지 함은 볼수 없게 막습니다
			// 로그인한 사용자와 정보를 요청하는 사용자가 정확히 일치할때 정보를 보내 줄수 있도록 합니다.
			HttpSession session = request.getSession();
			if (!URLDecoder.decode(fromID, "UTF-8").equals((String) session.getAttribute("userID"))) {
				// 로그인 되어있는 아이디와 현재 서블릿으로 넘어온 아이디가 같은지 검증
//				session.setAttribute("messageType", "오류 메시지");
//				session.setAttribute("messageContent", "접근할 수 없습니다.");
//				response.sendRedirect("index.jsp");

				// 서블릿은 위와 같은 문법을 사용 할 수 없다고 합니다.
				// 그러므로 공백을 출력 해서 요청을 받을때 공백을 출력 해서 아무 행동도 일어나지 않게 합니다.
				response.getWriter().write("");
				return;
			}

			chatContent = URLDecoder.decode(chatContent, "UTF-8");
			// submit 함수의 반환값은 int 이기때문에 문자형태로 만들어 주기위해 +"" 공백(?) 을 더해 문자열 형태로 변경시켜 줍니다.
			response.getWriter().write(new ChatDAO().submit(fromID, toID, chatContent) + "");
		}

	}

}

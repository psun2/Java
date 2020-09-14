package chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/htrml; charset=UTF-8");

		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");

		System.out.println(fromID);
		System.out.println(toID);
		System.out.println(chatContent);

		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || chatContent == null
				|| chatContent.equals("")) { // 오류 발생시
//			request.getSession().setAttribute("messageType", "오류 메시지");
//			request.getSession().setAttribute("messageContent", );
//			response.sendRedirect(".jsp");
			response.getWriter().write("0");
		} else if (fromID.equals(toID)) {
			response.getWriter().write("-1");
		} else {
			fromID = URLDecoder.decode(fromID, "UTF-8");

			HttpSession session = request.getSession();
			if (!fromID.equals((String) session.getAttribute("userID"))) {
				response.getWriter().write("");
				return;
			}

			toID = URLDecoder.decode(toID, "UTF-8");
			chatContent = URLDecoder.decode(chatContent, "UTF-8");
			response.getWriter().write(new ChatDAO().submit(fromID, toID, chatContent) + "");
		}

	}

}

package chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatSubmitServlet
 */
@WebServlet("/ChatListServlet")
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ChatSubmitServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

	// post 방식으로 만 보낼수 있게 dopost 메소드만을 사용합니다.
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// 어떠한 메세지를 파라미터로 받아서 처리 할 수 있도록 합니다.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String chatName = request.getParameter("chatName");
		System.out.println(chatName);
		String chatContent = request.getParameter("chatContent");
		System.out.println(chatContent);

		if (chatName == null || chatName.equals("") || chatContent == null || chatContent.equals("")) {
			response.getWriter().write("0"); // 오류 발생
		} else {
			// + "" 공백 문자열을 더해줌으로서 반듯이 문자열 형태로 출력 할 수 있게끔 합니다.
			response.getWriter().write(new ChatDAO().submit(chatName, chatContent) + "");
		}

	}

}

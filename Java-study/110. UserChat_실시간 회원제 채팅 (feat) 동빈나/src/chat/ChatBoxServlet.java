package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;

@WebServlet("/ChatBoxServlet")
public class ChatBoxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = request.getParameter("userID");
		System.out.println("userID : " + userID);
		System.out.println("userID : " + URLDecoder.decode(userID, "UTF-8"));
		if (userID == null || userID.equals("")) // 사용자의 아이디 정보가 도착하지 않은 경우 공백을 출력합니다.
			response.getWriter().write("");
		else {
			userID = URLDecoder.decode(userID, "UTF-8");
			// 아이디 정보가 도착했다면 특정한 사용자의 메시지 리스트를 출력 할수 있도록 합니다.
			try {
				// 본인 말고 다른사람의 메시지 함은 볼수 없게 막습니다
				// 로그인한 사용자와 정보를 요청하는 사용자가 정확히 일치할때 정보를 보내 줄수 있도록 합니다.
				HttpSession session = request.getSession();
				System.out.println("ChatBoxServlet : " + userID);
				System.out.println("ChatBoxServlet : " + userID.length());
				System.out.println("ChatBoxServlet : " + (String) session.getAttribute("userID"));
				System.out.println("ChatBoxServlet : " + ((String) session.getAttribute("userID")).length());
				if (!userID.equals((String) session.getAttribute("userID"))) {
					// 로그인 되어있는 아이디와 현재 서블릿으로 넘어온 아이디가 같은지 검증
//					session.setAttribute("messageType", "오류 메시지");
//					session.setAttribute("messageContent", "접근할 수 없습니다.");
//					response.sendRedirect("index.jsp");

					// 서블릿은 위와 같은 문법을 사용 할 수 없다고 합니다.
					// 그러므로 공백을 출력 해서 요청을 받을때 공백을 출력 해서 아무 행동도 일어나지 않게 합니다.
					response.getWriter().write("");
					return;
				}
				response.getWriter().write(getBox(userID));
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("");
			}
		}
	}

	public String getBox(String userID) {
		StringBuffer result = new StringBuffer("");
		// JSON 형태 사용
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getBox(userID);
		if (chatList.size() == 0)
			return "";
		for (int i = chatList.size() - 1; i >= 0; i--) {
			String unread = "";
			String userProfile = "";
			if (userID.equals(chatList.get(i).getToID())) {
				unread = chatDAO.getUnreadChat(chatList.get(i).getFromID(), chatList.get(i).getToID()) + "";
				if (unread.equals("0"))
					unread = ""; // 않 읽은 메시지가 없을때 공백 출력
			}

			if (userID.equals(chatList.get(i).getToID())) {
				userProfile = new UserDAO().getProfile(chatList.get(i).getFromID());
			} else {
				userProfile = new UserDAO().getProfile(chatList.get(i).getToID());
			}
			result.append("[{\"value\":\"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatTime() + "\"},");
			result.append("{\"value\":\"" + unread + "\"},");
			result.append("{\"value\":\"" + userProfile + "\"}]");

			// 마지막 원소가 아니라면
			if (i != 0)
				result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
		System.out.println(result.toString());

		return result.toString();
	}

}

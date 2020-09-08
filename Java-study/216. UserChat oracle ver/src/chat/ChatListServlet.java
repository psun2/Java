package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");

		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || listType == null
				|| listType.equals("")) { // 오류 발생시
			response.getWriter().write("");
			return;
		} else {

			fromID = URLDecoder.decode(fromID, "UTF-8");
			toID = URLDecoder.decode(toID, "UTF-8");
			listType = URLDecoder.decode(listType, "UTF-8");

			if (listType.equals("ten"))
				response.getWriter().write(getTen(fromID, toID));

			else
				response.getWriter().write(getID(fromID, toID, listType));

		}

	}

	public String getTen(String fromID, String toID) {

		StringBuffer result = new StringBuffer();
		result.append("{\"result\":[");

		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 100);

		if (chatList.size() == 0)
			return "";

		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"fromID\":\"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"toID\":\"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"chatContent\":\"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"time\":\"" + chatList.get(i).getTime() + "\"}]");

			if (i != chatList.size() - 1)
				result.append(",");
		}

		result.append("],\"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");

		// System.out.println("result.toString() : " + result.toString());

		chatDAO.readChat(fromID, toID); // 메시지를 읽을려고 가져 오는 서블릿이기 때문에 데이터 반환전에 읽음처리로 돌립니다.

		return result.toString();

	}

	public String getID(String fromID, String toID, String chatID) {

		StringBuffer result = new StringBuffer();
		result.append("{\"result\":[");

		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);

		if (chatList.size() == 0)
			return "";

		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"fromID\":\"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"toID\":\"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"chatContent\":\"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"time\":\"" + chatList.get(i).getTime() + "\"}]");

			if (i != chatList.size() - 1)
				result.append(",");
		}

		result.append("],\"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");

		// System.out.println("result.toString() : " + result.toString());

		chatDAO.readChat(fromID, toID); // 메시지를 읽을려고 가져 오는 서블릿이기 때문에 데이터 반환전에 읽음처리로 돌립니다.

		return result.toString();

	}

}

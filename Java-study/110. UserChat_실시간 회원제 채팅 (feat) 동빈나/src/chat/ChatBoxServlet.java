package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			// 아이디 정보가 도착했다면 특정한 사용자의 메시지 리스트를 출력 할수 있도록 합니다.
			try {
				userID = URLDecoder.decode(userID, "UTF-8");
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
			if (userID.equals(chatList.get(i).getToID())) {
				unread = chatDAO.getUnreadChat(chatList.get(i).getFromID(), chatList.get(i).getToID()) + "";
				if(unread.equals("0")) unread = ""; // 않 읽은 메시지가 없을때 공백 출력		
			}
			result.append("[{\"value\":\"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatTime() + "\"},");
			result.append("{\"value\":\"" + unread + "\"}]");

			// 마지막 원소가 아니라면
			if (i != 0)
				result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
		System.out.println(result.toString());

		return result.toString();
	}

}
